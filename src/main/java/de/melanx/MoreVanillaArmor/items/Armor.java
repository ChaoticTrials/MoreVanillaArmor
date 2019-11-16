package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class Armor extends ArmorItem {
    public Armor(ArmorTypes type, EquipmentSlotType slot, String typeName) {
        super(type.getType(), slot, new Item.Properties().group(MoreVanillaArmor.creativeTab));
        this.setRegistryName(MoreVanillaArmor.MODID, type.getName() + "_" + typeName);
    }
}
