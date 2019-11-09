package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.items.materials.coal.CoalHelmet;
import de.melanx.MoreVanillaArmor.items.materials.redstone.*;
import de.melanx.MoreVanillaArmor.items.materials.lapis.*;
import de.melanx.MoreVanillaArmor.items.materials.obsidian.*;
import de.melanx.MoreVanillaArmor.items.materials.coal.*;
import de.melanx.MoreVanillaArmor.items.materials.glowstone.*;
import de.melanx.MoreVanillaArmor.items.materials.emerald.*;
import de.melanx.MoreVanillaArmor.items.materials.quartz.*;
import de.melanx.MoreVanillaArmor.items.materials.bone.*;
import de.melanx.MoreVanillaArmor.items.materials.paper.*;
import net.minecraft.item.Item;

public class ModItems {

    public static Item redstone_helmet;
    public static Item redstone_chestplate;
    public static Item redstone_leggings;
    public static Item redstone_boots;

    public static Item lapis_helmet;
    public static Item lapis_chestplate;
    public static Item lapis_leggings;
    public static Item lapis_boots;

    public static Item obsidian_helmet;
    public static Item obsidian_chestplate;
    public static Item obsidian_leggings;
    public static Item obsidian_boots;

    public static Item coal_helmet;
    public static Item coal_chestplate;
    public static Item coal_leggings;
    public static Item coal_boots;

    public static Item glowstone_helmet;
    public static Item glowstone_chestplate;
    public static Item glowstone_leggings;
    public static Item glowstone_boots;

    public static Item emerald_helmet;
    public static Item emerald_chestplate;
    public static Item emerald_leggings;
    public static Item emerald_boots;

    public static Item quartz_helmet;
    public static Item quartz_chestplate;
    public static Item quartz_leggings;
    public static Item quartz_boots;

    public static Item bone_helmet;
    public static Item bone_chestplate;
    public static Item bone_leggings;
    public static Item bone_boots;

    public static Item paper_helmet;
    public static Item paper_chestplate;
    public static Item paper_leggings;
    public static Item paper_boots;

    public static void init() {
        redstone_helmet = new RedstoneHelmet();
        redstone_chestplate = new RedstoneChestplate();
        redstone_leggings = new RedstoneLeggings();
        redstone_boots = new RedstoneBoots();

        lapis_helmet = new LapisHelmet();
        lapis_chestplate = new LapisChestplate();
        lapis_leggings = new LapisLeggings();
        lapis_boots = new LapisBoots();

        obsidian_helmet = new ObsidianHelmet();
        obsidian_chestplate = new ObsidianChestplate();
        obsidian_leggings = new ObsidianLeggings();
        obsidian_boots = new ObsidianBoots();

        coal_helmet = new CoalHelmet();
        coal_chestplate = new CoalChestplate();
        coal_leggings = new CoalLeggings();
        coal_boots = new CoalBoots();

        glowstone_helmet = new GlowstoneHelmet();
        glowstone_chestplate = new GlowstoneChestplate();
        glowstone_leggings = new GlowstoneLeggings();
        glowstone_boots = new GlowstoneBoots();

        emerald_helmet = new EmeraldHelmet();
        emerald_chestplate = new EmeraldChestplate();
        emerald_leggings = new EmeraldLeggings();
        emerald_boots = new EmeraldBoots();

        quartz_helmet = new QuartzHelmet();
        quartz_chestplate = new QuartzChestplate();
        quartz_leggings = new QuartzLeggings();
        quartz_boots = new QuartzBoots();

        bone_helmet = new BoneHelmet();
        bone_chestplate = new BoneChestplate();
        bone_leggings = new BoneLeggings();
        bone_boots = new BoneBoots();

        paper_helmet = new PaperHelmet();
        paper_chestplate = new PaperChestplate();
        paper_leggings = new PaperLeggings();
        paper_boots = new PaperBoots();
    }

}
