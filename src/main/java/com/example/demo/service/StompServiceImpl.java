package com.example.demo.service;

import com.example.demo.config.WebSocketPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by huweining on 2017/7/23.
 */
@Service
public class StompServiceImpl implements StompService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void notifyUpdateGuest() {
        messagingTemplate.convertAndSend(WebSocketPath.GUEST_PIPE, "");
    }

//    @Override
//    public void notifyWorkflow(Long id) {
//        messagingTemplate.convertAndSend(WebSocketPath.GUEST_PIPE, "workflow");
//    }
}