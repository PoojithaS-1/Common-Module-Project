package com.xworkz.userapp.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Slf4j
public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    public SpringWebInitializer(){
//        System.out.println("Web Init object is created");
        log.info("Web Init object is created");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
//        System.out.println("getRootConfigClasses is invoked");
        log.info("getRootConfigClasses is invoked");
        return new Class[0];
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
//        System.out.println("getServletConfigClasses is invoked");
        log.info("getServletConfigClasses is invoked");
        return new Class[]{SpringConfiguration.class};
    }
    @Override
    protected String[] getServletMappings() {
//        System.out.println("getServletMappings is invoked");
        log.info("getServletMappings is invoked");
        return new String[]{"/"};
    }
}
