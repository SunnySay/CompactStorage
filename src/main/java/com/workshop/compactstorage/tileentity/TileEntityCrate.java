package com.workshop.compactstorage.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Toby on 19/02/2015.
 */
public class TileEntityCrate extends TileEntity
{
    public ItemStack stack;

    public TileEntityCrate()
    {
        super();
        this.stack = null;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);

        NBTTagCompound itemTag = new NBTTagCompound();
        tag.setTag("Item", writeItemNBT());
    }

    public NBTTagCompound writeItemNBT()
    {
        if(stack != null)
        {
            NBTTagCompound tag = new NBTTagCompound();
            stack.writeToNBT(tag);

            return tag;
        }

        return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        stack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Item"));
    }
}
