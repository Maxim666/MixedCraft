package com.MixedCraft.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.MixedCraft.items.tools.SwordBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlameSword extends SwordBase {
	
    private final ToolMaterial field_40439_b;
	private String IIconPath;

    public ItemFlameSword(ToolMaterial var2) {
        super(var2);
        this.field_40439_b = var2;
        this.maxStackSize = 1;
        this.setMaxDamage(-1);
    }

    public boolean hitEntity(ItemStack var1, EntityLivingBase var2, EntityLivingBase var3) {
        var1.damageItem(1, var3);
        var2.setFire(16);
        return true;
    }

    @Override 
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add("When hits: Burns for 16 seconds");
    }
}
