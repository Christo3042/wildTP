package me.christo.wild;

import me.christo.wild.listeners.TeleportCommand;
import me.christo.wild.listeners.TeleportListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {


    private static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic


        File file = new File(getDataFolder(), "config.yml");
        if(!file.exists())
            saveDefaultConfig();

        instance = this;

        this.getServer().getPluginManager().registerEvents(new TeleportListener(), this);
        getCommand("rt").setExecutor((CommandExecutor) new TeleportCommand());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

}
