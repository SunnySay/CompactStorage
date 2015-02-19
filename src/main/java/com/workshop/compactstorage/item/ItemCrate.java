package com.workshop.compactstorage.item;

import com.workshop.compactstorage.essential.CompactStorage;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Toby on 19/02/2015.
 */
public class ItemCrate extends Item
{
    public ItemCrate()
    {
        super();
        setUnlocalizedName("crateItem");
        setCreativeTab(CompactStorage.tabCS);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if(!world.isRemote && stack.hasTagCompound() && stack.getTagCompound().hasKey("Item") && stack.getTagCompound().getTag("Item") instanceof NBTTagCompound)
        {
            ItemStack containing = ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("Item"));
            world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, containing));
            world.playAuxSFX(1012, (int) player.posX, (int) player.posY, (int) player.posZ, 0);
        }

        return stack;
    }
}
