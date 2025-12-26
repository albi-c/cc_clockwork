package me.tabin.cc_cw.mixin;

import me.tabin.cc_cw.peripherals.FlapBearingBlockEntityMixinAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.valkyrienskies.clockwork.content.contraptions.flap.FlapBearingBlockEntity;

@Mixin(FlapBearingBlockEntity.class)
public class FlapBearingBlockEntityMixin implements FlapBearingBlockEntityMixinAccessor {
    @Unique
    boolean isLocked = false;
    @Unique
    int lockedPower;

    @Override
    public void setLockedPower(int power) {
        isLocked = true;
        lockedPower = power;
    }
    @Override
    public void unlock() {
        isLocked = false;
    }

    @Inject(method = "getPower", at = @At("HEAD"), remap = false, cancellable = true)
    void getPower(CallbackInfoReturnable<Integer> cir) {
        if (isLocked) {
            cir.setReturnValue(lockedPower);
        }
    }

    @Inject(method = "disassemble", at = @At("HEAD"), remap = false)
    void disassemble(CallbackInfo ci) {
        isLocked = false;
    }
}
