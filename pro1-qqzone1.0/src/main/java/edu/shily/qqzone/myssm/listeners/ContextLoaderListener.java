package edu.shily.qqzone.myssm.listeners;

import edu.shily.qqzone.myssm.ioc.BeanFactory;
import edu.shily.qqzone.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Shily-zhang
 * @Description
 */
//监听上下文启动，在上下文启动的时候去创建IOC容器，然后将其保存到application作用域
//后面中央控制器再从application作用域中获取IOC容器
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.获取Servlet上下文
        ServletContext application = servletContextEvent.getServletContext();
        //2.获取上下文的初始化的参数
        String path = application.getInitParameter("contextConfigLocation");
        //3.将IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext();
        //4.将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {


    }
}
