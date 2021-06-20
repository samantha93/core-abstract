package com.example.demo.core.impl.service.com.example.demo.core.impl.entity;

import com.example.demo.dto.in.ShoeFilter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shoe")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ShoeEntity {

  @Id
  @GeneratedValue
  @Column(name = "shoe_id", nullable = false)
  private Integer id;

  @Column(name = "name", length = 250, nullable = false)
  private String name;

  @Column(name = "size", nullable = false)
  private int size;

  @Column(name = "color", nullable = false)
  @Enumerated(EnumType.STRING)
  private ShoeFilter.Color color;

}
