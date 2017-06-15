package com.bobo.upms.client.kisso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by huabo on 2017/6/15.
 */
public class SpringContextHolder implements ApplicationContextAware {

    private static Logger _log = LoggerFactory.getLogger(SpringContextHolder.class);

    private static ApplicationContext context;

    public SpringContextHolder() {
    }

    public static ApplicationContext getApplicationContext() throws Exception {
        checkApplicationContext();
        return context;
    }

    public static <T> T getBean(Class<T> clazz) throws Exception {
        checkApplicationContext();
        return context.getBean(clazz);
    }

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        context = ac;
    }

    private static void checkApplicationContext() throws Exception {
        if(context == null) {
            throw new Exception("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
