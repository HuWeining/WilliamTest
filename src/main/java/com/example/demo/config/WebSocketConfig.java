package com.example.demo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.nio.channels.ServerSocketChannel;

/**
 * Created by huweining on 2017/7/18.
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    //client to server
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/stomp").setAllowedOrigins("*").withSockJS();
    }

    @Override
    //server to client
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/guest");
        registry.setApplicationDestinationPrefixes("/static");
    }
}