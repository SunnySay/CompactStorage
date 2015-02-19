package com.workshop.compactstorage.block;

import com.workshop.compactstorage.essential.CompactStorage;
import com.workshop.compactstorage.essential.init.StorageItems;
import com.workshop.compactstorage.tileentity.TileEntityCrate;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Toby on 19/02/2015.
 */
public class BlockCrate extends Block implements ITileEntityProvider
{
    public BlockCrate()
    {
        super(Material.wood);
        setBlockName("crate");
        setCreativeTab(CompactStorage.tabCS);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int dimension, float xx, float yy, float zz)
    {
        if(!world.isRemote)
        {
            if(player.getHeldItem() != null)
            {
                TileEntityCrate crate = (TileEntityCrate) world.getTileEntity(x, y, z);

                if(crate.stack == null)
                {
                    crate.stack = player.getHeldItem().copy();
                    breakAndDropCrate(world, x, y, z, player);

                    return true;
                }
                else
                {
                    breakAndDropCrate(world, x, y, z, player);
                    return false;
                }
            }
        }

        return false;
    }

    public void breakAndDropCrate(World world, int x, int y, int z, EntityPlayer player)
    {
        TileEntityCrate crate = (TileEntityCrate) world.getTileEntity(x, y, z);

        NBTTagCompound tag = crate.writeItemNBT();

        if(tag == null)
        {
            crate.stack = null;
            return;
        }

        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

        ItemStack stack = new ItemStack(StorageItems.crate, 1);
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setTag("Item", tag);

        EntityItem item = new EntityItem(world, x, y + 0.5f, z, stack);

        world.setBlockToAir(x, y, z);
        world.markBlockForUpdate(x, y, z);

        world.spawnEntityInWorld(item);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int dim)
    {
        return new TileEntityCrate();
    }
}
