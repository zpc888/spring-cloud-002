package com.prot.springcloud002.service;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class UserApiFallback implements UserApiConsumer {
    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return Collections.singletonMap(id, name + " from FallBack");
    }

    @Override
    public String getStatus() {
        return "restricted from FallBack";
    }
}
