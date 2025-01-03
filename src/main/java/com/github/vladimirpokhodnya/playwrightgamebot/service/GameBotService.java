package com.github.vladimirpokhodnya.playwrightgamebot.service;

import com.github.vladimirpokhodnya.playwrightgamebot.config.GameProperties;
import com.github.vladimirpokhodnya.playwrightgamebot.model.GameLocation;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GameBotService {

    private final Playwright playwright;
    private final GameProperties properties;
    private final GameLoginService gameLoginService;
    private final Path cookiesPath;

    public GameBotService(Playwright playwright, GameProperties properties, GameLoginService gameLoginService) {
        this.playwright = playwright;
        this.properties = properties;
        this.cookiesPath = Path.of(properties.getCookies());
        this.gameLoginService = gameLoginService;
    }

    public void screenshot(String fileName, GameLocation location) {
        gameLoginService.authentication();
        try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true))) {
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(cookiesPath));

            Page page = context.newPage();
            page.setViewportSize(1000, 2560);
            page.navigate(properties.getHost() + location.getName());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
        }
    }

    public void closeGameBot() {
        playwright.close();
    }

}
