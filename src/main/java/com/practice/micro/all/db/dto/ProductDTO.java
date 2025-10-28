package com.practice.micro.all.db.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    private int id;
    private String name;
    private double price;
    private double quantity;
}
