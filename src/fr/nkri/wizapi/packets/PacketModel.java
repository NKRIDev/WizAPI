package fr.nkri.wizapi.packets;


import net.minecraft.server.v1_9_R2.Packet;

public class PacketModel {

    private final Packet packet;
    private final int id;

    public PacketModel(final Packet packet, final int id){
        this.packet = packet;
        this.id = id;
    }

    public Packet getPacket() {
        return packet;
    }

    public int getId() {
        return id;
    }
}
