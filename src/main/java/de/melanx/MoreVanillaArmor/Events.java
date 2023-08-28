package de.melanx.MoreVanillaArmor;

import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class Events {

    @SubscribeEvent
    public void lightningStrike(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LightningBolt lightning) {
            Vec3 vec = new Vec3(lightning.getX(), lightning.getY(), lightning.getZ());

            Level level = event.getLevel();
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(vec, 20, 20, 20))
                    .stream()
                    .filter(livingEntity -> Armor.getArmorSetType(livingEntity) != ArmorTiers.COPPER)
                    .toList();

            if (!entities.isEmpty()) {
                LivingEntity entity1 = entities.get(level.random.nextInt(entities.size()));
                lightning.setPos(entity1.getX(), entity1.getY(), entity1.getZ());
            }
        }
    }

    @SubscribeEvent
    public void playerDamagedEvent(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        ArmorTiers type = Armor.getArmorSetType(entity);

        if (type == ArmorTiers.FIERY) {
            if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                event.setAmount(0.0F);
                return;
            }
        }

        if ((type == ArmorTiers.SLIME || type == ArmorTiers.OBSIDIAN) && !event.getSource().is(DamageTypeTags.IS_FALL)) {
            // Don't trump other mods/effects that might reduce the damage to less than .25
            if (event.getAmount() > 0.25) {
                if (event.getAmount() - 1 > 0.25) {
                    event.setAmount(event.getAmount() - 1);
                } else {
                    event.setAmount(0.25F);
                }
            }

            return;
        }

        if (event.getSource().is(DamageTypeTags.IS_FALL)) {
            if (Armor.getArmorTypes(entity).contains(ArmorTiers.OBSIDIAN)) {
                // Increase damage by 5% per level (example Heavy II increases damage by 10%, Heavy III increases damage by 15%)
                event.setAmount(event.getAmount() + event.getAmount() * Armor.calcAmplifier(entity, ArmorTiers.OBSIDIAN) * 0.05F);
            }
        }
    }

    @SubscribeEvent
    public void playerTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        Events.livingTickActions(entity);
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        LivingEntity entity = event.player;

        Events.livingTickActions(entity);
    }

    private static void livingTickActions(LivingEntity entity) {
        //noinspection resource
        Level level = entity.level();
        if (Armor.getArmorTypes(entity).contains(ArmorTiers.OBSIDIAN)) {
            if (entity.isInWater()) {
                BlockPos pos = entity.blockPosition();
                BlockState state = level.getBlockState(pos);
                // Prevent player from swimming up in water
                if (!state.getFluidState().isEmpty()) {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, entity.isSwimming() ? -0.06D : -0.03D, 0.0D));
                }
            }

            if (entity.hasEffect(MobEffects.LEVITATION)) {
                entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, -0.02D, 0.0D));
            }
        }

        if (entity.onGround() && Armor.getArmorSetType(entity) == ArmorTiers.REDSTONE) {
            BlockState state = level.getBlockState(entity.blockPosition());
            if (state.is(Blocks.AIR) || state.is(ModRegistries.redstoneEssence)) {
                BlockState invisiTorch = ModRegistries.redstoneEssence.defaultBlockState();
                level.setBlockAndUpdate(entity.blockPosition(), invisiTorch);
            }
        }
    }
}
