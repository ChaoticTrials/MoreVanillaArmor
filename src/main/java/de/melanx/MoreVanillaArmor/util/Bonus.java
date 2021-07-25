package de.melanx.MoreVanillaArmor.util;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class Bonus {

    private final MutableComponent component;

    public Bonus(String key) {
        this.component = new TranslatableComponent(MoreVanillaArmor.MODID + ".bonus." + key);
    }

    public MutableComponent getDisplayName() {
        return this.component;
    }
}
