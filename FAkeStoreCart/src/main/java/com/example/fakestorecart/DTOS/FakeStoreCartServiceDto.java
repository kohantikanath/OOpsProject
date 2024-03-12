package com.example.fakestorecart.DTOS;

import com.example.fakestorecart.Models.Products;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter

public class FakeStoreCartServiceDto {
    private double id;
    private double userId;
    private String date;
    private HashMap<String, Integer>[] products;
}
