package me.caneva20.c20core.modules.messagedispatcher.parsers;

import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.ITokenParser;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ParameterTokenParser implements ITokenParser {
    private final Map<MessageLevel, ColorSwatch> colorMap;
    private final ColorSwatch defaultColor;

    public ParameterTokenParser(
            Map<MessageLevel, ColorSwatch> colorMap,
            ColorSwatch defaultColor
    ) {
        this.colorMap = colorMap;
        this.defaultColor = defaultColor;
    }

    @Override
    public String parse(@NotNull String content, MessageLevel messageLevel) {
        ColorSwatch swatch = colorMap.getOrDefault(messageLevel, defaultColor);

        return String.format("&%s%s&%s", swatch.secondary, content, swatch.primary);
    }
}
