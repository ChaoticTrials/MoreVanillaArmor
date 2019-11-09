package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class Registry {
    public static final List<Item> ITEMS_TO_REGISTER = new ArrayList<>();
    public static final Map<ItemStack, ModelResourceLocation> MODEL_LOCATIONS = new HashMap<>();

    public static void registerItem(Item item, String name) {
        item.setRegistryName(MoreVanillaArmor.MODID, name);
        ITEMS_TO_REGISTER.add(item);
    }

    public static void registerModel(Object item) {
        if (item instanceof Item) {
            MODEL_LOCATIONS.put(new ItemStack((Item) item), new ModelResourceLocation(Objects.requireNonNull(((Item) item).getRegistryName()), "inventory"));
        } else {
            throw new IllegalArgumentException("item should be of type Item or Block");
        }
    }
}
