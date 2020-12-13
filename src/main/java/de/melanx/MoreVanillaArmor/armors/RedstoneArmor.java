package de.melanx.MoreVanillaArmor.armors;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.effects.ArmorEffectInstance;
import de.melanx.MoreVanillaArmor.effects.ArmorEffects;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RedstoneArmor {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {

        PlayerEntity player = event.player;

        if (Armor.playerIsWearingArmorSetOfType(player, ArmorTiers.REDSTONE)) {
            player.addPotionEffect(new ArmorEffectInstance(ArmorEffects.POWER_SOURCE, 0));
        }
    }
}
