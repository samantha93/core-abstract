package com.example.demo.core.impl.service;

import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.core.impl.service.com.example.demo.core.impl.repository.ShoeRepository;
import com.example.demo.core.impl.service.com.example.demo.core.impl.service.ShoeService;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Shoes;
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
public class ShoeServiceUT {

  @Mock
  private ShoeRepository shoeRepository;

  @InjectMocks
  private ShoeService shoeService;

  @Test
  public void search_shoesExistAndDoNotMatch_returnEmptyList() {
    // Given
    BigInteger actualSearchSize = BigInteger.valueOf(42);
    ShoeFilter.Color actualSearchColor = ShoeFilter.Color.BLACK;
    ShoeFilter filter = new ShoeFilter(actualSearchSize, actualSearchColor);
    Mockito.when(shoeRepository.findByColorAndSize(actualSearchColor, actualSearchSize.intValue())).thenReturn(Collections.emptyList());

    // When
    Shoes actual = shoeService.findAll(filter);

    // Then
    Assertions.assertTrue(actual.getShoes().isEmpty());
    Mockito.verify(shoeRepository, Mockito.times(1)).findByColorAndSize(Mockito.any(), Mockito.anyInt());
  }

  @Test
  public void search_shoesExistAndMatchOnlyColor_shouldCallFindByColor() {
    // Given
    int actualSize = 42;
    ShoeFilter.Color actualSearchColor = ShoeFilter.Color.BLACK;
    ShoeFilter filter = new ShoeFilter(null, actualSearchColor);

    List<ShoeEntity> shoe1 = List.of(new ShoeEntity(159, "Puma Running", actualSize, actualSearchColor, 12));

    // When
    Shoes actual = shoeService.findAll(filter);

    // Then
    Mockito.verify(shoeRepository, Mockito.never()).findBySize(Mockito.anyInt());
    Mockito.verify(shoeRepository, Mockito.times(1)).findByColor(Mockito.any());
    Mockito.verify(shoeRepository, Mockito.never()).findByColorAndSize(Mockito.any(), Mockito.anyInt());
    Mockito.verify(shoeRepository, Mockito.never()).findAll();
  }

  @Test
  public void search_shoesExistAndMatchOnlySize_shouldCallFindBySize() {
    // Given
    int actualSize = 42;
    BigInteger actualSearchSize = BigInteger.valueOf(actualSize);
    ShoeFilter.Color actualSearchColor = ShoeFilter.Color.BLACK;
    ShoeFilter filter = new ShoeFilter(actualSearchSize, null);

    List<ShoeEntity> shoe1 = List.of(new ShoeEntity(159, "Puma Running", actualSize, actualSearchColor, 12));

    // When
    shoeService.findAll(filter);

    // Then
    Mockito.verify(shoeRepository, Mockito.times(1)).findBySize(Mockito.anyInt());
    Mockito.verify(shoeRepository, Mockito.never()).findByColor(Mockito.any());
    Mockito.verify(shoeRepository, Mockito.never()).findByColorAndSize(Mockito.any(), Mockito.anyInt());
    Mockito.verify(shoeRepository, Mockito.never()).findAll();
  }

  @Test
  public void search_shoesExistAndMatchSizeAndColor_shouldCallFindByColorAndSize() {
    // Given
    int actualSize = 42;
    BigInteger actualSearchSize = BigInteger.valueOf(actualSize);
    ShoeFilter.Color actualSearchColor = ShoeFilter.Color.BLACK;
    ShoeFilter filter = new ShoeFilter(actualSearchSize, ShoeFilter.Color.BLACK);

    List<ShoeEntity> shoe1 = List.of(new ShoeEntity(159, "Puma Running", actualSize, actualSearchColor, 12));

    // When
    shoeService.findAll(filter);

    // Then
    Mockito.verify(shoeRepository, Mockito.never()).findBySize(Mockito.anyInt());
    Mockito.verify(shoeRepository, Mockito.never()).findByColor(Mockito.any());
    Mockito.verify(shoeRepository, Mockito.times(1)).findByColorAndSize(Mockito.any(), Mockito.anyInt());
    Mockito.verify(shoeRepository, Mockito.never()).findAll();
  }

  @Test
  public void search_shoesExistButNoFilterSet_shouldCallFindAll() {
    // Given
    int actualSize = 42;
    ShoeFilter.Color actualSearchColor = ShoeFilter.Color.BLACK;
    ShoeFilter filter = new ShoeFilter(null, null);

    List<ShoeEntity> shoe1 = List.of(new ShoeEntity(159, "Puma Running", actualSize, actualSearchColor, 12));

    // When
    shoeService.findAll(filter);

    // Then
    Mockito.verify(shoeRepository, Mockito.never()).findBySize(Mockito.anyInt());
    Mockito.verify(shoeRepository, Mockito.never()).findByColor(Mockito.any());
    Mockito.verify(shoeRepository, Mockito.never()).findByColorAndSize(Mockito.any(), Mockito.anyInt());
    Mockito.verify(shoeRepository, Mockito.times(1)).findAll();
  }

}
