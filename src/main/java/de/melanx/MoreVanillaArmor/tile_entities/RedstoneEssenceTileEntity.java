package de.melanx.MoreVanillaArmor.tile_entities;

import de.melanx.MoreVanillaArmor.util.Registry;
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
        if (this.world != null) {
            PlayerEntity closestPlayer = this.world.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ(), 1, false);
            if (closestPlayer == null || closestPlayer.getPosition() != this.pos || !closestPlayer.isPotionActive(Registry.POWER_SOURCE.get())) {
                this.tick++;
                if (this.tick > 40) {
                    this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
                }
            } else if (closestPlayer.getPosition() == this.pos && closestPlayer.isPotionActive(Registry.POWER_SOURCE.get())) {
                this.tick = 0;
            }
        }
    }
}
