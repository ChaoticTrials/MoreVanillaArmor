package de.melanx.MoreVanillaArmor.armors;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.effects.ArmorEffectInstance;
import de.melanx.MoreVanillaArmor.effects.ArmorEffects;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ObsidianArmor {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;

        int amplifier = -1;

        Item helmet = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
        Item leggings = player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem();
        Item boots = player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem();
        Item chestplate = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
        if (helmet instanceof Armor && ((Armor) helmet).getType() == ArmorTiers.OBSIDIAN) {
            amplifier +=1;
        }
        if (boots instanceof Armor && ((Armor) boots).getType() == ArmorTiers.OBSIDIAN) {
            amplifier +=1;
        }
        if (leggings instanceof Armor && ((Armor) leggings).getType() == ArmorTiers.OBSIDIAN) {
            amplifier +=2;
        }
        if (chestplate instanceof Armor && ((Armor) chestplate).getType() == ArmorTiers.OBSIDIAN) {
            amplifier +=3;
        }

        if (amplifier >= 0) {
            player.addPotionEffect(new ArmorEffectInstance(ArmorEffects.HEAVY, amplifier));
        }
        if (Armor.playerIsWearingArmorSetOfType(player, ArmorTiers.OBSIDIAN)) {
            player.addPotionEffect(new ArmorEffectInstance(ArmorEffects.DAMAGE_REDUCTION, 0));
        }
    }
}
