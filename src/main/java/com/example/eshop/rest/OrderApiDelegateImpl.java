package com.example.eshop.rest;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.dto.OrderResponse;
import com.example.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderApiDelegateImpl implements OrdersApiDelegate {

  @Autowired
  private OrderService orderService;

  @Override
  public ResponseEntity<OrderResponse> createOrder(OrderDto orderDto) {
    return ResponseEntity.ok(orderService.createOrder(orderDto));
  }

}
