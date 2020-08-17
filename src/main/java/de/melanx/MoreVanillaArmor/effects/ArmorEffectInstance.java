package de.melanx.MoreVanillaArmor.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class ArmorEffectInstance extends EffectInstance {

    public ArmorEffectInstance(Effect effect, int amplifier) {
        super(effect, 10, amplifier, false, false);
        this.setPotionDurationMax(true);
    }
/*
    @Override
    public boolean shouldRender() {
        return false;
    }
 */
}
