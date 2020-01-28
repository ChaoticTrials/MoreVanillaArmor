package de.melanx.MoreVanillaArmor;

import de.melanx.MoreVanillaArmor.util.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MoreVanillaArmor.MODID)
public class MoreVanillaArmor {

    public static MoreVanillaArmor instance;

    public static final String MODID = "morevanillaarmor";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public MoreVanillaArmor() {
        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
        Registry.registerArmor();
    }
}
