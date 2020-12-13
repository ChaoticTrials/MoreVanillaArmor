package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class Armor extends ArmorItem {
    private final ArmorTiers armorType;
    private final EquipmentSlotType slotType;

    public Armor(ArmorTiers type, EquipmentSlotType slot) {
        super(type, slot, new Item.Properties().group(MoreVanillaArmor.creativeTab));
        this.armorType = type;
        this.slotType = slot;
    }

    public ArmorTiers getType() {
        return this.armorType;
    }

    public EquipmentSlotType getSlotType() {
        return this.slotType;
    }
}
