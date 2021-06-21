package com.example.demo.core.impl.service.com.example.demo.core.impl.transformer;

import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.dto.in.NewShoe;
import com.example.demo.dto.in.ShoesStorage;

import java.util.List;
import java.util.stream.Collectors;

public class UpdateStorageTransformer {

  public static List<ShoeEntity> toShoesEntities(ShoesStorage shoesStorage) {
    return shoesStorage.getShoes()
            .stream()
            .map(UpdateStorageTransformer::toShoesEntities)
            .collect(Collectors.toList());
  }

  private static ShoeEntity toShoesEntities(NewShoe newShoe) {
    return ShoeEntity.builder()
            .name(newShoe.getName())
            .size(newShoe.getSize().intValue())
            .color(newShoe.getColor())
            .quantity(newShoe.getQuantity())
            .build();
  }
}
