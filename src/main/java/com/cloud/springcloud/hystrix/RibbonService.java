package com.cloud.springcloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private LoadBalancerClient loadBalancerClient ;

    private static final Logger logger = LoggerFactory.getLogger(RibbonService.class);


    @HystrixCommand(fallbackMethod = "fallBackTest")
    public String ribbon(final int a , final int b){

        MultiValueMap map = new LinkedMultiValueMap();
        map.add("a",a);
        map.add("b",b) ;
        this.loadBalancerClient.choose("My-SpringCloud-Service-B") ;// choose 策略
        String  str = this.restTemplate.postForObject("http://My-SpringCloud-Service-B/ServiceB/test/function",map,String.class);
        logger.info("请求springcloud服务返回的数据为： {}",str);
        return str;
    }

    public String fallBackTest(final int a , final int b){
        return "请求失败！" ;
    }

}
