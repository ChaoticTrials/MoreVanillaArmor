package de.melanx.MoreVanillaArmor.data;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.items.Armor;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModTags extends IntrinsicHolderTagsProvider<Item> {

    public ModTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper helper) {
        //noinspection deprecation
        super(output, Registries.ITEM, lookupProvider, item -> ResourceKey.create(BuiltInRegistries.ITEM.key(), BuiltInRegistries.ITEM.getKey(item)), MoreVanillaArmor.MODID, helper);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider provider) {
        TagAppender<Item> boots = this.tag(Tags.Items.ARMORS_BOOTS);
        TagAppender<Item> leggings = this.tag(Tags.Items.ARMORS_LEGGINGS);
        TagAppender<Item> chestplates = this.tag(Tags.Items.ARMORS_CHESTPLATES);
        TagAppender<Item> helmets = this.tag(Tags.Items.ARMORS_HELMETS);
        for (Map.Entry<ResourceKey<Item>, Item> entry : ForgeRegistries.ITEMS.getEntries()) {
            if (entry.getValue() instanceof Armor armor) {
                ForgeRegistries.ITEMS.getResourceKey(armor).ifPresent(key -> {
                    switch (armor.getSlotType()) {
                        case HEAD -> helmets.add(key);
                        case CHEST -> chestplates.add(key);
                        case LEGS -> leggings.add(key);
                        case FEET -> boots.add(key);
                    }
                });
            }
        }
    }
}
