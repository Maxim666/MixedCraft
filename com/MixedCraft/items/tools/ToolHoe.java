package com.MixedCraft.items.tools;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import com.MixedCraft.MixedCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ToolHoe extends HoeBase {
    private final ToolMaterial field_40439_b;

	public ToolHoe(ToolMaterial par3enum, String par2) {
		super(par3enum);
        registerItemTexture(par2);
        setUnlocalizedName(par2);
        setCreativeTab(MixedCraft.ToolTab);
        this.field_40439_b = par3enum;

	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add(EnumChatFormatting.GREEN + "Efficiency: " + this.field_40439_b.getEfficiencyOnProperMaterial());
        if (par1ItemStack.getMaxDamage() != -1)
        {
            par3List.add(par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() + " Uses");
        }
    }

}
