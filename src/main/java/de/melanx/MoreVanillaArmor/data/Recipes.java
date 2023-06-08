package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        for (Armor item : ModRegistries.armor.values()) {
            switch (item.getSlotType()) {
                case HEAD -> this.registerHelmetRecipe(item).save(consumer);
                case CHEST -> this.registerChestplateRecipe(item).save(consumer);
                case LEGS -> this.registerLeggingsRecipe(item).save(consumer);
                case FEET -> this.registerBootsRecipe(item).save(consumer);
            }
        }
    }

    private ShapedRecipeBuilder registerHelmetRecipe(Armor item) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .define('M', item.getArmorType().getIngredient())
                .pattern("MMM")
                .pattern("M M")
                .unlockedBy("already_crafted", has(item));
    }

    private ShapedRecipeBuilder registerChestplateRecipe(Armor item) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .define('M', item.getArmorType().getIngredient())
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .unlockedBy("already_crafted", has(item));
    }

    private ShapedRecipeBuilder registerLeggingsRecipe(Armor item) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .define('M', item.getArmorType().getIngredient())
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .unlockedBy("already_crafted", has(item));
    }

    private ShapedRecipeBuilder registerBootsRecipe(Armor item) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .define('M', item.getArmorType().getIngredient())
                .pattern("M M")
                .pattern("M M")
                .unlockedBy("already_crafted", has(item));
    }
}
