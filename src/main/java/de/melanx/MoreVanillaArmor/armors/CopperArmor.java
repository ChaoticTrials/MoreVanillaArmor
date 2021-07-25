package de.melanx.MoreVanillaArmor.armors;

import de.melanx.MoreVanillaArmor.MoreVanillaArmor;
import de.melanx.MoreVanillaArmor.effects.ArmorEffectInstance;
import de.melanx.MoreVanillaArmor.items.Armor;
import de.melanx.MoreVanillaArmor.items.ArmorTiers;
import de.melanx.MoreVanillaArmor.util.ModRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = MoreVanillaArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CopperArmor {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (Armor.playerIsWearingArmorSetOfType(player, ArmorTiers.COPPER)) {
            player.addEffect(new ArmorEffectInstance(ModRegistries.LIGHTNING_MAGNET.get(), 0));
        }
    }

    @SubscribeEvent
    public static void lightningStrike(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LightningBolt lightning) {
            Vec3 vec = new Vec3(lightning.getX(), lightning.getY(), lightning.getZ());

            Level level = event.getWorld();
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(vec, 20, 20, 20))
                    .stream()
                    .filter(livingEntity -> Armor.getArmorSetType(livingEntity) != null)
                    .collect(Collectors.toList());

            if (!entities.isEmpty()) {
                LivingEntity entity1 = entities.get(level.random.nextInt(entities.size()));
                lightning.setPos(entity1.getX(), entity1.getY(), entity1.getZ());
            }
        }
    }
}
