package com.example.demo.core.impl.service.com.example.demo.core.impl.transformer;

import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.dto.out.ShoeStorage;
import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockState;

import java.util.List;

public class StockTransformer {

  public static Stock toStock(List<ShoeEntity> shoeEntities, int capacity) {
    List<ShoeStorage> shoes = ShoesStorageTransformer.toShoesList(shoeEntities);
    int totalShoes = shoes.stream().mapToInt(ShoeStorage::getQuantity).sum();

    StockState state = StockState.SOME;
    if (totalShoes == 0) {
      state = StockState.EMPTY;
    }
    if (totalShoes >= capacity) {
      state = StockState.FULL;
    }

    return Stock.builder()
            .shoes(shoes)
            .state(state)
            .build();
  }
}
