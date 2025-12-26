package me.tabin.cc_cw.peripherals;

import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollOptionBehaviour;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.GenericPeripheral;
import me.tabin.cc_cw.ComputercraftClockwork;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jspecify.annotations.NonNull;
import org.valkyrienskies.clockwork.content.contraptions.phys.bearing.PhysBearingBlockEntity;
import org.valkyrienskies.clockwork.platform.api.ContraptionController;

import java.util.Objects;

public class PhysBearingPeripheral implements GenericPeripheral {
    @Override
    public @NonNull String id() {
        return ComputercraftClockwork.resourceLocation("phys_bearing").toString();
    }

    private @NonNull ScrollOptionBehaviour<ContraptionController.LockedMode> getMovementMode(@NonNull PhysBearingBlockEntity be) {
        return Objects.requireNonNull(be.getMovementMode());
    }

    public @LuaFunction void assemble(@NonNull PhysBearingBlockEntity be) {
        be.setAssembleNextTick(true);
    }
    public @LuaFunction void disassemble(@NonNull PhysBearingBlockEntity be) {
        be.disassemble();
    }

    public @LuaFunction void setLockedMode(@NonNull PhysBearingBlockEntity be) {
        getMovementMode(be).setValue(ContraptionController.LockedMode.LOCKED.ordinal());
    }
    public @LuaFunction void setUnlockedMode(@NonNull PhysBearingBlockEntity be) {
        getMovementMode(be).setValue(ContraptionController.LockedMode.UNLOCKED.ordinal());
    }
    public @LuaFunction void setAngle(@NonNull PhysBearingBlockEntity be, double angle) throws LuaException {
        if (be.getStopTargetAngleChange()) {
            be.setAngle((float) angle);
        } else {
            throw new LuaException("Can't be changed until target angle change is stopped");
        }
    }

    public @LuaFunction boolean isBeingDisassembled(@NonNull PhysBearingBlockEntity be) {
        return be.getDisassembleWhenPossible();
    }
    public @LuaFunction boolean isActive(@NonNull PhysBearingBlockEntity be) {
        return be.isRunning();
    }
    public @LuaFunction boolean isInLockedMode(@NonNull PhysBearingBlockEntity be) {
        return getMovementMode(be).get() == ContraptionController.LockedMode.LOCKED;
    }
    public @LuaFunction boolean targetAngleIsFrozen(@NonNull PhysBearingBlockEntity be) {
        return be.getStopTargetAngleChange();
    }

    public @LuaFunction long getConnectedToShip(@NonNull PhysBearingBlockEntity be) {
        return be.getShiptraptionID();
    }
    public @LuaFunction float getTargetAngle(@NonNull PhysBearingBlockEntity be) {
        return be.getTargetAngle();
    }
    public @LuaFunction double getActualAngle(@NonNull PhysBearingBlockEntity be) {
        return Objects.requireNonNull(be.getActualAngle());
    }
    public @LuaFunction float getTargetAngleChangeSpeed(@NonNull PhysBearingBlockEntity be) {
        return be.getActualAngularSpeed();
    }
    public @LuaFunction float getRPM(@NonNull PhysBearingBlockEntity be) {
        return be.getSpeed();
    }
    public @LuaFunction @NonNull String getFacingDirection(@NonNull PhysBearingBlockEntity be) {
        return be.getBlockState().getValue(BlockStateProperties.FACING).getName();
    }
}
