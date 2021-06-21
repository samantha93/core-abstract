package com.example.demo.core;

import com.example.demo.dto.in.ShoesStorage;
import com.example.demo.dto.out.Stock;

public interface StockCore {

  Stock getState();

  Stock updateStorage(ShoesStorage shoesStorage);
}
