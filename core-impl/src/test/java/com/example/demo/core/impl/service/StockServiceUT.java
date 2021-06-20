package com.example.demo.core.impl.service;

import com.example.demo.core.impl.service.com.example.demo.core.impl.properties.StockProperties;
import com.example.demo.core.impl.service.com.example.demo.core.impl.repository.ShoeRepository;
import com.example.demo.core.impl.service.com.example.demo.core.impl.service.StockService;
import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class StockServiceUT {

  @Mock
  private StockProperties stockProperties;

  @Mock
  private ShoeRepository shoeRepository;

  @InjectMocks
  private StockService stockService;

  @Test
  public void getState_noShoesInStock_returnEMPTYState() {
    // Given
    Mockito.when(shoeRepository.count()).thenReturn(0L);
    Mockito.when(stockProperties.getCapacity()).thenReturn(30);

    // When
    Stock actual = stockService.getState();

    // Then
    Assertions.assertEquals(StockState.EMPTY, actual.getState());
    Mockito.verify(shoeRepository, Mockito.times(1)).count();
  }

  @Test
  public void getState_allShoesInStock_returnSOMEState() {
    // Given
    Mockito.when(shoeRepository.count()).thenReturn(10L);
    Mockito.when(stockProperties.getCapacity()).thenReturn(30);

    // When
    Stock actual = stockService.getState();

    // Then
    Assertions.assertEquals(StockState.SOME, actual.getState());
    Mockito.verify(shoeRepository, Mockito.times(1)).count();
  }

  @Test
  public void getState_allShoesInStock_returnFULLState() {
    // Given
    Mockito.when(shoeRepository.count()).thenReturn(30L);
    Mockito.when(stockProperties.getCapacity()).thenReturn(30);

    // When
    Stock actual = stockService.getState();

    // Then
    Assertions.assertEquals(StockState.FULL, actual.getState());
    Mockito.verify(shoeRepository, Mockito.times(1)).count();
  }

}
