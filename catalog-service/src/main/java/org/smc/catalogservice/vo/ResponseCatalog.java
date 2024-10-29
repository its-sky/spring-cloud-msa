package org.smc.catalogservice.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseCatalog(
	String productId,
	String productName,
	Integer unitPrice,
	Integer stock,
	Date createdAt
) {
}
