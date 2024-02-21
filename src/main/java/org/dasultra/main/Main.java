package org.dasultra.main;

import org.bukkit.plugin.java.JavaPlugin;
import org.dasultra.file.FileManager;
import org.dasultra.listener.ConnectListener;
import org.dasultra.listener.PingListener;

import java.util.List;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Maintanceplugin enabled!");
        System.out.println("Coded by DasUltra!");

        getCommand("maintance").setExecutor(new org.dasultra.commands.MaintanceCommand());

        getServer().getPluginManager().registerEvents(new ConnectListener(), this);
        getServer().getPluginManager().registerEvents(new PingListener(), this);

        FileManager file = new FileManager("plugins/Maintance/config.yml");


        if (!file.exist()) {
            file.add("Prefix", "&aDeinServer.de &8» &7");
            file.add("Maintance", false);
            file.add("Motd", "&cDer Server ist im Wartungsmodus!");
            List<String> whitelist = file.getStringList("whitelist");

            whitelist.add("DasUltra");

            file.set("whitelist", whitelist);
            file.save();
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Maintanceplugin disabled!");
        System.out.println("Coded by DasUltra!");
    }

    public static String getMotd() {
        FileManager file = new FileManager("plugins/Maintance/config.yml");
        return file.getString("Motd").replaceAll("&", "§");
    }

    public static String getPrefix() {
        FileManager file = new FileManager("plugins/Maintance/config.yml");
        return file.getString("Prefix").replaceAll("&", "§");
    }
}
