package fr.nkri.wizapi.modules;

import fr.nkri.wizapi.WizAPI;
import fr.nkri.wizapi.packets.WizPacket;
import net.minecraft.server.v1_8_R3.ICommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class Module {

    private boolean isEnable;
    private final String name;

    /**
     * @param name module name
     */
    public Module(final String name) {
        this.name = name;
        this.isEnable = true;
    }

    //register an cmd
    public void registerCommand(final ICommand iCommand){
        WizAPI.getInstance().registerCommand(iCommand);
    }

    //register a listener
    public void registerListener(final Listener listener){
        WizAPI.getInstance().registerListeners(listener);
    }

    //register packets
    public void registerPacket(final WizPacket packet, final int packetId){
        WizAPI.getInstance().registerPacket(packet, packetId);
    }

    //save data
    public abstract void load();
    public abstract void save();
    //save data

    //Getter, Setter
    public String getName() {
        return name;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
