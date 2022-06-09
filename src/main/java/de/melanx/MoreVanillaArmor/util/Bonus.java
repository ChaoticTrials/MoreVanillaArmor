package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class Bonus {

    private final MutableComponent component;

    public Bonus(String key) {
        this.component = Component.translatable(MoreVanillaArmor.MODID + ".bonus." + key);
    }

    public MutableComponent getDisplayName() {
        return this.component;
    }
}
