package com.cloud.springcloud.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static  final Logger logger = LoggerFactory.getLogger(RibbonController.class);



    @Autowired
    private RibbonService ribbonService ;

    @RequestMapping(value = "/test" ,method = {RequestMethod.POST,RequestMethod.GET})
    public String test(@RequestParam int  a   , @RequestParam int b){
        return  this.ribbonService.ribbon(a,b);
    }

}
