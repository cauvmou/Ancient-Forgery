package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.item.custom.TestBrush;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static com.github.ancient_forgery.data.registry.BlockRegistry.*;
import static com.github.ancient_forgery.data.registry.ItemRegistry.*;

public class ItemGroupRegistry implements AFRegistry {

    private static final RegistryKey<ItemGroup> MOD_GROUP = RegistryKey.of(
            RegistryKeys.ITEM_GROUP, new Identifier("ancient_forgery", "mod_group"));

    @Override
    public void register() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content ->
                content.addAfter(Items.PINK_CANDLE, CANDELABRA));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content ->
                content.addAfter(Items.PINK_CANDLE, CANDELABRA));

        for (int i = 0; i < CANDELABRA_LIST.size()-1; i++) {
            int finalI = i;

            ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content ->
                    content.addAfter(CANDELABRA_LIST.get(finalI), CANDELABRA_LIST.get(finalI +1)));
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content ->
                    content.addAfter(CANDELABRA_LIST.get(finalI), CANDELABRA_LIST.get(finalI +1)));
        }

        Registry.register(Registries.ITEM_GROUP, MOD_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.ancient_forgery.mod_group"))
                .icon(() -> new ItemStack(Items.SOUL_CAMPFIRE))
                .entries((displayContext, entries) -> {
                    entries.add(CANDELABRA);
                    entries.add(COPPER_NUGGET);
                    entries.add(LONGBOW);
                })
                .build()
        );
    }
}
