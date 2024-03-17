package com.example.eshop.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Entity
@Getter
@Setter
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @ElementCollection
  @CollectionTable(name = "order_item_configurations", joinColumns = @JoinColumn(name = "order_item_id"))
  @MapKeyColumn(name = "config_name")
  @Column(name = "config_value")
  private Map<String, String> configurations = new HashMap<>();

}
