package de.melanx.MoreVanillaArmor.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Armor extends ArmorItem {
    private ArmorTypes armorType;
    private EquipmentSlotType slotType;

    public Armor(ArmorTypes type, EquipmentSlotType slot) {
        super(type.getType(), slot, new Item.Properties().group(ItemGroup.COMBAT));
        this.armorType = type;
        this.slotType = slot;
    }

    public ArmorTypes getType() {
        return this.armorType;
    }

    public EquipmentSlotType getSlotType() {
        return this.slotType;
    }
}
