package com.example.demo.core.impl.service;

import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Shoes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigInteger;

public class ShoeServiceUT {

    @InjectMocks
    private ShoeService shoeService;

    @BeforeEach
    public void before() {
        shoeService = new ShoeService();
    }

    @Test
    public void search_noShoesExist_returnEmptyList() {
        // Given
        ShoeFilter filter = new ShoeFilter(BigInteger.valueOf(42), ShoeFilter.Color.BLACK);

        // When
        Shoes actual = shoeService.search(filter);

        // Then
        Assertions.assertTrue(actual.getShoes().isEmpty());
    }

    @Test
    public void search_shoesExistAndDoNotMatch_returnEmptyList() {
        // Given
        ShoeFilter filter = new ShoeFilter(BigInteger.valueOf(42), ShoeFilter.Color.BLACK);

        // When
        Shoes actual = shoeService.search(filter);

        // Then
        Assertions.assertTrue(actual.getShoes().isEmpty());
    }

    @Test
    public void search_shoesExistAndMatch_returnMatchedShoes() {
        // Given
        ShoeFilter filter = new ShoeFilter(BigInteger.valueOf(42), ShoeFilter.Color.BLACK);

        // When
        Shoes actual = shoeService.search(filter);

        // Then
        Assertions.assertFalse(actual.getShoes().isEmpty());
        Assertions.assertEquals(actual.getShoes().get(0).getColor(), ShoeFilter.Color.BLACK);
        Assertions.assertEquals(actual.getShoes().get(0).getSize(), BigInteger.valueOf(42));
    }

}
