package com.MixedCraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.MixedCraft.client.particles.Particles;
import com.MixedCraft.helper.BlocksBase;
import com.MixedCraft.helper.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PoisionOre extends BlocksBase {

	public PoisionOre(int id) {
		super(id, Material.rock);
		setHardness(1F);
	}
	
	@SideOnly(Side.CLIENT)	
	public Icon particleIcon;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		particleIcon = register.registerIcon(Utils.MOD_ID + ":" + "Poison_NoColour");
		blockIcon = register.registerIcon(Utils.MOD_ID + ":" + "Poison");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		for (int i = 0; i < 4; i++) {
			
			float particleX = x + rand.nextFloat();
			float particleY = y + rand.nextFloat();
			float particleZ = z + rand.nextFloat();
			
			float particleMotionX = -0.5F + rand.nextFloat();
			float particleMotionY = -0.5F + rand.nextFloat();
			float particleMotionZ = -0.5F + rand.nextFloat();
			
			Particles.POISON.spawnParticle(world, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
		}
	}
	
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
    	if (!world.isRemote) {
    		player.addPotionEffect(new PotionEffect(Potion.poison.id, 40, 1));
    	}
    }	
	
}
