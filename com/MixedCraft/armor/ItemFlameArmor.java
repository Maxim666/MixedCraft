package com.MixedCraft.armor;


import java.util.List;

import com.MixedCraft.ItemHelper;
import com.MixedCraft.helper.Utils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlameArmor extends ItemArmor{

    private String[] description;

    public ItemFlameArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par1, par2EnumArmorMaterial, par3, par4);
    }

    public ItemFlameArmor setDescriptors(String[] descs) {
    	description = descs;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer)
    {
        if (itemstack.itemID == ItemHelper.FlameHelmet.itemID || itemstack.itemID == ItemHelper.FlameBody.itemID || itemstack.itemID == ItemHelper.FlameBoots.itemID)
            return Utils.MOD_ID + ":" + "/textures/items/armor/Flame_1.png";

        if (itemstack.itemID == ItemHelper.FlameLegs.itemID)
            return Utils.MOD_ID + ":" + "/textures/items/armor/Flame_2.png";
        return Utils.MOD_ID + ":" + "/textures/items/armor/Flame_2.png"; 
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        if(itemID == ItemHelper.FlameHelmet.itemID) {
            this.itemIcon = par1IconRegister.registerIcon(Utils.MOD_ID + ":" + "FlameHelmet");
        }
        if(itemID == ItemHelper.FlameBody.itemID) {
            this.itemIcon = par1IconRegister.registerIcon(Utils.MOD_ID + ":" + "FlameBody");
        }
        if(itemID == ItemHelper.FlameLegs.itemID) {
            this.itemIcon = par1IconRegister.registerIcon(Utils.MOD_ID + ":" + "FlameLegs");
        }
        if(itemID == ItemHelper.FlameBoots.itemID) {
            this.itemIcon = par1IconRegister.registerIcon(Utils.MOD_ID + ":" + "FlameBoots");
        }	   
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
       // for (String desc : description) {
        //    par3List.add(desc);
        //}
    }
}