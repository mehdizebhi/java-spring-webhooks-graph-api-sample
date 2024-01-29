package com.example.graphwebhook.controller;

import com.example.graphwebhook.core.GraphClientHelper;
import com.microsoft.graph.models.ChatMessage;
import com.microsoft.graph.models.ItemBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/me")
    public ResponseEntity<Object> getProfileInfo(
            @RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient oauthClient) {

        Object user = GraphClientHelper.getGraphClient(oauthClient).me().buildRequest().get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/send")
    public ResponseEntity<Object> sendMessage(
            @RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient oauthClient) {


        ChatMessage chatMessage = new ChatMessage();
        ItemBody body = new ItemBody();
        body.content = "Message Sent By Microsoft Teams Bot";
        chatMessage.body = body;

        Object user = GraphClientHelper.getGraphClient(oauthClient).teams("933db7b3-4c3e-4ad1-acec-c5011dbea87b")
                .channels("19:4db0361697c74095934dd76b030b6c7a@thread.tacv2").messages()
                .buildRequest()
                .post(chatMessage);
        return ResponseEntity.ok(user);
    }
}
