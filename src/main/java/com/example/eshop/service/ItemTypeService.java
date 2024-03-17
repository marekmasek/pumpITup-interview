package com.example.eshop.service;

import com.example.eshop.dto.ItemTypeDto;
import com.example.eshop.mapper.ItemTypeMapper;
import com.example.eshop.model.ItemType;
import com.example.eshop.model.OrderItem;
import com.example.eshop.repository.ItemTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemTypeService {
  @Autowired
  private ItemTypeRepository repository;
  @Autowired
  private ItemTypeMapper mapper;

  public List<ItemTypeDto> getAllItemTypes(Long itemId) {
    log.debug("Getting all item types for itemId: " + itemId);
    return mapper.toDtoList(repository.findByItemId(itemId));
  }

  public ItemTypeDto createItemType(Long itemId, ItemTypeDto itemTypeDto) {
    log.debug("Creating item type: " + itemTypeDto);
    try {
      return mapper.toDto(repository.save(mapper.toEntity(itemId, itemTypeDto)));
    } catch (DataIntegrityViolationException e) {
      throw new EntityNotFoundException("Item with provided 'itemId' doesn't exist.");
    }
  }

  public ItemType isRequiredConfigurationAvailable(OrderItem orderItem) {
    return repository.findByItemId(orderItem.getItem().getId()).stream()
        .filter(itemType -> orderItem.getConfigurations().entrySet().stream()
            .allMatch(entry -> itemType.getConfigurations().containsKey(entry.getKey()) &&
                itemType.getConfigurations().get(entry.getKey()).equals(entry.getValue())))
        .findFirst()
        .orElse(null);
  }

}
