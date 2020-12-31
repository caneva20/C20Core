package me.caneva20.c20core.modules.messagedispatcher;

import me.caneva20.c20core.modules.messagedispatcher.configurations.ParameterTokenConfiguration;
import me.caneva20.c20core.modules.messagedispatcher.parsers.DefaultTokenParser;
import me.caneva20.c20core.modules.messagedispatcher.parsers.ParameterTokenParser;
import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.Messaging;
import me.caneva20.messagedispatcher.configurations.ColorConfiguration;
import me.caneva20.messagedispatcher.configurations.DefaultConfiguration;
import me.caneva20.messagedispatcher.configurations.TokenConfiguration;
import me.caneva20.messagedispatcher.parsing.tokens.TagTokenParser;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessageDispatcher {

    public static void configure(JavaPlugin plugin) {
        var config = MessageDispatcherConfiguration.load(plugin);
        var colorMap = config.colorMap;
        var fallbackSwatch = new ColorSwatch('a', '2', '6');

        Messaging.configure(ColorConfiguration.class, colorConfiguration -> {
            for (var level : colorMap.keySet()) {
                colorConfiguration.register(level, colorMap.get(level));
            }
        });

        Messaging.configure(ParameterTokenConfiguration.class);

        Messaging.configure(DefaultConfiguration.class, defaultConfiguration -> {
            var tokenConfiguration = Messaging.get(TokenConfiguration.class);

            var registry = defaultConfiguration.getTokenRegistry();

            registry.setDefaultParser(new DefaultTokenParser(colorMap, fallbackSwatch));

            registry.registerParser(tokenConfiguration.getTag(), new TagTokenParser(
                    colorMap,
                    fallbackSwatch,
                    config.opening,
                    config.closing,
                    null,
                    null
            ));

            registry.registerParser(tokenConfiguration.getDebugTag(), new TagTokenParser(
                    colorMap,
                    new ColorSwatch('d', '5', '9'),
                    config.opening,
                    config.closing,
                    config.debugPrefix,
                    config.debugColor
            ));

            var parameterTokenConfiguration = Messaging.get(ParameterTokenConfiguration.class);

            registry.registerParser(
                    parameterTokenConfiguration.getTokenName(),
                    new ParameterTokenParser(colorMap, fallbackSwatch)
            );
        });
    }
}
