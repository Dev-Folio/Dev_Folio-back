package com.inhatc.dev_folio.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class MainController {

    @GetMapping("/")
    public String main(@AuthenticationPrincipal UserDetails userDetails) {
        log.info(userDetails.getUsername());
        return "Hello World!";
    }
}
