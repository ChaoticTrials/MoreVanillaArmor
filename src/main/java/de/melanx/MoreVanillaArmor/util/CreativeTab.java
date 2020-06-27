package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.RegistryObject;

import java.util.Collection;

public class CreativeTab extends ItemGroup {

    public CreativeTab() {
        super(MoreVanillaArmor.MODID);
    }

    @Override
    public ItemStack createIcon() {
        Collection<RegistryObject<Item>> entries = Registry.ITEMS.getEntries();
        for (RegistryObject<Item> entry : entries) {
            if (entry.get().getRegistryName().getPath().contains("emerald_chestplate")) {
                return new ItemStack(entry.get());
            }
        }
        return new ItemStack(Items.DIAMOND_CHESTPLATE);
    }

    @Override
    public void fill(NonNullList<ItemStack> list) {
        for (RegistryObject<Item> entry : Registry.ITEMS.getEntries()) {
            entry.get().asItem().fillItemGroup(this, list);
        }
    }
}
