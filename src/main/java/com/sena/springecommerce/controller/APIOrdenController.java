package com.sena.springecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.springecommerce.model.Orden;
import com.sena.springecommerce.service.DetalleOrdenServiceImplement;
import com.sena.springecommerce.service.IDetalleOrdenService;
import com.sena.springecommerce.service.IOrdenService;

@RestController
@RequestMapping(("/apiorden"))
public class APIOrdenController {

	@Autowired
	private IOrdenService ordenService;
	@Autowired
	private IDetalleOrdenService detalleOrdenService; 

	public APIOrdenController(DetalleOrdenServiceImplement detalleOrdenServiceImplement) {
		this.detalleOrdenService = detalleOrdenServiceImplement;
	}

	// Endpoint GET para obtener todos los productos
	@GetMapping("/list")
	public List<Orden> getAllorden() {
		return ordenService.findAll();
	}

	// Endpoint GET para obtener una orden por ID
	@GetMapping("/orden/{id}")
	public ResponseEntity<Orden> getOrdenById(@PathVariable Integer id) {
		Optional<Orden> orden = ordenService.get(id);
		return orden.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// ENdpoint POST crear un nueva orden
	@PostMapping("/create")
	public ResponseEntity<Orden> createOrden(@RequestBody Orden orden) {
		Orden savedOrden = ordenService.save(orden);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrden);
	}
}
