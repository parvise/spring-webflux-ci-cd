package com.practice.micro.all.db.repository;

import com.practice.micro.all.db.entity.Order;
import com.practice.micro.all.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
