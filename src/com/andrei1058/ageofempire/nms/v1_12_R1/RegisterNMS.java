package com.andrei1058.ageofempire.nms.v1_12_R1;

import net.minecraft.server.v1_12_R1.EntityTypes;
import net.minecraft.server.v1_12_R1.MinecraftKey;

public class RegisterNMS {
    public static void registerEntity(String name, int id, Class customClass) {
        EntityTypes.b.a(id, new MinecraftKey(name), customClass);
    }
}