package fr.nkri.wizapi.logs;

import fr.nkri.wizapi.logs.enums.LogsType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logs {

    //Faire un système + complet

    public static void sendLog(final String prefix, final String message, final LogsType logsType){
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + prefix + " " + logsType.getColor() + message);
    }
}