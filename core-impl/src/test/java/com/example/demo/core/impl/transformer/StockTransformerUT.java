package com.example.demo.core.impl.transformer;

import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.core.impl.service.com.example.demo.core.impl.transformer.StockTransformer;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class StockTransformerUT {

  @Test
  public void toStockState_noShoeInStock_returnEMPTYState() {

    // When
    Stock actualState = StockTransformer.toStock(Collections.emptyList(), 123);

    // Then
    Assertions.assertEquals(StockState.EMPTY, actualState.getState());
    Assertions.assertTrue(actualState.getShoes().isEmpty());
  }

  @Test
  public void toStockState_availableSpaceInStock_returnSOMEState() {
    // Given
    ShoeEntity shoeEntity = new ShoeEntity(123, "Running shoes", 40, ShoeFilter.Color.BLACK, 12);

    // When
    Stock actualState = StockTransformer.toStock(List.of(shoeEntity), 123);

    // Then
    Assertions.assertEquals(StockState.SOME, actualState.getState());
    Assertions.assertEquals(1, actualState.getShoes().size());
  }

  @Test
  public void toStockState_noMoreAvailabilityInStock_returnFULLState() {
    // Given
    ShoeEntity shoeEntity1 = new ShoeEntity(123, "Running shoes", 40, ShoeFilter.Color.BLACK, 10);
    ShoeEntity shoeEntity2 = new ShoeEntity(456, "Running shoes", 40, ShoeFilter.Color.BLUE, 5);
    ShoeEntity shoeEntity3 = new ShoeEntity(789, "Running shoes", 40, ShoeFilter.Color.BLACK, 2);

    // When
    Stock actualState = StockTransformer.toStock(
            List.of(shoeEntity1, shoeEntity2, shoeEntity3),
            17);

    // Then
    Assertions.assertEquals(StockState.FULL, actualState.getState());
    Assertions.assertEquals(3, actualState.getShoes().size());
  }

}
