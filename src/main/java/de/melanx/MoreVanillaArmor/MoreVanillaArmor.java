package de.melanx.MoreVanillaArmor;

//import de.melanx.MoreVanillaArmor.init.ModEffects;
import de.melanx.MoreVanillaArmor.util.CreativeTab;
import de.melanx.MoreVanillaArmor.util.Registry;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        Registry.registerArmor();
        Registry.registerArmorEffects();
    }

}