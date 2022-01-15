package com.mundolog.mundologapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mundolog.mundologapi.api.mapper.EntregaMapper;
import com.mundolog.mundologapi.api.model.request.Entrega.EntregaRequestModel;
import com.mundolog.mundologapi.api.model.response.Entrega.EntregaResponseModel;
import com.mundolog.mundologapi.domain.model.Entrega;
import com.mundolog.mundologapi.domain.services.EntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/entregas")
public class EntregaController {

	private EntregaService entregaService;
	private EntregaMapper entregaMapper;

	@GetMapping
	public List<EntregaResponseModel> index() {
		return entregaMapper.toCollection(entregaService.index());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EntregaResponseModel> get(@PathVariable Long id) {
		return ResponseEntity.ok(entregaMapper.toModel(entregaService.get(id)));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaResponseModel solicitar(@Valid @RequestBody EntregaRequestModel entregaRequestModel) {
		Entrega entrega = entregaMapper.toEntity(entregaRequestModel);
		return entregaMapper.toModel(entregaService.solicitarEntrega(entrega));
	}
	
	@PutMapping("/{entregaId}/finalizar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		entregaService.finalizar(entregaId);
	}
	
	@PutMapping("/{entregaId}/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long entregaId) {
		entregaService.cancelar(entregaId);
	}
}
