package org.smc.catalogservice.service;

import org.smc.catalogservice.entity.CatalogEntity;

public interface CatalogService {
	Iterable<CatalogEntity> getAllCatalogs();
}
