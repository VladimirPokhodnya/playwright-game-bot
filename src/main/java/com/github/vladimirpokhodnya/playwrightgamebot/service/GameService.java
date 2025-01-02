package com.github.vladimirpokhodnya.playwrightgamebot.service;

import com.github.vladimirpokhodnya.playwrightgamebot.config.GameProperties;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class GameService {

    private final GameProperties properties;
    private final Playwright playwright;
    private final Path cookiesPath;

    public GameService(GameProperties properties, Playwright playwright) {
        this.properties = properties;
        this.playwright = playwright;
        this.cookiesPath = Path.of(properties.getCookies());
        authentication();
    }

    private void authentication() {
        try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true))) {
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(cookiesPath));
            Page page = context.newPage();
            page.navigate(properties.getHost());
            if (page.url().equals(properties.getHost())) {
                page.getByLabel("E-mail:").click();
                page.getByLabel("E-mail:").fill(properties.getUsername());
                page.getByLabel("Пароль:").click();
                page.getByLabel("Пароль:").fill(properties.getPassword());
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Войти")).click();

                context.storageState(new BrowserContext.StorageStateOptions().setPath(cookiesPath));
            }
        }
    }

    public void screenshot() {
        try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true))) {
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(cookiesPath));

            Page page = context.newPage();
            page.navigate(properties.getHost());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        }
    }

    public void closeGame() {
        playwright.close();
    }
}
