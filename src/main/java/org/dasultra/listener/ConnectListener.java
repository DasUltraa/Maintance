package org.dasultra.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.dasultra.file.FileManager;

import static org.dasultra.main.Main.getPrefix;

public class ConnectListener implements Listener {

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        FileManager file = new FileManager("plugins/Maintance/config.yml");

        if (file.getBoolean("Maintance") == true) {
            if (!file.getStringList("whitelist").contains(event.getPlayer().getName())) {
                Bukkit.broadcastMessage(getPrefix() + "§cDer Spieler §e" + event.getPlayer().getName() + " §chat versucht den Server zu betreten!");
                Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Maintance"), () -> event.getPlayer().kickPlayer("§cDer Server ist im Wartungsmodus!"), 60L);
                event.getPlayer().kickPlayer("§cDer Server ist im Wartungsmodus!");
            }
        }
    }
}
