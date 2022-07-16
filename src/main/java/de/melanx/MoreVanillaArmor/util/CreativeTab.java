package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CreativeTab extends CreativeModeTab {

    public CreativeTab() {
        super(MoreVanillaArmor.MODID);
    }

    @Nonnull
    @Override
    public ItemStack makeIcon() {
        Armor armor = ModRegistries.armor.get(new ModRegistries.ArmorPiece(ArmorTiers.EMERALD, ModRegistries.ArmorSlot.CHESTPLATE));
        return new ItemStack(Objects.requireNonNullElse(armor, Items.DIAMOND_CHESTPLATE));
    }
}
