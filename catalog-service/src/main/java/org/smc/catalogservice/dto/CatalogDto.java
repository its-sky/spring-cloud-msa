package org.smc.catalogservice.dto;

public record CatalogDto(String productId,
						 Integer qty,
						 Integer unitPrice,
						 Integer totalPrice,
						 String orderId,
						 String userId) {
}
