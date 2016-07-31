package com.github.alexthe666.archipelago.enums;

public enum TropicGrassColor {

    CLASSIC_TROPICAL(0.95F, 0.9F),
    DRY(3F, 0.0F),
    SWAMP(3.0F, 3.0F),
    BURNT(5F, 0.0F);

    public float temperature;
    public float humidity;

    TropicGrassColor(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
