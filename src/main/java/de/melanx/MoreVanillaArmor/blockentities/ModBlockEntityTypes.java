package de.melanx.MoreVanillaArmor.blockentities;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MoreVanillaArmor.MODID);

    public static final RegistryObject<BlockEntityType<RedstoneEssenceBlockEntity>> REDSTONE_ESSENCE = BLOCK_ENTITY_TYPES.register("redstone_essence", () -> BlockEntityType.Builder.of(RedstoneEssenceBlockEntity::new, ModBlocks.REDSTONE_ESSENCE.get()).build(null));
}
