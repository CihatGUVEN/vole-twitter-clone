package com.vole.voletwitterclone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @GetMapping
    public String withResponseEntity() {
        return "status : " + HttpStatus.valueOf(200).getReasonPhrase().toLowerCase();
    }

}
