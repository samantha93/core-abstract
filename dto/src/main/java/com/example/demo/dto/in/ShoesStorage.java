package com.example.demo.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShoesStorage {

  @JsonProperty("shoes")
  List<NewShoe> shoes;

}
