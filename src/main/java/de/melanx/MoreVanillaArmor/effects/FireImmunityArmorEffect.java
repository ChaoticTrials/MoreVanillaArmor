package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FireImmunityArmorEffect extends ArmorEffect {

    public FireImmunityArmorEffect() { super(EffectType.BENEFICIAL); }

    @SubscribeEvent
    public static void  playerDamagedEvent(LivingDamageEvent event) {

        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();

            if (event.getSource().isFireDamage()) {
                if (Armor.getArmorSetType(player) != null && Armor.getArmorSetType(player) == ArmorTypes.FIERY) {
                    event.setAmount(0.0F);
                }
            }
        }
    }
}
