package edu.shily.myssm.myspringmvc;

import edu.shily.myssm.io.BeanFactory;
import edu.shily.myssm.io.ClassPathXmlApplicationContext;
import edu.shily.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shily-zhang
 * @Description 中央控制器，用来实现统一处理servlet（初步理解）
 */

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet() {

    }

    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        //request.setCharacterEncoding("UTF-8");

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

        Object controllerObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }


        try {
            Method[] methods = controllerObj.getClass().getDeclaredMethods();

            for (Method method : methods) {

                if (operate.equals(method.getName())) {
                    //1.统一获取请求参数

                    //1.1获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //1.2parametersValues用来承载参数的值
                    Object[] parametersValues = new Object[parameters.length];
                    for (int i = 0;i < parameters.length;i++){
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();

                        //如果参数名是 request response session那么就不是通过请求中获取参数的方式
                        if ("request".equals(parameterName)){
                            parametersValues[i] = request;
                        }else if("response".equals(parameterName)){
                            parametersValues[i] = response;
                        }else if ("session".equals(parameterName)){
                            parametersValues[i] = request.getSession();
                        }else {
                            //从请求中获取参数值
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;
                            if (parameterObj != null){
                                if ("java.lang.Integer".equals(typeName)){
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }

                            parametersValues[i] = parameterObj; //"2" 而不是 2
                        }
                    }

                    //2.Controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerObj, parametersValues);

                    //3.视图处理
                    String methodReturnStr = (String) returnObj;
                    if (methodReturnStr.startsWith("redirect:")) {   //比如 redirect:fruit.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr, request, response); //比如   "edit"
                    }
                }
            }

//            }else {
//                throw new RuntimeException("operate值非法！");
//            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

//java.lang.IllegalArgumentException: argument type mismatch