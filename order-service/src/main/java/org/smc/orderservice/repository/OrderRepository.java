package org.smc.orderservice.repository;

import java.util.List;

import org.smc.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	OrderEntity findByOrderId(String orderId);

	List<OrderEntity> findByUserId(String userId);
}
