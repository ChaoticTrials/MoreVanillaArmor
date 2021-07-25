package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        for (RegistryObject<Item> object : ModRegistries.ITEMS.getEntries()) {
            Armor item = (Armor) object.get();
            EquipmentSlot slotType = item.getSlotType();
            if (slotType == EquipmentSlot.HEAD)
                this.registerHelmetRecipe(item).save(consumer);
            else if (slotType == EquipmentSlot.CHEST)
                this.registerChestplateRecipe(item).save(consumer);
            else if (slotType == EquipmentSlot.LEGS)
                this.registerLeggingsRecipe(item).save(consumer);
            else if (slotType == EquipmentSlot.FEET)
                this.registerBootsRecipe(item).save(consumer);
        }
    }

    private ShapedRecipeBuilder registerHelmetRecipe(Armor item) {
        return ShapedRecipeBuilder.shaped(item)
                .define('M', item.getType().getIngredient())
                .pattern("MMM")
                .pattern("M M")
                .unlockedBy("already_crafted", has(item));
    }

    private ShapedRecipeBuilder registerChestplateRecipe(Armor item) {
        return ShapedRecipeBuilder.shaped(item)
                .define('M', item.getType().getIngredient())
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .unlockedBy("already_crafted", has(item));
    }

    private ShapedRecipeBuilder registerLeggingsRecipe(Armor item) {
        return ShapedRecipeBuilder.shaped(item)
                .define('M', item.getType().getIngredient())
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .unlockedBy("already_crafted", has(item));
    }

    private ShapedRecipeBuilder registerBootsRecipe(Armor item) {
        return ShapedRecipeBuilder.shaped(item)
                .define('M', item.getType().getIngredient())
                .pattern("M M")
                .pattern("M M")
                .unlockedBy("already_crafted", has(item));
    }
}
