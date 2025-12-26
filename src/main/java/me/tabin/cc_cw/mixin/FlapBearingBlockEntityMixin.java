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
    boolean cc_cw$isLocked = false;
    @Unique
    int cc_cw$lockedPower;

    @Override
    public void cc_cw$setLockedPower(int power) {
        cc_cw$isLocked = true;
        cc_cw$lockedPower = power;
    }
    @Override
    public void cc_cw$unlock() {
        cc_cw$isLocked = false;
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
}
