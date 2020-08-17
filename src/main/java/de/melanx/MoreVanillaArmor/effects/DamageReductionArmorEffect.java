package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DamageReductionArmorEffect extends ArmorEffect {

    public DamageReductionArmorEffect() { super(EffectType.BENEFICIAL); }

    @SubscribeEvent
    public static void  playerDamagedEvent(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();

            if (player.isPotionActive(ArmorEffects.DAMAGE_REDUCTION) && event.getSource() != DamageSource.FALL) {
                int damageReduction = 0;
                // Don't trump other mods/effects that might reduce the damage to less than .25
                if (event.getAmount() > 0.25) {
                    damageReduction = 1 + player.getActivePotionEffect(ArmorEffects.DAMAGE_REDUCTION).getAmplifier();
                    if (event.getAmount() - damageReduction > 0.25) {
                        event.setAmount(event.getAmount() - damageReduction);
                    } else {
                        event.setAmount(0.25F);
                    }
                }
            }
        }
    }
}
