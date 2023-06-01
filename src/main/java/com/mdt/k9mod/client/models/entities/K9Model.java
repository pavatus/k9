package com.mdt.k9mod.client.models.entities;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.common.entities.K9Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class K9Model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(K9Mod.MOD_ID, "k9"), "main");
	private final ModelPart dog;

	public K9Model(ModelPart root) {
		this.dog = root.getChild("dog");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dog = partdefinition.addOrReplaceChild("dog", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition head = dog.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-13.2793F, -14.1478F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(67, 48).addBox(-20.0F, 12.25F, -2.0F, 4.0F, 0.0F, 5.0F, new CubeDeformation(0.01F))
		.texOffs(28, 30).addBox(-24.0F, 3.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(31, 55).addBox(-24.0F, 3.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(9, 9).addBox(-23.075F, 3.0F, 1.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(0, 17).addBox(-23.075F, 3.0F, -2.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(46, 66).addBox(-23.0F, 2.0F, -2.5F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(52, 49).addBox(-20.0F, 2.0F, -2.5F, 4.0F, 10.0F, 6.0F, new CubeDeformation(-0.25F))
		.texOffs(31, 55).addBox(-20.0F, 2.0F, -2.5F, 4.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, 15.0F, -0.5F, 0.0F, 0.0F, 1.309F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(65, 66).addBox(-23.0F, 4.0F, -2.5F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-8.5465F, 17.5084F, -0.5F, 0.0F, 0.0F, 2.0508F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(-1.5591F, -7.0925F, -1.9805F));

		PartDefinition cube_r3 = right_ear.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(53, 12).addBox(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(8.975F, 22.45F, 4.125F, -0.48F, 0.0F, 1.309F));

		PartDefinition cube_r4 = right_ear.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(55, 25).addBox(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(10.3091F, 22.0925F, 1.4805F, 0.0F, 0.0F, 1.309F));

		PartDefinition cube_r5 = right_ear.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(28, 28).addBox(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(10.313F, 22.0915F, -1.503F, 0.48F, 0.0F, 1.309F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-1.5592F, -7.0925F, 2.0195F));

		PartDefinition cube_r6 = left_ear.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 22).addBox(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(10.3131F, 22.0914F, 1.464F, -0.48F, 0.0F, 1.309F));

		PartDefinition cube_r7 = left_ear.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 12).addBox(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(10.3092F, 22.0925F, -1.5195F, 0.0F, 0.0F, 1.309F));

		PartDefinition cube_r8 = left_ear.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(33, 40).addBox(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(8.975F, 22.45F, -4.164F, 0.48F, 0.0F, 1.309F));

		PartDefinition tongue = head.addOrReplaceChild("tongue", CubeListBuilder.create(), PartPose.offset(-7.5F, 1.75F, 0.0F));

		PartDefinition cube_r9 = tongue.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(15, 28).addBox(-17.0F, 12.0F, -0.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(16.0F, 13.25F, -0.5F, 0.0F, 0.0F, 1.309F));

		PartDefinition cube_r10 = tongue.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(9, 0).addBox(-17.0F, 12.0F, -0.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(19.8418F, 0.1217F, -0.5F, 0.0F, 0.0F, 0.6109F));

		PartDefinition body = dog.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, 6.0F, -7.0F, 19.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-9.5F, -6.0F, -4.0F, 19.0F, 2.0F, 8.0F, new CubeDeformation(0.01F))
		.texOffs(0, 0).addBox(-10.5F, -5.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-9.475F, -6.0F, -7.0F, 0.0F, 12.0F, 14.0F, new CubeDeformation(0.001F))
		.texOffs(29, 28).addBox(9.475F, -6.0F, -7.0F, 0.0F, 12.0F, 14.0F, new CubeDeformation(0.001F))
		.texOffs(73, 54).addBox(8.5F, -4.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(15, 69).addBox(-10.0F, -7.0F, -8.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 28).addBox(7.0F, -7.0F, -8.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 28).addBox(-10.0F, -12.0F, -8.0F, 19.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 55).addBox(-8.0F, -7.0F, -7.0F, 15.0F, 6.0F, 0.0F, new CubeDeformation(0.01F))
		.texOffs(44, 36).addBox(-10.0F, -1.0F, -8.0F, 19.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 8.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(24, 70).addBox(8.0F, -7.0F, 6.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(33, 72).addBox(-9.0F, -7.0F, 6.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 62).addBox(-7.0F, -7.0F, 7.0F, 15.0F, 6.0F, 0.0F, new CubeDeformation(0.01F))
		.texOffs(53, 0).addBox(-9.0F, -1.0F, 6.0F, 19.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(47, 17).addBox(-9.0F, -12.0F, 6.0F, 19.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 8.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 28).addBox(-17.0F, -5.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-4.7793F, 8.8522F, 0.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 35).addBox(-17.0F, -5.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 8.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition panels = body.addOrReplaceChild("panels", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition cube_r15 = panels.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(58, 41).addBox(-8.0F, -7.0F, 7.5F, 15.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 6.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r16 = panels.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(53, 5).addBox(-8.0F, -7.0F, -7.5F, 15.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 6.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition collar = body.addOrReplaceChild("collar", CubeListBuilder.create(), PartPose.offset(0.5F, 8.0F, 0.0F));

		PartDefinition cube_r17 = collar.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 69).addBox(-17.0F, -5.0F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.51F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition tail = dog.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(9.5F, -10.5F, 0.0F));

		PartDefinition cube_r18 = tail.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(34, 37).addBox(14.0F, -4.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 39).addBox(17.0F, -3.5F, -0.5F, 8.0F, 0.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(0, 9).addBox(25.0F, -3.5F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-10.0F, 10.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.dog.getChild("head").xRot = headPitch * ((float) Math.PI / 300f);
		this.dog.getChild("head").yRot = netHeadYaw * ((float) Math.PI / 300f);
	}

	@Override
	public void prepareMobModel(T entity, float p_102615_, float p_102616_, float p_102617_) {
		if (!(entity instanceof K9Entity k9)) return;

		// I cant be assed making the .getChild() into variables so just cope or do it urself
		if (k9.isAngry()) {
			this.dog.getChild("tail").yRot = 0.0F;

			this.dog.getChild("head").getChild("left_ear").yRot = ++this.dog.getChild("head").getChild("left_ear").yRot / 8;
			this.dog.getChild("head").getChild("right_ear").yRot = --this.dog.getChild("head").getChild("right_ear").yRot / 8;
		} else {
			this.dog.getChild("tail").yRot = (float) (Math.cos(p_102615_ * 0.6662F) * 1.4F * p_102616_);
			this.dog.getChild("head").getChild("left_ear").yRot = 0.0F;
			this.dog.getChild("head").getChild("right_ear").yRot = 0.0F;
		}
		if (k9.isInSittingPose()) {
			this.dog.getChild("body").setPos(0.0F, 25.0F, 0.0F);
			this.dog.getChild("body").xRot = ((float) Math.PI * 180F);
			this.dog.getChild("head").setPos(-13.2793F, 18.8522F, 0.0F);
		} else {
			this.dog.getChild("body").setPos(0.0F, 24F, 0.0F);
			this.dog.getChild("body").xRot = ((float) Math.PI * 180F);
			this.dog.getChild("head").setPos(-13.2793F, 17.8522F, 0.0F);
		}
		if (k9.getHealth() < k9.getMaxHealth()) {
			this.dog.getChild("head").getChild("tongue").visible = true;
		} else {
			this.dog.getChild("head").getChild("tongue").visible = false;
		}
		this.dog.getChild("body").getChild("collar").visible = k9.isTame();
		this.dog.getChild("tail").y = 21.5F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		poseStack.scale(0.8F, 0.8F, 0.8F);
		poseStack.translate(0, -1.6, 0);
		dog.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		poseStack.popPose();
	}
}