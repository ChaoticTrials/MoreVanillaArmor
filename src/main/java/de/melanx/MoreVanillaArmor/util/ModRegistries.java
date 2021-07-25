package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.effects.*;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModRegistries {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreVanillaArmor.MODID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MoreVanillaArmor.MODID);

    public static final RegistryObject<MobEffect> HEAVY = EFFECTS.register("heavy", () -> (new HeavyArmorEffect()).addAttributeModifier(Attributes.MOVEMENT_SPEED, "6dc9f142-dc17-11ea-87d0-0242ac130003", -0.05F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> DAMAGE_REDUCTION = EFFECTS.register("damage_reduction", DamageReductionArmorEffect::new);
    public static final RegistryObject<MobEffect> FIRE_IMMUNITY = EFFECTS.register("fire_immunity", FireImmunityArmorEffect::new);
    public static final RegistryObject<MobEffect> LIGHTNING_MAGNET = EFFECTS.register("lightning_magnet", LightningMagnetEffect::new);
    public static final RegistryObject<MobEffect> POWER_SOURCE = EFFECTS.register("power_source", PowerSourceArmorEffect::new);

    public static void registerArmor() {
        EquipmentSlot[] slotTypes = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        String[] names = new String[]{"helmet", "chestplate", "leggings", "boots"};

        ArmorTiers[] values = ArmorTiers.values();
        for (ArmorTiers type : values) {
            for (int i = 0; i < slotTypes.length; i++) {
                int j = i; // idk why but IntelliJ wanted this
                ITEMS.register(type.getName() + "_" + names[j], () -> new Armor(type, slotTypes[j]));
            }
        }
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void init() {
        registerArmor();
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static String getTooltip(String title) {
        return "tooltip." + MoreVanillaArmor.MODID + "." + title;
    }
}
