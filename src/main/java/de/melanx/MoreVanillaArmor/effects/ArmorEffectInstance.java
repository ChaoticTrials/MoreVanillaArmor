package de.melanx.MoreVanillaArmor.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

public class ArmorEffectInstance extends MobEffectInstance {

    public ArmorEffectInstance(MobEffect effect, int amplifier) {
        super(effect, 10, amplifier, false, false);
        this.setNoCounter(true);
    }
/*
    @Override
    public boolean shouldRender() {
        return false;
    }
 */
}
