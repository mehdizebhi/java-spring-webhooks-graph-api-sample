// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.example.graphwebhook.dto;

import java.util.Objects;
import com.microsoft.graph.models.ChatMessage;
import org.springframework.lang.NonNull;

/**
 * Represents the information sent via SocketIO to subscribed clients when a new Teams channel
 * message notification is received
 */
public class NewChatMessageNotification {

    /**
     * The display name of the sender
     */
    public final String sender;

    /**
     * The content of the message
     */
    public final String body;

    public NewChatMessageNotification(@NonNull ChatMessage message) {
        sender = Objects.requireNonNull(Objects.requireNonNull(message.from).user).displayName;
        body = Objects.requireNonNull(message.body).content;
    }
}
