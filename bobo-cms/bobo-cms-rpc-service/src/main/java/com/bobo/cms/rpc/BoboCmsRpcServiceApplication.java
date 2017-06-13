package com.bobo.cms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huabo on 2017/5/26.
 */
public class BoboCmsRpcServiceApplication {
    private static Logger _log = LoggerFactory.getLogger(BoboCmsRpcServiceApplication.class);

    public static void main(String[] args) {
        _log.info(">>>>> bobo-cms-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        _log.info(">>>>> bobo-cms-rpc-service 启动完成 <<<<<");
    }
}
