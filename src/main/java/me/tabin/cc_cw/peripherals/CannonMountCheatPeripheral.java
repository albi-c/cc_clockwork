package me.tabin.cc_cw.peripherals;

import dan200.computercraft.api.lua.LuaFunction;
import org.jspecify.annotations.NonNull;
import rbasamoyai.createbigcannons.cannon_control.cannon_mount.CannonMountBlockEntity;

public class CannonMountCheatPeripheral extends CannonMountPeripheral {
    public @LuaFunction void setPitch(@NonNull CannonMountBlockEntity be, double value) {
        if (be.isRunning()) {
            be.setPitch((float) value);
        }
    }
    public @LuaFunction void setYaw(@NonNull CannonMountBlockEntity be, double value) {
        if (be.isRunning()) {
            be.setYaw((float) value);
        }
    }

    @Override
    public @LuaFunction boolean isCheatMode(@NonNull CannonMountBlockEntity be) {
        return true;
    }
}
