package de.melanx.MoreVanillaArmor;

import de.melanx.MoreVanillaArmor.util.CreativeTab;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreVanillaArmor.MODID)
public class MoreVanillaArmor {

    public static MoreVanillaArmor instance;
    public static final String MODID = "morevanillaarmor";
    public static final CreativeModeTab creativeTab = new CreativeTab();

    public MoreVanillaArmor() {
        instance = this;

        MinecraftForge.EVENT_BUS.register(new Events());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModRegistries::register);
    }
}
