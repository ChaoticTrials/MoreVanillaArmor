package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.blockentities.RedstoneEssenceBlockEntity;
import de.melanx.MoreVanillaArmor.blocks.RedstoneEssenceBlock;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class ModRegistries {

    public static final Map<ArmorPiece, Armor> armor = new HashMap<>();
    public static Block redstoneEssence = new RedstoneEssenceBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak());
    public static BlockEntityType<RedstoneEssenceBlockEntity> redstoneEssenceBlockEntityType = BlockEntityType.Builder.of(RedstoneEssenceBlockEntity::new, redstoneEssence).build(null);

    public static void createTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(MoreVanillaArmor.MODID, "tab"), builder -> {
            builder.title(Component.literal("Vanilla AIOTs"));
            builder.icon(() -> new ItemStack(Objects.requireNonNullElse(armor.get(new ArmorPiece(ArmorTiers.EMERALD, ArmorSlot.CHESTPLATE)), Items.DIAMOND_CHESTPLATE)))
                    .displayItems((enabledFlags, output) -> {
                        for (Item item : ForgeRegistries.ITEMS.getValues()) {
                            //noinspection DataFlowIssue
                            if (MoreVanillaArmor.MODID.equals(ForgeRegistries.ITEMS.getKey(item).getNamespace())) {
                                output.accept(new ItemStack(item));
                            }
                        }
                    });
        });
    }

    public static void register(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            for (ArmorTiers armorType : ArmorTiers.values()) {
                for (ArmorItem.Type type : ArmorItem.Type.values()) {
                    ArmorPiece piece = new ArmorPiece(armorType, ArmorSlot.get(type.getSlot()));
                    Armor armor = new Armor(armorType, type);
                    ModRegistries.armor.put(piece, armor);
                    helper.register(piece.name(), armor);
                }
            }
        });

        event.register(ForgeRegistries.Keys.BLOCKS, helper -> {
            helper.register("redstone_essence", redstoneEssence);
        });

        event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, helper -> {
            helper.register("redstone_essence", redstoneEssenceBlockEntityType);
        });
    }

    public static String getTooltip(String title) {
        return "tooltip." + MoreVanillaArmor.MODID + "." + title;
    }

    public enum ArmorSlot {
        HELMET(EquipmentSlot.HEAD),
        CHESTPLATE(EquipmentSlot.CHEST),
        LEGGINGS(EquipmentSlot.LEGS),
        BOOTS(EquipmentSlot.FEET);

        private final EquipmentSlot slot;

        ArmorSlot(EquipmentSlot slot) {
            this.slot = slot;
        }

        public static ArmorSlot get(EquipmentSlot slot) {
            for (ArmorSlot value : ArmorSlot.values()) {
                if (value.slot == slot) {
                    return value;
                }
            }

            throw new IllegalArgumentException("That shouldn't happen. Why?");
        }

        public EquipmentSlot slot() {
            return this.slot;
        }
    }

    record ArmorPiece(ArmorTiers type, ArmorSlot slot) {

        public String name() {
            return this.type.toString().toLowerCase(Locale.ROOT) + "_" + this.slot.toString().toLowerCase(Locale.ROOT);
        }
    }
}
