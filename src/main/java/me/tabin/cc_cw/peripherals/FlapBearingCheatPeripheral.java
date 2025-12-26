package me.tabin.cc_cw.peripherals;

import dan200.computercraft.api.lua.LuaFunction;
import org.jspecify.annotations.NonNull;
import org.valkyrienskies.clockwork.content.contraptions.flap.FlapBearingBlockEntity;

public class FlapBearingCheatPeripheral extends FlapBearingPeripheral {
    @Override
    public @LuaFunction void setAngle(@NonNull FlapBearingBlockEntity be, double angle) {
        if (be.isRunning()) {
            ((FlapBearingBlockEntityMixinAccessor) be).cc_cw$setSpeedLockedPower(Math.max(-15, Math.min(15, (int)(angle * 15.0 / 22.5))));
        }
    }
    @Override
    public @LuaFunction void setAnglePower(@NonNull FlapBearingBlockEntity be, int power) {
        if (be.isRunning()) {
            ((FlapBearingBlockEntityMixinAccessor) be).cc_cw$setSpeedLockedPower(Math.max(-15, Math.min(15, power)));
        }
    }

    @Override
    public @LuaFunction boolean isCheatMode(@NonNull FlapBearingBlockEntity be) {
        return true;
    }
}
