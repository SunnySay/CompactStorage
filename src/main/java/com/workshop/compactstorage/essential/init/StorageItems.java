package com.workshop.compactstorage.essential.init;

import com.workshop.compactstorage.item.ItemBackpack;
import com.workshop.compactstorage.item.ItemCrate;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Toby on 06/11/2014.
 */
public class StorageItems
{
    public static Item backpack;
    public static Item crate;

    public static void init()
    {
        backpack = new ItemBackpack();
        GameRegistry.registerItem(backpack, "backpack");

        crate = new ItemCrate();
        GameRegistry.registerItem(crate, "crateItem");
    }
}
