package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.item.custom.SoulBottleItem;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;

public class EventRegistry implements AFRegistry {

    @Override
    public void register() {
        UseEntityCallback.EVENT.register(SoulBottleItem::OnEntityUseEvent);
    }
}
