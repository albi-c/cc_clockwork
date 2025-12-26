package me.tabin.cc_cw;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ComputercraftClockwork.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue CHEAT_CANNON_MOUNT = BUILDER.comment("Cheat cannon mount").define("cheatCannonMount", true);
    public static final ForgeConfigSpec.BooleanValue CHEAT_FLAP_BEARING = BUILDER.comment("Cheat flap bearing").define("cheatFlapBearing", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean cheatCannonMount;
    public static boolean cheatFlapBearing;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        cheatCannonMount = CHEAT_CANNON_MOUNT.get();
        cheatFlapBearing = CHEAT_FLAP_BEARING.get();
    }
}
