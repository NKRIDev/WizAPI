package fr.nkri.wizapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.nkri.wizapi.cmds.CommandFramework;
import fr.nkri.wizapi.logs.Logs;
import fr.nkri.wizapi.logs.enums.LogsType;
import fr.nkri.wizapi.utils.guis.WizInvManager;
import fr.nkri.wizapi.utils.json.adapter.ItemStackAdpater;
import fr.nkri.wizapi.utils.json.adapter.LocationAdapter;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
        super.onDisable();
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