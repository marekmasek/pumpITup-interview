package com.example.eshop.repository;

import com.example.eshop.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {

  List<ItemType> findByItemId(Long itemId);


}
