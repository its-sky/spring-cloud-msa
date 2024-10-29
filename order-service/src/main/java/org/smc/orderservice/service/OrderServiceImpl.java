package org.smc.orderservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.smc.orderservice.dto.OrderDto;
import org.smc.orderservice.entity.OrderEntity;
import org.smc.orderservice.repository.OrderRepository;
import org.smc.orderservice.vo.ResponseOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		orderDto.setOrderId(UUID.randomUUID().toString());
		orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		OrderEntity orderEntity = mapper.map(orderDto, OrderEntity.class);

		orderRepository.save(orderEntity);

		orderDto = mapper.map(orderEntity, OrderDto.class);
		return orderDto;
	}

	@Override
	public OrderDto getOrdersByOrderId(String orderId) {
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		OrderDto orderDto = new ModelMapper().map(orderEntity, OrderDto.class);

		return orderDto;
	}

	@Override
	public List<ResponseOrder> getOrdersByUserId(String userId) {
		List<OrderEntity> orderEntities = orderRepository.findByUserId(userId);

		ModelMapper mapper = new ModelMapper();
		ArrayList<ResponseOrder> result = new ArrayList<>();
		orderEntities.forEach(order -> result.add(mapper.map(order, ResponseOrder.class)));

		return result;
	}
}
