package de.melanx.MoreVanillaArmor.effects;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;

public class ArmorEffects {

    public static final Effect HEAVY = (new HeavyArmorEffect()).addAttributesModifier(Attributes.MOVEMENT_SPEED, "6dc9f142-dc17-11ea-87d0-0242ac130003", -0.05F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final Effect DAMAGE_REDUCTION = new DamageReductionArmorEffect();
    public static final Effect FIRE_IMMUNITY = new FireImmunityArmorEffect();
    public static final Effect POWER_SOURCE = new PowerSourceArmorEffect();

}
