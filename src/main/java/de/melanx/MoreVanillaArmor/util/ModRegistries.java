package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModRegistries {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreVanillaArmor.MODID);

    public static void registerArmor() {
        EquipmentSlot[] slotTypes = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        String[] names = new String[]{"helmet", "chestplate", "leggings", "boots"};

        ArmorTiers[] values = ArmorTiers.values();
        for (ArmorTiers type : values) {
            for (int i = 0; i < slotTypes.length; i++) {
                int j = i; // idk why but IntelliJ wanted this
                ITEMS.register(type.getName() + "_" + names[j], () -> new Armor(type, slotTypes[j]));
            }
        }
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static String getTooltip(String title) {
        return "tooltip." + MoreVanillaArmor.MODID + "." + title;
    }
}
