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
}
