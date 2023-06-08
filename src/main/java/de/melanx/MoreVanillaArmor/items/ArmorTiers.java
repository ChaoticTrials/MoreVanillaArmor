package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.Config;
import de.melanx.MoreVanillaArmor.util.Bonus;
import de.melanx.MoreVanillaArmor.util.Bonuses;
import de.melanx.MoreVanillaArmor.util.LazyValue;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum ArmorTiers implements ArmorMaterial {

    BONE(Config.DefaultMaterial.BONE, SoundEvents.SKELETON_AMBIENT, null, () -> Ingredient.of(Tags.Items.BONES)),
    COAL(Config.DefaultMaterial.COAL, SoundEvents.ARMOR_EQUIP_ELYTRA, null, () -> Ingredient.of(Items.COAL), () -> Ingredient.of(Tags.Items.STORAGE_BLOCKS_COAL)),
    COPPER(Config.DefaultMaterial.COPPER, SoundEvents.ARMOR_EQUIP_IRON, Bonuses.LIGHTNING_MAGNET, () -> Ingredient.of(Items.COPPER_INGOT)),
    EMERALD(Config.DefaultMaterial.EMERALD, SoundEvents.ARMOR_EQUIP_DIAMOND, null, () -> Ingredient.of(Tags.Items.GEMS_EMERALD)),
    ENDER(Config.DefaultMaterial.ENDER, SoundEvents.ENDER_EYE_LAUNCH, null, () -> Ingredient.of(Tags.Items.ENDER_PEARLS), () -> Ingredient.of(Tags.Items.END_STONES)),
    FIERY(Config.DefaultMaterial.FIERY, SoundEvents.BLAZE_SHOOT, Bonuses.FIRE_IMMUNITY, () -> Ingredient.of(Items.MAGMA_BLOCK)),
    GLOWSTONE(Config.DefaultMaterial.GLOWSTONE, SoundEvents.ARMOR_EQUIP_GOLD, null, () -> Ingredient.of(Tags.Items.DUSTS_GLOWSTONE), () -> Ingredient.of(Items.GLOWSTONE)),
    LAPIS(Config.DefaultMaterial.LAPIS, SoundEvents.ARMOR_EQUIP_GOLD, null, () -> Ingredient.of(Tags.Items.GEMS_LAPIS), () -> Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS)),
    NETHER(Config.DefaultMaterial.NETHER, SoundEvents.LAVA_EXTINGUISH, null, () -> Ingredient.of(Items.NETHER_BRICK)),
    OBSIDIAN(Config.DefaultMaterial.OBSIDIAN, SoundEvents.ENDER_EYE_DEATH, Bonuses.HEAVY, () -> Ingredient.of(Tags.Items.OBSIDIAN)),
    PAPER(Config.DefaultMaterial.PAPER, SoundEvents.PLAYER_ATTACK_SWEEP, null, () -> Ingredient.of(Items.PAPER)),
    PRISMARINE(Config.DefaultMaterial.PRISMARINE, SoundEvents.WATER_AMBIENT, null, () -> Ingredient.of(Tags.Items.DUSTS_PRISMARINE), () -> Ingredient.of(Items.PRISMARINE)),
    QUARTZ(Config.DefaultMaterial.QUARTZ, SoundEvents.ARMOR_EQUIP_ELYTRA, null, () -> Ingredient.of(Tags.Items.GEMS_QUARTZ), () -> Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ)),
    REDSTONE(Config.DefaultMaterial.REDSTONE, SoundEvents.ARMOR_EQUIP_GOLD, Bonuses.POWER_SOURCE, () -> Ingredient.of(Tags.Items.DUSTS_REDSTONE), () -> Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE)),
    SLIME(Config.DefaultMaterial.SLIME, SoundEvents.SLIME_BLOCK_STEP, Bonuses.DAMAGE_REDUCTION, () -> Ingredient.of(Tags.Items.SLIMEBALLS), () -> Ingredient.of(Items.SLIME_BLOCK)),
    STONE(Config.DefaultMaterial.STONE, SoundEvents.STONE_BREAK, null, () -> Ingredient.of(Tags.Items.STONE)),
    WOOD(Config.DefaultMaterial.WOOD, SoundEvents.WOODEN_DOOR_OPEN, null, () -> Ingredient.of(ItemTags.LOGS));

    private static final int[] DURABILITY_ARRAY = new int[]{13, 15, 16, 11}; // vanilla copy [boots, leggings, chest plate, head]
    private final Config.DefaultMaterial material;
    private final SoundEvent soundEvent;
    private final LazyValue<Ingredient> repairMaterial;
    private final LazyValue<Ingredient> ingredient;
    private final Bonus bonus;

    ArmorTiers(Config.DefaultMaterial material, SoundEvent sound, Bonus bonus, Supplier<Ingredient> repairMaterial, Supplier<Ingredient> ingredient) {
        this.material = material;
        this.soundEvent = sound;
        this.bonus = bonus;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = new LazyValue<>(ingredient);
    }

    ArmorTiers(Config.DefaultMaterial material, SoundEvent sound, Bonus bonus, Supplier<Ingredient> repairMaterial) {
        this.material = material;
        this.soundEvent = sound;
        this.bonus = bonus;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = new LazyValue<>(repairMaterial);
    }

    public int getDurabilityForType(ArmorItem.Type type) {
        return DURABILITY_ARRAY[type.getSlot().getIndex()] * this.material.getDurabilityFactor();
    }

    public int getDefenseForType(ArmorItem.Type type) {
        return this.material.getDamageReduction()[type.getSlot().getIndex()];
    }

    public int getEnchantmentValue() {
        return this.material.getEnchantmentValue();
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
        return this.material.getName();
    }

    public float getToughness() {
        return this.material.getToughness();
    }

    public float getKnockbackResistance() {
        return this.material.getKnockbackResistance();
    }

    @Nonnull
    public Ingredient getIngredient() {
        return this.ingredient.get();
    }

    @Nonnull
    public ArmorTiers getType() {
        return this;
    }

    @Nullable
    public Component getBonusName() {
        return this.bonus != null ? this.bonus.getDisplayName() : null;
    }
}
