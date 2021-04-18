package com.dykov.bakery.converter;

import com.dykov.bakery.dto.ItemDTO;
import com.dykov.bakery.entity.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter implements Converter<Item, ItemDTO> {
    @Override
    public ItemDTO convert(@NonNull final Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setCost(item.getCost());
        return itemDTO;
    }

    public Item parse(@NonNull final ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setCost(itemDTO.getCost());
        return item;
    }
}
