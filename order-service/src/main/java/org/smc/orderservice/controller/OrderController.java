package org.smc.orderservice.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.smc.orderservice.dto.OrderDto;
import org.smc.orderservice.entity.OrderEntity;
import org.smc.orderservice.service.OrderService;
import org.smc.orderservice.vo.RequestOrder;
import org.smc.orderservice.vo.ResponseOrder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
	private final Environment env;
	private final OrderService orderService;

	@GetMapping("/health_check")
	public String healthCheck() {
		return String.format("It's Working in Catalog Service on PORT %s", env.getProperty("local.server.port"));
	}

	@PostMapping("/{userId}/orders")
	public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
													@RequestBody RequestOrder request) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		OrderDto orderDto = mapper.map(request, OrderDto.class);
		orderDto.setUserId(userId);

		OrderDto createdOrder = orderService.createOrder(orderDto);
		ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
	}

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
		List<ResponseOrder> orders = orderService.getOrdersByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}
}
