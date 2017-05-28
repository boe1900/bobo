package com.bobo.upms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huabo on 2017/5/26.
 */
public class BoboUpmsRpcServiceApplication {
    private static Logger _log = LoggerFactory.getLogger(BoboUpmsRpcServiceApplication.class);

    public static void main(String[] args) {
        _log.info(">>>>> bobo-upms-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        _log.info(">>>>> bobo-upms-rpc-service 启动完成 <<<<<");
    }
}
