package mdt.k9mod.client.models;

import com.google.common.collect.ImmutableList;
import mdt.k9mod.entity.K9Entity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.TintableAnimalModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Vector3f;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class K9EntityModel<T extends K9Entity> extends TintableAnimalModel<T> {
	public final ModelPart dog;
	public ModelPart head;
	public ModelPart right_ear;
	public ModelPart left_ear;
	public ModelPart tongue;
	public ModelPart body;
	public ModelPart panels;
	public ModelPart collar;
	public ModelPart tail;
	public K9EntityModel(ModelPart root) {
		this.dog = root.getChild("dog");
		this.head = this.dog.getChild("head");
		this.body = this.dog.getChild("body");
		this.tail = this.dog.getChild("tail");
		this.right_ear = this.head.getChild("right_ear");
		this.left_ear = this.head.getChild("left_ear");
		this.tongue = this.head.getChild("tongue");
		this.panels = this.body.getChild("panels");
		this.collar = this.body.getChild("collar");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData dog = modelPartData.addChild("dog", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData head = dog.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(-13.2793F, -14.1478F, 0.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(67, 48).cuboid(-20.0F, 12.25F, -2.0F, 4.0F, 0.0F, 5.0F, new Dilation(0.01F))
		.uv(28, 30).cuboid(-24.0F, 3.5F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(31, 55).cuboid(-24.0F, 3.5F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(9, 9).cuboid(-23.075F, 3.0F, 1.5F, 0.0F, 2.0F, 2.0F, new Dilation(0.01F))
		.uv(0, 17).cuboid(-23.075F, 3.0F, -2.5F, 0.0F, 2.0F, 2.0F, new Dilation(0.01F))
		.uv(46, 66).cuboid(-23.0F, 2.0F, -2.5F, 3.0F, 4.0F, 6.0F, new Dilation(0.0F))
		.uv(52, 49).cuboid(-20.0F, 2.0F, -2.5F, 4.0F, 10.0F, 6.0F, new Dilation(-0.25F))
		.uv(31, 55).cuboid(-20.0F, 2.0F, -2.5F, 4.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(8.5F, 15.0F, -0.5F, 0.0F, 0.0F, 1.309F));

		ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(65, 66).cuboid(-23.0F, 4.0F, -2.5F, 4.0F, 2.0F, 6.0F, new Dilation(0.01F)), ModelTransform.of(-8.5465F, 17.5084F, -0.5F, 0.0F, 0.0F, 2.0508F));

		ModelPartData right_ear = head.addChild("right_ear", ModelPartBuilder.create(), ModelTransform.pivot(-1.5591F, -7.0925F, -1.9805F));

		ModelPartData cube_r3 = right_ear.addChild("cube_r3", ModelPartBuilder.create().uv(53, 12).cuboid(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.01F)), ModelTransform.of(8.975F, 22.45F, 4.125F, -0.48F, 0.0F, 1.309F));

		ModelPartData cube_r4 = right_ear.addChild("cube_r4", ModelPartBuilder.create().uv(55, 25).cuboid(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.01F)), ModelTransform.of(10.3091F, 22.0925F, 1.4805F, 0.0F, 0.0F, 1.309F));

		ModelPartData cube_r5 = right_ear.addChild("cube_r5", ModelPartBuilder.create().uv(28, 28).cuboid(-26.0F, 4.0F, -2.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.01F)), ModelTransform.of(10.313F, 22.0915F, -1.503F, 0.48F, 0.0F, 1.309F));

		ModelPartData left_ear = head.addChild("left_ear", ModelPartBuilder.create(), ModelTransform.pivot(-1.5592F, -7.0925F, 2.0195F));

		ModelPartData cube_r6 = left_ear.addChild("cube_r6", ModelPartBuilder.create().uv(0, 22).cuboid(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.01F)), ModelTransform.of(10.3131F, 22.0914F, 1.464F, -0.48F, 0.0F, 1.309F));

		ModelPartData cube_r7 = left_ear.addChild("cube_r7", ModelPartBuilder.create().uv(0, 12).cuboid(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.01F)), ModelTransform.of(10.3092F, 22.0925F, -1.5195F, 0.0F, 0.0F, 1.309F));

		ModelPartData cube_r8 = left_ear.addChild("cube_r8", ModelPartBuilder.create().uv(33, 40).cuboid(-26.0F, 4.0F, 1.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.01F)), ModelTransform.of(8.975F, 22.45F, -4.164F, 0.48F, 0.0F, 1.309F));

		ModelPartData tongue = head.addChild("tongue", ModelPartBuilder.create(), ModelTransform.pivot(-7.5F, 1.75F, 0.0F));

		ModelPartData cube_r9 = tongue.addChild("cube_r9", ModelPartBuilder.create().uv(15, 28).cuboid(-17.0F, 12.0F, -0.5F, 0.0F, 1.0F, 2.0F, new Dilation(0.01F)), ModelTransform.of(16.0F, 13.25F, -0.5F, 0.0F, 0.0F, 1.309F));

		ModelPartData cube_r10 = tongue.addChild("cube_r10", ModelPartBuilder.create().uv(9, 0).cuboid(-17.0F, 12.0F, -0.5F, 0.0F, 1.0F, 2.0F, new Dilation(0.01F)), ModelTransform.of(19.8418F, 0.1217F, -0.5F, 0.0F, 0.0F, 0.6109F));

		ModelPartData body = dog.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-9.5F, 6.0F, -7.0F, 19.0F, 2.0F, 14.0F, new Dilation(0.0F))
		.uv(0, 17).cuboid(-9.5F, -6.0F, -4.0F, 19.0F, 2.0F, 8.0F, new Dilation(0.01F))
		.uv(0, 0).cuboid(-10.5F, -5.0F, -2.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-9.475F, -6.0F, -7.0F, 0.0F, 12.0F, 14.0F, new Dilation(0.001F))
		.uv(29, 28).cuboid(9.475F, -6.0F, -7.0F, 0.0F, 12.0F, 14.0F, new Dilation(0.001F))
		.uv(73, 54).cuboid(8.5F, -4.5F, -1.5F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

		ModelPartData cube_r11 = body.addChild("cube_r11", ModelPartBuilder.create().uv(15, 69).cuboid(-10.0F, -7.0F, -8.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(34, 28).cuboid(7.0F, -7.0F, -8.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(44, 28).cuboid(-10.0F, -12.0F, -8.0F, 19.0F, 5.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 55).cuboid(-8.0F, -7.0F, -7.0F, 15.0F, 6.0F, 0.0F, new Dilation(0.01F))
		.uv(44, 36).cuboid(-10.0F, -1.0F, -8.0F, 19.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 8.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r12 = body.addChild("cube_r12", ModelPartBuilder.create().uv(24, 70).cuboid(8.0F, -7.0F, 6.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(33, 72).cuboid(-9.0F, -7.0F, 6.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 62).cuboid(-7.0F, -7.0F, 7.0F, 15.0F, 6.0F, 0.0F, new Dilation(0.01F))
		.uv(53, 0).cuboid(-9.0F, -1.0F, 6.0F, 19.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(47, 17).cuboid(-9.0F, -12.0F, 6.0F, 19.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 8.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r13 = body.addChild("cube_r13", ModelPartBuilder.create().uv(0, 28).cuboid(-17.0F, -5.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.01F)), ModelTransform.of(-4.7793F, 8.8522F, 0.0F, 0.0F, 0.0F, 0.829F));

		ModelPartData cube_r14 = body.addChild("cube_r14", ModelPartBuilder.create().uv(0, 35).cuboid(-17.0F, -5.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 8.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		ModelPartData panels = body.addChild("panels", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

		ModelPartData cube_r15 = panels.addChild("cube_r15", ModelPartBuilder.create().uv(58, 41).cuboid(-8.0F, -7.0F, 7.5F, 15.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r16 = panels.addChild("cube_r16", ModelPartBuilder.create().uv(53, 5).cuboid(-8.0F, -7.0F, -7.5F, 15.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData collar = body.addChild("collar", ModelPartBuilder.create(), ModelTransform.pivot(0.5F, 8.0F, 0.0F));

		ModelPartData cube_r17 = collar.addChild("cube_r17", ModelPartBuilder.create().uv(0, 69).cuboid(-17.0F, -5.0F, -1.5F, 4.0F, 3.0F, 3.0F, new Dilation(0.51F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		ModelPartData tail = dog.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(9.5F, -10.5F, 0.0F));

		ModelPartData cube_r18 = tail.addChild("cube_r18", ModelPartBuilder.create().uv(34, 37).cuboid(14.0F, -4.0F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.01F))
		.uv(15, 39).cuboid(17.0F, -3.5F, -0.5F, 8.0F, 0.0F, 1.0F, new Dilation(0.01F))
		.uv(0, 9).cuboid(25.0F, -3.5F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.01F)), ModelTransform.of(-10.0F, 10.5F, 0.0F, 0.0F, 0.0F, -0.5672F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.pitch = headPitch * ((float) Math.PI / 300f);
		this.head.yaw = netHeadYaw * ((float) Math.PI / 300f);
	}


	@Override
	public void animateModel(T k9Entity, float f, float g, float h) {
		if (k9Entity.isUniversallyAngry(k9Entity.getWorld())) {
			this.tail.yaw = 0.0F;

			this.left_ear.yaw = ++this.left_ear.yaw / 8;
			this.right_ear.yaw = --this.right_ear.yaw / 8;
		} else {
			this.tail.yaw = MathHelper.cos(f * 0.6662F) * 1.4F * g;
			this.left_ear.yaw = 0.0F;
			this.right_ear.yaw = 0.0F;
		}
		if (k9Entity.isInSittingPose()) {
			this.body.setPivot(0.0F, 25.0F, 0.0F);
			this.tail.pivotY = 22.5F;
			this.body.pitch = ((float) Math.PI * 180F);
			this.head.setPivot(-13.2793F, 18.8522F, 0.0F);
		} else {
			this.body.setPivot(0.0F, 24F, 0.0F);
			this.tail.pivotY = 21.5F;
			this.body.pitch = ((float) Math.PI * 180F);
			this.head.setPivot(-13.2793F, 17.8522F, 0.0F);
		}
		if (k9Entity.getHealth() < 10.0F) {
			this.tongue.visible = true;
		} else {
			this.tongue.visible = false;
		}
		if(!k9Entity.isTamed()) {
			this.collar.visible = false;
		} else {
			this.collar.visible = true;
		}
		if(k9Entity.isSongPlaying()) {
			this.dog.yaw = this.dog.yaw + MathHelper.sin(f * 0.6662F) * 0.2F;
		} else {
			this.dog.yaw = this.dog.getDefaultTransform().yaw;
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.push();
		matrices.scale(0.8F, 0.8F, 0.8F);
		matrices.translate(0, -1.6, 0);
		dog.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		matrices.pop();
	}

	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.tail);
	}
}