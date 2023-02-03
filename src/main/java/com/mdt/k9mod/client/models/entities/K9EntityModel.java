package com.mdt.k9mod.client.models.entities;// Made with Blockbench 4.6.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mdt.k9mod.common.entities.K9Entity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class K9EntityModel extends EntityModel<K9Entity> {
	public final ModelRenderer dog;
	public final ModelRenderer head;
	public final ModelRenderer cube_r1;
	public final ModelRenderer cube_r2;
	public final ModelRenderer right_ear;
	public final ModelRenderer cube_r3;
	public final ModelRenderer cube_r4;
	public final ModelRenderer cube_r5;
	public final ModelRenderer left_ear;
	public final ModelRenderer cube_r6;
	public final ModelRenderer cube_r7;
	public final ModelRenderer cube_r8;
	public final ModelRenderer tongue;
	public final ModelRenderer cube_r9;
	public final ModelRenderer cube_r10;
	public final ModelRenderer body;
	public final ModelRenderer cube_r11;
	public final ModelRenderer cube_r12;
	public final ModelRenderer cube_r13;
	public final ModelRenderer cube_r14;
	public final ModelRenderer panels;
	public final ModelRenderer cube_r15;
	public final ModelRenderer cube_r16;
	public final ModelRenderer collar;
	public final ModelRenderer cube_r17;
	public final ModelRenderer tail;
	public final ModelRenderer cube_r18;

	public K9EntityModel() {
		texWidth = 128;
		texHeight = 128;

		dog = new ModelRenderer(this);
		dog.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(dog, 0.0F, -1.5708F, 0.0F);


		head = new ModelRenderer(this);
		head.setPos(-13.2793F, -14.1478F, 0.0F);
		dog.addChild(head);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(8.5F, 15.0F, -0.5F);
		head.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 1.309F);
		cube_r1.texOffs(67, 48).addBox(-20.0F, 12.25F, -2.0F, 4.0F, 0.0F, 5.0F, 0.01F, false);
		cube_r1.texOffs(28, 30).addBox(-24.0F, 3.5F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r1.texOffs(31, 55).addBox(-24.0F, 3.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r1.texOffs(9, 9).addBox(-23.075F, 3.0F, 1.5F, 0.0F, 2.0F, 2.0F, 0.01F, false);
		cube_r1.texOffs(0, 17).addBox(-23.075F, 3.0F, -2.5F, 0.0F, 2.0F, 2.0F, 0.01F, false);
		cube_r1.texOffs(46, 66).addBox(-23.0F, 2.0F, -2.5F, 3.0F, 4.0F, 6.0F, 0.0F, false);
		cube_r1.texOffs(52, 49).addBox(-20.0F, 2.0F, -2.5F, 4.0F, 10.0F, 6.0F, -0.25F, false);
		cube_r1.texOffs(31, 55).addBox(-20.0F, 2.0F, -2.5F, 4.0F, 10.0F, 6.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-8.5465F, 17.5084F, -0.5F);
		head.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 2.0508F);
		cube_r2.texOffs(65, 66).addBox(-23.0F, 4.0F, -2.5F, 4.0F, 2.0F, 6.0F, 0.01F, false);

		right_ear = new ModelRenderer(this);
		right_ear.setPos(-1.5591F, -7.0925F, -1.9805F);
		head.addChild(right_ear);


		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(8.975F, 22.45F, 4.125F);
		right_ear.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.48F, 0.0F, 1.309F);
		cube_r3.texOffs(53, 12).addBox(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, 0.01F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(10.3091F, 22.0925F, 1.4805F);
		right_ear.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 1.309F);
		cube_r4.texOffs(55, 25).addBox(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, 0.01F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(10.313F, 22.0915F, -1.503F);
		right_ear.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.48F, 0.0F, 1.309F);
		cube_r5.texOffs(28, 28).addBox(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, 0.01F, false);

		left_ear = new ModelRenderer(this);
		left_ear.setPos(-1.5592F, -7.0925F, 2.0195F);
		head.addChild(left_ear);


		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(10.3131F, 22.0914F, 1.464F);
		left_ear.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.48F, 0.0F, 1.309F);
		cube_r6.texOffs(0, 22).addBox(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, 0.01F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(10.3092F, 22.0925F, -1.5195F);
		left_ear.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 1.309F);
		cube_r7.texOffs(0, 12).addBox(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, 0.01F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(8.975F, 22.45F, -4.164F);
		left_ear.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.48F, 0.0F, 1.309F);
		cube_r8.texOffs(33, 40).addBox(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, 0.01F, false);

		tongue = new ModelRenderer(this);
		tongue.setPos(-7.5F, 1.75F, 0.0F);
		head.addChild(tongue);


		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(16.0F, 13.25F, -0.5F);
		tongue.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 1.309F);
		cube_r9.texOffs(15, 28).addBox(-17.0F, 12.0F, -0.5F, 0.0F, 1.0F, 2.0F, 0.01F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(19.8418F, 0.1217F, -0.5F);
		tongue.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.6109F);
		cube_r10.texOffs(9, 0).addBox(-17.0F, 12.0F, -0.5F, 0.0F, 1.0F, 2.0F, 0.01F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -8.0F, 0.0F);
		dog.addChild(body);
		body.texOffs(0, 0).addBox(-9.5F, 6.0F, -7.0F, 19.0F, 2.0F, 14.0F, 0.0F, false);
		body.texOffs(0, 17).addBox(-9.5F, -6.0F, -4.0F, 19.0F, 2.0F, 8.0F, 0.01F, false);
		body.texOffs(0, 0).addBox(-10.5F, -5.0F, -2.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
		body.texOffs(0, 28).addBox(-9.475F, -6.0F, -7.0F, 0.0F, 12.0F, 14.0F, 0.001F, false);
		body.texOffs(29, 28).addBox(9.475F, -6.0F, -7.0F, 0.0F, 12.0F, 14.0F, 0.001F, false);
		body.texOffs(73, 54).addBox(8.5F, -4.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(0.5F, 8.0F, 0.0F);
		body.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.3054F, 0.0F, 0.0F);
		cube_r11.texOffs(15, 69).addBox(-10.0F, -7.0F, -8.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		cube_r11.texOffs(34, 28).addBox(7.0F, -7.0F, -8.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		cube_r11.texOffs(44, 28).addBox(-10.0F, -12.0F, -8.0F, 19.0F, 5.0F, 2.0F, 0.0F, false);
		cube_r11.texOffs(0, 55).addBox(-8.0F, -7.0F, -7.0F, 15.0F, 6.0F, 0.0F, 0.01F, false);
		cube_r11.texOffs(44, 36).addBox(-10.0F, -1.0F, -8.0F, 19.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(-0.5F, 8.0F, 0.0F);
		body.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.3054F, 0.0F, 0.0F);
		cube_r12.texOffs(24, 70).addBox(8.0F, -7.0F, 6.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		cube_r12.texOffs(33, 72).addBox(-9.0F, -7.0F, 6.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		cube_r12.texOffs(0, 62).addBox(-7.0F, -7.0F, 7.0F, 15.0F, 6.0F, 0.0F, 0.01F, false);
		cube_r12.texOffs(53, 0).addBox(-9.0F, -1.0F, 6.0F, 19.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r12.texOffs(47, 17).addBox(-9.0F, -12.0F, 6.0F, 19.0F, 5.0F, 2.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(-4.7793F, 8.8522F, 0.0F);
		body.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, 0.829F);
		cube_r13.texOffs(0, 28).addBox(-17.0F, -5.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.01F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(0.5F, 8.0F, 0.0F);
		body.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, 0.5672F);
		cube_r14.texOffs(0, 35).addBox(-17.0F, -5.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		panels = new ModelRenderer(this);
		panels.setPos(0.0F, 8.0F, 0.0F);
		body.addChild(panels);


		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(0.5F, 0.0F, 0.0F);
		panels.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.3054F, 0.0F, 0.0F);
		cube_r15.texOffs(58, 41).addBox(-8.0F, -7.0F, 7.5F, 15.0F, 6.0F, 0.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(0.5F, 0.0F, 0.0F);
		panels.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.3054F, 0.0F, 0.0F);
		cube_r16.texOffs(53, 5).addBox(-8.0F, -7.0F, -7.5F, 15.0F, 6.0F, 0.0F, 0.0F, false);

		collar = new ModelRenderer(this);
		collar.setPos(0.5F, 8.0F, 0.0F);
		body.addChild(collar);


		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(0.0F, 0.0F, 0.0F);
		collar.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, 0.5672F);
		cube_r17.texOffs(0, 69).addBox(-17.0F, -5.0F, -1.5F, 4.0F, 3.0F, 3.0F, 0.51F, false);

		tail = new ModelRenderer(this);
		tail.setPos(9.5F, -10.5F, 0.0F);
		dog.addChild(tail);


		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(-10.0F, 10.5F, 0.0F);
		tail.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, -0.5672F);
		cube_r18.texOffs(34, 37).addBox(14.0F, -4.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.01F, false);
		cube_r18.texOffs(15, 39).addBox(17.0F, -3.5F, -0.5F, 8.0F, 0.0F, 1.0F, 0.01F, false);
		cube_r18.texOffs(0, 9).addBox(25.0F, -3.5F, -1.0F, 2.0F, 0.0F, 2.0F, 0.01F, false);
	}

	@Override
	public void setupAnim(K9Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		this.head.xRot = p_225597_6_ * ((float) Math.PI / 300f);
		this.head.yRot = p_225597_5_ * ((float) Math.PI / 300f);
	}


	public void prepareMobModel(K9Entity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
		if (p_212843_1_.isAngry()) {
			this.tail.yRot = 0.0F;

			this.left_ear.yRot = ++this.left_ear.yRot / 8;
			this.right_ear.yRot = --this.right_ear.yRot / 8;
		} else {
			this.tail.yRot = MathHelper.cos(p_212843_2_ * 0.6662F) * 1.4F * p_212843_3_;
			this.left_ear.yRot = 0.0F;
			this.right_ear.yRot = 0.0F;
		}
		if (p_212843_1_.isInSittingPose()) {
			this.body.setPos(0.0F, 25.0F, 0.0F);
			this.body.xRot = ((float) Math.PI * 180F);
			this.head.setPos(-13.2793F, 18.8522F, 0.0F);
		} else {
			this.body.setPos(0.0F, 24F, 0.0F);
			this.body.xRot = ((float) Math.PI * 180F);
			this.head.setPos(-13.2793F, 17.8522F, 0.0F);
		}
		if (p_212843_1_.getHealth() < p_212843_1_.getMaxHealth()) {
			this.tongue.visible = true;
		} else {
			this.tongue.visible = false;
		}
		this.tail.y = 21.5F;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		matrixStack.pushPose();
		matrixStack.scale(0.8F, 0.8F, 0.8F);
		matrixStack.translate(0, -1.6, 0);
		dog.render(matrixStack, buffer, packedLight, packedOverlay);
		matrixStack.popPose();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}