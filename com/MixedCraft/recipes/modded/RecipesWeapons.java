package com.MixedCraft.recipes.modded;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.MixedCraft.recipes.CraftingBigManager;

public class RecipesWeapons
{
    private String[][] recipePatterns = new String[][] {{"X", "X", "#"}};
    private Object[][] recipeItems;

    public RecipesWeapons()
    {
        this.recipeItems = new Object[][] {{Blocks.planks, Blocks.cobblestone, Items.iron_ingot, Items.diamond, Items.gold_ingot}, {Items.wooden_sword, Items.stone_sword, Items.iron_sword, Items.diamond_sword, Items.golden_sword}};
    }

    /**
     * Adds the weapon recipes to the CraftingManager.
     */
    public void addRecipes(CraftingBigManager par1CraftingManager)
    {
        for (int i = 0; i < this.recipeItems[0].length; ++i)
        {
            Object object = this.recipeItems[0][i];

            for (int j = 0; j < this.recipeItems.length - 1; ++j)
            {
                Item item = (Item)this.recipeItems[j + 1][i];
                par1CraftingManager.addShapedRecipe(new ItemStack(item), new Object[] {this.recipePatterns[j], '#', Items.stick, 'X', object});
            }
        }

        par1CraftingManager.addShapedRecipe(new ItemStack(Items.bow, 1), new Object[] {" #X", "# X", " #X", 'X', Items.string, '#', Items.stick});
        par1CraftingManager.addShapedRecipe(new ItemStack(Items.arrow, 4), new Object[] {"X", "#", "Y", 'Y', Items.feather, 'X', Items.flint, '#', Items.stick});
    }
}
