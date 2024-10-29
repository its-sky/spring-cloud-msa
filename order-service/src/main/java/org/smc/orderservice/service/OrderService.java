package org.smc.orderservice.service;

import org.smc.orderservice.dto.OrderDto;
import org.smc.orderservice.entity.OrderEntity;

public interface OrderService {
	OrderDto createOrder(OrderDto orderDto);

	OrderDto getOrdersByOrderId(String orderId);

	Iterable<OrderEntity> getOrdersByUserId(String userId);
}
