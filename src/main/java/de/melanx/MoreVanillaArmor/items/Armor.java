package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
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

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Minecraft.getInstance().player != null) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            ArmorTypes fullArmorSetType = getArmorSetType(player);
            String setBonusColor = "\u00A78"; // Dark Gray
            String setBonusText = "";
            String missingPiecesText = "";
            if (fullArmorSetType == this.armorType) {
                setBonusColor = "\u00A7a"; // Green
            }
            else {
                List<String> missingPieces = getMissingPieces(player, this.armorType);
                if (missingPieces.size() > 1) {
                    missingPiecesText = "multiple pieces";
                }
                else if (missingPieces.size() == 1) {
                    missingPiecesText = this.armorType.getName() + " " + missingPieces.get(0);
                }

            }

            switch (this.armorType) {
                case FIERY:
                    setBonusText = "Fire Immunity";
                    break;
            }
            if (setBonusText != "") {
                tooltip.add(new StringTextComponent(setBonusColor + "Set Bonus: " + setBonusText + setBonusColor));
            }
            if (missingPiecesText != "") {
                tooltip.add(new StringTextComponent(setBonusColor + "Missing " + missingPiecesText + setBonusColor));
            }
        }

    }

    public static List<String> getMissingPieces(PlayerEntity player, ArmorTypes type) {
        EquipmentSlotType[] slotTypes = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
        String[] names = new String[]{"helmet", "chestplate", "leggings", "boots"};

        List<String> missingPieces = new ArrayList();

        for (int i = 0; i < slotTypes.length; i++) {
            Item armorPiece = player.getItemStackFromSlot(slotTypes[i]).getItem();
            if (!(armorPiece instanceof Armor) || ((Armor) armorPiece).getType() != type) {
                missingPieces.add(names[i]);
            }
        }

        return missingPieces;
    }

    public static List<ArmorTypes> getArmorTypes(PlayerEntity player) {
        List<ArmorTypes> types = new ArrayList();
        for (ItemStack armorPieceStack : player.inventory.armorInventory) {
            if (armorPieceStack.getItem() instanceof Armor) {
                ArmorTypes type = ((Armor) armorPieceStack.getItem()).getType();
                if (!types.contains(type)) {
                    types.add(type);
                }
            }
        }
        return types;
    }

    public static boolean playerIsWearingArmorSetOfType(PlayerEntity player, ArmorTypes type) {
        for (ItemStack armorPieceStack : player.inventory.armorInventory) {
            if (
                    armorPieceStack.isEmpty()
                    || !(armorPieceStack.getItem() instanceof Armor)
                    || ((Armor) armorPieceStack.getItem()).getType() != type
            ) {
                return false;
            }
        }
        return true;
    }

    public static boolean playerIsWearingArmorOfType(PlayerEntity player, ArmorTypes type) {
        for (ItemStack armorPieceStack : player.inventory.armorInventory) {
            if (armorPieceStack.getItem() instanceof Armor) {
                if (((Armor) armorPieceStack.getItem()).getType() == type) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public static ArmorTypes getArmorSetType(PlayerEntity player) {
        Item helmet = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
        if (helmet instanceof Armor) {
            ArmorTypes type = ((Armor) helmet).getType();
            for (ItemStack armorPieceStack : player.inventory.armorInventory) {
                Item armorPiece = armorPieceStack.getItem();
                if (armorPiece instanceof Armor) {
                    if (((Armor) armorPiece).getType() == type) {
                        continue;
                    }
                }
                return null;
            }
            return type;
        }
        return null;
    }
}
