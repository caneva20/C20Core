package me.caneva20.c20core.modules.messagedispatcher.parsers;

import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.MessageLevel;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class DefaultTokenParser extends TokenParser {
    public DefaultTokenParser(
            Map<MessageLevel, ColorSwatch> colorMap,
            ColorSwatch defaultColor
    ) {
        super(colorMap, defaultColor);
    }

    @Override
    public String parse(
            @NotNull String content, MessageLevel messageLevel
    ) {
        var swatch = getColor(messageLevel);

        return String.format("&%s%s", swatch.primary, content);
    }
}
