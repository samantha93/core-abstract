package com.example.demo.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoesStorage {

  @JsonProperty("shoes")
  List<NewShoe> shoes;

}
