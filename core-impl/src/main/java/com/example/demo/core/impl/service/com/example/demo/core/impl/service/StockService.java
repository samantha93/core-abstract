package com.example.demo.core.impl.service.com.example.demo.core.impl.service;


import com.example.demo.core.AbstractStockCore;
import com.example.demo.core.Implementation;
import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.core.impl.service.com.example.demo.core.impl.properties.StockProperties;
import com.example.demo.core.impl.service.com.example.demo.core.impl.repository.ShoeRepository;
import com.example.demo.core.impl.service.com.example.demo.core.impl.transformer.StockTransformer;
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

}
