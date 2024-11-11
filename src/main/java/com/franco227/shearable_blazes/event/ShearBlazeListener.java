package com.franco227.shearable_blazes.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ShearBlazeListener {

    public static ActionResult callback(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!player.isSpectator() && player.getStackInHand(hand).isOf(Items.SHEARS) && entity instanceof BlazeEntity) {
            if (entity instanceof Shearable shearableEntity && shearableEntity.isShearable()) {
                shearableEntity.sheared(SoundCategory.PLAYERS);
                player.playSound(SoundEvents.ENTITY_BLAZE_HURT, 1.0F, 1.0F);
                player.getStackInHand(hand).damage(1, player, playerEntity -> {});
                player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
