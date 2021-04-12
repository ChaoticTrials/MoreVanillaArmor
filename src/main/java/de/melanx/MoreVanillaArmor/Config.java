package de.melanx.MoreVanillaArmor;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    public static final ForgeConfigSpec COMMON_CONFIG;
    private static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();

    static {
        init(CONFIG_BUILDER);
        COMMON_CONFIG = CONFIG_BUILDER.build();
    }

    private static void init(ForgeConfigSpec.Builder builder) {
        for (Material material : Material.values()) {
            builder.comment(String.format("Values for %s armor", material.name));
            builder.comment("Will be multiplied with", "11 for head", "16 for chestplate", "15 for leggings", "13 for boots")
                    .defineInRange(material.name + ".durabilityFactor", material.durabilityFactor, 0, Integer.MAX_VALUE);
            builder.defineInRange(material.name + ".headDamageReduction", material.damageReduction[3], 0, Integer.MAX_VALUE);
            builder.defineInRange(material.name + ".chestDamageReduction", material.damageReduction[2], 0, Integer.MAX_VALUE);
            builder.defineInRange(material.name + ".leggingsDamageReduction", material.damageReduction[1], 0, Integer.MAX_VALUE);
            builder.defineInRange(material.name + ".bootsDamageReduction", material.damageReduction[0], 0, Integer.MAX_VALUE);
            builder.defineInRange(material.name + ".enchantability", material.enchantability, 0, Integer.MAX_VALUE);
            builder.defineInRange(material.name + ".thoughness", material.toughness, 0, Float.MAX_VALUE);
            builder.defineInRange(material.name + ".knockbackResistance", material.knockbackResistance, 0, Float.MAX_VALUE);
        }
    }

    public enum Material {
        BONE("bone", 15, 1, 4, 5, 2, 14, 1.5f, 0.0f),
        COAL("coal", 10, 1, 2, 3, 1, 11, 0.5F, 0.0F),
        EMERALD("emerald", 69, 4, 8, 12, 4, 25, 3.0F, 0.0F),
        ENDER("ender", 31, 2, 6, 8, 3, 20, 3.14F, 0.0F),
        FIERY("fiery", 17, 2, 4, 7, 3, 15, 1.3F, 0.0F),
        GLOWSTONE("glowstone", 13, 2, 5, 6, 2, 13, 1.0F, 0.0F),
        LAPIS("lapis", 13, 2, 5, 6, 2, 13, 1.0F, 0.0F),
        NETHER("nether", 17, 3, 4, 7, 3, 66, 2.1F, 0.0F),
        OBSIDIAN("obsidian", 81, 5, 9, 15, 4, 11, 4.0F, 0.0F),
        PAPER("paper", 1, 0, 1, 2, 0, 13, 0.0F, 0.0F),
        PRISMARINE("prismarine", 21, 4, 6, 8, 2, 20, 1.0F, 0.0F),
        QUARTZ("quartz", 10, 1, 2, 3, 1, 11, 0.5F, 0.0F),
        REDSTONE("redstone", 13, 2, 5, 6, 2, 13, 1.0F, 0.0F),
        SLIME("slime", 42, 1, 3, 4, 2, 20, 0.3F, 0.2F),
        STONE("stone", 5, 1, 3, 4, 2, 7, 0.3F, 0.0F),
        WOOD("wood", 13, 0, 2, 3, 2, 5, 0.1F, 0.1F);

        private final String name;
        private final int durabilityFactor;
        private final int[] damageReduction;
        private final int enchantability;
        private final float toughness;
        private final float knockbackResistance;

        Material(String name, int durabilityFactor, int bootsReduction, int leggingsReduction, int chestReduction, int headReduction, int enchantability, float toughness, float knockbackResistance) {
            this.name = name;
            this.durabilityFactor = durabilityFactor;
            this.damageReduction = new int[]{bootsReduction, leggingsReduction, chestReduction, headReduction};
            this.enchantability = enchantability;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        public String getName() {
            return name;
        }

        public int getDurabilityFactor() {
            return durabilityFactor;
        }

        public int[] getDamageReduction() {
            return damageReduction;
        }

        public int getEnchantability() {
            return enchantability;
        }

        public float getToughness() {
            return toughness;
        }

        public float getKnockbackResistance() {
            return knockbackResistance;
        }
    }
}
