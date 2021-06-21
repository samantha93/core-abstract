package com.example.demo.core.impl.service.com.example.demo.core.impl.service;


import com.example.demo.core.AbstractStockCore;
import com.example.demo.core.Implementation;
import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.core.impl.service.com.example.demo.core.impl.exception.NoEnoughSpaceException;
import com.example.demo.core.impl.service.com.example.demo.core.impl.properties.StockProperties;
import com.example.demo.core.impl.service.com.example.demo.core.impl.repository.ShoeRepository;
import com.example.demo.core.impl.service.com.example.demo.core.impl.transformer.StockTransformer;
import com.example.demo.core.impl.service.com.example.demo.core.impl.transformer.UpdateStorageTransformer;
import com.example.demo.dto.in.ShoesStorage;
import com.example.demo.dto.out.Stock;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Implementation(version = 1)
@RequiredArgsConstructor
public class StockService extends AbstractStockCore {

  private final StockProperties stockProperties;

  private final ShoeRepository shoeRepository;

  @Override
  public Stock getState() {
    List<ShoeEntity> shoeEntities = shoeRepository.findAll();
    return StockTransformer.toStock(shoeEntities, stockProperties.getCapacity());

  }

  @Override
  public Stock updateStorage(ShoesStorage shoesStorage) {
    List<ShoeEntity> actualShoeEntities = shoeRepository.findAll();
    List<ShoeEntity> expectedShoeEntity = UpdateStorageTransformer.toShoesEntities(shoesStorage);

    isValid(actualShoeEntities, expectedShoeEntity);

    shoeRepository.saveAll(expectedShoeEntity);

    List<ShoeEntity> updatedEntities = shoeRepository.findAll();
    return StockTransformer.toStock(updatedEntities, stockProperties.getCapacity());
  }

  private void isValid(List<ShoeEntity> actualShoes, List<ShoeEntity> newShoes) {
    long actualCount = actualShoes.stream().mapToInt(ShoeEntity::getQuantity).sum();
    long newCount = newShoes.stream().mapToInt(ShoeEntity::getQuantity).sum();

    if (actualCount + newCount > stockProperties.getCapacity()) {
      throw new NoEnoughSpaceException(String.format("There is no space for %s shoes", actualCount + newCount));
    }
  }

}
