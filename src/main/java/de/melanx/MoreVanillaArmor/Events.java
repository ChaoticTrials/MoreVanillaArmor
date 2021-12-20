package de.melanx.MoreVanillaArmor;

import de.melanx.MoreVanillaArmor.blocks.ModBlocks;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class Events {

    @SubscribeEvent
    public void lightningStrike(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LightningBolt lightning) {
            Vec3 vec = new Vec3(lightning.getX(), lightning.getY(), lightning.getZ());

            Level level = event.getWorld();
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(vec, 20, 20, 20))
                    .stream()
                    .filter(livingEntity -> Armor.getArmorSetType(livingEntity) != null)
                    .toList();

            if (!entities.isEmpty()) {
                LivingEntity entity1 = entities.get(level.random.nextInt(entities.size()));
                lightning.setPos(entity1.getX(), entity1.getY(), entity1.getZ());
            }
        }
    }

    @SubscribeEvent
    public void playerDamagedEvent(LivingDamageEvent event) {
        LivingEntity entity = event.getEntityLiving();
        ArmorTiers type = Armor.getArmorSetType(entity);

        if (type == ArmorTiers.FIERY) {
            if (event.getSource().isFire()) {
                event.setAmount(0.0F);
                return;
            }
        }

        if ((type == ArmorTiers.SLIME || type == ArmorTiers.OBSIDIAN) && event.getSource() != DamageSource.FALL) {
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

        if (event.getSource() == DamageSource.FALL) {
            if (Armor.getArmorTypes(entity).contains(ArmorTiers.OBSIDIAN)) {
                // Increase damage by 5% per level (example Heavy II increases damage by 10%, Heavy III increases damage by 15%)
                event.setAmount(event.getAmount() + event.getAmount() * Armor.calcAmplifier(entity, ArmorTiers.OBSIDIAN) * 0.05F);
            }
        }
    }

    @SubscribeEvent
    public void playerTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();

        Events.livingTickActions(entity);
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        LivingEntity entity = event.player;

        Events.livingTickActions(entity);
    }

    private static void livingTickActions(LivingEntity entity) {
        if (entity.isInWater() && Armor.getArmorTypes(entity).contains(ArmorTiers.OBSIDIAN)) {
            BlockPos pos = entity.blockPosition();
            BlockState state = entity.level.getBlockState(pos);
            // Prevent player from swimming up in water
            if (!state.getFluidState().isEmpty()) {
                entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, entity.isSwimming() ? -0.06D : -0.03D, 0.0D));
            }
        }

        if (entity.isOnGround() && Armor.getArmorSetType(entity) == ArmorTiers.REDSTONE) {
            BlockState state = entity.level.getBlockState(entity.blockPosition());
            if (state.is(Blocks.AIR) || state.is(ModBlocks.REDSTONE_ESSENCE.get())) {
                BlockState invisiTorch = ModBlocks.REDSTONE_ESSENCE.get().defaultBlockState();
                entity.level.setBlockAndUpdate(entity.blockPosition(), invisiTorch);
            }
        }
    }
}
