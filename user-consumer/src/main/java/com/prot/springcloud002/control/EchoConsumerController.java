package com.prot.springcloud002.control;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.prot.springcloud002.service.UserApiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class EchoConsumerController {
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private LoadBalancerClient lbc;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserApiConsumer userApiConsumer;

    @Value("${server.port}")
    private int port;

    @GetMapping("/hi")
    public String sayHi() {
        List<InstanceInfo> instances = eurekaClient.getInstancesByVipAddress("user-provider", false);
        for (InstanceInfo inst: instances) {
            System.out.println(Objects.toString(inst));
        }
        return "api client en saying hi";
    }

    @GetMapping("/hi2")
    public String sayHi2() {
        ServiceInstance instance = lbc.choose("user-provider");
        String url = String.format("http://%s:%s", instance.getHost(), instance.getPort());
        final String ret  = new RestTemplate().getForObject(url + "/hi", String.class);
        return "api client-2 echo what providers saying: [" + ret + "]";
    }

    @GetMapping("/hi3")
    public String sayHi3() {
        final String url = "http://user-provider/hi";
        final String ret  = restTemplate.getForObject(url, String.class);
        return "port:" + port + " api client-3 echo what providers saying: [" + ret + "]";
    }

    @HystrixCommand(fallbackMethod = "fallbackForHi")
    @GetMapping("/hi-fallback")
    public String sayHiFallback() {
        final String url = "http://user-provider/hi-fallback";
        final String ret  = restTemplate.getForObject(url, String.class);
        return "api client-hi-fallback-via-rest-template echo what providers saying: [" + ret + "]";
    }

    public String fallbackForHi() {
        return "fallback-for-hi";
    }

    @GetMapping("/users-app-status")
    public String displayUsersAppStatus() {
        return "port: " + port + " => " + userApiConsumer.getStatus();
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        return userApiConsumer.getMap2(id, name);
    }

}
