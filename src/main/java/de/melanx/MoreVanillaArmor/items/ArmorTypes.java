package de.melanx.MoreVanillaArmor.items;

public enum ArmorTypes {
    BONE(ArmorTiers.BONE),
    COAL(ArmorTiers.COAL),
    EMERALD(ArmorTiers.EMERALD),
    ENDER(ArmorTiers.ENDER),
    FIERY(ArmorTiers.FIERY),
    GLOWSTONE(ArmorTiers.GLOWSTONE),
    LAPIS(ArmorTiers.LAPIS),
    NETHER(ArmorTiers.NETHER),
    OBSIDIAN(ArmorTiers.OBSIDIAN),
    PAPER(ArmorTiers.PAPER),
    PRISMARINE(ArmorTiers.PRISMARINE),
    QUARTZ(ArmorTiers.QUARTZ),
    REDSTONE(ArmorTiers.REDSTONE),
    SLIME(ArmorTiers.SLIME);

    private ArmorTiers type;
    private String name;

    private ArmorTypes(ArmorTiers type) {
        this.type = type;
        this.name = type.getName();
    }

    public String getName() {
        return name;
    }

    public ArmorTiers getType() {
        return type;
    }
}
