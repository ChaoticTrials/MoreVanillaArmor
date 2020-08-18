package de.melanx.MoreVanillaArmor.tile_entities;

import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class InvisiTorchTileEntity extends TileEntity implements ITickableTileEntity {

    private int tick;
    private boolean isInitialized = false;

    public InvisiTorchTileEntity(final TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public InvisiTorchTileEntity() {
        this(ModTileEntityTypes.INVISI_TORCH_TILE_ENTITY);
    }

    @Override
    public void tick() {
        if (!isInitialized) {
            init();
        }
        tick++;
        if (tick > 40) {
            PlayerEntity closestPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1, false);
            if (closestPlayer == null || closestPlayer.getPosition() != pos || !Armor.playerIsWearingArmorSetOfType(closestPlayer, ArmorTypes.REDSTONE)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    private void init() {
        isInitialized = true;
        tick = 0;
    }

}
