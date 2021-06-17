package com.example.demo.core.impl.service;

import com.example.demo.AbstractShoeCore;
import com.example.demo.Implementation;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Shoes;

import java.util.List;

@Implementation(version = 1)
public class ShoeService extends AbstractShoeCore {

    @Override
    public Shoes search(ShoeFilter filter) {
        // Call DB
        return Shoes.builder().shoes(List.of(Shoe.builder().color(ShoeFilter.Color.BLACK).name("SamSam").build())).build();
    }
}