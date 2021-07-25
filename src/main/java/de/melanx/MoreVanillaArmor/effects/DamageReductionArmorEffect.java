package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DamageReductionArmorEffect extends ArmorEffect {

    public DamageReductionArmorEffect() {
        super(MobEffectCategory.BENEFICIAL);
    }

    @SubscribeEvent
    public static void  playerDamagedEvent(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            if (player.hasEffect(ModRegistries.DAMAGE_REDUCTION.get()) && event.getSource() != DamageSource.FALL) {
                int damageReduction;
                // Don't trump other mods/effects that might reduce the damage to less than .25
                if (event.getAmount() > 0.25) {
                    //noinspection ConstantConditions
                    damageReduction = 1 + player.getEffect(ModRegistries.DAMAGE_REDUCTION.get()).getAmplifier();
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
