package de.melanx.MoreVanillaArmor.blocks;

import de.melanx.MoreVanillaArmor.tile_entities.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class RedstoneEssenceBlock extends RedstoneBlock {

    public RedstoneEssenceBlock(Properties props) {
        super(props);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.REDSTONE_ESSENCE.get().create();
    }

    public boolean isOpaqueCube(IBlockReader worldIn, BlockPos pos) {
        return false;
    }

}
