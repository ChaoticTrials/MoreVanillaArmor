package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTypes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MoreVanillaArmor.MODID);

    public static void registerArmor() {
        EquipmentSlotType[] slotTypes = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
        String[] names = new String[]{"helmet", "chestplate", "leggings", "boots"};

        for (ArmorTypes type : ArmorTypes.values()) {
            for (int i = 0; i < slotTypes.length; i++) {
                int j = i; // idk why but IntelliJ wanted this
                ITEMS.register(type.getName() + "_" + names[j], () -> new Armor(type, slotTypes[j]));
            }
        }
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
