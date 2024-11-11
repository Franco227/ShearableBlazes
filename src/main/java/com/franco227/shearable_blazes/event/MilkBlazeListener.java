package com.franco227.shearable_blazes.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MilkBlazeListener {

    public static ActionResult callback(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!player.isSpectator() && player.getStackInHand(hand).isOf(Items.BUCKET) && entity instanceof BlazeEntity) {
            ItemStack itemStack = player.getStackInHand(hand);
            player.playSound(SoundEvents.ITEM_BUCKET_FILL_LAVA, 1.0F, 1.0F);
            ItemStack newItemStack = ItemUsage.exchangeStack(itemStack, player, Items.LAVA_BUCKET.getDefaultStack());
            player.setStackInHand(hand, newItemStack);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.BUCKET));
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
