package com.example.demo.configuration;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Component
public class StartupSwagger {
    @EventListener(ApplicationReadyEvent.class)
    public static void launchBrowser(){
        System.setProperty("java.awt.headless", "false");
        Desktop desktop = Desktop.getDesktop();

        try{
         desktop.browse(new URI("http://localhost:8081/swagger-ui/index.html?url=/v3/api-docs&validatorUrl="));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
