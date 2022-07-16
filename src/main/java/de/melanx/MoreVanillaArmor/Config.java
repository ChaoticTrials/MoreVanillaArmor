package de.melanx.MoreVanillaArmor;

public class Config {

    public enum DefaultMaterial {
        BONE("bone", 7, 1, 2, 3, 2, 20, 0.0f, 0.0f),
        COAL("coal", 6, 1, 1, 2, 1, 10, 0.0F, 0.0F),
        COPPER("copper", 6, 1, 1, 2, 1, 15, 0.0F, 0.0F),
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
        private final int enchantmentValue;
        private final float toughness;
        private final float knockbackResistance;

        DefaultMaterial(String name, int durabilityFactor, int bootsReduction, int leggingsReduction, int chestReduction, int headReduction, int enchantmentValue, float toughness, float knockbackResistance) {
            this.name = name;
            this.durabilityFactor = durabilityFactor;
            this.damageReduction = new int[]{bootsReduction, leggingsReduction, chestReduction, headReduction};
            this.enchantmentValue = enchantmentValue;
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

        public int getEnchantmentValue() {
            return this.enchantmentValue;
        }

        public float getToughness() {
            return this.toughness;
        }

        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    }
}
