package com.example.demo.core.impl.service.com.example.demo.core.impl.transformer;

import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.dto.in.ShoesStorage;
import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.ShoeStorage;
import com.example.demo.dto.out.Shoes;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class ShoesStorageTransformer {

  public static List<ShoeStorage> toShoesList(List<ShoeEntity> shoeEntities) {
    return shoeEntities.stream().map(ShoesStorageTransformer::toShoeStorage).collect(Collectors.toList());
  }

  private static ShoeStorage toShoeStorage(ShoeEntity shoeEntity) {
    return ShoeStorage.builder()
            .name(shoeEntity.getName())
            .color(shoeEntity.getColor())
            .size(BigInteger.valueOf(shoeEntity.getSize()))
            .quantity(shoeEntity.getQuantity())
            .build();
  }
}
