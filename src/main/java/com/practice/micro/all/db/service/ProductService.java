package com.practice.micro.all.db.service;

import com.practice.micro.all.db.entity.Product;
import com.practice.micro.all.db.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> saveDefaultProducts() {
        List<Product> list=List.of(Product.builder().name("Laptop").price(25000.00).quantity(10).build(),
                Product.builder().name("Iphone 17").price(100000.00).quantity(15).build());

        Product.builder().name("ProductC").price(300.0).build();
        return repository.saveAll(list);



    }
}
