package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class ItemCollectorMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null || mc.world == null) return;

            mc.options.forwardKey.setPressed(false);

            ItemEntity closestItem = null;
            double closestDistance = 9999;
            Box searchBox = mc.player.getBoundingBox().expand(30);

            for (ItemEntity item : mc.world.getEntitiesByClass(
                    ItemEntity.class, searchBox, e -> true)) {
                if (!isTargetItem(item.getStack().getItem())) continue;
                double distance = mc.player.distanceTo(item);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestItem = item;
                }
            }

            if (closestItem != null) {
                Vec3d playerPos = mc.player.getPos();
                Vec3d itemPos   = closestItem.getPos();
                double dx = itemPos.x - playerPos.x;
                double dz = itemPos.z - playerPos.z;
                float  yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));

                mc.player.setYaw(yaw);
                mc.options.forwardKey.setPressed(true);

                if (mc.player.horizontalCollision) {
                    mc.player.jump();
                }
            }
        });
    }

    private boolean isTargetItem(Item item) {
        return item == Items.DIAMOND
            || item == Items.EMERALD
            || item == Items.GOLD_INGOT
            || item == Items.IRON_INGOT
            || item == Items.NETHERITE_INGOT
            || item == Items.NETHERITE_SCRAP
            || item == Items.ANCIENT_DEBRIS;
    }
}
