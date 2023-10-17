package net.anubis.spectatormod.entity.client;

import net.anubis.spectatormod.SpectatorMod;
import net.anubis.spectatormod.entity.custom.SpectatorEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SpectatorRenderer extends MobEntityRenderer<SpectatorEntity, SpectatorModel<SpectatorEntity>> {
    private static final Identifier TEXTURE = new Identifier(SpectatorMod.MOD_ID,"textures/entity/spectator.png");


    public SpectatorRenderer(EntityRendererFactory.Context context) {
        super(context, new SpectatorModel<>(context.getPart(ModModelLayers.SPECTATOR)),0.7f);
    }

    @Override
    public Identifier getTexture(SpectatorEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(SpectatorEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()){
            matrixStack.scale(0.25f,0.25f,0.25f);
        } else {
            matrixStack.scale(0.5f,0.5f,0.5f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
