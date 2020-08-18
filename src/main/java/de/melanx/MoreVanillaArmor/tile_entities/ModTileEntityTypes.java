package de.melanx.MoreVanillaArmor.tile_entities;

import de.melanx.MoreVanillaArmor.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntityType;

public class ModTileEntityTypes {
    public static final TileEntityType<InvisiTorchTileEntity> INVISI_TORCH_TILE_ENTITY = TileEntityType.Builder.create(InvisiTorchTileEntity::new, ModBlocks.INVISI_TORCH).build(null);
}
