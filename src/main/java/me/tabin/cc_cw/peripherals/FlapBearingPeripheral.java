package me.tabin.cc_cw.peripherals;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.GenericPeripheral;
import me.tabin.cc_cw.ComputercraftClockwork;
import org.jspecify.annotations.NonNull;
import org.valkyrienskies.clockwork.content.contraptions.flap.FlapBearingBlockEntity;

public class FlapBearingPeripheral implements GenericPeripheral {
    @Override
    public @NonNull String id() {
        return ComputercraftClockwork.resourceLocation("flap_bearing").toString();
    }

    public @LuaFunction void assemble(@NonNull FlapBearingBlockEntity be) {
        be.setAssembleNextTick(true);
    }
    public @LuaFunction void disassemble(@NonNull FlapBearingBlockEntity be) {
        be.disassemble();
    }
    public @LuaFunction boolean isAssembled(@NonNull FlapBearingBlockEntity be) {
        return be.isRunning();
    }

    public @LuaFunction void setAngle(@NonNull FlapBearingBlockEntity be, double angle) {
        if (be.isRunning()) {
            ((FlapBearingBlockEntityMixinAccessor) be).cc_cw$setLockedPower(Math.max(-15, Math.min(15, (int)(angle * 15.0 / 22.5))));
        }
    }
    public @LuaFunction void setAnglePower(@NonNull FlapBearingBlockEntity be, int power) {
        if (be.isRunning()) {
            ((FlapBearingBlockEntityMixinAccessor) be).cc_cw$setLockedPower(Math.max(-15, Math.min(15, power)));
        }
    }
    public @LuaFunction void resetAngle(@NonNull FlapBearingBlockEntity be) {
        ((FlapBearingBlockEntityMixinAccessor) be).cc_cw$unlock();
    }
    public @LuaFunction float getAngle(@NonNull FlapBearingBlockEntity be) {
        return be.getInterpolatedAngle(0.f);
    }
}
