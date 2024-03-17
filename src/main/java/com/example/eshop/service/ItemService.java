package com.example.eshop.service;

import com.example.eshop.dto.ItemDto;
import com.example.eshop.mapper.ItemMapper;
import com.example.eshop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemService {
  @Autowired
  private ItemRepository repository;

  @Autowired
  private ItemMapper mapper;

  public List<ItemDto> getAllItems() {
    log.debug("Getting all items: " + repository.findAll().toString());
    return mapper.toDtoList(repository.findAll());
  }

  public ItemDto createItem(ItemDto itemDto) {
    log.debug("Creating item: " + itemDto);
    try {
      return mapper.toDto(repository.save(mapper.toEntity(itemDto)));
    } catch (DataIntegrityViolationException e) {
      throw new EntityNotFoundException("Category with provided 'categoryId' doesn't exist.");
    }
  }

}
