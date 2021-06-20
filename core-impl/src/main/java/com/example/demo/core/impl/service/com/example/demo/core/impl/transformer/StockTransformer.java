package com.example.demo.core.impl.service.com.example.demo.core.impl.transformer;

import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockState;

public class StockTransformer {

  public static Stock toStock(int capacity, long numberShoe) {
    StockState state = StockState.SOME;
    if (numberShoe == 0) {
      state = StockState.EMPTY;
    }

    if (numberShoe == capacity) {
      state = StockState.FULL;
    }

    return Stock.builder().state(state).build();
  }
}
