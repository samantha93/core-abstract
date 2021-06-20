package com.example.demo.core.impl.transformer;

import com.example.demo.core.impl.service.com.example.demo.core.impl.transformer.StockTransformer;
import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StockTransformerUT {

  @Test
  public void toStockState_noShoeInStock_returnEMPTYState() {

    // When
    Stock actualState = StockTransformer.toStock(123, 0);

    // Then
    Assertions.assertEquals(StockState.EMPTY, actualState.getState());
  }

  @Test
  public void toStockState_availableSpaceInStock_returnSOMEState() {

    // When
    Stock actualState = StockTransformer.toStock(123, 15);

    // Then
    Assertions.assertEquals(StockState.SOME, actualState.getState());
  }

  @Test
  public void toStockState_noMoreAvailabilityInStock_returnFULLState() {

    // When
    Stock actualState = StockTransformer.toStock(123, 123);

    // Then
    Assertions.assertEquals(StockState.FULL, actualState.getState());
  }
}
