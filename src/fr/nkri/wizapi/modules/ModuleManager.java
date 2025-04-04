package fr.nkri.wizapi.modules;

import fr.nkri.wizapi.WizAPI;
import fr.nkri.wizapi.logs.Logs;
import fr.nkri.wizapi.logs.enums.LogsType;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private final WizAPI main;
    private static ModuleManager INSTANCE;
    private final List<Module> modules;

    /**
     * allows you to manage modules
     */
    public ModuleManager(final WizAPI main){
        this.main = main;
        INSTANCE = this;
        this.modules = new ArrayList<>();
    }

    /**
     * @param name
     * @return a module thanks to its name
     */
    public Module getModuleByName(final String name){
        for(Module module : modules){
            if(module.getName().equalsIgnoreCase(name)){
                return module;
            }
        }

        return null;
    }

    //add a module in list
    public void registerModule(final Module module){
        this.modules.add(module);
        Logs.sendLog("[Module]", "The '%name%' module has just been loaded".replace("%name%", module.getName()),
                LogsType.SUCCES);
    }

    //save data
    public void loadAll() {
        modules.forEach(Module::load);
        //main.registerPackets();
    }

    public void saveAll() {
        modules.forEach(Module::save);
    }
    //save data

    //Getter
    public static ModuleManager getInstance() {
        return INSTANCE;
    }

    public List<Module> getModules() {
        return modules;
    }
}
