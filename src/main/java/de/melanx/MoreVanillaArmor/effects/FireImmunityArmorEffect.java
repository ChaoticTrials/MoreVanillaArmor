package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FireImmunityArmorEffect extends ArmorEffect {

    public FireImmunityArmorEffect() {
        super(MobEffectCategory.BENEFICIAL);
    }

    @SubscribeEvent
    public static void playerDamagedEvent(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            if (event.getSource().isFire()) {
                if (player.hasEffect(ModRegistries.FIRE_IMMUNITY.get())) {
                    event.setAmount(0.0F);
                }
            }
        }
    }
}
