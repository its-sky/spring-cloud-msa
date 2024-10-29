package org.smc.catalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.smc.catalogservice.entity.CatalogEntity;
import org.smc.catalogservice.service.CatalogService;
import org.smc.catalogservice.vo.ResponseCatalog;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {
	private final Environment env;
	private final CatalogService catalogService;

	@GetMapping("/health_check")
	public String healthCheck() {
		return String.format("It's Working in Catalog Service on PORT %s", env.getProperty("local.server.port"));
	}

	@GetMapping("/catalogs")
	public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
		Iterable<CatalogEntity> catalogs = catalogService.getAllCatalogs();

		ArrayList<ResponseCatalog> result = new ArrayList<>();
		catalogs.forEach(catalog -> result.add(new ModelMapper().map(catalog, ResponseCatalog.class)));

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
