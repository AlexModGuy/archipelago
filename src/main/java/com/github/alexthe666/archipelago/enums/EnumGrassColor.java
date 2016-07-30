package com.github.alexthe666.archipelago.enums;

public enum EnumGrassColor {

    CLASSIC_TROPICAL(0.95F, 0.9F), DRY(3F, 0.0F), BURNT(5F, 0.0F);

    public float tempature;
    public float humidity;

    EnumGrassColor(float tempature, float humidity) {
        this.tempature = tempature;
        this.humidity = humidity;
    }
}
