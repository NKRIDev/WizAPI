package fr.nkri.wizapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WizUtils {

    private static String LINE = "§7—————————————————————————————————————";

    //Retourne vrai si l'inventaire du joueur est plein
    public static boolean isInventoryFull(final Player player) {
        final Inventory inventory = player.getInventory();

        return inventory.firstEmpty() == -1;
    }

    //Retourne vrai si le joueur à un material dans sa main
    public static boolean haveItemInHand(final Player player, final Material material){
        final ItemStack stack = player.getItemInHand();

        if(stack != null && stack.getType() != Material.AIR) {
            if(stack.getType() == material){
                return true;
            }
        }
        return false;
    }

    //Retourne vrai si le joueur à un objet dans sa main
    public static boolean haveItemInHand(final Player player) {
        final ItemStack stack = player.getItemInHand();

        if(stack != null && stack.getType() != Material.AIR) {
            return true;
        }
        return false;
    }

    //Envoi un message à tout les joueurs ayant la permission
    public static void sendBroadcastByPermission(final String permission, final List<String> messages){
        for(Player pls : Bukkit.getServer().getOnlinePlayers()){
            if(!pls.hasPermission(permission)){
                return;
            }

            for(String message : messages){
                pls.sendMessage(message);
            }
        }
    }

    //Sérialisation/Désérialisation d'itemstacl (util pour les stocker en base de donnée)
    private List<ItemStack> deserializeItemStack(final String itemInDataBase){
        final List<ItemStack> itemList = new ArrayList<>();
        final List<String> stringList = new ArrayList<>(Arrays.asList(itemInDataBase.split(";")));

        for(String s : stringList) {
            final String materialString = s.split(":")[0];
            final String amountString = s.split(":")[1];

            final Material material = Material.getMaterial(materialString);
            final int amount = Integer.parseInt(amountString);
            final ItemStack stack = new ItemStack(material, amount);

            itemList.add(stack);
        }

        return itemList;
    }

    private String serializeItemStack(final List<ItemStack> stackList) {
        String item = "";

        for(ItemStack stack : stackList) {
            item = item + stack.getType() + ":" + stack.getAmount() + ";";
        }

        return item;
    }
}
