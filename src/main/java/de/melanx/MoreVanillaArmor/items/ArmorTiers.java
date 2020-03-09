package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.data.ModTags;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

public enum ArmorTiers implements IArmorMaterial {

    BONE("bone", 15, new int[]{1, 4, 5, 2}, 14, SoundEvents.ENTITY_SKELETON_AMBIENT, 1.5F, () -> {
        return Ingredient.fromTag(Tags.Items.BONES);
    }, Tags.Items.BONES),
    COAL("coal", 10, new int[]{1, 2, 3, 1}, 11, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 0.5F, () -> {
        return Ingredient.fromItems(Items.COAL);
    }, Tags.Items.STORAGE_BLOCKS_COAL),
    EMERALD("emerald", 69, new int[]{4, 8, 12, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, () -> {
        return Ingredient.fromTag(Tags.Items.GEMS_EMERALD);
    }, Tags.Items.GEMS_EMERALD),
    ENDER("ender", 31, new int[]{2, 6, 8, 3}, 20, SoundEvents.ENTITY_ENDER_EYE_LAUNCH, 3.14F, () -> {
        return Ingredient.fromTag(Tags.Items.ENDER_PEARLS);
    }, Tags.Items.END_STONES),
    FIERY("fiery", 17, new int[]{2, 4, 7, 3}, 15, SoundEvents.ENTITY_BLAZE_SHOOT, 1.3F, () -> {
        return Ingredient.fromTag(ModTags.Items.MAGMA_BLOCK);
    }, ModTags.Items.MAGMA_BLOCK),
    GLOWSTONE("glowstone", 13, new int[]{2, 5, 6, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F, () -> {
        return Ingredient.fromTag(Tags.Items.DUSTS_GLOWSTONE);
    }, ModTags.Items.STORAGE_BLOCKS_GLOWSTONE),
    LAPIS("lapis", 13, new int[]{2, 5, 6, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F, () -> {
        return Ingredient.fromTag(Tags.Items.GEMS_LAPIS);
    }, Tags.Items.STORAGE_BLOCKS_LAPIS),
    NETHER("nether", 17, new int[]{3, 4, 7, 3}, 66, SoundEvents.BLOCK_LAVA_EXTINGUISH, 2.1F, () -> {
        return Ingredient.fromTag(ModTags.Items.NETHER_BRICKS);
    }, ModTags.Items.NETHER_BRICKS),
    OBSIDIAN("obsidian", 81, new int[]{5, 9, 15, 4}, 11, SoundEvents.ENTITY_ENDER_EYE_DEATH, 4.0F, () -> {
        return Ingredient.fromTag(Tags.Items.OBSIDIAN);
    }, Tags.Items.OBSIDIAN),
    PAPER("paper", 1, new int[]{0, 1, 2, 0}, 13, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.0F, () -> {
        return Ingredient.fromItems(Items.PAPER);
    }, ModTags.Items.PAPER),
    PRISMARINE("prismarine", 21, new int[]{4, 6, 8, 2}, 20, SoundEvents.BLOCK_WATER_AMBIENT, 1.0F, () -> {
        return Ingredient.fromTag(Tags.Items.DUSTS_PRISMARINE);
    }, ModTags.Items.PRISMARINE),
    QUARTZ("quartz", 10, new int[]{1, 2, 3, 1}, 11, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 0.5F, () -> {
        return Ingredient.fromTag(Tags.Items.GEMS_QUARTZ);
    }, Tags.Items.STORAGE_BLOCKS_QUARTZ),
    REDSTONE("redstone", 13, new int[]{2, 5, 6, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F, () -> {
        return Ingredient.fromTag(Tags.Items.DUSTS_REDSTONE);
    }, Tags.Items.STORAGE_BLOCKS_REDSTONE),
    SLIME("slime", 42, new int[]{1, 3, 4, 2}, 20, SoundEvents.BLOCK_SLIME_BLOCK_STEP, 0.3F, () -> {
        return Ingredient.fromTag(Tags.Items.SLIMEBALLS);
    }, ModTags.Items.SLIME_BLOCK);

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyValue<Ingredient> repairMaterial;
    private final Tag<Item> ingredient;

    ArmorTiers(String nameIn, int maxDamageFactor, int[] damageReduction, int enchantability, SoundEvent sound, float toughness, Supplier<Ingredient> repairMaterial, Tag<Item> ingredient) {
        this.name = nameIn;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReduction;
        this.enchantability = enchantability;
        this.soundEvent = sound;
        this.toughness = toughness;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = ingredient;
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public Tag<Item> getIngredient() {
        return this.ingredient;
    }

}
