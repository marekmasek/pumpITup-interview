package com.example.eshop.mapper;

import com.example.eshop.dto.CategoryDto;
import com.example.eshop.model.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  Category toEntity(CategoryDto categoryDto);

  @Named("toDto")
  @InheritInverseConfiguration
  CategoryDto toDto(Category category);

  @IterableMapping(qualifiedByName = "toDto")
  List<CategoryDto> toDtoList(List<Category> categories);

}
