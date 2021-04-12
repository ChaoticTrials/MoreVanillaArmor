package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.Registry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HeavyArmorEffect extends ArmorEffect {

    public HeavyArmorEffect() {
        super(EffectType.HARMFUL);
    }

    @SubscribeEvent
    public static void playerDamagedEvent(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();

            if (player.isPotionActive(Registry.HEAVY.get())) {
                if (event.getSource() == DamageSource.FALL) {
                    // Increase damage by 50% per level (example Heavy II increases damage by 100%, Heavy III increases damage by 150%)
                    //noinspection ConstantConditions
                    event.setAmount(event.getAmount() + event.getAmount() * ((player.getActivePotionEffect(Registry.HEAVY.get())).getAmplifier() + 1) * 0.5F);
                }
            }
        }
    }

    @Override
    public void performEffect(@Nonnull LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if (player.isInWater()) {
                BlockPos pos = player.getPosition();
                BlockState state = player.world.getBlockState(pos);
                // Prevent player from swimming up in water
                if (!state.getFluidState().isEmpty()) {
                    player.setMotion(player.getMotion().add(0.0D, player.isSwimming() ? -0.06D : -0.03D, 0.0D));
                }
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

}
