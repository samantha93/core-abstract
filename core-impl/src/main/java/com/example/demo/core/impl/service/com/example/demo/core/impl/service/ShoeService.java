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
    List<ShoeEntity> shoeEntities;
    if (filter.getSize().isEmpty() && filter.getColor().isEmpty()) {
      shoeEntities = shoeRepository.findAll();
      return ShoesTransformer.toShoes(shoeEntities);
    }

    if (filter.getSize().isPresent() && filter.getColor().isPresent()) {
      shoeEntities = shoeRepository.findByColorAndSize(filter.getColor().get(), filter.getSize().get().intValue());
      return ShoesTransformer.toShoes(shoeEntities);
    }

    if (filter.getSize().isPresent()) {
      shoeEntities = shoeRepository.findBySize(filter.getSize().get().intValue());
      return ShoesTransformer.toShoes(shoeEntities);
    }

    if (filter.getColor().isPresent()) {
      shoeEntities = shoeRepository.findByColor(filter.getColor().get());
      return ShoesTransformer.toShoes(shoeEntities);
    }

    return ShoesTransformer.toEmptyShoes();
  }

}
