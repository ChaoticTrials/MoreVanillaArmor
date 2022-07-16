package de.melanx.MoreVanillaArmor.events;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerDamagedEvent {

    @SubscribeEvent
    public static void  playerDamagedEvent(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getSource().isFire()) {
                if (Armor.getArmorSetType(player) != null && Armor.getArmorSetType(player) == ArmorTiers.FIERY) {
                    event.setAmount(0.0F);
                }
            }
        }
    }
}
