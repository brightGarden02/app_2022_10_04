package com.example.app__2022_10_04.app;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Getter
    private static ApplicationContext context;


    @Autowired
    public void setContext(ApplicationContext context) {
        AppConfig.context = context;
    }

}
