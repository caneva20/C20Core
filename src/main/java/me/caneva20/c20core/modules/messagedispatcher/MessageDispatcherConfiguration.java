package me.caneva20.c20core.modules.messagedispatcher;

import me.caneva20.configapi.Config;
import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.MessageLevel;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class MessageDispatcherConfiguration {
    private static final String CONFIG_FILE_NAME = "messages-config";

    final String opening;
    final String closing;

    final ColorSwatch color;

    final String debugPrefix;
    final ColorSwatch debugColor;

    final Map<MessageLevel, ColorSwatch> colorMap;

    public MessageDispatcherConfiguration(
            String opening,
            String closing,
            ColorSwatch color,
            String debugPrefix,
            ColorSwatch debugColor,
            Map<MessageLevel, ColorSwatch> colorMap
    ) {
        this.opening = opening;
        this.closing = closing;
        this.color = color;
        this.debugPrefix = debugPrefix;
        this.debugColor = debugColor;
        this.colorMap = colorMap;
    }

    public static MessageDispatcherConfiguration load(JavaPlugin plugin) {
        var config = new Config(plugin, CONFIG_FILE_NAME);

        var opening = config.getString("tag.opening-char", "[");
        var closing = config.getString("tag.closing-char", "] ");

        var color = getColor(config, "tag.color", 'f');

        var debugColor = getColor(config, "tag.debug.color", 'f');
        var debugPrefix = config.getString("tag.debug.prefix");

        var colorMap = new HashMap<MessageLevel, ColorSwatch>();

        for (var level : MessageLevel.values()) {
            colorMap.put(level, getColor(config, "coloring." + level.name().toLowerCase(), 'f'));
        }

        return new MessageDispatcherConfiguration(opening, closing, color, debugPrefix, debugColor, colorMap);
    }

    private static ColorSwatch getColor(Config config, String section, char defaultValue) {
        var primaryColor = config.getString(section + ".primary", null);
        var secondaryColor = config.getString(section + ".secondary", null);
        var accentColor = config.getString(section + ".accent", null);
        var detailColor = config.getString(section + ".detail", null);

        return new ColorSwatch(
                primaryColor == null ? defaultValue : primaryColor.charAt(0),
                secondaryColor == null ? defaultValue : secondaryColor.charAt(0),
                accentColor == null ? defaultValue : accentColor.charAt(0),
                detailColor == null ? defaultValue : detailColor.charAt(0)
        );
    }
}
