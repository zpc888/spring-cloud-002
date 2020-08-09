package com.prot.springcloud002.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.cloud.client.discovery.health.DiscoveryHealthIndicator;
import org.springframework.cloud.client.discovery.health.reactive.ReactiveDiscoveryHealthIndicator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
//public class HealthStatusService implements ReactiveDiscoveryHealthIndicator {
public class HealthStatusService implements DiscoveryHealthIndicator {
    private boolean status = true;

    public void setStatus(boolean newStatus) {
        this.status = newStatus;
    }

    @Override
    public String getName() {
        return "Calculator-Version-01";
    }

    @Override
//    public Mono<org.springframework.boot.actuate.health.Health> health() {
    public Health health() {
        Health health = status ? Health.up().build() : Health.down().build();
//        return Mono.just(health);
        return health;
    }
}
