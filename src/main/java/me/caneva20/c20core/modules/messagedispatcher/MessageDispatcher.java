package me.caneva20.c20core.modules.messagedispatcher;

import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.Messaging;
import me.caneva20.messagedispatcher.configurations.ColorConfiguration;
import me.caneva20.messagedispatcher.configurations.DefaultConfiguration;
import me.caneva20.messagedispatcher.configurations.TokenConfiguration;
import me.caneva20.messagedispatcher.parsing.tokens.TagTokenParser;
import me.caneva20.messagedispatcher.registries.ITokenRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessageDispatcher {

    public static void configure(JavaPlugin plugin) {
        MessageDispatcherConfiguration config = MessageDispatcherConfiguration.load(plugin);

        Messaging.configure(ColorConfiguration.class, colorConfiguration -> {
            for (MessageLevel level : config.colorMap.keySet()) {
                colorConfiguration.register(level, config.colorMap.get(level));
            }
        });

        Messaging.configure(DefaultConfiguration.class, defaultConfiguration -> {
            TokenConfiguration tokenConfiguration = Messaging.get(TokenConfiguration.class);

            ITokenRegistry registry = defaultConfiguration.getTokenRegistry();

            registry.registerParser(tokenConfiguration.getTag(), new TagTokenParser(
                    config.colorMap,
                    new ColorSwatch('a', '2', '6'),
                    config.opening,
                    config.closing,
                    null,
                    null
            ));

            registry.registerParser(tokenConfiguration.getDebugTag(), new TagTokenParser(
                    config.colorMap,
                    new ColorSwatch('d', '5', '9'),
                    config.opening,
                    config.closing,
                    config.debugPrefix,
                    config.debugColor
            ));
        });
    }
}
