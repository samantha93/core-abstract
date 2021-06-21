package com.example.demo.core.impl.service.com.example.demo.core.impl.service;


import com.example.demo.core.AbstractShoeCore;
import com.example.demo.core.Implementation;
import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.core.impl.service.com.example.demo.core.impl.repository.ShoeRepository;
import com.example.demo.core.impl.service.com.example.demo.core.impl.transformer.ShoesTransformer;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Shoes;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Implementation(version = 1)
@RequiredArgsConstructor
public class ShoeService extends AbstractShoeCore {

  private final ShoeRepository shoeRepository;

  @Override
  public Shoes findAll(ShoeFilter filter) {
    // TODO define default
    List<ShoeEntity> shoeEntities = shoeRepository.findByColorAndSize(
            filter.getColor().orElse(null),
            filter.getSize().isPresent() ? filter.getSize().get().intValue() : null);
    return ShoesTransformer.toShoes(shoeEntities);
  }

}
