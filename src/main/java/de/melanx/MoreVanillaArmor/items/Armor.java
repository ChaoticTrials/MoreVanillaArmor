package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Armor extends ArmorItem {
    private static final String SETBONUS_KEY = Registry.getTooltip("setbonus");
    private static final String MISSING_KEY = Registry.getTooltip("missing");
    private static final TranslationTextComponent MISSING_PIECES_COMPONENT = new TranslationTextComponent(Registry.getTooltip("missing_pieces"));
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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flag) {
        if (Minecraft.getInstance().player != null) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            ArmorTiers fullArmorSetType = getArmorSetType(player);
            TextFormatting setBonusColor = TextFormatting.DARK_GRAY;
            ITextComponent missingPiecesText = null;
            if (fullArmorSetType == this.armorType) {
                setBonusColor = TextFormatting.GREEN;
            } else {
                List<Item> missingPieces = getMissingPieces(player, this.armorType);
                if (missingPieces.size() > 1) {
                    missingPiecesText = MISSING_PIECES_COMPONENT;
                } else if (missingPieces.size() == 1) {
                    missingPiecesText = missingPieces.get(0).getName();
                }
            }

            //noinspection ConstantConditions
            if (this.armorType.getSetBonus() != null) {
                TranslationTextComponent component = new TranslationTextComponent(SETBONUS_KEY);
                component.append(this.armorType.getTextComponent());
                component.mergeStyle(setBonusColor);
                tooltip.add(component);
                if (missingPiecesText != null) {
                    TranslationTextComponent missingComponent = new TranslationTextComponent(MISSING_KEY);
                    missingComponent.append(missingPiecesText);
                    missingComponent.mergeStyle(setBonusColor);
                    tooltip.add(missingComponent);
                }
            }
        }
    }

    public static List<Item> getMissingPieces(PlayerEntity player, ArmorTiers type) {
        EquipmentSlotType[] slotTypes = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
        String[] names = new String[]{"helmet", "chestplate", "leggings", "boots"};

        List<Item> missingPieces = new ArrayList<>();

        for (int i = 0; i < slotTypes.length; i++) {
            Item armorPiece = player.getItemStackFromSlot(slotTypes[i]).getItem();
            if (!(armorPiece instanceof Armor) || ((Armor) armorPiece).getType() != type) {
                missingPieces.add(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MoreVanillaArmor.MODID, type.getName() + "_" + names[i])));
            }
        }

        return missingPieces;
    }

    public static List<ArmorTiers> getArmorTypes(PlayerEntity player) {
        List<ArmorTiers> types = new ArrayList<>();
        for (ItemStack armorPieceStack : player.inventory.armorInventory) {
            if (armorPieceStack.getItem() instanceof Armor) {
                ArmorTiers type = ((Armor) armorPieceStack.getItem()).getType();
                if (!types.contains(type)) {
                    types.add(type);
                }
            }
        }

        return types;
    }

    public static boolean playerIsWearingArmorSetOfType(PlayerEntity player, ArmorTiers type) {
        for (ItemStack armorPieceStack : player.inventory.armorInventory) {
            if (armorPieceStack.isEmpty()
                    || !(armorPieceStack.getItem() instanceof Armor)
                    || ((Armor) armorPieceStack.getItem()).getType() != type) {
                return false;
            }
        }

        return true;
    }

    @Nullable
    public static ArmorTiers getArmorSetType(PlayerEntity player) {
        Item helmet = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
        if (helmet instanceof Armor) {
            ArmorTiers type = ((Armor) helmet).getType();
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
