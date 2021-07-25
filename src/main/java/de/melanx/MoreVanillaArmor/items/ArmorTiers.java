package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.Config;
import de.melanx.MoreVanillaArmor.util.LazyValue;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum ArmorTiers implements ArmorMaterial {

    BONE(Config.materials.get(Config.DefaultMaterial.BONE), SoundEvents.SKELETON_AMBIENT, null, () -> Ingredient.of(Tags.Items.BONES)),
    COAL(Config.materials.get(Config.DefaultMaterial.COAL), SoundEvents.ARMOR_EQUIP_ELYTRA, null, () -> Ingredient.of(Items.COAL), () -> Ingredient.of(Tags.Items.STORAGE_BLOCKS_COAL)),
    COPPER(Config.materials.get(Config.DefaultMaterial.COPPER), SoundEvents.ARMOR_EQUIP_IRON, ModRegistries.LIGHTNING_MAGNET, () -> Ingredient.of(Items.COPPER_INGOT)),
    EMERALD(Config.materials.get(Config.DefaultMaterial.EMERALD), SoundEvents.ARMOR_EQUIP_DIAMOND, null, () -> Ingredient.of(Tags.Items.GEMS_EMERALD)),
    ENDER(Config.materials.get(Config.DefaultMaterial.ENDER), SoundEvents.ENDER_EYE_LAUNCH, null, () -> Ingredient.of(Tags.Items.ENDER_PEARLS), () -> Ingredient.of(Tags.Items.END_STONES)),
    FIERY(Config.materials.get(Config.DefaultMaterial.FIERY), SoundEvents.BLAZE_SHOOT, ModRegistries.FIRE_IMMUNITY, () -> Ingredient.of(Items.MAGMA_BLOCK)),
    GLOWSTONE(Config.materials.get(Config.DefaultMaterial.GLOWSTONE), SoundEvents.ARMOR_EQUIP_GOLD, null, () -> Ingredient.of(Tags.Items.DUSTS_GLOWSTONE), () -> Ingredient.of(Items.GLOWSTONE)),
    LAPIS(Config.materials.get(Config.DefaultMaterial.LAPIS), SoundEvents.ARMOR_EQUIP_GOLD, null, () -> Ingredient.of(Tags.Items.GEMS_LAPIS), () -> Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS)),
    NETHER(Config.materials.get(Config.DefaultMaterial.NETHER), SoundEvents.LAVA_EXTINGUISH, null, () -> Ingredient.of(Items.NETHER_BRICK)),
    OBSIDIAN(Config.materials.get(Config.DefaultMaterial.OBSIDIAN), SoundEvents.ENDER_EYE_DEATH, ModRegistries.HEAVY, () -> Ingredient.of(Tags.Items.OBSIDIAN)),
    PAPER(Config.materials.get(Config.DefaultMaterial.PAPER), SoundEvents.PLAYER_ATTACK_SWEEP, null, () -> Ingredient.of(Items.PAPER)),
    PRISMARINE(Config.materials.get(Config.DefaultMaterial.PRISMARINE), SoundEvents.WATER_AMBIENT, null, () -> Ingredient.of(Tags.Items.DUSTS_PRISMARINE), () -> Ingredient.of(Items.PRISMARINE)),
    QUARTZ(Config.materials.get(Config.DefaultMaterial.QUARTZ), SoundEvents.ARMOR_EQUIP_ELYTRA, null, () -> Ingredient.of(Tags.Items.GEMS_QUARTZ), () -> Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ)),
    REDSTONE(Config.materials.get(Config.DefaultMaterial.REDSTONE), SoundEvents.ARMOR_EQUIP_GOLD, ModRegistries.POWER_SOURCE, () -> Ingredient.of(Tags.Items.DUSTS_REDSTONE), () -> Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE)),
    SLIME(Config.materials.get(Config.DefaultMaterial.SLIME), SoundEvents.SLIME_BLOCK_STEP, ModRegistries.DAMAGE_REDUCTION, () -> Ingredient.of(Tags.Items.SLIMEBALLS), () -> Ingredient.of(Items.SLIME_BLOCK)),
    STONE(Config.materials.get(Config.DefaultMaterial.STONE), SoundEvents.STONE_BREAK, null, () -> Ingredient.of(Tags.Items.STONE)),
    WOOD(Config.materials.get(Config.DefaultMaterial.WOOD), SoundEvents.WOODEN_DOOR_OPEN, null, () -> Ingredient.of(ItemTags.LOGS));

    private static final int[] DURABILITY_ARRAY = new int[]{13, 15, 16, 11}; // vanilla copy [boots, leggings, chest plate, head]
    private final String name;
    private final int durabilityFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantmentValue;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;
    private final LazyValue<Ingredient> ingredient;
    private final LazyValue<MobEffect> bonus;

    ArmorTiers(Config.Material material, SoundEvent sound, Supplier<MobEffect> bonus, Supplier<Ingredient> repairMaterial, Supplier<Ingredient> ingredient) {
        this.name = material.getName();
        this.durabilityFactor = material.getDurabilityFactor();
        this.damageReductionAmountArray = material.getDamageReduction();
        this.enchantmentValue = material.getEnchantmentValue();
        this.soundEvent = sound;
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        this.bonus = new LazyValue<>(bonus);
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = new LazyValue<>(ingredient);
    }

    ArmorTiers(Config.Material material, SoundEvent sound, Supplier<MobEffect> bonus, Supplier<Ingredient> repairMaterial) {
        this.name = material.getName();
        this.durabilityFactor = material.getDurabilityFactor();
        this.damageReductionAmountArray = material.getDamageReduction();
        this.enchantmentValue = material.getEnchantmentValue();
        this.soundEvent = sound;
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        this.bonus = new LazyValue<>(bonus);
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = new LazyValue<>(repairMaterial);
    }

    public int getDurabilityForSlot(EquipmentSlot slot) {
        return DURABILITY_ARRAY[slot.getIndex()] * this.durabilityFactor;
    }

    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.damageReductionAmountArray[slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Nonnull
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Nonnull
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    @Nonnull
    public Ingredient getIngredient() {
        return this.ingredient.get();
    }

    @Nonnull
    public ArmorTiers getType() {
        return this;
    }

    public MobEffect getSetBonus() {
        return this.bonus.get();
    }

    public Component getComponent() {
        return this.bonus.get().getDisplayName();
    }
}
