package de.melanx.MoreVanillaArmor;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Config {

    public static final ForgeConfigSpec COMMON_CONFIG;
    private static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();

    static {
        init(CONFIG_BUILDER);
        COMMON_CONFIG = CONFIG_BUILDER.build();
    }

    public static Map<DefaultMaterial, Material> materials;

    private static void init(ForgeConfigSpec.Builder builder) {
        materials = new HashMap<>();

        for (DefaultMaterial material : DefaultMaterial.values()) {
            materials.put(material, new Material(
                    material.name,
                    builder.comment("Will be multiplied with", "11 for head", "16 for chestplate", "15 for leggings", "13 for boots")
                            .defineInRange(material.name + ".durabilityFactor", material.durabilityFactor, 0, Integer.MAX_VALUE),
                    builder.defineInRange(material.name + ".damageReduction.boots", material.damageReduction[0], 0, Integer.MAX_VALUE),
                    builder.defineInRange(material.name + ".damageReduction.leggings", material.damageReduction[1], 0, Integer.MAX_VALUE),
                    builder.defineInRange(material.name + ".damageReduction.chest", material.damageReduction[2], 0, Integer.MAX_VALUE),
                    builder.defineInRange(material.name + ".damageReduction.head", material.damageReduction[3], 0, Integer.MAX_VALUE),
                    builder.defineInRange(material.name + ".enchantability", material.enchantability, 0, Integer.MAX_VALUE),
                    builder.defineInRange(material.name + ".thoughness", material.toughness, 0, Float.MAX_VALUE),
                    builder.defineInRange(material.name + ".knockbackResistance", material.knockbackResistance, 0, Float.MAX_VALUE)
            ));
        }
    }

    public static class Material {
        private final String name;
        private final ForgeConfigSpec.IntValue durabilityFactor;
        private final ForgeConfigSpec.IntValue bootsReduction;
        private final ForgeConfigSpec.IntValue leggingsReduction;
        private final ForgeConfigSpec.IntValue chestReduction;
        private final ForgeConfigSpec.IntValue headReduction;
        private final ForgeConfigSpec.IntValue enchantability;
        private final ForgeConfigSpec.DoubleValue toughness;
        private final ForgeConfigSpec.DoubleValue knockbackResistance;

        private Material(String name, ForgeConfigSpec.IntValue durabilityFactor, ForgeConfigSpec.IntValue bootsReduction, ForgeConfigSpec.IntValue leggingsReduction, ForgeConfigSpec.IntValue chestReduction, ForgeConfigSpec.IntValue headReduction, ForgeConfigSpec.IntValue enchantability, ForgeConfigSpec.DoubleValue toughness, ForgeConfigSpec.DoubleValue knockbackResistance) {
            this.name = name;
            this.durabilityFactor = durabilityFactor;
            this.enchantability = enchantability;
            this.bootsReduction = bootsReduction;
            this.chestReduction = chestReduction;
            this.leggingsReduction = leggingsReduction;
            this.headReduction = headReduction;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        public String getName() {
            return this.name;
        }

        public int getDurabilityFactor() {
            return this.durabilityFactor.get();
        }

        public int[] getDamageReduction() {
            return new int[]{this.bootsReduction.get(), this.leggingsReduction.get(), this.chestReduction.get(), this.headReduction.get()};
        }

        public int getEnchantability() {
            return this.enchantability.get();
        }

        public float getToughness() {
            return (float) (double) this.toughness.get();
        }

        public float getKnockbackResistance() {
            return (float) (double) this.knockbackResistance.get();
        }
    }

    public enum DefaultMaterial {
        BONE("bone", 7, 1, 2, 3, 2, 20, 0.0f, 0.0f),
        COAL("coal", 6, 1, 1, 2, 1, 10, 0.0F, 0.0F),
        EMERALD("emerald", 25, 2, 4, 6, 2, 25, 1.9F, 0.0F),
        ENDER("ender", 21, 1, 3, 5, 2, 15, 0.1F, 0.0F),
        FIERY("fiery", 13, 1, 3, 4, 1, 13, 0.0F, 0.0F),
        GLOWSTONE("glowstone", 7, 1, 2, 3, 1, 15, 0.0F, 0.0F),
        LAPIS("lapis", 8, 1, 3, 5, 1, 30, 0.0F, 0.0F),
        NETHER("nether", 11, 2, 2, 4, 1, 20, 0.1F, 0.0F),
        OBSIDIAN("obsidian", 40, 3, 6, 8, 2, 8, 1.5F, 0.3F),
        PAPER("paper", 1, 0, 1, 2, 0, 13, 0.0F, 0.0F),
        PRISMARINE("prismarine", 14, 2, 3, 5, 1, 15, 0.1F, 0.0F),
        QUARTZ("quartz", 7, 1, 1, 2, 1, 15, 0.0F, 0.0F),
        REDSTONE("redstone", 8, 1, 3, 5, 1, 15, 0.0F, 0.0F),
        SLIME("slime", 36, 4, 6, 8, 3, 14, 0.1F, 0.2F),
        STONE("stone", 4, 1, 1, 3, 1, 7, 0.0F, 0.0F),
        WOOD("wood", 8, 1, 2, 3, 2, 5, 0.0F, 0.0F);

        private final String name;
        private final int durabilityFactor;
        private final int[] damageReduction;
        private final int enchantability;
        private final float toughness;
        private final float knockbackResistance;

        DefaultMaterial(String name, int durabilityFactor, int bootsReduction, int leggingsReduction, int chestReduction, int headReduction, int enchantability, float toughness, float knockbackResistance) {
            this.name = name;
            this.durabilityFactor = durabilityFactor;
            this.damageReduction = new int[]{bootsReduction, leggingsReduction, chestReduction, headReduction};
            this.enchantability = enchantability;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        public String getName() {
            return this.name;
        }

        public int getDurabilityFactor() {
            return this.durabilityFactor;
        }

        public int[] getDamageReduction() {
            return this.damageReduction;
        }

        public int getEnchantability() {
            return this.enchantability;
        }

        public float getToughness() {
            return this.toughness;
        }

        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    }
}
