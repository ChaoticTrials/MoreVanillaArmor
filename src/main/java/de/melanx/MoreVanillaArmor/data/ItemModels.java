package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, MoreVanillaArmor.MODID, helper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> item : ModRegistries.ITEMS.getEntries()) {
            this.generateItem(item.get());
        }
    }

    private void generateItem(Item item) {
        //noinspection ConstantConditions
        String path = ForgeRegistries.ITEMS.getKey(item).getPath();
        this.getBuilder(path).parent(this.getExistingFile(this.mcLoc("item/handheld")))
                .texture("layer0", "item/" + path);
    }
}
