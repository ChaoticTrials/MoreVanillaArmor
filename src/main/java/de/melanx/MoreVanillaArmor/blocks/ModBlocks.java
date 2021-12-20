package de.melanx.MoreVanillaArmor.blocks;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreVanillaArmor.MODID);

    public static final RegistryObject<Block> REDSTONE_ESSENCE = BLOCKS.register(
            "redstone_essence",
            () -> new RedstoneEssenceBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak())
    );
}
