package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.Armor;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ModTags extends ItemTagsProvider {

    public ModTags(DataGenerator generator, @Nullable ExistingFileHelper helper) {
        super(generator, new BlockTagsProvider(generator, MoreVanillaArmor.MODID, helper), MoreVanillaArmor.MODID, helper);
    }

    @Override
    protected void addTags() {
        TagAppender<Item> boots = this.tag(Tags.Items.ARMORS_BOOTS);
        TagAppender<Item> leggings = this.tag(Tags.Items.ARMORS_LEGGINGS);
        TagAppender<Item> chestplates = this.tag(Tags.Items.ARMORS_CHESTPLATES);
        TagAppender<Item> helmets = this.tag(Tags.Items.ARMORS_HELMETS);
        for (Map.Entry<ResourceKey<Item>, Item> entry : ForgeRegistries.ITEMS.getEntries()) {
            if (entry.getValue() instanceof Armor armor) {
                switch (armor.getSlotType()) {
                    case HEAD -> helmets.add(armor);
                    case CHEST -> chestplates.add(armor);
                    case LEGS -> leggings.add(armor);
                    case FEET -> boots.add(armor);
                }
            }
        }
    }
}
