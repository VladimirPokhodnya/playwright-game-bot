package com.github.vladimirpokhodnya.playwrightgamebot.model;

public enum GameLocation {
    ALLEY("alley"),
    BANK("bank"),
    HOME("home"),
    PLAYER("player"),
    STASH("stash"),
    TRAINER_VIP("trainer/vip"),
    TRAINER("trainer");

    private final String name;

    GameLocation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

