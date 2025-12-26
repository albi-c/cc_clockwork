package me.tabin.cc_cw;

import com.mojang.logging.LogUtils;
import dan200.computercraft.api.ComputerCraftAPI;
import me.tabin.cc_cw.peripherals.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ComputercraftClockwork.MODID)
public class ComputercraftClockwork {
    public static final String MODID = "cc_cw";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ComputercraftClockwork() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.debug("registering peripherals");
        ComputerCraftAPI.registerGenericSource(new PhysBearingPeripheral());
        if (Config.cheatFlapBearing) {
            ComputerCraftAPI.registerGenericSource(new FlapBearingCheatPeripheral());
        } else {
            ComputerCraftAPI.registerGenericSource(new FlapBearingPeripheral());
        }
        if (ModList.get().isLoaded("createbigcannons")) {
            if (Config.cheatCannonMount) {
                ComputerCraftAPI.registerGenericSource(new CannonMountCheatPeripheral());
            } else {
                ComputerCraftAPI.registerGenericSource(new CannonMountPeripheral());
            }
        }
    }

    public static ResourceLocation resourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
