package com.dzone.mancala.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

@Component
public class MancalaClientConfig {

    private LoadBalancerClient loadBalancer;

    @Value("${mancala.api.service.id}")
    private final String apiServiceId = "mancala-api";

    @Autowired
    public void setLoadBalancer(LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public String getNewMancalaGameUrl(){
        ServiceInstance instance = this.loadBalancer.choose(apiServiceId);

        String url = String.format("http://%s:%s/games", instance.getHost(), instance.getPort());

        return url;
    }

    public String getSowMancalaGameUrl(String gameId, Integer pitIndex){
        ServiceInstance instance = this.loadBalancer.choose(apiServiceId);

        String url = String.format("http://%s:%s/games/%s/pits/%s", instance.getHost(), instance.getPort(), gameId, pitIndex);

        return url;
    }
}
