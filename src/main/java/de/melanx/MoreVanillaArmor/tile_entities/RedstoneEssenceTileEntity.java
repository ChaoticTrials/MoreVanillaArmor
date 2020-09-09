package de.melanx.MoreVanillaArmor.tile_entities;

import de.melanx.MoreVanillaArmor.effects.ArmorEffects;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class RedstoneEssenceTileEntity extends TileEntity implements ITickableTileEntity {

    private int tick = 0;

    public RedstoneEssenceTileEntity(final TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public RedstoneEssenceTileEntity() {
        this(ModTileEntityTypes.REDSTONE_ESSENCE.get());
    }

    @Override
    public void tick() {

        PlayerEntity closestPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1, false);
        if (closestPlayer == null || closestPlayer.getPosition() != pos || !closestPlayer.isPotionActive(ArmorEffects.POWER_SOURCE)) {
            tick++;
            if (tick > 40) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        else if (closestPlayer != null && closestPlayer.getPosition() == pos && closestPlayer.isPotionActive(ArmorEffects.POWER_SOURCE)) {
            tick=0;
        }
    }
}
