package com.workshop.compactstorage.client.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by Toby on 19/02/2015.
 */
public class RenderSpecials
{
    public ModelChest model;

    public RenderSpecials()
    {
        this.model = new ModelChest();
    }

    @SubscribeEvent
    public void render(RenderPlayerEvent.Specials.Post event)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("compactstorage", "textures/models/chest.png"));
        int color = 0xFFF;

        GL11.glPushMatrix();

        //GL11.glTranslatef((float) event.entityPlayer.posX, (float) event.entityPlayer.posY, (float) event.entityPlayer.posZ);
        GL11.glScalef(1.0F, -1.0F, -1.0F);

        GL11.glScalef(0.5f, 0.5f, 0.5f);

        GL11.glTranslatef(0.5F, 2.5F, 0.5F);
        float r = (float)(color >> 16 & 255) / 255.0F;
        float g = (float)(color >> 8 & 255) / 255.0F;
        float b = (float)(color & 255) / 255.0F;
        GL11.glColor4f(r, g, b, 0.5F);

        GL11.glRotatef(180f, 0f, 0f, 1f);
        GL11.glRotatef(180f, 0f, 1f, 0f);

        GL11.glTranslatef(-1f, 0f, 0f);

        model.chestBelow.render(0.0625f);
        model.chestLid.render(0.0625f);

        GL11.glColor4f(1f, 1f, 1f, 1f);
        model.chestKnob.render(0.0625f);

        GL11.glPopMatrix();
    }
}
