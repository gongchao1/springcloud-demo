package com.sckj.svca.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wangheduo on 2018/9/5
 */
@Component
public class ServiceBClientFallback implements ServiceBClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBClientFallback.class);

        @Override
        public String printServiceB() {
            LOGGER.info("微服务调用异常！！！！");
            return "svcb-service调用失败!";
        }

}
