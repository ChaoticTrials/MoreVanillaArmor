package de.melanx.MoreVanillaArmor;

import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTypes;
import de.melanx.MoreVanillaArmor.util.CreativeTab;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MoreVanillaArmor.MODID)
public class MoreVanillaArmor {

    public static MoreVanillaArmor instance;

    public static final String MODID = "morevanillaarmor";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final ItemGroup creativeTab = new CreativeTab();

    public MoreVanillaArmor() {
        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            EntityEquipmentSlot[] slotTypes = new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
            String[] names = new String[]{"helmet", "chestplate", "leggings", "boots"};

            for (ArmorTypes type : ArmorTypes.values()) {
                for (int i = 0; i < slotTypes.length; i++) {
                    event.getRegistry().register(new Armor(type, slotTypes[i], names[i]));
                }
            }

            LOGGER.info("Items registered.");
        }

    }
}
