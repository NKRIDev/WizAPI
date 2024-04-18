package fr.nkri.wizapi.packets.client;

import fr.nkri.wizapi.packets.WizPacket;
import net.minecraft.server.v1_9_R2.PacketDataSerializer;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public abstract class ClientPacket extends WizPacket {

    @Override
    public void writePacket(final PacketDataSerializer packetDataSerializer) throws IOException {}

    public void writeDouble(final PacketDataSerializer packetDataSerializer) {
        packetDataSerializer.readDouble();
    }

    public int readInt(final PacketDataSerializer packetDataSerializer) throws IOException{
        return packetDataSerializer.readInt();
    }

    public double readDouble(final PacketDataSerializer packetDataSerializer) throws IOException{
        return packetDataSerializer.readDouble();
    }

    public int[] readString(final PacketDataSerializer packetDataSerializer) throws IOException{
        return packetDataSerializer.c(191919);
    }

    public boolean readBoolean(final PacketDataSerializer packetDataSerializer) throws IOException{
        return packetDataSerializer.readBoolean();
    }

    public ItemStack readStack(final PacketDataSerializer packetDataSerializer) throws IOException{
        return CraftItemStack.asBukkitCopy(packetDataSerializer.k());
    }

    public float readFloat(final PacketDataSerializer packetDataSerializer) throws IOException{
        return packetDataSerializer.readFloat();
    }
}