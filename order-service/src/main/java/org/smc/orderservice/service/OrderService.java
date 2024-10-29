package org.smc.orderservice.service;

import java.util.List;

import org.smc.orderservice.dto.OrderDto;
import org.smc.orderservice.entity.OrderEntity;
import org.smc.orderservice.vo.ResponseOrder;

public interface OrderService {
	OrderDto createOrder(OrderDto orderDto);

	OrderDto getOrdersByOrderId(String orderId);

	List<ResponseOrder> getOrdersByUserId(String userId);
}
