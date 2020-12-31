package me.caneva20.c20core;

import io.papermc.lib.PaperLib;
import me.caneva20.c20core.modules.messagedispatcher.MessageDispatcher;
import me.caneva20.messagedispatcher.Messaging;
import me.caneva20.messagedispatcher.dispachers.IConsoleMessageDispatcher;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
    private IConsoleMessageDispatcher dispatcher;

    @Override
    public void onEnable() {
        PaperLib.suggestPaper(this);

        MessageDispatcher.configure(this);

        dispatcher = Messaging.createConsoleDispatcher(this, true);

        dispatcher.success("Enabled & all  \\\\o/");
        dispatcher.success("dependencies    |");
        dispatcher.success("configured!    / \\\\");
    }
}
