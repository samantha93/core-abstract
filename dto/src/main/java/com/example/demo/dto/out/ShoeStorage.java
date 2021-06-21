package com.example.demo.dto.out;

import com.example.demo.dto.in.ShoeFilter.Color;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;

@Value
@Builder
@JsonDeserialize(builder = ShoeStorage.StorageShoeBuilder.class)
public class ShoeStorage {

  String name;
  BigInteger size;
  Color color;
  int quantity;

  @JsonPOJOBuilder(withPrefix = "")
  public static class StorageShoeBuilder {

  }


}
