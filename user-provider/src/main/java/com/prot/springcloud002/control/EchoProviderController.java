package com.prot.springcloud002.control;

import com.prot.springcloud002.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class EchoProviderController {
    @Autowired
    private HealthStatusService healthStatusService;

    @Value("${server.port}")
    private int port;

    @Value("${user.api.property.prop1:DEFAULT}")
    private String configProp1;

    @GetMapping("/show-config")
    public String showConfig() {
        return port + " => " + configProp1;
    }

    @GetMapping("/hi")
    public String sayHi() {
        return "hi from port: " + port;
    }

    @GetMapping("/hi-fallback")
    public String sayHiFallback() {
        int i = 1 / 0;
        return "hi-fallback impossible from port: " + port;
    }

    @GetMapping("/healthStatus")
    public boolean changeStatus(@RequestParam("status") boolean status) {
        healthStatusService.setStatus(status);
        return status;
    }
}
