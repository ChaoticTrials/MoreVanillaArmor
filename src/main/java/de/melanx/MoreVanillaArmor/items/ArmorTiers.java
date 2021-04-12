package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.Config;
import de.melanx.MoreVanillaArmor.util.Registry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effect;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum ArmorTiers implements IArmorMaterial {

    BONE(Config.Material.BONE, SoundEvents.ENTITY_SKELETON_AMBIENT, null, () -> Ingredient.fromTag(Tags.Items.BONES)),
    COAL(Config.Material.COAL, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, null, () -> Ingredient.fromItems(Items.COAL), () -> Ingredient.fromTag(Tags.Items.STORAGE_BLOCKS_COAL)),
    EMERALD(Config.Material.EMERALD, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, null, () -> Ingredient.fromTag(Tags.Items.GEMS_EMERALD)),
    ENDER(Config.Material.ENDER, SoundEvents.ENTITY_ENDER_EYE_LAUNCH, null, () -> Ingredient.fromTag(Tags.Items.ENDER_PEARLS), () -> Ingredient.fromTag(Tags.Items.END_STONES)),
    FIERY(Config.Material.FIERY, SoundEvents.ENTITY_BLAZE_SHOOT, Registry.FIRE_IMMUNITY, () -> Ingredient.fromItems(Items.MAGMA_BLOCK)),
    GLOWSTONE(Config.Material.GLOWSTONE, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, null, () -> Ingredient.fromTag(Tags.Items.DUSTS_GLOWSTONE), () -> Ingredient.fromItems(Items.GLOWSTONE)),
    LAPIS(Config.Material.LAPIS, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, null, () -> Ingredient.fromTag(Tags.Items.GEMS_LAPIS), () -> Ingredient.fromTag(Tags.Items.STORAGE_BLOCKS_LAPIS)),
    NETHER(Config.Material.NETHER, SoundEvents.BLOCK_LAVA_EXTINGUISH, null, () -> Ingredient.fromItems(Items.NETHER_BRICK)),
    OBSIDIAN(Config.Material.OBSIDIAN, SoundEvents.ENTITY_ENDER_EYE_DEATH, Registry.HEAVY, () -> Ingredient.fromTag(Tags.Items.OBSIDIAN)),
    PAPER(Config.Material.PAPER, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, null, () -> Ingredient.fromItems(Items.PAPER)),
    PRISMARINE(Config.Material.PRISMARINE, SoundEvents.BLOCK_WATER_AMBIENT, null, () -> Ingredient.fromTag(Tags.Items.DUSTS_PRISMARINE), () -> Ingredient.fromItems(Items.PRISMARINE)),
    QUARTZ(Config.Material.QUARTZ, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, null, () -> Ingredient.fromTag(Tags.Items.GEMS_QUARTZ), () -> Ingredient.fromTag(Tags.Items.STORAGE_BLOCKS_QUARTZ)),
    REDSTONE(Config.Material.REDSTONE, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, Registry.POWER_SOURCE, () -> Ingredient.fromTag(Tags.Items.DUSTS_REDSTONE), () -> Ingredient.fromTag(Tags.Items.STORAGE_BLOCKS_REDSTONE)),
    SLIME(Config.Material.SLIME, SoundEvents.BLOCK_SLIME_BLOCK_STEP, Registry.DAMAGE_REDUCTION, () -> Ingredient.fromTag(Tags.Items.SLIMEBALLS), () -> Ingredient.fromItems(Items.SLIME_BLOCK)),
    STONE(Config.Material.STONE, SoundEvents.BLOCK_STONE_BREAK, null, () -> Ingredient.fromTag(Tags.Items.STONE)),
    WOOD(Config.Material.WOOD, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, null, () -> Ingredient.fromTag(ItemTags.LOGS));

    private static final int[] DURABILITY_ARRAY = new int[]{13, 15, 16, 11}; // vanilla copy [boots, leggings, chest plate, head]
    private final String name;
    private final int durabilityFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;
    private final LazyValue<Ingredient> ingredient;
    private final LazyValue<Effect> bonus;

    ArmorTiers(Config.Material material, SoundEvent sound, Supplier<Effect> bonus, Supplier<Ingredient> repairMaterial, Supplier<Ingredient> ingredient) {
        this.name = material.getName();
        this.durabilityFactor = material.getDurabilityFactor();
        this.damageReductionAmountArray = material.getDamageReduction();
        this.enchantability = material.getEnchantability();
        this.soundEvent = sound;
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        this.bonus = new LazyValue<>(bonus);
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = new LazyValue<>(ingredient);
    }

    ArmorTiers(Config.Material material, SoundEvent sound, Supplier<Effect> bonus, Supplier<Ingredient> repairMaterial) {
        this.name = material.getName();
        this.durabilityFactor = material.getDurabilityFactor();
        this.damageReductionAmountArray = material.getDamageReduction();
        this.enchantability = material.getEnchantability();
        this.soundEvent = sound;
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        this.bonus = new LazyValue<>(bonus);
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = new LazyValue<>(repairMaterial);
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return DURABILITY_ARRAY[slotIn.getIndex()] * this.durabilityFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    @Nonnull
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Nonnull
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
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
        return this.ingredient.getValue();
    }

    @Nonnull
    public ArmorTiers getType() {
        return this;
    }

    public Effect getSetBonus() {
        return this.bonus.getValue();
    }

    public ITextComponent getTextComponent() {
        return this.bonus.getValue().getDisplayName();
    }
}
