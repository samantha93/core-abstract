package com.example.demo.core.impl.service.com.example.demo.core.impl.transformer;

import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Shoes;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class ShoesTransformer {

  public static Shoes toShoes(List<ShoeEntity> shoeEntities) {
    List<Shoe> shoes = shoeEntities.stream().map(ShoesTransformer::toShoe).collect(Collectors.toList());
    return Shoes.builder().shoes(shoes).build();
  }

  private static Shoe toShoe(ShoeEntity shoeEntity) {
    return Shoe.builder()
            .name(shoeEntity.getName())
            .color(shoeEntity.getColor())
            .size(BigInteger.valueOf(shoeEntity.getSize()))
            .build();
  }
}
