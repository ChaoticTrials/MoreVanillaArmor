package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class Armor extends ItemArmor {
    public Armor(ArmorTypes type, EntityEquipmentSlot slot, String typeName) {
        super(type.getType(), slot, new Item.Properties().group(MoreVanillaArmor.creativeTab));
        this.setRegistryName(MoreVanillaArmor.MODID, type.getName() + "_" + typeName);
    }
}
