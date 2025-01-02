package com.github.vladimirpokhodnya.playwrightgamebot.config;

import com.microsoft.playwright.Playwright;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaywrightConfig {

    private Playwright playwright;

    @Bean
    public Playwright playwright() {
        return Playwright.create();
    }

    @PreDestroy
    public void destroy() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
