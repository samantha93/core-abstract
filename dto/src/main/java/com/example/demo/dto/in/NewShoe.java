package com.example.demo.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class NewShoe {

  @JsonProperty("name")
  String name;

  @JsonProperty("size")
  BigInteger size;

  @JsonProperty("color")
  ShoeFilter.Color color;

  @JsonProperty("quantity")
  int quantity;

}
