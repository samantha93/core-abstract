package com.example.demo.core.impl.service.com.example.demo.core.impl.repository;

import com.example.demo.core.impl.service.com.example.demo.core.impl.entity.ShoeEntity;
import com.example.demo.dto.in.ShoeFilter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoeRepository extends CrudRepository<ShoeEntity, Integer> {

  List<ShoeEntity> findByColorAndSize(ShoeFilter.Color color, Integer size);

  List<ShoeEntity> findAll();

}
