package com.github.vladimirpokhodnya.playwrightgamebot;

import com.github.vladimirpokhodnya.playwrightgamebot.model.GameLocation;
import com.github.vladimirpokhodnya.playwrightgamebot.service.GameBotService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PlaywrightGameBootApplication implements CommandLineRunner {

    private final GameBotService gameBotService;

    public PlaywrightGameBootApplication(GameBotService gameBotService) {
        this.gameBotService = gameBotService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PlaywrightGameBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        gameBotService.screenshot("example.png", GameLocation.TRAINER_VIP);
        System.out.println("Скриншот сохранён");
        gameBotService.closeGameBot();
    }
}
