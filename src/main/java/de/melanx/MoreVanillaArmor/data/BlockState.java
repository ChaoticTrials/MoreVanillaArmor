package de.melanx.MoreVanillaArmor.data;


import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockState extends BlockStateProvider {

    public BlockState(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MoreVanillaArmor.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.REDSTONE_ESSENCE.get());
    }
}
