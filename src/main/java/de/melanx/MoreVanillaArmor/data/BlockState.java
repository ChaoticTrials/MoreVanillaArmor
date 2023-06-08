package de.melanx.MoreVanillaArmor.data;


import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockState extends BlockStateProvider {

    public BlockState(PackOutput output, ExistingFileHelper helper) {
        super(output, MoreVanillaArmor.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(ModRegistries.redstoneEssence);
    }
}
