package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fmllegacy.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Collection;

public class CreativeTab extends CreativeModeTab {

    public CreativeTab() {
        super(MoreVanillaArmor.MODID);
    }

    @Nonnull
    @Override
    public ItemStack makeIcon() {
        Collection<RegistryObject<Item>> entries = ModRegistries.ITEMS.getEntries();
        for (RegistryObject<Item> entry : entries) {
            //noinspection ConstantConditions
            if (entry.get().getRegistryName().getPath().contains("emerald_chestplate")) {
                return new ItemStack(entry.get());
            }
        }
        return new ItemStack(Items.DIAMOND_CHESTPLATE);
    }
}
