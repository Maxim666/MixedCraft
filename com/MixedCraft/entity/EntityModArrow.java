package com.MixedCraft.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.MixedCraft.ItemHelper;
import com.MixedCraft.handler.ModReflectionHelper;

public class EntityModArrow extends EntityArrow {

    public static String[] TEXTURES = new String[] {
        "/item/arrow.png"
    };

    /** Stack to pickup on player collision **/
    ItemStack pickupStack = new ItemStack(ItemHelper.TNTArrow, 1);

    public EntityModArrow(World world, int arrowID) {
        super(world);
        setID(arrowID);
    }

    public EntityModArrow(World world, double x, double y, double z, int arrowID)  {
        super(world, x, y, z);
        setID(arrowID);
    }

    public EntityModArrow(World world, EntityLivingBase entity1, EntityLivingBase entity2, float var4, float var5, int arrowID) {
        super(world, entity1, entity2, var4, var5);
        setID(arrowID);
    }

    public EntityModArrow(World world, EntityLivingBase entity, float var3, int arrowID) {
        super(world, entity, var3);
        setID(arrowID);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(12, 0);
        dataWatcher.setObjectWatched(12);
    }

    public void setID(int id) {
        dataWatcher.updateObject(12, id);
    }

    public int getID() {
        return dataWatcher.getWatchableObjectInt(12);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer){
        if (!worldObj.isRemote && arrowShake <= 0) {
            boolean canPickup = canBePickedUp == 1 || canBePickedUp == 2 && par1EntityPlayer.capabilities.isCreativeMode;

            if (canBePickedUp == 1 && !par1EntityPlayer.inventory.addItemStackToInventory(pickupStack)) {
                ;
            }
            canPickup = false;

            if (canPickup) {
                playSound("random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                par1EntityPlayer.onItemPickup(this, 1);
                //setDead();
            }
        }
    }
}
