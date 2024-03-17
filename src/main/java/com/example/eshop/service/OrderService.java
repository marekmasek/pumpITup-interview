package com.example.eshop.service;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.dto.OrderResponse;
import com.example.eshop.mapper.OrderMapper;
import com.example.eshop.model.Order;
import com.example.eshop.model.OrderItem;
import com.example.eshop.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
  @Autowired
  private OrderRepository repository;

  @Autowired
  private OrderMapper mapper;

  @Autowired
  private ItemTypeService itemTypeService;

  public OrderResponse createOrder(OrderDto orderDto) {
    log.debug("Creating order: " + orderDto);
    Order order;
    try {
      order = repository.save(mapper.toEntity(orderDto));
    } catch (DataIntegrityViolationException e) {
      throw new EntityNotFoundException("Item with provided 'itemId' doesn't exist.");
    }

    //The processing part of the order should be asynchronous...
    List<OrderItem> inStockItems = new ArrayList<>();
    List<OrderItem> notInStockItems = new ArrayList<>();
    for (OrderItem orderItem : order.getItems()) {
      if (itemTypeService.isRequiredConfigurationAvailable(orderItem) == null) {
        // todo order the item from supplier
        log.debug("The following item is not in stock, so it will be ordered from supplier, item: " + orderItem);
        notInStockItems.add(orderItem);
      } else {
        inStockItems.add(orderItem);
      }
    }

    String message = "The order was successfully received";
    if (notInStockItems.isEmpty()) {
      message += ", items in stock: "
          + inStockItems.stream()
          .map(x -> "{itemId: " + x.getItem().getId() + ", config: " + x.getConfigurations() + "}")
          .collect(Collectors.joining(", "));
    } else {
      message += ", items not in stock: "
          + notInStockItems.stream()
          .map(x -> "{itemId: " + x.getItem().getId() + ", config: " + x.getConfigurations() + "}")
          .collect(Collectors.joining(", "));
    }

    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setMessage(message);
    return orderResponse;
  }

}
