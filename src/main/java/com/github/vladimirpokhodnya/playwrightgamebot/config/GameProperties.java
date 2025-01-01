package com.github.vladimirpokhodnya.playwrightgamebot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "game")
@Setter
@Getter
public class GameProperties {
    String host;
    String username;
    String password;

}
