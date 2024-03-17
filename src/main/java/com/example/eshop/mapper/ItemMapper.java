package com.example.eshop.mapper;

import com.example.eshop.dto.ItemDto;
import com.example.eshop.model.Item;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

  @Mapping(source = "categoryId", target = "category.id")
  Item toEntity(ItemDto itemDto);

  @Named("toDto")
  @InheritInverseConfiguration
  ItemDto toDto(Item item);

  @IterableMapping(qualifiedByName = "toDto")
  List<ItemDto> toDtoList(List<Item> items);

}
