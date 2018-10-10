package com.sckj.svca.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "svcb-service", fallback = ServiceBClientFallback.class)
public interface ServiceBClient {

    @GetMapping(value = "/")
    String printServiceB();

}