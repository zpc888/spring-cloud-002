package com.prot.springcloud002.service;

import com.prot.springcloud002.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "user-provider", fallback = UserApiFallback.class)
public interface UserApiConsumer extends UserApi {
    @GetMapping("Users/map2")
    Map<Integer, String> getMap2(@RequestParam("id") Integer id, @RequestParam("name") String name);
}
