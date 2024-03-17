package com.example.eshop.mapper;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

  Order toEntity(OrderDto orderDto);

}
