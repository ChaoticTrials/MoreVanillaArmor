package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HeavyArmorEffect extends ArmorEffect {

    public HeavyArmorEffect() {
        super(MobEffectCategory.HARMFUL);
    }

    @SubscribeEvent
    public static void playerDamagedEvent(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            if (player.hasEffect(ModRegistries.HEAVY.get())) {
                if (event.getSource() == DamageSource.FALL) {
                    // Increase damage by 50% per level (example Heavy II increases damage by 100%, Heavy III increases damage by 150%)
                    //noinspection ConstantConditions
                    event.setAmount(event.getAmount() + event.getAmount() * ((player.getEffect(ModRegistries.HEAVY.get())).getAmplifier() + 1) * 0.5F);
                }
            }
        }
    }

    @Override
    public void applyEffectTick(@Nonnull LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof Player) {
            Player player = (Player) livingEntity;
            if (player.isInWater()) {
                BlockPos pos = player.blockPosition();
                BlockState state = player.level.getBlockState(pos);
                // Prevent player from swimming up in water
                if (!state.getFluidState().isEmpty()) {
                    player.setDeltaMovement(player.getDeltaMovement().add(0.0D, player.isSwimming() ? -0.06D : -0.03D, 0.0D));
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
