package com.franco227.shearable_blazes.event;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;

public class ShearableBlazesEvents {
    public static void initialize() {
        UseEntityCallback.EVENT.register(MilkBlazeListener::callback);
        UseEntityCallback.EVENT.register(ShearBlazeListener::callback);
    }
}
