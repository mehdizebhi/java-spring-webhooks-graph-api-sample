package com.example.graphwebhook.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    /**
     * @return A configured SocketIO server instance
     */
    @Bean
    public SocketIOServer socketIOServer() {
        var config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(8081);
        return new SocketIOServer(config);
    }
}
