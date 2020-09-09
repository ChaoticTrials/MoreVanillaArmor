package de.melanx.MoreVanillaArmor.blocks;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreVanillaArmor.MODID);

    public static final RegistryObject<Block> REDSTONE_ESSENCE = BLOCKS.register(
            "redstone_essence",
            () -> new RedstoneEssenceBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance())
    );
}
