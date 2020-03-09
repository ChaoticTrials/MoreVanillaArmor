package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import de.melanx.MoreVanillaArmor.util.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

public class Languages {

    public static class English extends LanguageProvider {
        public English(DataGenerator generator) {
            super(generator, MoreVanillaArmor.MODID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add("itemGroup.morevanillaarmor", "MoreVanillaArmor");
            for (RegistryObject<Item> object : Registry.ITEMS.getEntries()) {
                Armor item = (Armor) object.get();
                EquipmentSlotType slotType = item.getSlotType();
                String name = item.getRegistryName().getPath().split("_")[0];
                add(item, Languages.capitalize(name) + " " + getSlotTypeName(slotType));
            }
        }

        private static String getSlotTypeName(EquipmentSlotType type) {
            switch (type) {
                case HEAD:
                    return "Helmet";
                case CHEST:
                    return "Chestplate";
                case LEGS:
                    return "Leggings";
                case FEET:
                    return "Boots";
                default:
                    return null;
            }
        }
    }

    public static class German extends LanguageProvider {
        public German(DataGenerator generator) {
            super(generator, MoreVanillaArmor.MODID, "de_de");
        }

        @Override
        protected void addTranslations() {
            for (RegistryObject<Item> object : Registry.ITEMS.getEntries()) {
                Armor item = (Armor) object.get();
                EquipmentSlotType slotType = item.getSlotType();
                String name = item.getRegistryName().getPath().split("_")[0];
                add(item, getGermanMaterialName(item) + getSlotTypeName(slotType));
            }
        }

        private static String getGermanMaterialName(Item item) {
            ArmorTiers type = ((Armor) item).getType().getType();
            switch (type) {
                case BONE:
                    return "Knochen";
                case COAL:
                    return "Kohle";
                case EMERALD:
                    return "Smaragd";
                case ENDER:
                    return "Ender";
                case FIERY:
                    return "Feuer";
                case GLOWSTONE:
                    return "Glowstone";
                case LAPIS:
                    return "Lapis";
                case NETHER:
                    return "Nether";
                case OBSIDIAN:
                    return "Obsidian";
                case PAPER:
                    return "Papier";
                case PRISMARINE:
                    return "Prismarin";
                case QUARTZ:
                    return "Netherquartz";
                case REDSTONE:
                    return "Redstone";
                case SLIME:
                    return "Schleim";
                default:
                    return null;
            }
        }

        private static String getSlotTypeName(EquipmentSlotType type) {
            switch (type) {
                case HEAD:
                    return "helm";
                case CHEST:
                    return "harnisch";
                case LEGS:
                    return "beinschutz";
                case FEET:
                    return "stiefel";
                default:
                    return null;
            }
        }
    }

    private static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
