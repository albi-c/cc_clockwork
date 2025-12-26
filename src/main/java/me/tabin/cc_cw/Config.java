package me.tabin.cc_cw;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ComputercraftClockwork.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue CHEAT_CANNON_MOUNT = BUILDER.comment("Cheat cannon mount").define("cheatCannonMount", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean cheatCannonMount;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        cheatCannonMount = CHEAT_CANNON_MOUNT.get();
    }
}
