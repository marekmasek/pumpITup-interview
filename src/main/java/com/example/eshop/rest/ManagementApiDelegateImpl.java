package com.example.eshop.rest;

import com.example.eshop.dto.CategoryDto;
import com.example.eshop.dto.ItemDto;
import com.example.eshop.dto.ItemTypeDto;
import com.example.eshop.service.CategoryService;
import com.example.eshop.service.ItemService;
import com.example.eshop.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagementApiDelegateImpl implements ManagementApiDelegate {

  @Autowired
  private CategoryService categoryService;
  @Autowired
  private ItemService itemService;
  @Autowired
  private ItemTypeService itemTypeService;

  @Override
  public ResponseEntity<List<CategoryDto>> getCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @Override
  public ResponseEntity<List<ItemDto>> getItems() {
    return ResponseEntity.ok(itemService.getAllItems());
  }

  @Override
  public ResponseEntity<List<ItemTypeDto>> getItemTypesForItem(Long itemId) {
    return ResponseEntity.ok(itemTypeService.getAllItemTypes(itemId));
  }

  @Override
  public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto) {
    CategoryDto createdCategory = categoryService.createCategory(categoryDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
  }

  @Override
  public ResponseEntity<ItemDto> createItem(ItemDto itemDto) {
    ItemDto createdItem = itemService.createItem(itemDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
  }

  @Override
  public ResponseEntity<ItemTypeDto> createItemTypeForItem(Long itemId, ItemTypeDto itemTypeDto) {
    ItemTypeDto createdItemType = itemTypeService.createItemType(itemId, itemTypeDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdItemType);
  }

}
