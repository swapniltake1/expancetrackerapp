package com.expansetrackerapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("✅ Expanse Tracker App is ONLINE and ready to serve requests!");
    }
}
