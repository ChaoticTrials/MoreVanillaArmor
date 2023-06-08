package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataCreator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        PackOutput packOutput = generator.getPackOutput();
        if (event.includeServer()) {
            generator.addProvider(true, new Recipes(packOutput));
            generator.addProvider(true, new ModTags(packOutput, lookupProvider, helper));
        }
        if (event.includeClient()) {
            generator.addProvider(true, new BlockState(packOutput, helper));
            generator.addProvider(true, new ItemModels(packOutput, helper));
        }
    }
}
