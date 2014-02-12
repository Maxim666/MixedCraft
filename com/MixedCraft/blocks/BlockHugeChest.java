package com.MixedCraft.blocks;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.MixedCraft.MixedCraft;
import com.MixedCraft.blocks.tileEntity.TileEntityDNAMixer;
import com.MixedCraft.blocks.tileEntity.TileEntityHugeChest;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHugeChest extends BlockContainer
{
    private final Random random = new Random();

    public int isTrapped;
    
    public BlockHugeChest()
    {
        super(Material.wood);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
       
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        if (par1IBlockAccess.getBlock(par2, par3, par4 - 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (par1IBlockAccess.getBlock(par2, par3, par4 + 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        }
        else if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) == this)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (par1IBlockAccess.getBlock(par2 + 1, par3, par4) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        }
        else
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.unifyAdjacentChests(par1World, par2, par3, par4);
        int l = par1World.getBlock(par2, par3, par4 - 1);
        int i1 = par1World.getBlock(par2, par3, par4 + 1);
        int j1 = par1World.getBlock(par2 - 1, par3, par4);
        int k1 = par1World.getBlock(par2 + 1, par3, par4);

        if (l == this)
        {
            this.unifyAdjacentChests(par1World, par2, par3, par4 - 1);
        }

        if (i1 == this)
        {
            this.unifyAdjacentChests(par1World, par2, par3, par4 + 1);
        }

        if (j1 == this)
        {
            this.unifyAdjacentChests(par1World, par2 - 1, par3, par4);
        }

        if (k1 == this)
        {
            this.unifyAdjacentChests(par1World, par2 + 1, par3, par4);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = par1World.getBlock(par2, par3, par4 - 1);
        int i1 = par1World.getBlock(par2, par3, par4 + 1);
        int j1 = par1World.getBlock(par2 - 1, par3, par4);
        int k1 = par1World.getBlock(par2 + 1, par3, par4);
        byte b0 = 0;
        int l1 = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l1 == 0)
        {
            b0 = 2;
        }

        if (l1 == 1)
        {
            b0 = 5;
        }

        if (l1 == 2)
        {
            b0 = 3;
        }

        if (l1 == 3)
        {
            b0 = 4;
        }

        if (l != this && i1 != this && j1 != this && k1 != this)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
        }
        else
        {
            if ((l == this || i1 == this) && (b0 == 4 || b0 == 5))
            {
                if (l == this)
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4 - 1, b0, 3);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4 + 1, b0, 3);
                }

                par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
            }

            if ((j1 == this || k1 == this) && (b0 == 2 || b0 == 3))
            {
                if (j1 == this)
                {
                    par1World.setBlockMetadataWithNotify(par2 - 1, par3, par4, b0, 3);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2 + 1, par3, par4, b0, 3);
                }

                par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
            }
        }

        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityHugeChest)par1World.getTileEntity(par2, par3, par4)).setChestGuiName(par6ItemStack.getDisplayName());
        }
    }

    /**
     * Turns the adjacent chests to a double chest.
     */
    public void unifyAdjacentChests(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlock(par2, par3, par4 - 1);
            int i1 = par1World.getBlock(par2, par3, par4 + 1);
            int j1 = par1World.getBlock(par2 - 1, par3, par4);
            int k1 = par1World.getBlock(par2 + 1, par3, par4);
            boolean flag = true;
            int l1;
            int i2;
            boolean flag1;
            byte b0;
            int j2;

            if (l != this && i1 != this)
            {
                if (j1 != this && k1 != this)
                {
                    b0 = 3;

                    if (Block.func_149730_j[l] && !Block.func_149730_j[i1])
                    {
                        b0 = 3;
                    }

                    if (Block.func_149730_j[i1] && !Block.func_149730_j[l])
                    {
                        b0 = 2;
                    }

                    if (Block.func_149730_j[j1] && !Block.func_149730_j[k1])
                    {
                        b0 = 5;
                    }

                    if (Block.func_149730_j[k1] && !Block.func_149730_j[j1])
                    {
                        b0 = 4;
                    }
                }
                else
                {
                    l1 = par1World.getBlock(j1 == this ? par2 - 1 : par2 + 1, par3, par4 - 1);
                    i2 = par1World.getBlock(j1 == this ? par2 - 1 : par2 + 1, par3, par4 + 1);
                    b0 = 3;
                    flag1 = true;

                    if (j1 == this)
                    {
                        j2 = par1World.getBlockMetadata(par2 - 1, par3, par4);
                    }
                    else
                    {
                        j2 = par1World.getBlockMetadata(par2 + 1, par3, par4);
                    }

                    if (j2 == 2)
                    {
                        b0 = 2;
                    }

                    if ((Block.func_149730_j[l] || Block.func_149730_j[l1]) && !Block.func_149730_j[i1] && !Block.func_149730_j[i2])
                    {
                        b0 = 3;
                    }

                    if ((Block.func_149730_j[i1] || Block.func_149730_j[i2]) && !Block.func_149730_j[l] && !Block.func_149730_j[l1])
                    {
                        b0 = 2;
                    }
                }
            }
            else
            {
                l1 = par1World.getBlock(par2 - 1, par3, l == this ? par4 - 1 : par4 + 1);
                i2 = par1World.getBlock(par2 + 1, par3, l == this ? par4 - 1 : par4 + 1);
                b0 = 5;
                flag1 = true;

                if (l == this)
                {
                    j2 = par1World.getBlockMetadata(par2, par3, par4 - 1);
                }
                else
                {
                    j2 = par1World.getBlockMetadata(par2, par3, par4 + 1);
                }

                if (j2 == 4)
                {
                    b0 = 4;
                }

                if ((Block.func_149730_j[j1] || Block.func_149730_j[l1]) && !Block.func_149730_j[k1] && !Block.func_149730_j[i2])
                {
                    b0 = 5;
                }

                if ((Block.func_149730_j[k1] || Block.func_149730_j[i2]) && !Block.func_149730_j[j1] && !Block.func_149730_j[l1])
                {
                    b0 = 4;
                }
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        int l = 0;

        if (par1World.getBlock(par2 - 1, par3, par4) == this)
        {
            ++l;
        }

        if (par1World.getBlock(par2 + 1, par3, par4) == this)
        {
            ++l;
        }

        if (par1World.getBlock(par2, par3, par4 - 1) == this)
        {
            ++l;
        }

        if (par1World.getBlock(par2, par3, par4 + 1) == this)
        {
            ++l;
        }

        return l > 1 ? false : (this.isThereANeighborChest(par1World, par2 - 1, par3, par4) ? false : (this.isThereANeighborChest(par1World, par2 + 1, par3, par4) ? false : (this.isThereANeighborChest(par1World, par2, par3, par4 - 1) ? false : !this.isThereANeighborChest(par1World, par2, par3, par4 + 1))));
    }

    /**
     * Checks the neighbor blocks to see if there is a chest there. Args: world, x, y, z
     */
    private boolean isThereANeighborChest(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlock(par2, par3, par4) != this ? false : (par1World.getBlock(par2 - 1, par3, par4) == this ? true : (par1World.getBlock(par2 + 1, par3, par4) == this ? true : (par1World.getBlock(par2, par3, par4 - 1) == this ? true : par1World.getBlock(par2, par3, par4 + 1) == this)));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        TileEntityHugeChest TileEntityHugeChest = (TileEntityHugeChest)par1World.getTileEntity(par2, par3, par4);

        if (TileEntityHugeChest != null)
        {
            TileEntityHugeChest.updateContainingBlockInfo();
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        TileEntityHugeChest TileEntityHugeChest = (TileEntityHugeChest)par1World.getTileEntity(par2, par3, par4);

        if (TileEntityHugeChest != null)
        {
            for (int j1 = 0; j1 < TileEntityHugeChest.getSizeInventory(); ++j1)
            {
                ItemStack itemstack = TileEntityHugeChest.getStackInSlot(j1);

                if (itemstack != null)
                {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World.spawnEntityInWorld(entityitem))
                    {
                        int k1 = this.random.nextInt(21) + 10;

                        if (k1 > itemstack.stackSize)
                        {
                            k1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= k1;
                        entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            par1World.func_96440_m(par2, par3, par4, par5);
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {        
       if (par1World.isRemote)
        {
            return true;
        }
        else
        {
        	TileEntityHugeChest var10 = (TileEntityHugeChest)par1World.getTileEntity(par2, par3, par4);
        	
            if (var10 != null)
            {
            	par5EntityPlayer.openGui(MixedCraft.instance, 17, par1World, par2, par3, par4);
            }

            return true;
        }
   }

    /**
     * Gets the inventory of the chest at the specified coords, accounting for blocks or ocelots on top of the chest,
     * and double chests.
     */
    public IInventory getInventory(World par1World, int par2, int par3, int par4)
    {
        Object object = (TileEntityHugeChest)par1World.getTileEntity(par2, par3, par4);

        if (object == null)
        {
            return null;
        }
        else if (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
        {
            return null;
        }
        else
        {
            if (par1World.getBlock(par2 - 1, par3, par4) == this)
            {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityHugeChest)par1World.getTileEntity(par2 - 1, par3, par4), (IInventory)object);
            }

            if (par1World.getBlock(par2 + 1, par3, par4) == this)
            {
                object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityHugeChest)par1World.getTileEntity(par2 + 1, par3, par4));
            }

            if (par1World.getBlock(par2, par3, par4 - 1) == this)
            {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityHugeChest)par1World.getTileEntity(par2, par3, par4 - 1), (IInventory)object);
            }

            if (par1World.getBlock(par2, par3, par4 + 1) == this)
            {
                object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityHugeChest)par1World.getTileEntity(par2, par3, par4 + 1));
            }

            return (IInventory)object;
        }
    }

    public TileEntity createNewTileEntity(World par1World)
    {
        TileEntityHugeChest TileEntityHugeChest = new TileEntityHugeChest();
        return TileEntityHugeChest;
    }

    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory(this.getInventory(par1World, par2, par3, par4));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        this.blockIcon = par1IIconRegister.registerIcon("diamond_block");
    }
}
