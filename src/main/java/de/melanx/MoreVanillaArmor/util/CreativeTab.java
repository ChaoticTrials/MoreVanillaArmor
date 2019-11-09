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
        return new ItemStack(ModItems.emerald_chestplate);
    }

    @Override
    public void fill(@Nonnull NonNullList<ItemStack> list) {
        this.list = list;

        addItem(ModItems.redstone_helmet);
        addItem(ModItems.redstone_chestplate);
        addItem(ModItems.redstone_leggings);
        addItem(ModItems.redstone_boots);

        addItem(ModItems.lapis_helmet);
        addItem(ModItems.lapis_chestplate);
        addItem(ModItems.lapis_leggings);
        addItem(ModItems.lapis_boots);

        addItem(ModItems.obsidian_helmet);
        addItem(ModItems.obsidian_chestplate);
        addItem(ModItems.obsidian_leggings);
        addItem(ModItems.obsidian_boots);

        addItem(ModItems.coal_helmet);
        addItem(ModItems.coal_chestplate);
        addItem(ModItems.coal_leggings);
        addItem(ModItems.coal_boots);

        addItem(ModItems.glowstone_helmet);
        addItem(ModItems.glowstone_chestplate);
        addItem(ModItems.glowstone_leggings);
        addItem(ModItems.glowstone_boots);

        addItem(ModItems.emerald_helmet);
        addItem(ModItems.emerald_chestplate);
        addItem(ModItems.emerald_leggings);
        addItem(ModItems.emerald_boots);

        addItem(ModItems.quartz_helmet);
        addItem(ModItems.quartz_chestplate);
        addItem(ModItems.quartz_leggings);
        addItem(ModItems.quartz_boots);

        addItem(ModItems.bone_helmet);
        addItem(ModItems.bone_chestplate);
        addItem(ModItems.bone_leggings);
        addItem(ModItems.bone_boots);

        addItem(ModItems.paper_helmet);
        addItem(ModItems.paper_chestplate);
        addItem(ModItems.paper_leggings);
        addItem(ModItems.paper_boots);
    }

    private void addItem(IItemProvider item) {
        item.asItem().fillItemGroup(this, list);
    }

}
