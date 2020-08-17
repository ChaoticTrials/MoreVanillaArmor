package de.melanx.MoreVanillaArmor.effects;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class PowerSourceArmorEffect extends ArmorEffect {

    public PowerSourceArmorEffect()
    {
        super(EffectType.NEUTRAL);
    }

    @Override
    public void performEffect(LivingEntity livingEntity, int amplifier) {
        // TODO: Power all nearby redstone devices while present.

        /*
        // THIS IS DUMB TEST CODE
        BlockState downBlockState = livingEntity.world.getBlockState(livingEntity.getPosition().down());
        if (downBlockState.hasProperty(BlockStateProperties.LIT)) {
            livingEntity.world.setBlockState(livingEntity.getPosition().down(), downBlockState.with(BlockStateProperties.LIT, true));
        }
        */
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

}
