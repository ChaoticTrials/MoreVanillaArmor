package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.blockentities.RedstoneEssenceBlockEntity;
import de.melanx.MoreVanillaArmor.blocks.RedstoneEssenceBlock;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class ModRegistries {

    public static final Map<ArmorPiece, Armor> armor = new HashMap<>();
    public static Block redstoneEssence = new RedstoneEssenceBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak());
    public static BlockEntityType<RedstoneEssenceBlockEntity> redstoneEssenceBlockEntityType = BlockEntityType.Builder.of(RedstoneEssenceBlockEntity::new, redstoneEssence).build(null);

    public static void register(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            for (ArmorTiers type : ArmorTiers.values()) {
                for (ArmorSlot slot : ArmorSlot.values()) {
                    ArmorPiece piece = new ArmorPiece(type, slot);
                    Armor armor = new Armor(type, slot);
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
