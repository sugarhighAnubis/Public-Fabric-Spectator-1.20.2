package net.anubis.spectatormod.entity.client;

import net.anubis.spectatormod.entity.animation.ModAnimations;
import net.anubis.spectatormod.entity.custom.SpectatorEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class SpectatorModel<T extends SpectatorEntity> extends SinglePartEntityModel<T> {
	private final ModelPart spectator;
	private final ModelPart head;

	public SpectatorModel(ModelPart root) {
		this.spectator = root.getChild("spectator");
		this.head = spectator.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData spectator = modelPartData.addChild("spectator", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.5F, 1.0F));

		ModelPartData head = spectator.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 17.5F, -1.0F));

		ModelPartData Base = head.addChild("Base", ModelPartBuilder.create().uv(0, 14).cuboid(-3.5F, -4.0F, -3.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F))
				.uv(49, 31).cuboid(-4.5F, -4.0F, 2.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.002F))
				.uv(33, 51).cuboid(1.5F, 0.0F, 2.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.002F))
				.uv(21, 49).cuboid(-4.5F, 0.0F, 2.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.002F))
				.uv(53, 53).cuboid(1.5F, -4.0F, 2.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.002F))
				.uv(56, 21).cuboid(-2.0F, -2.5F, -4.0F, 4.0F, 4.0F, 2.0F, new Dilation(-0.002F)), ModelTransform.pivot(0.0F, -17.0F, 0.0F));

		ModelPartData BackJaw_r1 = Base.addChild("BackJaw_r1", ModelPartBuilder.create().uv(21, 14).cuboid(-2.5F, -8.9F, -13.6F, 5.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 17.0F, 0.0F, -1.1781F, 0.0F, 0.0F));

		ModelPartData Jaw = Base.addChild("Jaw", ModelPartBuilder.create().uv(21, 0).cuboid(-2.5F, 0.0F, -3.0F, 5.0F, 1.0F, 4.0F, new Dilation(-0.002F)), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

		ModelPartData Hitbox = Base.addChild("Hitbox", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -21.0F, -3.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 17.0F, 0.0F));

		ModelPartData Iris = head.addChild("Iris", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -18.5F, -4.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData LeftTopEyestalk = head.addChild("LeftTopEyestalk", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -19.5F, 4.0F));

		ModelPartData onesectionone = LeftTopEyestalk.addChild("onesectionone", ModelPartBuilder.create().uv(70, 41).cuboid(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(70, 35).cuboid(-1.5F, -1.5F, 4.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.5F));

		ModelPartData StalkTR_r1 = onesectionone.addChild("StalkTR_r1", ModelPartBuilder.create().uv(41, 53).cuboid(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.5F, 0.0F, 0.0F, 0.7854F));

		ModelPartData onesectiontwo = onesectionone.addChild("onesectiontwo", ModelPartBuilder.create().uv(16, 70).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 70).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 5.5F));

		ModelPartData StalkTR_r2 = onesectiontwo.addChild("StalkTR_r2", ModelPartBuilder.create().uv(53, 15).cuboid(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -11.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData onesectionthree = onesectiontwo.addChild("onesectionthree", ModelPartBuilder.create().uv(0, 70).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(56, 69).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r3 = onesectionthree.addChild("StalkTR_r3", ModelPartBuilder.create().uv(12, 52).cuboid(-1.0F, -1.0F, 18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -17.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData onesectionfour = onesectionthree.addChild("onesectionfour", ModelPartBuilder.create().uv(69, 48).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(69, 54).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r4 = onesectionfour.addChild("StalkTR_r4", ModelPartBuilder.create().uv(0, 52).cuboid(-1.0F, -1.0F, 24.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -23.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData onesectionfive = onesectionfour.addChild("onesectionfive", ModelPartBuilder.create().uv(68, 31).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(32, 69).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r5 = onesectionfive.addChild("StalkTR_r5", ModelPartBuilder.create().uv(51, 9).cuboid(-1.0F, -1.0F, 30.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -29.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData TREye = onesectionfive.addChild("TREye", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData onesectionsix = TREye.addChild("onesectionsix", ModelPartBuilder.create().uv(44, 0).cuboid(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(28, 43).cuboid(-1.5F, -1.5F, 4.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.5F));

		ModelPartData StalkTR_r6 = onesectionsix.addChild("StalkTR_r6", ModelPartBuilder.create().uv(12, 28).cuboid(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.5F, 0.0F, 0.0F, 0.7854F));

		ModelPartData onesectionseven = onesectionsix.addChild("onesectionseven", ModelPartBuilder.create().uv(8, 40).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 34).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 5.5F));

		ModelPartData StalkTR_r7 = onesectionseven.addChild("StalkTR_r7", ModelPartBuilder.create().uv(28, 5).cuboid(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -11.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData onesectioneight = onesectionseven.addChild("onesectioneight", ModelPartBuilder.create().uv(28, 31).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 28).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r8 = onesectioneight.addChild("StalkTR_r8", ModelPartBuilder.create().uv(0, 28).cuboid(-1.0F, -1.0F, 18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -17.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData TREyeone = onesectioneight.addChild("TREyeone", ModelPartBuilder.create().uv(0, 2).cuboid(-0.75F, -0.75F, 3.0F, 1.5F, 1.5F, 0.0F, new Dilation(0.0F))
				.uv(25, 25).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.002F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData RightTopEyestalk = head.addChild("RightTopEyestalk", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -19.5F, 4.0F));

		ModelPartData twosectionone = RightTopEyestalk.addChild("twosectionone", ModelPartBuilder.create().uv(40, 59).cuboid(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(59, 30).cuboid(-1.5F, -1.5F, 4.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.5F));

		ModelPartData StalkTR_r9 = twosectionone.addChild("StalkTR_r9", ModelPartBuilder.create().uv(20, 37).cuboid(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.5F, 0.0F, 0.0F, -0.7854F));

		ModelPartData twosectiontwo = twosectionone.addChild("twosectiontwo", ModelPartBuilder.create().uv(24, 59).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(59, 9).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 5.5F));

		ModelPartData StalkTR_r10 = twosectiontwo.addChild("StalkTR_r10", ModelPartBuilder.create().uv(36, 21).cuboid(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -11.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData twosectionthree = twosectiontwo.addChild("twosectionthree", ModelPartBuilder.create().uv(59, 5).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(16, 58).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r11 = twosectionthree.addChild("StalkTR_r11", ModelPartBuilder.create().uv(36, 1).cuboid(-1.0F, -1.0F, 18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -17.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData twosectionfour = twosectionthree.addChild("twosectionfour", ModelPartBuilder.create().uv(8, 58).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 58).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r12 = twosectionfour.addChild("StalkTR_r12", ModelPartBuilder.create().uv(35, 11).cuboid(-1.0F, -1.0F, 24.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -23.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData twosectionfive = twosectionfour.addChild("twosectionfive", ModelPartBuilder.create().uv(32, 57).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(56, 47).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r13 = twosectionfive.addChild("StalkTR_r13", ModelPartBuilder.create().uv(12, 34).cuboid(-1.0F, -1.0F, 30.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -29.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData TREyetwo = twosectionfive.addChild("TREyetwo", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData twosectionsix = TREyetwo.addChild("twosectionsix", ModelPartBuilder.create().uv(56, 41).cuboid(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(56, 0).cuboid(-1.5F, -1.5F, 4.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.5F));

		ModelPartData StalkTR_r14 = twosectionsix.addChild("StalkTR_r14", ModelPartBuilder.create().uv(0, 34).cuboid(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.5F, 0.0F, 0.0F, -0.7854F));

		ModelPartData twosectionseven = twosectionsix.addChild("twosectionseven", ModelPartBuilder.create().uv(24, 55).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(53, 37).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 5.5F));

		ModelPartData StalkTR_r15 = twosectionseven.addChild("StalkTR_r15", ModelPartBuilder.create().uv(32, 31).cuboid(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -11.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData twosectioneight = twosectionseven.addChild("twosectioneight", ModelPartBuilder.create().uv(8, 52).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 46).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r16 = twosectioneight.addChild("StalkTR_r16", ModelPartBuilder.create().uv(20, 31).cuboid(-1.0F, -1.0F, 18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -17.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData TREyethree = twosectioneight.addChild("TREyethree", ModelPartBuilder.create().uv(0, 4).cuboid(-0.75F, -0.75F, 3.0F, 1.5F, 1.5F, 0.0F, new Dilation(0.0F))
				.uv(28, 19).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.002F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData RightBottomEyestalk = head.addChild("RightBottomEyestalk", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 0.0F));

		ModelPartData threesectionone = RightBottomEyestalk.addChild("threesectionone", ModelPartBuilder.create().uv(68, 23).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(68, 12).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -19.5F, 5.0F));

		ModelPartData StalkTR_r17 = threesectionone.addChild("StalkTR_r17", ModelPartBuilder.create().uv(48, 47).cuboid(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData threesectiontwo = threesectionone.addChild("threesectiontwo", ModelPartBuilder.create().uv(48, 67).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(40, 67).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r18 = threesectiontwo.addChild("StalkTR_r18", ModelPartBuilder.create().uv(48, 41).cuboid(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -11.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData threesectionthree = threesectiontwo.addChild("threesectionthree", ModelPartBuilder.create().uv(24, 67).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 8).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r19 = threesectionthree.addChild("StalkTR_r19", ModelPartBuilder.create().uv(48, 25).cuboid(-1.0F, -1.0F, 18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -17.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData threesectionfour = threesectionthree.addChild("threesectionfour", ModelPartBuilder.create().uv(67, 4).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 66).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r20 = threesectionfour.addChild("StalkTR_r20", ModelPartBuilder.create().uv(48, 0).cuboid(-1.0F, -1.0F, 24.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -23.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData threesectionfive = threesectionfour.addChild("threesectionfive", ModelPartBuilder.create().uv(66, 27).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(66, 19).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r21 = threesectionfive.addChild("StalkTR_r21", ModelPartBuilder.create().uv(12, 46).cuboid(-1.0F, -1.0F, 30.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -29.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData threesectionsix = threesectionfive.addChild("threesectionsix", ModelPartBuilder.create().uv(16, 66).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 66).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r22 = threesectionsix.addChild("StalkTR_r22", ModelPartBuilder.create().uv(0, 46).cuboid(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData threesectionseven = threesectionsix.addChild("threesectionseven", ModelPartBuilder.create().uv(0, 66).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(32, 65).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r23 = threesectionseven.addChild("StalkTR_r23", ModelPartBuilder.create().uv(40, 45).cuboid(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -11.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData threesectioneight = threesectionseven.addChild("threesectioneight", ModelPartBuilder.create().uv(64, 62).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(64, 58).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r24 = threesectioneight.addChild("StalkTR_r24", ModelPartBuilder.create().uv(32, 43).cuboid(-1.0F, -1.0F, 18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -17.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData TREyefour = threesectioneight.addChild("TREyefour", ModelPartBuilder.create().uv(0, 16).cuboid(-0.75F, -0.75F, 3.0F, 1.5F, 1.5F, 0.0F, new Dilation(0.0F))
				.uv(44, 19).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.002F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData LeftBottomEyestalk = head.addChild("LeftBottomEyestalk", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -15.5F, 5.0F));

		ModelPartData foursectionone = LeftBottomEyestalk.addChild("foursectionone", ModelPartBuilder.create().uv(64, 0).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(56, 63).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData StalkTR_r25 = foursectionone.addChild("StalkTR_r25", ModelPartBuilder.create().uv(20, 43).cuboid(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData foursectiontwo = foursectionone.addChild("foursectiontwo", ModelPartBuilder.create().uv(48, 63).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 44).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r26 = foursectiontwo.addChild("StalkTR_r26", ModelPartBuilder.create().uv(43, 13).cuboid(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -11.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData foursectionthree = foursectiontwo.addChild("foursectionthree", ModelPartBuilder.create().uv(40, 63).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 38).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r27 = foursectionthree.addChild("StalkTR_r27", ModelPartBuilder.create().uv(43, 7).cuboid(-1.0F, -1.0F, 18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -17.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData foursectionfour = foursectionthree.addChild("foursectionfour", ModelPartBuilder.create().uv(24, 63).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(62, 51).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r28 = foursectionfour.addChild("StalkTR_r28", ModelPartBuilder.create().uv(41, 33).cuboid(-1.0F, -1.0F, 24.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -23.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData foursectionfive = foursectionfour.addChild("foursectionfive", ModelPartBuilder.create().uv(16, 62).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 62).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r29 = foursectionfive.addChild("StalkTR_r29", ModelPartBuilder.create().uv(40, 39).cuboid(-1.0F, -1.0F, 30.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -29.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData foursectionsix = foursectionfive.addChild("foursectionsix", ModelPartBuilder.create().uv(0, 62).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(61, 34).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r30 = foursectionsix.addChild("StalkTR_r30", ModelPartBuilder.create().uv(40, 27).cuboid(-1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -5.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData foursectionseven = foursectionsix.addChild("foursectionseven", ModelPartBuilder.create().uv(32, 61).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(61, 15).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r31 = foursectionseven.addChild("StalkTR_r31", ModelPartBuilder.create().uv(12, 40).cuboid(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -11.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData foursectioneight = foursectionseven.addChild("foursectioneight", ModelPartBuilder.create().uv(56, 59).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(48, 59).cuboid(-1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData StalkTR_r32 = foursectioneight.addChild("StalkTR_r32", ModelPartBuilder.create().uv(0, 40).cuboid(-1.0F, -1.0F, 18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -17.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData TREyefive = foursectioneight.addChild("TREyefive", ModelPartBuilder.create().uv(0, 14).cuboid(-0.75F, -0.75F, 3.0F, 1.5F, 1.5F, 0.0F, new Dilation(0.0F))
				.uv(32, 37).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.002F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(SpectatorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.WALK,limbSwing,limbSwingAmount,2f,2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.IDLE, ageInTicks, 1f);
		this.updateAnimation(entity.sitAnimationState, ModAnimations.SIT,ageInTicks,1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
	headYaw = MathHelper.clamp(headYaw,-30.0f, 30.0f);
	headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

	this.head.yaw = headYaw * 0.017453292F;
	this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		spectator.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return spectator;
	}



    }