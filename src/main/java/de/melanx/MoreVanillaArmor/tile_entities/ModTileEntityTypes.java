package de.melanx.MoreVanillaArmor.tile_entities;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.blocks.ModBlocks;
import de.melanx.MoreVanillaArmor.tile_entities.RedstoneEssenceTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MoreVanillaArmor.MODID);

    public static final RegistryObject<TileEntityType<RedstoneEssenceTileEntity>> REDSTONE_ESSENCE = TILE_ENTITY_TYPES.register("redstone_essence", () -> TileEntityType.Builder.create(RedstoneEssenceTileEntity::new, ModBlocks.REDSTONE_ESSENCE.get()).build(null));
}
