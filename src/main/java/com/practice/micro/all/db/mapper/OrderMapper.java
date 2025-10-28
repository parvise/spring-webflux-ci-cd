package com.practice.micro.all.db.mapper;

import com.practice.micro.all.db.dto.OrderDTO;
import com.practice.micro.all.db.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    public OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(OrderDTO dto);
    OrderDTO toDto(Order  entity);
}


