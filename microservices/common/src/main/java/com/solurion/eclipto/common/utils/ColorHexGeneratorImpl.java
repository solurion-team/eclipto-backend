package com.solurion.eclipto.common.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ColorHexGeneratorImpl implements ColorHexGenerator {
    String[] darkColors = {"#0B3954", "#024959", "#5F0F40", "#145C9E", "#1C2826",
            "#412234", "#1B1B3A", "#3E0E2F", "#2C1320", "#18206F"};

    String[] brightColors = {"#FF5733", "#FF8D1A", "#FFC300", "#DAF7A6", "#33FF57",
            "#33FFDD", "#3385FF", "#8D33FF", "#FF33A6", "#FF3357"};

    @Override
    public String generateDarkColorHex() {
        return getRandomFrom(darkColors);
    }

    @Override
    public String generateBrightColorHex() {
        return getRandomFrom(brightColors);
    }

    private String getRandomFrom(String[] colors) {
        Random random = new Random();
        int index = random.nextInt(colors.length);
        return colors[index];
    }
}
