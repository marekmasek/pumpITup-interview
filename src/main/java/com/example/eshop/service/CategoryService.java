package com.example.eshop.service;

import com.example.eshop.dto.CategoryDto;
import com.example.eshop.mapper.CategoryMapper;
import com.example.eshop.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {
  @Autowired
  private CategoryRepository repository;
  @Autowired
  private CategoryMapper mapper;

  public List<CategoryDto> getAllCategories() {
    log.debug("Getting all categories");
    return mapper.toDtoList(repository.findAll());
  }

  public CategoryDto createCategory(CategoryDto categoryDto) {
    log.debug("Creating category: " + categoryDto);
    return mapper.toDto(repository.save(mapper.toEntity(categoryDto)));
  }
}
