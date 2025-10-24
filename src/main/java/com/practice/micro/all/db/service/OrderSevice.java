package com.practice.micro.all.db.service;

import com.practice.micro.all.db.dto.OrderDTO;
import com.practice.micro.all.db.entity.Order;
import com.practice.micro.all.db.entity.Product;
import com.practice.micro.all.db.mapper.OrderMapper;
import com.practice.micro.all.db.repository.OrderRepository;
import com.practice.micro.all.db.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSevice {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderMapper orderMapper;

    public OrderSevice(OrderRepository orderRepository,ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository=productRepository;
    }

    @Transactional
    public OrderDTO bookOrder(OrderDTO orderDTO) throws Exception {
        Order orderEntity = modelMapper.map(orderDTO, Order.class);
        Order order2=OrderMapper.INSTANCE.toEntity(orderDTO);

        System.out.println("Order Entity using model mapper: "+orderEntity);
        System.out.println("Order Entity using mapstruct: "+order2);

        Product product = getProduct(orderEntity);
        if (product != null && product.getQuantity() >= orderEntity.getQuantity()) {
            updateProducts(product, orderEntity);
            Order savedOrder = saveOrders(orderDTO, orderEntity, product);
            return modelMapper.map(savedOrder, OrderDTO.class);
        }else{
            throw new Exception("Product not available or In sufficient quantity");
        }

    }

    private Order saveOrders(OrderDTO orderDTO, Order orderEntity, Product product) throws Exception {
        orderEntity.setPrice(orderDTO.getQuantity()* product.getPrice());
        // Save the order
        if(product.getPrice()>=100000){
            throw new Exception("Can't book too much price");
        }
        Order savedOrder = orderRepository.save(orderEntity);
        return savedOrder;
    }

    private void updateProducts(Product product, Order orderEntity) {
        // Reduce the product quantity
        product.setQuantity(product.getQuantity() - orderEntity.getQuantity());
        orderEntity.setProduct(product);
        productRepository.save(product);
    }

    private Product getProduct(Order orderEntity) {
        return  productRepository.findById(orderEntity.getProduct().getId()).orElse(null);
    }
}
