package net.anubis.spectatormod.datagen;

import net.anubis.spectatormod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SOULCAGE,1)
                .pattern("X")
                .pattern("#")
                .input('X', Items.GHAST_TEAR)
                .input('#', Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .criterion(hasItem(Items.GHAST_TEAR),conditionsFromItem(Items.GHAST_TEAR))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SOULCAGE)));
    }
}
