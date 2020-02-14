package com.dzone.mancala.game.redis;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import redis.embedded.RedisServer;

@Component
public class EmbeddedRedis implements ExitCodeGenerator, DisposableBean{

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = RedisServer.builder()
                .port(redisPort)
                .setting("maxmemory 128M") //maxheap 128M
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }

    @Override
    public int getExitCode() {
        redisServer.stop();
        return 0;
    }

    @Override
    public void destroy() throws Exception {
        redisServer.stop();
    }
}