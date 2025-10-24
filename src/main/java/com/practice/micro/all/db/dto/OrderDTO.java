package com.practice.micro.all.db.dto;

import com.practice.micro.all.db.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private int id;
    private ProductDTO product;
    private int quantity;
    private double price;
}
