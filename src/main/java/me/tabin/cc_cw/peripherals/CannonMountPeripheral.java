package me.tabin.cc_cw.peripherals;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.GenericPeripheral;
import me.tabin.cc_cw.ComputercraftClockwork;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jspecify.annotations.NonNull;
import rbasamoyai.createbigcannons.cannon_control.cannon_mount.CannonMountBlockEntity;

public class CannonMountPeripheral implements GenericPeripheral {
    @Override
    public @NonNull String id() {
        return ComputercraftClockwork.resourceLocation("cannon_mount").toString();
    }

    public @LuaFunction boolean isAssembled(@NonNull CannonMountBlockEntity be) {
        return be.isRunning();
    }

    public @LuaFunction(mainThread = true) void fire(@NonNull CannonMountBlockEntity be, int power) {
        if (!be.isRunning()) {
            return;
        }
        int powerClamped = Math.max(0, Math.min(15, power));
        if (powerClamped > 0) {
            be.onRedstoneUpdate(true, true, true, false, powerClamped);
        } else {
            be.onRedstoneUpdate(true, true, false, true, 0);
        }
    }

    public @LuaFunction double getPitch(@NonNull CannonMountBlockEntity be) {
        var mountedContraption = be.getContraption();
        if (mountedContraption == null) {
            return 0.0;
        }
        return mountedContraption.pitch;
    }
    public @LuaFunction double getYaw(@NonNull CannonMountBlockEntity be) {
        var mountedContraption = be.getContraption();
        if (mountedContraption == null) {
            return 0.0;
        }
        return mountedContraption.yaw;
    }

    public @LuaFunction int getX(@NonNull CannonMountBlockEntity be) {
        return be.getControllerBlockPos().getX();
    }
    public @LuaFunction int getY(@NonNull CannonMountBlockEntity be) {
        return be.getControllerBlockPos().getY();
    }
    public @LuaFunction int getZ(@NonNull CannonMountBlockEntity be) {
        return be.getControllerBlockPos().getZ();
    }

    public @LuaFunction double getMaxDepression(@NonNull CannonMountBlockEntity be) {
        var mountedContraption = be.getContraption();
        if (mountedContraption == null) {
            return 0.0;
        }
        return mountedContraption.maximumDepression();
    }
    public @LuaFunction double getMaxElevation(@NonNull CannonMountBlockEntity be) {
        var mountedContraption = be.getContraption();
        if (mountedContraption == null) {
            return 0.0;
        }
        return mountedContraption.maximumElevation();
    }

    public @LuaFunction @NonNull String getDirection(@NonNull CannonMountBlockEntity be) {
        return be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).toString();
    }

    public @LuaFunction boolean isCheatMode(@NonNull CannonMountBlockEntity be) {
        return false;
    }
}
