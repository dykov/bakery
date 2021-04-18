package com.dykov.bakery.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.dykov.bakery.converter.ItemConverter;
import com.dykov.bakery.dto.ItemDTO;
import com.dykov.bakery.entity.Item;
import com.dykov.bakery.exception.ItemAlreadyExistsException;
import com.dykov.bakery.exception.ItemNotFound;
import com.dykov.bakery.repository.ItemRepository;
import com.dykov.bakery.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemConverter converter;

    public ItemDTO getItem(UUID id) throws ItemNotFound {
        Item item = this.getOrThrow(id);
        return converter.convert(item);
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
            .map(converter::convert).collect(Collectors.toList());
    }

    public Page<ItemDTO> getAllItems(Pageable pageable) {
        Page<Item> items = itemRepository.findAll(pageable);
        List<ItemDTO> itemDTOs = items.stream()
            .map(converter::convert)
            .collect(Collectors.toList());
        return new PageImpl<>(itemDTOs, pageable, items.getTotalElements());
    }

    public ItemDTO createItem(ItemDTO itemDTO) throws ItemAlreadyExistsException {
        if (itemRepository.existsByName(itemDTO.getName())) {
            throw new ItemAlreadyExistsException(itemDTO.getName());
        }
        Item item = converter.parse(itemDTO);
        return converter.convert(itemRepository.saveAndFlush(item));
    }

    public ItemDTO editItem(UUID id, ItemDTO itemDTO) throws ItemNotFound,
        ItemAlreadyExistsException {
        Item stored = this.getOrThrow(id);
        Item edited = converter.parse(itemDTO);
        if (!stored.getName().equals(edited.getName())) {
            this.checkIfNameExist(edited.getName());
        }
        edited.setId(stored.getId());
        return converter.convert(itemRepository.saveAndFlush(edited));
    }

    public void deleteItem(UUID id) throws ItemNotFound {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFound(id);
        }
        itemRepository.deleteById(id);
    }

    private Item getOrThrow(UUID id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFound(id));
    }

    private void checkIfNameExist(String newName) {
        if (itemRepository.existsByName(newName)) {
            throw new ItemAlreadyExistsException(newName);
        }
    }
}
