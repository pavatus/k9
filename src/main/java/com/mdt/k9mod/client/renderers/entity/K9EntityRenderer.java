//package com.mdt.k9mod.client.renderers.entity;
//
//import com.mdt.k9mod.K9Mod;
//import com.mdt.k9mod.client.models.entities.K9EntityModel;
//import com.mdt.k9mod.client.renderers.layers.K9EmissionLayer;
//import com.mdt.k9mod.common.entities.K9Entity;
//import com.mojang.blaze3d.matrix.MatrixStack;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.entity.EntityRendererManager;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
//import net.minecraft.entity.passive.WolfEntity;
//import net.minecraft.item.DyeColor;
//import net.minecraft.util.ResourceLocation;
//
//
//public class K9EntityRenderer extends MobRenderer<K9Entity, K9EntityModel<K9Entity>> {
//
//    public static final ResourceLocation RED_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9.png");
//    public static final ResourceLocation WHITE_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_white.png");
//    public static final ResourceLocation ORANGE_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_orange.png");
//    public static final ResourceLocation PINK_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_pink.png");
//    public static final ResourceLocation YELLOW_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_yellow.png");
//    public static final ResourceLocation LIME_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_lime.png");
//    public static final ResourceLocation LIGHT_BLUE_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_light_blue.png");
//    public static final ResourceLocation CYAN_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_cyan.png");
//    public static final ResourceLocation BLUE_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_blue.png");
//    public static final ResourceLocation MAGENTA_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_magenta.png");
//    public static final ResourceLocation PURPLE_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_purple.png");
//    public static final ResourceLocation BROWN_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_brown.png");
//    public static final ResourceLocation GRAY_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_gray.png");
//    public static final ResourceLocation LIGHT_GRAY_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_light_gray.png");
//    public static final ResourceLocation BLACK_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_black.png");
//    public static final ResourceLocation SCARF_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_scarf.png");
//
//    public ResourceLocation texture;
//
//    public K9EntityRenderer(EntityRendererManager rendererManagerIn) {
//        super(rendererManagerIn, new K9EntityModel<>(), 0.35f);
//        this.texture = RED_TEXTURE;
//        this.addLayer(new K9EmissionLayer(this));
//    }
//
//    @Override
//    public void render(K9Entity model, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
//        if(model.getCollarColor() == DyeColor.RED) {
//            this.texture = RED_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.WHITE) {
//            this.texture = WHITE_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.ORANGE) {
//            this.texture = ORANGE_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.PINK) {
//            this.texture = PINK_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.YELLOW) {
//            this.texture = YELLOW_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.LIME) {
//            this.texture = LIME_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.LIGHT_BLUE) {
//            this.texture = LIGHT_BLUE_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.CYAN) {
//            this.texture = CYAN_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.BLUE) {
//            this.texture = BLUE_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.MAGENTA) {
//            this.texture = MAGENTA_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.PURPLE) {
//            this.texture = PURPLE_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.BROWN) {
//            this.texture = BROWN_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.GRAY) {
//            this.texture = GRAY_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.LIGHT_GRAY) {
//            this.texture = LIGHT_GRAY_TEXTURE;
//        } else if(model.getCollarColor() == DyeColor.BLACK) {
//            this.texture = BLACK_TEXTURE;
//        }
//        if (model.getEntity().hasCustomName() && "Tom".equals(model.getEntity().getName().getContents())) {
//            this.texture = SCARF_TEXTURE;
//        }
//        super.render(model, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(K9Entity p_110775_1_) {
//        return this.texture;
//    }
//}