package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.blocks.ModBlocks;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class PowerSourceArmorEffect extends ArmorEffect {

    public PowerSourceArmorEffect()
    {
        super(MobEffectCategory.NEUTRAL);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.isOnGround()) {
            BlockState blockState = livingEntity.level.getBlockState(livingEntity.blockPosition());
            if (blockState.getMaterial() == Material.AIR) {
                BlockState invisiTorch = ModBlocks.REDSTONE_ESSENCE.get().defaultBlockState();
                livingEntity.level.setBlockAndUpdate(livingEntity.blockPosition(), invisiTorch);
            }
        }

    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
