package com.franco227.shearable_blazes;

import com.franco227.shearable_blazes.event.ShearableBlazesEvents;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShearableBlazes implements ModInitializer {
    public static final String MOD_ID = "shearable-blazes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ShearableBlazesEvents.initialize();
        LOGGER.info("Shear Blazes!");
    }
}
