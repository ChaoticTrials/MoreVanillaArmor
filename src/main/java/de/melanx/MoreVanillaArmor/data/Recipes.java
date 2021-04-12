package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.util.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        for (RegistryObject<Item> object : Registry.ITEMS.getEntries()) {
            Armor item = (Armor) object.get();
            EquipmentSlotType slotType = item.getSlotType();
            if (slotType == EquipmentSlotType.HEAD)
                this.registerHelmetRecipe(item).build(consumer);
            else if (slotType == EquipmentSlotType.CHEST)
                this.registerChestplateRecipe(item).build(consumer);
            else if (slotType == EquipmentSlotType.LEGS)
                this.registerLeggingsRecipe(item).build(consumer);
            else if (slotType == EquipmentSlotType.FEET)
                this.registerBootsRecipe(item).build(consumer);
        }
    }

    private ShapedRecipeBuilder registerHelmetRecipe(Armor item) {
        return ShapedRecipeBuilder.shapedRecipe(item)
                .key('M', item.getType().getIngredient())
                .patternLine("MMM")
                .patternLine("M M")
                .addCriterion("already_crafted", hasItem(item));
    }

    private ShapedRecipeBuilder registerChestplateRecipe(Armor item) {
        return ShapedRecipeBuilder.shapedRecipe(item)
                .key('M', item.getType().getIngredient())
                .patternLine("M M")
                .patternLine("MMM")
                .patternLine("MMM")
                .addCriterion("already_crafted", hasItem(item));
    }

    private ShapedRecipeBuilder registerLeggingsRecipe(Armor item) {
        return ShapedRecipeBuilder.shapedRecipe(item)
                .key('M', item.getType().getIngredient())
                .patternLine("MMM")
                .patternLine("M M")
                .patternLine("M M")
                .addCriterion("already_crafted", hasItem(item));
    }

    private ShapedRecipeBuilder registerBootsRecipe(Armor item) {
        return ShapedRecipeBuilder.shapedRecipe(item)
                .key('M', item.getType().getIngredient())
                .patternLine("M M")
                .patternLine("M M")
                .addCriterion("already_crafted", hasItem(item));
    }
}
