package com.github.vladimirpokhodnya.playwrightgamebot.service;

import com.github.vladimirpokhodnya.playwrightgamebot.config.GameProperties;
import com.github.vladimirpokhodnya.playwrightgamebot.util.GameLogin;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GameService {

    private final Playwright playwright;
    private final GameProperties properties;
    private final GameLogin gameLogin;
    private final Path cookiesPath;

    public GameService(Playwright playwright, GameProperties properties, GameLogin gameLogin) {
        this.playwright = playwright;
        this.properties = properties;
        this.cookiesPath = Path.of(properties.getCookies());
        this.gameLogin = gameLogin;
    }

    public void screenshot(String fileName) {
        gameLogin.authentication();
        try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true))) {
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(cookiesPath));

            Page page = context.newPage();
            page.navigate(properties.getHost());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
        }
    }

    public void closePlaywright() {
        playwright.close();
    }


}
