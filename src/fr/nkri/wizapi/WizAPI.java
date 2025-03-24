package fr.nkri.wizapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.nkri.wizapi.cmds.CommandFramework;
import fr.nkri.wizapi.logs.Logs;
import fr.nkri.wizapi.logs.enums.LogsType;
import fr.nkri.wizapi.guis.WizInvManager;
import fr.nkri.wizapi.modules.Module;
import fr.nkri.wizapi.modules.ModuleManager;
import fr.nkri.wizapi.modules.cmds.CommandModule;
import fr.nkri.wizapi.packets.PacketManager;
import fr.nkri.wizapi.packets.WizPacket;
import fr.nkri.wizapi.utils.json.adapter.ItemStackAdpater;
import fr.nkri.wizapi.utils.json.adapter.LocationAdapter;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 *
 * @author NKRI
 * @version 1.0
 * @date 10/03/2024
 */

public class WizAPI extends JavaPlugin {

    private static WizAPI INSTANCE;
    private CommandFramework commandFramework;
    private Gson gson;
    private PacketManager packetManager;

    //module management
    private ModuleManager moduleManager;

    public WizAPI(){}

    @Override
    public void onEnable() {
        INSTANCE = this;
        Logs.sendLog("WizAPI", "Starting... Please wait for the initialization process to complete.", LogsType.INFO);
        this.commandFramework = new CommandFramework(this);
        this.gson = getGsonBuilder().create();
        WizInvManager.register(this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Logs.sendLog("WizAPI", "Good Bye ! Thanks for using WizAPI, plugin being unloaded. \n\n Developed by NKRI", LogsType.INFO);

        if(this.moduleManager != null){
            this.moduleManager.saveAll();
        }

        super.onDisable();
    }

    //module
    public void registerModules(final List<Module> modules){
        this.moduleManager = new ModuleManager(this);

        for(Module module : modules){
            this.moduleManager.registerModule(module);
        }

        this.moduleManager.loadAll();
        registerCommand(new CommandModule(moduleManager));
    }
    //module

    public void registerPacket(final WizPacket wizPacket, final int id){
        packetManager.registerPacket(wizPacket, id);
    }

    public void registerPackets(){
        packetManager.registerPackets();
    }

    public void registerCommand(final Object object){
        this.commandFramework.registerCommands(object);
    }

    public void registerListeners(final Listener listener){
        final PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(listener, this);
    }

    //Json
    public GsonBuilder getGsonBuilder(){
        return new GsonBuilder().setPrettyPrinting().serializeNulls()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(ItemStack.class, new ItemStackAdpater())
                .registerTypeAdapter(Location.class, new LocationAdapter());
    }

    public String serialize(final Object obj){
        return this.gson.toJson(obj);
    }

    public <T> T deserialize(final String json, final Class<T> type){
        return this.gson.fromJson(json, type);
    }

    public Gson getGson() {
        return gson;
    }

    public static WizAPI getInstance() {
        return INSTANCE;
    }
}