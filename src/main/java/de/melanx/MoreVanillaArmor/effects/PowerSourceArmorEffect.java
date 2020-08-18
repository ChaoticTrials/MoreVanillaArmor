package de.melanx.MoreVanillaArmor.effects;

import de.melanx.MoreVanillaArmor.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class PowerSourceArmorEffect extends ArmorEffect {

    public PowerSourceArmorEffect()
    {
        super(EffectType.NEUTRAL);
    }

    @Override
    public void performEffect(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.isOnGround()) {
            BlockState blockState = livingEntity.world.getBlockState(livingEntity.getPosition());
            if (blockState.getMaterial() == Material.AIR) {
                BlockState invisiTorch = ModBlocks.INVISI_TORCH.getDefaultState();
                livingEntity.world.setBlockState(livingEntity.getPosition(), invisiTorch);
            }
        }

    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

}
