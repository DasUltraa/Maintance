package org.dasultra.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.file.FileManager;

import java.util.ArrayList;
import java.util.List;

import static org.dasultra.main.Main.getPrefix;

public class MaintanceCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player p) {
            if (p.hasPermission("maintance.admin")) {
                FileManager file = new FileManager("plugins/Maintance/config.yml");

                if (strings.length == 0) {
                    p.sendMessage("§8---------------------------");
                    p.sendMessage("");
                    p.sendMessage("§aMaintance Status");
                    p.sendMessage("");
                    p.sendMessage("§8» §7Status: §a" + file.getBoolean("Maintance".replaceAll("true", "§aAktiviert").replaceAll("false", "§cDeaktiviert")));
                    p.sendMessage("");
                    p.sendMessage("§aMaintance Help");
                    p.sendMessage("");
                    p.sendMessage("§8» §7/maintance on §8- §7Aktiviert den Wartungsmodus");
                    p.sendMessage("§8» §7/maintance off §8- §7Deaktiviert den Wartungsmodus");
                    p.sendMessage("§8» §7/maintance whitelist §8- §7Zeigt die Whitelist an");
                    p.sendMessage("§8» §7/maintance status §8- §7Zeigt den Wartungsstatus an");
                    p.sendMessage("");
                    p.sendMessage("§8» §7/maintance add <Spieler> §8- §7Fügt einen Spieler zur Whitelist hinzu");
                    p.sendMessage("§8» §7/maintance remove <Spieler> §8- §7Entfernt einen Spieler von der Whitelist");
                    p.sendMessage("");
                    p.sendMessage("§8» §7/maintance motd <Nachricht> §8- §7Setzt die Wartungsnachricht");
                    p.sendMessage("");
                    p.sendMessage("§8» §7/maintance help §8- §7Zeigt diese Hilfe an");
                    p.sendMessage("");
                    p.sendMessage("§8---------------------------");
                } else if (strings.length == 1) {
                    if (strings[0].equalsIgnoreCase("on")) {
                        file.set("Maintance", true);
                        file.save();
                        p.sendMessage(getPrefix() + "§aDer Wartungsmodus wurde §aaktiviert§a!");
                    } else if (strings[0].equalsIgnoreCase("off")) {
                        file.set("Maintance", false);
                        file.save();
                        p.sendMessage(getPrefix() + "§aDer Wartungsmodus wurde §cdeaktiviert§a!");
                    } else if (strings[0].equalsIgnoreCase("whitelist")) {
                        p.sendMessage("§8---------------------------");
                        p.sendMessage("");
                        p.sendMessage("§aWhitelist");
                        p.sendMessage("§8» §7Spieler auf der Whitelist:");
                        for (String player : file.getStringList("whitelist")) {
                            p.sendMessage("§8» §7- §a" + player);
                        }
                        p.sendMessage("");
                        p.sendMessage("§8---------------------------");
                    } else if (strings[0].equalsIgnoreCase("help")) {
                        p.sendMessage("§8---------------------------");
                        p.sendMessage("");
                        p.sendMessage("§aMaintance Help");
                        p.sendMessage("§8» §7/maintance on §8- §7Aktiviert den Wartungsmodus");
                        p.sendMessage("§8» §7/maintance off §8- §7Deaktiviert den Wartungsmodus");
                        p.sendMessage("§8» §7/maintance whitelist §8- §7Zeigt die Whitelist an");
                        p.sendMessage("§8» §7/maintance status §8- §7Zeigt den Wartungsstatus an");
                        p.sendMessage("");
                        p.sendMessage("§8» §7/maintance add <Spieler> §8- §7Fügt einen Spieler zur Whitelist hinzu");
                        p.sendMessage("§8» §7/maintance remove <Spieler> §8- §7Entfernt einen Spieler von der Whitelist");
                        p.sendMessage("");
                        p.sendMessage("§8» §7/maintance motd <Nachricht> §8- §7Setzt die Wartungsnachricht");
                        p.sendMessage("");
                        p.sendMessage("§8» §7/maintance help §8- §7Zeigt diese Hilfe an");
                        p.sendMessage("");
                        p.sendMessage("§8---------------------------");
                    } else if (strings[0].equalsIgnoreCase("status")) {
                        p.sendMessage("§8---------------------------");
                        p.sendMessage("");
                        p.sendMessage("§aMaintance Status");
                        p.sendMessage("§8» §7Status: §a" + file.getBoolean("Maintance"));
                        p.sendMessage("");
                        p.sendMessage("§8---------------------------");
                    } else {
                        p.sendMessage(getPrefix() + "§cBenutze §7/maintance help §cum die Hilfe anzuzeigen!");
                    }
                } else if (strings.length == 2) {
                    if (strings[0].equalsIgnoreCase("add")) {
                        List<String> whitelist = file.getStringList("whitelist");
                        whitelist.add(strings[1]);
                        file.set("whitelist", whitelist);
                        file.save();
                        p.sendMessage(getPrefix() + "§aDer Spieler §e" + strings[1] + " §awurde zur Whitelist hinzugefügt!");
                    } else if (strings[0].equalsIgnoreCase("remove")) {
                        List<String> whitelist = file.getStringList("whitelist");
                        whitelist.remove(strings[1]);
                        file.set("whitelist", whitelist);
                        file.save();
                        p.sendMessage(getPrefix() + "§aDer Spieler §e" + strings[1] + " §awurde von der Whitelist entfernt!");
                    } else {
                        p.sendMessage(getPrefix() + "§cBenutze §7/maintance help §cum die Hilfe anzuzeigen!");
                    }
                } else if (strings.length >= 2) {
                    if (strings[0].equalsIgnoreCase("motd")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 1; i < strings.length; i++) {
                            stringBuilder.append(strings[i]).append(" ");
                        }
                        file.set("Motd", stringBuilder.toString());
                        file.save();
                        p.sendMessage(getPrefix() + "§aDie Wartungsnachricht wurde gesetzt!");
                    } else {
                        p.sendMessage(getPrefix() + "§cBenutze §7/maintance help §cum die Hilfe anzuzeigen!");
                    }

                } else {
                    p.sendMessage(getPrefix() + "§cBenutze §7/maintance help §cum die Hilfe anzuzeigen!");
                }
            } else {
                p.sendMessage(getPrefix() + "§cDazu hast du keine Rechte!");
            }


        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> list = new ArrayList<>();
        if (strings.length == 1) {
            list.add("on");
            list.add("off");
            list.add("whitelist");
            list.add("status");
            list.add("add");
            list.add("remove");
            list.add("motd");
            list.add("help");
        } else if (strings.length == 2) {
            if (strings[0].equalsIgnoreCase("add") || strings[0].equalsIgnoreCase("remove")) {
                for (Player player : commandSender.getServer().getOnlinePlayers()) {
                    list.add(player.getName());
                }
            } else if (strings[0].equalsIgnoreCase("motd")) {
                list.add("<Nachricht>");
            }
        }
        return list;
    }
}
