package fox.eternity.mixin;

import fox.eternity.Item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> {


    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("TAIL"), cancellable = true)
    private void setAngles(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (livingEntity.getMainHandStack().isOf(ModItems.FOX_GUITAR)) {
            ((BipedEntityModel<? extends LivingEntity>) (Object) this).rightArm.pitch = -(float)Math.toRadians(19.5108d); // X
            ((BipedEntityModel<? extends LivingEntity>) (Object) this).rightArm.yaw = -(float)Math.toRadians(3.841d); // Y
            ((BipedEntityModel<? extends LivingEntity>) (Object) this).rightArm.roll = (float)Math.toRadians(-14.5108d); // Z

            ((BipedEntityModel<? extends LivingEntity>) (Object) this).leftArm.pitch = -(float)Math.toRadians(2.4927d);
            ((BipedEntityModel<? extends LivingEntity>) (Object) this).leftArm.yaw = -(float)Math.toRadians(0.449d);
            ((BipedEntityModel<? extends LivingEntity>) (Object) this).leftArm.roll = (float)Math.toRadians(-127.538d);
        }
    }
}