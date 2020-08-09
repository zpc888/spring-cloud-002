package com.prot.springcloud002.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/Users")
// @RequestMapping at root level will cause FeignClient.fallback having issue when enabling it
public interface UserApi {
    @GetMapping("Users/appStatus")
    public String getStatus();
}
