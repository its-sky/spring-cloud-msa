package org.smc.catalogservice.service;

import org.smc.catalogservice.entity.CatalogEntity;
import org.smc.catalogservice.repository.CatalogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
	private final CatalogRepository catalogRepository;

	@Override
	public Iterable<CatalogEntity> getAllCatalogs() {
		return catalogRepository.findAll();
	}
}
