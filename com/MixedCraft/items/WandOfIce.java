package com.MixedCraft.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WandOfIce extends ItemBaseWand {

	public static int cooldown = 10;
	
	public static int defaultCharges = 100;
	
	public WandOfIce(String par2) {
		super(par2);
        this.setMaxDamage(defaultCharges + 1);
	}

	@Override 
	public int getUseCost() {
		return 1;
	}

	@Override 
	public int getBaseRepairCost() {
		return 5;
	}
	
	@Override public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ, int par7, float par8, float par9, float par10){
		if (!playerEntity.capabilities.isCreativeMode)
        {
        	if(isOutOfCharge(srcItemStack)){
        		// wand out of magic
        		playSound(noChargeAttackSound,world,playerEntity);
        		return true;
        	}
        }
		int blocksChanged = 0;
		for(int y = targetY+1; y >= targetY-1; y--){
			if(y < 0) continue;
			for(int x = targetX-1; x <= targetX+1; x++){
				for(int z = targetZ-1; z <= targetZ+1; z++){
					blocksChanged += freezeBlock(world,x,y,z);
				}
			}
		}
		if(blocksChanged > 0){
			srcItemStack.damageItem(getUseCost(), playerEntity);
	        playSound("random.orb",world,playerEntity);
			return true;
		}else {
			return false;
		}
	}
	
	protected int freezeBlock(World w, int x, int y, int z){
		int target = w.getBlock(x, y, z);
		if(target == Block.waterStill.block || target == Block.waterMoving.block){
			w.setBlock(x, y, z, Block.ice.block);
			return 1;
		} else if(target == Block.lavaMoving.block || target == Block.lavaStill.block){
			w.setBlock(x, y, z, Block.cobblestone.block);
			return 1;
		}
		return 0;
	}

}
