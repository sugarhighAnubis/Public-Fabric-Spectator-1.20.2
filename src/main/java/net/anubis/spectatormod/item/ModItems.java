package net.anubis.spectatormod.item;

import net.anubis.spectatormod.SpectatorMod;
import net.anubis.spectatormod.entity.ModEntities;
import net.anubis.spectatormod.item.custom.ShockwaveItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import javax.xml.namespace.QName;
public class ModItems {
    public static final Item SOULCAGE = registerItem("soulcage", new SpawnEggItem(ModEntities.SPECTATOR, 0xF9F9F9, 0xF9F9F9, new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(SOULCAGE);
        entries.add(SHOCKWAVE);
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SpectatorMod.MOD_ID, name), item);
    }

    public static final Item SHOCKWAVE = registerItem("shockwave",
            new Item(new FabricItemSettings()));


    public static void registerModItems() {
        SpectatorMod.LOGGER.info("Registering Mod Items for " + SpectatorMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);

    }
}