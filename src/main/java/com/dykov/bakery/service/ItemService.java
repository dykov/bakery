package com.dykov.bakery.service;

import java.util.List;
import java.util.UUID;
import com.dykov.bakery.dto.ItemDTO;
import com.dykov.bakery.exception.ItemAlreadyExistsException;
import com.dykov.bakery.exception.ItemNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    ItemDTO getItem(UUID id) throws ItemNotFound;

    List<ItemDTO> getAllItems();

    Page<ItemDTO> getAllItems(Pageable pageable);

    ItemDTO createItem(ItemDTO itemDTO) throws ItemAlreadyExistsException;

    ItemDTO editItem(UUID id, ItemDTO itemDTO) throws ItemNotFound, ItemAlreadyExistsException;

    void deleteItem(UUID id) throws ItemNotFound;
}
