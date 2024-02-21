package org.dasultra.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.dasultra.file.FileManager;

import static org.dasultra.main.Main.getMotd;

public class PingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event) {

        FileManager file = new FileManager("plugins/Maintance/config.yml");

        if (file.getBoolean("Maintance") == true) {
            event.setMotd(getMotd());
        }

    }
}
