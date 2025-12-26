package me.tabin.cc_cw.mixin;

import me.tabin.cc_cw.peripherals.FlapBearingBlockEntityMixinAccessor;
import net.createmod.catnip.animation.LerpedFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.valkyrienskies.clockwork.content.contraptions.flap.FlapBearingBlockEntity;

@Mixin(FlapBearingBlockEntity.class)
public class FlapBearingBlockEntityMixin implements FlapBearingBlockEntityMixinAccessor {
    @Unique
    boolean cc_cw$isLocked = false;
    @Unique
    int cc_cw$lockedPower;

    @Unique
    boolean cc_cw$hasFakeSpeed = false;

    @Override
    public void cc_cw$setLockedPower(int power) {
        cc_cw$isLocked = true;
        cc_cw$lockedPower = power;
    }
    @Override
    public void cc_cw$unlock() {
        cc_cw$isLocked = false;
    }
    @Override
    public void cc_cw$setSpeedLockedPower(int power) {
        cc_cw$isLocked = true;
        cc_cw$lockedPower = power;
        cc_cw$hasFakeSpeed = true;
    }

    @Inject(method = "getPower", at = @At("HEAD"), remap = false, cancellable = true)
    void getPower(CallbackInfoReturnable<Integer> cir) {
        if (cc_cw$isLocked) {
            cir.setReturnValue(cc_cw$lockedPower);
        }
    }

    @Inject(method = "disassemble", at = @At("HEAD"), remap = false)
    void disassemble(CallbackInfo ci) {
        cc_cw$isLocked = false;
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/createmod/catnip/animation/LerpedFloat;chase(DDLnet/createmod/catnip/animation/LerpedFloat$Chaser;)Lnet/createmod/catnip/animation/LerpedFloat;"
            ),
            remap = false
    )
    LerpedFloat chase(LerpedFloat instance, double value, double speed, LerpedFloat.Chaser chaseFunction) {
        if (cc_cw$hasFakeSpeed) {
            speed = 256.0 * 3.0 / 10.0;
        }
        return instance.chase(value, speed, chaseFunction);
    }
}
