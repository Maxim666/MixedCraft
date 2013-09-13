package com.MixedCraft.helper;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if(packet.channel.equals("MixedCraft")){
			handlePckaet(packet); 
		}
	}

	private void handlePckaet(Packet250CustomPayload packet) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		int Int1;
		int Int2;
		
		try{
			Int1 = inputStream.readInt();
			Int2 = inputStream.readInt();
		}
		catch(IOException e){
			e.printStackTrace(); 
			return;
		}
		
		System.out.println(Int1 + "" + Int2);
	}
}
