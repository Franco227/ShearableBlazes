package com.franco227.shearable_blazes.mixin;

import com.franco227.shearable_blazes.ShearableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.franco227.shearable_blazes.ShearableBlazes.MOD_ID;

@Mixin(BlazeEntity.class)
public abstract class BlazeEntityMixin extends HostileEntity implements Shearable, ShearableEntity {

    @Unique
    private static final TrackedData<Boolean> IS_SHEARED = DataTracker.registerData(BlazeEntityMixin.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected BlazeEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public Identifier getLootTableId() {
        if (this.shearableBlazes$isSheared()) {
            Identifier identifier = Identifier.of(MOD_ID, "blaze");
            if (identifier != null) {
                return identifier.withPrefixedPath("entities/");
            }
        }
        return this.getType().getLootTableId();
    }

    @Override
    public boolean shearableBlazes$isSheared() {
        return this.getDataTracker().get(IS_SHEARED);
    }

    @Override
    public boolean isShearable() {
        return !this.shearableBlazes$isSheared();
    }

    @Override
    public void sheared(SoundCategory soundCategory) {
        this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_BLAZE_HURT, soundCategory, 1.0F, 1.0F);
        int i = 1 + this.random.nextInt(3);

        for(int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.dropItem(Items.BLAZE_ROD);
            if (itemEntity != null) {
                itemEntity.setVelocity(itemEntity.getVelocity().add(
                    ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F),
                    (this.random.nextFloat() * 0.05F),
                    ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)
                ));
            }
        }
        this.dataTracker.set(IS_SHEARED, true);
    }

    @Inject(at = @At("TAIL"), method = "initDataTracker")
    public void initDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(IS_SHEARED, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("sheared", shearableBlazes$isSheared());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.dataTracker.set(IS_SHEARED, nbt.getBoolean("sheared"));
    }
}
