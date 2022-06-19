package br.com.fiapgrp02.jvapptoqueue.controllers;

import br.com.fiapgrp02.jvapptoqueue.model.Objeto;
import br.com.fiapgrp02.jvapptoqueue.services.AppToQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppToQueueController {

    @Autowired
    private AppToQueueService service;

    @GetMapping("/test")
    public String testAppToQueue(){
        return "FIAP 40SCJ : JvAppToQueue [1] : UP";
    }

    @PostMapping("/")
    public ResponseEntity postMessage(@RequestBody Objeto objeto){
        return service.postMessage(objeto);
    }
}
