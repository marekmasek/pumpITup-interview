package com.example.eshop.mapper;

import com.example.eshop.dto.OrderDtoItemsInner;
import com.example.eshop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

  @Mapping(source = "itemId", target = "item.id")
  OrderItem toEntity(OrderDtoItemsInner orderDtoItemsInner);

}
