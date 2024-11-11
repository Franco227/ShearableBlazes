package com.franco227.shearable_blazes.mixin.client;

import com.franco227.shearable_blazes.ShearableEntity;
import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.franco227.shearable_blazes.ShearableBlazes.MOD_ID;

@Mixin(BlazeEntityRenderer.class)
public class BlazeEntityRendererMixin {

    @Inject(at = @At("HEAD"), method = "getTexture(Lnet/minecraft/entity/mob/BlazeEntity;)Lnet/minecraft/util/Identifier;", cancellable = true)
    public void getTexture(BlazeEntity blazeEntity, CallbackInfoReturnable<Identifier> cir) {
        if (blazeEntity instanceof ShearableEntity shearableEntity && shearableEntity.shearableBlazes$isSheared()) {
            cir.setReturnValue(Identifier.of(MOD_ID, "textures/entity/blaze.png"));
        }
    }
}
