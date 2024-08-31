
# WizAPI
This is the Minecraft API (version: 1.8.8)
If you use it, and need help ask NKRI.

## What is WizAPI?

WizAPI is a **minecraft api** mainly intended for *version 1.8.8*. This API contains already developed systems such as: titles, cooldowns, database management (MySQL). This little API simplifies the creation of plugins, you will no longer need to copy/paste your cooldown systems for example !

## How it works ?
Do you want to integrate WizAPI into your project? That's great ! You will see, it's very simple:

1. Download the .jar archive by clicking on the green *“<> Code”* button.
2. Add the *"WizAPI.jar"* file to your project as a library.
3. Also make sure to include the *"WizAPI.jar"* file in the plugins folder of your

## Documentation

**→ Titles:**
You only need to call the instance of the Vanilla Title class. Then choose your method, in our case here we will display a message in the player's action bar

    VanillaTitle.getInstance().sendActionBar(player, "Your text");
NOTE : Only available in 1.8.8

**→ Cooldown:**
To put a cooldown on a player, you must enter the player, the duration of the cooldown in seconds and an object (this can be a String, an ItemStack, etc.). Don't forget to call the instance of the CooldownManager class. Here is an example where we put a 100 second cooldown on players who eat a golden apple.

    public class CooldownListeners implements Listener {
    
        private final CooldownManager cooldownManager = CooldownManager.getInstance();
    
        @EventHandler
        public void onItemEat(final PlayerItemConsumeEvent e){
            final Player player = e.getPlayer();
            final ItemStack stack = e.getItem();
    
            if(stack == null){
                return;
            }
    
            if(stack.getType() == Material.GOLDEN_APPLE){
                if(!cooldownManager.isCooldown(player, stack)){
                    e.setCancelled(true);
                    player.sendMessage("§cYou have to wait before you can eat an apple!");
                    return;
                }
    
                cooldownManager.setCooldown(player, 100, stack);
                player.sendMessage("§cYou have just entered cooldown with the golden apple.");
            }
        }
    }
    
 **→ Recover the instance:** retrieving the WizAPI instance allows you to retrieve a lot of classes and methods. Here's how to do it, do this in your Main class of your plugin and retrieve the API instance with its getter:

    import fr.nkri.wizapi.WizAPI;
    
    public class Main extends JavaPlugin {
    
        private static Main INSTANCE;
        private WizAPI wizAPI;
    
        @Override
        public void onEnable() {
            INSTANCE = this;
            this.wizAPI = WizAPI.getInstance();
        }
    
        @Override
        public void onDisable() {}
    
        public WizAPI getWizAPI() {
            return wizAPI;
        }
        
        public static Main getInstance(){
			return INSTANCE;
        }
    }

**→ Saving JSON:**
To recover the 2 methods:
- Serialize your object into json:

    Main.getInstance().getWizAPI().serialize(votre_objet);
- Deserialize your object into json:

    final String json = FileUtils.loadContent(file); //We assume that you have already defined your File
    final FactionPoint factionPoint = Main.getInstance()getWizAPI().deserialize(json, votre_objet.class);
    
Here is an example with a class that allows you to save a 'FactionPoint' object in a 'datas' folder. It should be noted that here we use the 'FileUtils' class of the API.

    import fr.nkri.tuto.Main;
    import fr.nkri.wizapi.logs.Logs;
    import fr.nkri.wizapi.logs.enums.LogsType;
    import fr.nkri.wizapi.utils.json.FileUtils;
    
    import java.io.File;
    import java.util.Map;
    
    public class FactionData {
    
        private final Main main;
        private final File saveDir;
    
        public FactionData(final Main main){
            this.main = main;
            this.saveDir = new File(main.getDataFolder(), "/datas/");
        }
    
        public void load() {
            if (!saveDir.exists()) {
                return;
            }
    
            for (File file : saveDir.listFiles()) {
                if (file.isFile()) {
                    final String json = FileUtils.loadContent(file);
                    //This is an example object
                    final FactionPoint factionPoint = this.main.getWizAPI().deserialize(json, FactionPoint.class); //This is how we deserialize an object
    
                    //Your action...
                }
            }
    
            Logs.sendLog("[Loader]", "Data recovery completed successfully.", LogsType.SUCCES);
        }
    
        public void save(final FactionPoint factionPoint) {
            final File file = new File(this.saveDir, factionPoint.getTag() + ".json");
            final String json = main.getWizAPI().serialize(factionPoint); //How to serialize
    
            FileUtils.save(file, json);
            Logs.sendLog("[Saver]", "Saved data successfully.", LogsType.SUCCES);
        }
    }

**Important**, you do not need to create 'types adapter' classes for 'itemstack' and 'location': they are already done.

**→ Others:**
*I'm still doing the documentation.*

## Other
In case of problem or information, here are my contact details:

• Discord: https://discord.gg/fjhQ9nfpFw

• Email: nkri.dev@gmail.com
