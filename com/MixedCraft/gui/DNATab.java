package com.MixedCraft.gui;

import com.MixedCraft.ItemHelper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DNATab extends CreativeTabs {

	public static String label;
	
	public DNATab(String Label) {
		super(Label);
		
		label = Label;
		
	}
	

	public ItemStack getIconItemStack() { 
	return new ItemStack(ItemHelper.CowDNADrive);

	}

}