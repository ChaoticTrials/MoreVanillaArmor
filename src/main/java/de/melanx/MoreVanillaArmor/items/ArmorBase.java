package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.Registry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

public class ArmorBase extends ArmorItem {

    public ArmorBase(String name, IArmorMaterial mat, EquipmentSlotType slotType) {
        super(mat, slotType, new Item.Properties().group(MoreVanillaArmor.creativeTab));
        Registry.registerItem(this, name);
        Registry.registerModel(this);
    }
}
