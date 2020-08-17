package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HeavyArmorEffect extends ArmorEffect {

    public HeavyArmorEffect()
    {
        super(EffectType.HARMFUL);
    }

    @SubscribeEvent
    public static void  playerDamagedEvent(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();

            if (player.isPotionActive(ArmorEffects.HEAVY)) {
                if (event.getSource() == DamageSource.FALL) {
                    // Increase damage by 50% per level (example Heavy II increases damage by 100%, Heavy III increases damage by 150%)
                    event.setAmount(event.getAmount() + event.getAmount() * (player.getActivePotionEffect(ArmorEffects.HEAVY).getAmplifier()+1)*0.5F);
                }
            }
        }
    }

    /*
    TODO: Make player swim slower in water.
    TODO: Disable or hamper elytra flight.
    @Override
    public void performEffect(LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) livingEntity;
            //if (player.isInWater() && player.getMotion().getY() > 0) {
                //player.setMotion(player.getMotion().mul(0, (double) 1/(amplifier), 0));
                //if (player.world.getBlockState(player.getPosition()).getMaterial() == Material.WATER) {
                //    player.world.setBlockState(player.getPosition(), Blocks.AIR.getDefaultState());
                //}

                BlockPos posBelow = player.getPosition().down();
                BlockState blockStateBelow = player.world.getBlockState(posBelow);
                // Prevent player from swimming up in water
                if (blockStateBelow.getMaterial() == Material.WATER) {
                    //player.setMotion(player.getMotion().add(0.0D, -0.1D, 0.0D));
                    blockStateBelow.getBlock().
                }
            //} else if (player.isElytraFlying()) {
                // Players should not be able to fly effectively with an elytra
              //  player.setMotion(player.getMotion().add(0.0D, -0.01D * (1 + amplifier), 0.0D));
            //}
        }
    }
    */

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

}
