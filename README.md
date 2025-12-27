# CC: Clockwork

Clockwork peripherals for CC: Tweaked

### Currently implemented

- Phys Bearing
- Flap Bearing\*
- Cannon Mount from Create Big Cannons\*

\* can be moved without requiring input rotation, can be disabled in config

### Added methods

All angles are in degrees unless specified otherwise

- **Phys Bearing**
  - `isAssembled(): boolean`
  - `lock()`
  - `unlock()`
  - `isLocked(): boolean`
  - `setAngle(angle: float)` set target angle of the bearing, must be locked
  - `isBeingDisassembled()`
  - `getConnectedToShip(): integer` id of ship to which the bearing is connected, or -1 if none
  - `getTargetAngle(): float`
  - `getActualAngle(): float [radians]`
  - `getRPM(): float`
  - `getDirection(): string`
- **Flap Bearing**
  - `assemble()`
  - `disassemble()`
  - `isAssembled(): boolean`
  - `setAngle(angle: float [-22.5 - 22.5])`
  - `setAnglePower(power: integer [-15 - 15])` simulate redstone power to set the angle
  - `resetAngle()`
  - `getAngle(): float`
  - `isCheatMode(): boolean` if true, input rotation is not required to change angle
- **Cannon Mount**
  - `isAssembled(): boolean`
  - `fire(power: int)` simulate redstone power to fire the cannon
  - `getPitch(): float`
  - `getYaw(): float`
  - `getX(): int` get position of the cannon controller
  - `getY(): int` get position of the cannon controller
  - `getZ(): int` get position of the cannon controller
  - `getMaxDepression(): float`
  - `getMaxElevation(): float`
  - `getDirection(): string`
  - `isCheatMode(): boolean` if true, cannon rotation can be directly changed
  - `setPitch(angle: float)` *cheat mode only*
  - `setYaw(angle: float)` *cheat mode only*
