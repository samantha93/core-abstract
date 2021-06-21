package com.example.demo.core.impl.service;

import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.core.impl.service.com.example.demo.core.impl.exception.NoEnoughSpaceException;
import com.example.demo.core.impl.service.com.example.demo.core.impl.properties.StockProperties;
import com.example.demo.core.impl.service.com.example.demo.core.impl.repository.ShoeRepository;
import com.example.demo.core.impl.service.com.example.demo.core.impl.service.StockService;
import com.example.demo.dto.in.NewShoe;
import com.example.demo.dto.in.ShoesStorage;
import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

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
    Mockito.when(shoeRepository.findAll()).thenReturn(Collections.emptyList());
    Mockito.when(stockProperties.getCapacity()).thenReturn(30);

    // When
    Stock actual = stockService.getState();

    // Then
    Assertions.assertEquals(StockState.EMPTY, actual.getState());
    Mockito.verify(shoeRepository, Mockito.times(1)).findAll();
  }

  @Test
  public void getState_allShoesInStock_returnSOMEState() {
    // Given
    ShoeEntity shoeEntity = mockShoeEntity(15);
    Mockito.when(shoeRepository.findAll()).thenReturn(List.of(shoeEntity));
    Mockito.when(stockProperties.getCapacity()).thenReturn(30);

    // When
    Stock actual = stockService.getState();

    // Then
    Assertions.assertEquals(StockState.SOME, actual.getState());
    Mockito.verify(shoeRepository, Mockito.times(1)).findAll();
  }

  @Test
  public void getState_allShoesInStock_returnFULLState() {
    // Given
    ShoeEntity shoeEntity1 = mockShoeEntity(13);
    ShoeEntity shoeEntity2 = mockShoeEntity(17);
    Mockito.when(shoeRepository.findAll()).thenReturn(List.of(shoeEntity1, shoeEntity2));
    Mockito.when(stockProperties.getCapacity()).thenReturn(30);
    Mockito.when(stockProperties.getCapacity()).thenReturn(30);

    // When
    Stock actual = stockService.getState();

    // Then
    Assertions.assertEquals(StockState.FULL, actual.getState());
    Mockito.verify(shoeRepository, Mockito.times(1)).findAll();
  }

  @Test
  public void updateStorage_tooMuchShoesToAdd_throwException() {
    // Given
    ShoeEntity shoeEntity = mockShoeEntity(15);
    List<ShoeEntity> shoeEntities = List.of(shoeEntity);
    Mockito.when(shoeRepository.findAll()).thenReturn(shoeEntities);
    Mockito.when(stockProperties.getCapacity()).thenReturn(20);
    ShoesStorage shoesStorage = ShoesStorage.builder()
            .shoes(List.of(mockNewShoe(10)))
            .build();

    // When
    Assertions.assertThrows(NoEnoughSpaceException.class, () -> stockService.updateStorage(shoesStorage));

    // Then
    Mockito.verify(shoeRepository, Mockito.times(1)).findAll();
    Mockito.verify(shoeRepository, Mockito.times(0)).saveAll(Mockito.any());
  }

  @Test
  public void updateStorage_correctNumberOfShoesToAdd_shoesAdded() {
    // Given
    ShoeEntity shoeEntity = mockShoeEntity(10);
    List<ShoeEntity> shoeEntities = List.of(shoeEntity);
    Mockito.when(shoeRepository.findAll()).thenReturn(shoeEntities);
    Mockito.when(stockProperties.getCapacity()).thenReturn(30);
    ShoesStorage shoesStorage = ShoesStorage.builder()
            .shoes(List.of(mockNewShoe(5)))
            .build();

    // When
    stockService.updateStorage(shoesStorage);

    // Then
    Mockito.verify(shoeRepository, Mockito.times(2)).findAll();
    Mockito.verify(shoeRepository, Mockito.times(1)).saveAll(Mockito.any());
  }

  private ShoeEntity mockShoeEntity(int qt) {
    ShoeEntity shoeEntity = Mockito.mock(ShoeEntity.class);
    Mockito.when(shoeEntity.getQuantity()).thenReturn(qt);
    return shoeEntity;
  }

  private NewShoe mockNewShoe(int quantity){
    NewShoe newShoe = Mockito.mock(NewShoe.class);
    Mockito.when(newShoe.getQuantity()).thenReturn(quantity);
    Mockito.when(newShoe.getSize()).thenReturn(BigInteger.valueOf(42));
    return newShoe;
  }

}
