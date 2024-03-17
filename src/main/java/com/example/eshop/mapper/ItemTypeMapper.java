package com.example.eshop.mapper;

import com.example.eshop.dto.ItemTypeDto;
import com.example.eshop.model.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemTypeMapper {


  @Mapping(source = "itemId", target = "item.id")
  ItemType toEntity(Long itemId, ItemTypeDto itemTypeDto);

  @Named("toDto")
  @InheritInverseConfiguration
  ItemTypeDto toDto(ItemType itemType);

  @IterableMapping(qualifiedByName = "toDto")
  List<ItemTypeDto> toDtoList(List<ItemType> itemTypes);

}
