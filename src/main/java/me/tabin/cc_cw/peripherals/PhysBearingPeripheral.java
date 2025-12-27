package me.tabin.cc_cw.peripherals;

import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.GenericPeripheral;
import me.tabin.cc_cw.ComputercraftClockwork;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jspecify.annotations.NonNull;
import org.valkyrienskies.clockwork.content.contraptions.phys.bearing.PhysBearingBlockEntity;

import java.util.Objects;

public class PhysBearingPeripheral implements GenericPeripheral {
    @Override
    public @NonNull String id() {
        return ComputercraftClockwork.resourceLocation("phys_bearing").toString();
    }

    public @LuaFunction boolean isAssembled(@NonNull PhysBearingBlockEntity be) {
        return be.isRunning();
    }

    public @LuaFunction void lock(@NonNull PhysBearingBlockEntity be) {
        be.setStopTargetAngleChange(true);
    }
    public @LuaFunction void unlock(@NonNull PhysBearingBlockEntity be) {
        be.setStopTargetAngleChange(false);
    }
    public @LuaFunction void setAngle(@NonNull PhysBearingBlockEntity be, double angle) throws LuaException {
        if (be.getStopTargetAngleChange()) {
            be.setAngle((float) angle);
        } else {
            throw new LuaException("Angle can't be changed unless bearing is locked");
        }
    }

    public @LuaFunction boolean isBeingDisassembled(@NonNull PhysBearingBlockEntity be) {
        return be.getDisassembleWhenPossible();
    }
    public @LuaFunction boolean isLocked(@NonNull PhysBearingBlockEntity be) {
        return be.getStopTargetAngleChange();
    }

    public @LuaFunction long getConnectedToShip(@NonNull PhysBearingBlockEntity be) {
        return be.getShiptraptionID();
    }
    public @LuaFunction float getTargetAngle(@NonNull PhysBearingBlockEntity be) {
        return be.getTargetAngle();
    }
    public @LuaFunction double getActualAngle(@NonNull PhysBearingBlockEntity be) {
        return Objects.requireNonNullElse(be.getActualAngle(), 0.0);
    }
    public @LuaFunction float getAngularSpeed(@NonNull PhysBearingBlockEntity be) {
        return be.getActualAngularSpeed();
    }
    public @LuaFunction float getRPM(@NonNull PhysBearingBlockEntity be) {
        return be.getSpeed();
    }
    public @LuaFunction @NonNull String getDirection(@NonNull PhysBearingBlockEntity be) {
        return be.getBlockState().getValue(BlockStateProperties.FACING).getName();
    }
}
