package com.example.demo.core.impl.service.com.example.demo.core.impl.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class StockProperties {

  @Value("${stock.capacity:30}")
  public int capacity;

}
