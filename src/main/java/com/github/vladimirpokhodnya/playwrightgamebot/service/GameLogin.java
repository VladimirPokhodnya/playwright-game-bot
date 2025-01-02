package com.github.vladimirpokhodnya.playwrightgamebot.service;

import com.github.vladimirpokhodnya.playwrightgamebot.config.GameProperties;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;


@Service
public class GameLogin {

    public GameLogin(GameProperties properties) {
        authentication(properties);
    }

    private void authentication(GameProperties properties) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(true));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate(properties.getHost());
            page.getByLabel("E-mail:").click();
            page.getByLabel("E-mail:").fill(properties.getUsername());
            page.getByLabel("Пароль:").click();
            page.getByLabel("Пароль:").fill(properties.getPassword());
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Войти")).click();

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        }
    }
}
