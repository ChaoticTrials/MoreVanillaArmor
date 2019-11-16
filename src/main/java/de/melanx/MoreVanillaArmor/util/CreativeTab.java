package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class CreativeTab extends ItemGroup {

    private NonNullList<ItemStack> list;

    public CreativeTab() {
        super(MoreVanillaArmor.MODID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.EMERALD_CHESTPLATE);
    }

    @Override
    public void fill(@Nonnull NonNullList<ItemStack> list) {
        this.list = list;

        addItem(ModItems.REDSTONE_HELMET);
        addItem(ModItems.REDSTONE_CHESTPLATE);
        addItem(ModItems.REDSTONE_LEGGINGS);
        addItem(ModItems.REDSTONE_BOOTS);

        addItem(ModItems.LAPIS_HELMET);
        addItem(ModItems.LAPIS_CHESTPLATE);
        addItem(ModItems.LAPIS_LEGGINGS);
        addItem(ModItems.LAPIS_BOOTS);

        addItem(ModItems.OBSIDIAN_HELMET);
        addItem(ModItems.OBSIDIAN_CHESTPLATE);
        addItem(ModItems.OBSIDIAN_LEGGINGS);
        addItem(ModItems.OBSIDIAN_BOOTS);

        addItem(ModItems.COAL_HELMET);
        addItem(ModItems.COAL_CHESTPLATE);
        addItem(ModItems.COAL_LEGGINGS);
        addItem(ModItems.COAL_BOOTS);

        addItem(ModItems.GLOWSTONE_HELMET);
        addItem(ModItems.GLOWSTONE_CHESTPLATE);
        addItem(ModItems.GLOWSTONE_LEGGINGS);
        addItem(ModItems.GLOWSTONE_BOOTS);

        addItem(ModItems.EMERALD_HELMET);
        addItem(ModItems.EMERALD_CHESTPLATE);
        addItem(ModItems.EMERALD_LEGGINGS);
        addItem(ModItems.EMERALD_BOOTS);

        addItem(ModItems.QUARTZ_HELMET);
        addItem(ModItems.QUARTZ_CHESTPLATE);
        addItem(ModItems.QUARTZ_LEGGINGS);
        addItem(ModItems.QUARTZ_BOOTS);

        addItem(ModItems.BONE_HELMET);
        addItem(ModItems.BONE_CHESTPLATE);
        addItem(ModItems.BONE_LEGGINGS);
        addItem(ModItems.BONE_BOOTS);

        addItem(ModItems.PAPER_HELMET);
        addItem(ModItems.PAPER_CHESTPLATE);
        addItem(ModItems.PAPER_LEGGINGS);
        addItem(ModItems.PAPER_BOOTS);
    }

    private void addItem(IItemProvider item) {
        item.asItem().fillItemGroup(this, list);
    }

}
