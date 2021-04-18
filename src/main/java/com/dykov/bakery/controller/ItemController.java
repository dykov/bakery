package com.dykov.bakery.controller;


import java.util.List;
import java.util.UUID;
import com.dykov.bakery.dto.ItemDTO;
import com.dykov.bakery.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/item")
public class ItemController {
    private ItemService itemService;

    @GetMapping(path = "/{id}")
    public ItemDTO getItem(@PathVariable(name = "id") UUID id) {
        return itemService.getItem(id);
    }

//    @GetMapping
//    public List<ItemDTO> getAllItems() {
//        return itemService.getAllItems();
//    }

    @GetMapping
    public Page<ItemDTO> getAllItems(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return itemService.getAllItems(pageable);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        return itemService.createItem(itemDTO);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ItemDTO editItem(@PathVariable(name = "id") UUID id,
                            @RequestBody ItemDTO itemDTO) {
        return itemService.editItem(id, itemDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable(name = "id") UUID id) {
        itemService.deleteItem(id);
    }

}
