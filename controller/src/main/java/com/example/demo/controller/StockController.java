package com.example.demo.controller;

import com.example.demo.dto.in.ShoesStorage;
import com.example.demo.dto.out.Stock;
import com.example.demo.facade.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

  private final StockFacade stockFacade;

  @GetMapping(path = "/state", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Stock> getState(@RequestHeader Integer version) {
    return ResponseEntity.ok(stockFacade.get(version).getState());
  }

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Stock> updateStorage(@RequestHeader Integer version, @RequestBody ShoesStorage shoesStorage) {
    return ResponseEntity.ok(stockFacade.get(version).updateStorage(shoesStorage));
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void clearAll(@RequestHeader Integer version) {
    stockFacade.get(version).clearAll();
  }

}
