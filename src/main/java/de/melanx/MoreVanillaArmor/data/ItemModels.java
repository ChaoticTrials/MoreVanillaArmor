package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemModels extends ItemModelProvider {

    public ItemModels(PackOutput output, ExistingFileHelper helper) {
        super(output, MoreVanillaArmor.MODID, helper);
    }

    @Override
    protected void registerModels() {
        ModRegistries.armor.values().forEach(this::generateItem);
    }

    private void generateItem(Item item) {
        //noinspection ConstantConditions
        String path = ForgeRegistries.ITEMS.getKey(item).getPath();
        this.getBuilder(path).parent(this.getExistingFile(this.mcLoc("item/handheld")))
                .texture("layer0", "item/" + path);
    }
}
