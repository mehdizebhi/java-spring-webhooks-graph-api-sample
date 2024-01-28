// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.example.graphwebhook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    /**
     * @return the template name to render
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
