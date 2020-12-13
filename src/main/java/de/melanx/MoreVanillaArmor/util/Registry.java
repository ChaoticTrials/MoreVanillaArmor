package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.effects.DamageReductionArmorEffect;
import de.melanx.MoreVanillaArmor.effects.FireImmunityArmorEffect;
import de.melanx.MoreVanillaArmor.effects.HeavyArmorEffect;
import de.melanx.MoreVanillaArmor.effects.PowerSourceArmorEffect;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class Registry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreVanillaArmor.MODID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MoreVanillaArmor.MODID);

    public static final RegistryObject<Effect> HEAVY = EFFECTS.register("heavy", () -> (new HeavyArmorEffect()).addAttributesModifier(Attributes.MOVEMENT_SPEED, "6dc9f142-dc17-11ea-87d0-0242ac130003", -0.05F,AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> DAMAGE_REDUCTION = EFFECTS.register("damage_reduction", DamageReductionArmorEffect::new);
    public static final RegistryObject<Effect> FIRE_IMMUNITY = EFFECTS.register("fire_immunity", FireImmunityArmorEffect::new);
    public static final RegistryObject<Effect> POWER_SOURCE = EFFECTS.register("power_source", PowerSourceArmorEffect::new);

    public static void registerArmor() {
        EquipmentSlotType[] slotTypes = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
        String[] names = new String[]{"helmet", "chestplate", "leggings", "boots"};

        for (ArmorTiers type : ArmorTiers.values()) {
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
}
