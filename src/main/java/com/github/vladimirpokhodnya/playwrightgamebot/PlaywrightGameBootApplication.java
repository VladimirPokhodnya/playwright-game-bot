package com.github.vladimirpokhodnya.playwrightgamebot;

import com.github.vladimirpokhodnya.playwrightgamebot.service.LoginService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PlaywrightGameBootApplication implements CommandLineRunner {

    private final LoginService loginService;

    public PlaywrightGameBootApplication(LoginService loginService) {
        this.loginService = loginService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PlaywrightGameBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loginService.authentication();
        loginService.screenshot();
        System.out.println("Скриншот сохранён");
        loginService.closePlaywright();
    }
}
