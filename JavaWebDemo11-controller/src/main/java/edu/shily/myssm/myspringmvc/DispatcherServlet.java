package edu.shily.myssm.myspringmvc;

import edu.shily.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shily-zhang
 * @Description 中央控制器，用来实现统一处理servlet（初步理解）
 */

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private Map<String,Object> beanMap = new HashMap<>();


    public DispatcherServlet() {

    }

    public void init() throws ServletException {
        super.init();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            if (inputStream != null){
                //1.创建DocumentBuilderFactory
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                //2.创建DocumentBuilder对象
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                //3.创建Document对象
                Document document = documentBuilder.parse(inputStream);

                //4.获取所有的bean的节点
                NodeList beanNodeList = document.getElementsByTagName("bean");
                for (int i = 0; i < beanNodeList.getLength(); i++) {
                    Node beanNode = beanNodeList.item(i);
                    if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element beanElement = (Element) beanNode;
                        String beanId = beanElement.getAttribute("id");
                        String className = beanElement.getAttribute("class");
                        Class controllerBeanClass = Class.forName(className);
                        Object beanObj = controllerBeanClass.newInstance();
                        beanMap.put(beanId,beanObj);
                    }
                }
            }else {
                throw new RuntimeException("Document不能为空");
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");

        //假设URL：http://localhost:8080/JavaWebDemo10/fruit.do
        //那么servletPath是： /fruit.do
        //思路：
        //第一步：/fruit.do -> fruit
        //第二步：fruit ->  FruitController

        //此处在截取字符串
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastIndexOf);

        Object controllerObj = beanMap.get(servletPath);

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }


        try {
            Method method = controllerObj.getClass().getDeclaredMethod(operate,HttpServletRequest.class);
            if (method != null){
                //2.Controller组件中的方法调用
                method.setAccessible(true);
                Object returnObj = method.invoke(controllerObj, request);

                //3.视图处理
                String methodReturnStr = (String)returnObj;
                if (methodReturnStr.startsWith("redirect:")){   //比如 redirect:fruit.do
                    String redirectStr = methodReturnStr.substring("redirect:".length());
                    response.sendRedirect(redirectStr);
                }else {
                    super.processTemplate(methodReturnStr,request,response); //比如   "edit"
                }
            }else {
                throw new RuntimeException("operate值非法！");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
