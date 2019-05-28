package com.cloud.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/ribbon")
public class RibbonController {


    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private LoadBalancerClient loadBalancerClient ;

    @RequestMapping(value = "/test" ,method = {RequestMethod.POST})
    public String test(@RequestParam int  a   , @RequestParam int b){
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("a",a);
        map.add("b",b) ;
        this.loadBalancerClient.choose("My-SpringCloud-Service-B") ;// choose 策略
        return this.restTemplate.postForObject("http://My-SpringCloud-Service-B/ServiceB/test/function",map,String.class) ;
    }

}
