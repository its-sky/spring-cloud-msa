package org.smc.catalogservice.repository;

import org.smc.catalogservice.entity.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogEntity, Long> {
	CatalogEntity findByProductId(String productId);
}
