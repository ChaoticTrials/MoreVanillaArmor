package de.melanx.MoreVanillaArmor.armors;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.effects.ArmorEffectInstance;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ObsidianArmor {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        int amplifier = -1;

        Item helmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem();
        Item leggings = player.getItemBySlot(EquipmentSlot.LEGS).getItem();
        Item boots = player.getItemBySlot(EquipmentSlot.FEET).getItem();
        Item chestplate = player.getItemBySlot(EquipmentSlot.CHEST).getItem();

        if (helmet instanceof Armor && ((Armor) helmet).getType() == ArmorTiers.OBSIDIAN) {
            amplifier += 1;
        }

        if (boots instanceof Armor && ((Armor) boots).getType() == ArmorTiers.OBSIDIAN) {
            amplifier += 1;
        }

        if (leggings instanceof Armor && ((Armor) leggings).getType() == ArmorTiers.OBSIDIAN) {
            amplifier += 2;
        }

        if (chestplate instanceof Armor && ((Armor) chestplate).getType() == ArmorTiers.OBSIDIAN) {
            amplifier += 3;
        }

        if (amplifier >= 0) {
            player.addEffect(new ArmorEffectInstance(ModRegistries.HEAVY.get(), amplifier));
        }

        if (Armor.playerIsWearingArmorSetOfType(player, ArmorTiers.OBSIDIAN)) {
            player.addEffect(new ArmorEffectInstance(ModRegistries.LIGHTNING_MAGNET.get(), 0));
        }
    }
}
