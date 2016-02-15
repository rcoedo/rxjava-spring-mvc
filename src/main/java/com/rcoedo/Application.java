package com.rcoedo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.rcoedo.config.DispatcherContextConfig;
import com.rcoedo.config.RootContextConfig;

public class Application implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Create the root application context
        WebApplicationContext rootContext = getContext(RootContextConfig.class);

        // Load the root application context with the servlet context
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's application context
        WebApplicationContext dispatcherContext = getContext(DispatcherContextConfig.class);

        // Create and register the dispatcher servlet in the servlet context using the dispatcher servlet's application context
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));

        // Configure the dispatcher servlet
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.setAsyncSupported(true);
        dispatcherServlet.addMapping("/");
    }

    private AnnotationConfigWebApplicationContext getContext(Class config) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(config);
        return context;
    }
}
