package com.prot.springcloud002.control;

import com.prot.springcloud002.api.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UserApiProvider implements UserApi {
    @Value("${server.port}")
    private int port;
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public String getStatus() {
        return port + ": alive";
    }

    @GetMapping("Users/map2")
    public Map<Integer, String> getMap2(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        System.out.printf("%05d - %s - %s:%s%n", counter.incrementAndGet(), port, id, name);
        try {
            Thread.sleep(3000);
        } catch (Exception ex) {
            // ignore it
        }
        return Collections.singletonMap(id, name + ": " + port);
    }
}
