package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Armor extends ArmorItem {
    private static final String SETBONUS_KEY = ModRegistries.getTooltip("setbonus");
    private static final String MISSING_KEY = ModRegistries.getTooltip("missing");
    private static final TranslatableComponent MISSING_PIECES_COMPONENT = new TranslatableComponent(ModRegistries.getTooltip("missing_pieces"));
    private final ArmorTiers armorType;
    private final EquipmentSlot slotType;

    public Armor(ArmorTiers type, EquipmentSlot slot) {
        super(type, slot, new Item.Properties().tab(MoreVanillaArmor.creativeTab));
        this.armorType = type;
        this.slotType = slot;
    }

    public ArmorTiers getType() {
        return this.armorType;
    }

    public EquipmentSlot getSlotType() {
        return this.slotType;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            ArmorTiers fullArmorSetType = getArmorSetType(player);
            ChatFormatting setBonusColor = ChatFormatting.DARK_GRAY;
            Component missingPiecesText = null;
            if (fullArmorSetType == this.armorType) {
                setBonusColor = ChatFormatting.GREEN;
            } else {
                List<Item> missingPieces = getMissingPieces(player, this.armorType);
                if (missingPieces.size() > 1) {
                    missingPiecesText = MISSING_PIECES_COMPONENT;
                } else if (missingPieces.size() == 1) {
                    missingPiecesText = missingPieces.get(0).getDescription();
                }
            }

            if (this.armorType.getBonusName() != null) {
                TranslatableComponent component = new TranslatableComponent(SETBONUS_KEY);
                component.append(this.armorType.getBonusName());
                component.withStyle(setBonusColor);
                tooltip.add(component);
                if (missingPiecesText != null) {
                    TranslatableComponent missingComponent = new TranslatableComponent(MISSING_KEY);
                    missingComponent.append(missingPiecesText);
                    missingComponent.withStyle(setBonusColor);
                    tooltip.add(missingComponent);
                }
            }
        }
    }

    public static List<Item> getMissingPieces(Player player, ArmorTiers type) {
        EquipmentSlot[] slotTypes = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        String[] names = new String[]{"helmet", "chestplate", "leggings", "boots"};

        List<Item> missingPieces = new ArrayList<>();

        for (int i = 0; i < slotTypes.length; i++) {
            Item armorPiece = player.getItemBySlot(slotTypes[i]).getItem();
            if (!(armorPiece instanceof Armor) || ((Armor) armorPiece).getType() != type) {
                missingPieces.add(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MoreVanillaArmor.MODID, type.getName() + "_" + names[i])));
            }
        }

        return missingPieces;
    }

    public static List<ArmorTiers> getArmorTypes(LivingEntity player) {
        List<ArmorTiers> types = new ArrayList<>();
        for (ItemStack armorPieceStack : player.getArmorSlots()) {
            if (armorPieceStack.getItem() instanceof Armor) {
                ArmorTiers type = ((Armor) armorPieceStack.getItem()).getType();
                if (!types.contains(type)) {
                    types.add(type);
                }
            }
        }

        return types;
    }

    public static boolean entityIsWearingArmorSetOfType(LivingEntity entity, ArmorTiers type) {
        for (ItemStack armorPieceStack : entity.getArmorSlots()) {
            if (armorPieceStack.isEmpty()
                    || !(armorPieceStack.getItem() instanceof Armor)
                    || ((Armor) armorPieceStack.getItem()).getType() != type) {
                return false;
            }
        }

        return true;
    }

    @Nullable
    public static ArmorTiers getArmorSetType(LivingEntity entity) {
        Item helmet = entity.getItemBySlot(EquipmentSlot.HEAD).getItem();
        if (helmet instanceof Armor) {
            ArmorTiers type = ((Armor) helmet).getType();
            for (ItemStack armorPieceStack : entity.getArmorSlots()) {
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

    public static int calcAmplifier(LivingEntity entity, ArmorTiers type) {
        int amplifier = 0;

        Item helmet = entity.getItemBySlot(EquipmentSlot.HEAD).getItem();
        Item leggings = entity.getItemBySlot(EquipmentSlot.LEGS).getItem();
        Item boots = entity.getItemBySlot(EquipmentSlot.FEET).getItem();
        Item chestplate = entity.getItemBySlot(EquipmentSlot.CHEST).getItem();

        if (helmet instanceof Armor && ((Armor) helmet).getType() == type) {
            amplifier += 1;
        }

        if (boots instanceof Armor && ((Armor) boots).getType() == type) {
            amplifier += 1;
        }

        if (leggings instanceof Armor && ((Armor) leggings).getType() == type) {
            amplifier += 2;
        }

        if (chestplate instanceof Armor && ((Armor) chestplate).getType() == type) {
            amplifier += 3;
        }

        return amplifier;
    }
}
