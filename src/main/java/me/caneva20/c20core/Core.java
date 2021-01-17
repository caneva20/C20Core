package me.caneva20.c20core;

import io.papermc.lib.PaperLib;
import me.caneva20.c20core.modules.messagedispatcher.MessageDispatcher;
import me.caneva20.messagedispatcher.Messaging;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
    @Override
    public void onEnable() {
        PaperLib.suggestPaper(this);

        MessageDispatcher.configure(this);

        var dispatcher = Messaging.createConsoleDispatcher(this);

        dispatcher.success("Enabled & all  \\\\o/");
        dispatcher.success("dependencies    |");
        dispatcher.success("configured!    / \\\\");
    }
}
