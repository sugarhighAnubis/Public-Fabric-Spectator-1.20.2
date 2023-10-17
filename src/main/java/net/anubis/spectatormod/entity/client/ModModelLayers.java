package net.anubis.spectatormod.entity.client;

import net.anubis.spectatormod.SpectatorMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer SPECTATOR =
            new EntityModelLayer(new Identifier(SpectatorMod.MOD_ID, "spectator"),"main");
}
