package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class Armor extends ArmorItem implements IForgeItem {
    private ArmorTypes armorType;
    private EquipmentSlotType slotType;

    public Armor(ArmorTypes type, EquipmentSlotType slot) {
        super(type.getType(), slot, new Item.Properties().group(MoreVanillaArmor.creativeTab));
        this.armorType = type;
        this.slotType = slot;
    }

    public ArmorTypes getType() {
        return this.armorType;
    }

    public EquipmentSlotType getSlotType() {
        return this.slotType;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        ArmorTypes fullArmorSetType = this.getArmorSetType(player);
        if (fullArmorSetType != null) {
            switch (fullArmorSetType) {
                case BONE:
                    // Reduces detection distance from mobs
                    break;
                case COAL:
                    break;
                case EMERALD:
                    // discounts from villagers? level up villagers faster?
                    player.addPotionEffect(new EffectInstance(Effects.LUCK, 10, 0, false, false));
                    break;
                case ENDER:
                    // don't aggro endermen?
                    // shortcut to end?
                    // teleportation?
                    break;
                case FIERY:
                    // Gives fire resistance
                    player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 10, 0, false, false));
                    break;
                case GLOWSTONE:
                    // Lights up the area around you
                    break;
                case LAPIS:
                    // Increase enchant effect of equipped tools
                    break;
                case NETHER:
                    // Not sure...
                    break;
                case OBSIDIAN:
                    // Increases strength
                    player.addPotionEffect((new EffectInstance(Effects.STRENGTH, 10, 0, false, false)));
                    break;
                case QUARTZ:
                    // Prevents blindness
                    player.addPotionEffect((new EffectInstance(Effects.NIGHT_VISION, 10, 0, false, false)));
                    break;
                case PRISMARINE:
                    // Allows water breathing and freedom of movement in water
                    player.addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER, 10, 0, false, false));
                    break;
                case REDSTONE:
                    // Speed boost for player
                    player.addPotionEffect(new EffectInstance(Effects.SPEED, 10, 0, false, false));
                    break;
                case SLIME:
                    // Negates fall damage
                    // Treats all blocks as slime blocks
                    player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 10, 2, false, false));
                    break;
            }
        }
        List<ArmorTypes> armorSetContains = this.getArmorTypes(player);
        for (ArmorTypes type : armorSetContains) {
            if (type == ArmorTypes.OBSIDIAN) {
                player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 2, false, false));
            }
        }
    }

    private List<ArmorTypes> getArmorTypes(PlayerEntity player) {
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

    @Nullable
    private ArmorTypes getArmorSetType(PlayerEntity player) {
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
