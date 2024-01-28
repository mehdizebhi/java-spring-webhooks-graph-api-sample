// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.example.graphwebhook.core;

import com.corundumstudio.socketio.SocketIOServer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * A CommandLineRunner that Spring will run on startup to
 * start the SocketIO server
 */
@Component
public class SocketIOServerRunner implements CommandLineRunner {

    private final SocketIOServer socketIOServer;

    public SocketIOServerRunner(SocketIOServer server) {
        socketIOServer = server;
    }

    @Override
    public void run(String... args) throws Exception {
        socketIOServer.start();
    }
}
