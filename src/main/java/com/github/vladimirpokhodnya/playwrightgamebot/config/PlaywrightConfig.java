package com.github.vladimirpokhodnya.playwrightgamebot.config;

import com.microsoft.playwright.Playwright;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaywrightConfig {

    @Bean
    public Playwright playwright() {
        return Playwright.create();
    }

}
