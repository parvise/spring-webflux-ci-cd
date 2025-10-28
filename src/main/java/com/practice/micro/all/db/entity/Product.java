package com.practice.micro.all.db.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name ="products")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private double quantity;

}
