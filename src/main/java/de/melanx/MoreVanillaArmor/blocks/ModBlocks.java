package de.melanx.MoreVanillaArmor.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
    public static final Block INVISI_TORCH = new InvisiTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance());
}
