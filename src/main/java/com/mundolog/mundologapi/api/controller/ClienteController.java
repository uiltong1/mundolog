package com.mundolog.mundologapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mundolog.mundologapi.api.mapper.ClienteModelMapper;
import com.mundolog.mundologapi.api.model.request.Cliente.ClienteRequestModel;
import com.mundolog.mundologapi.api.model.response.Cliente.ClienteResponseModel;
import com.mundolog.mundologapi.domain.model.Cliente;
import com.mundolog.mundologapi.domain.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Api(tags = "Cliente")
@RequestMapping("/api/v1/clientes")
public class ClienteController {

	private ClienteService clienteService;
	private ClienteModelMapper clienteModelMapper;

	@ApiOperation(value = "Listar")
	@GetMapping
	public List<ClienteResponseModel> index() {
		System.out.println("testando");
		return clienteModelMapper.toCollection(clienteService.index());
	}
	
	@ApiOperation(value = "Consultar")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponseModel> get(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.buscar(id);
		return ResponseEntity.ok(clienteModelMapper.toModel(cliente));
	}

	@ApiOperation(value = "Registrar")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteResponseModel store(@Valid @RequestBody ClienteRequestModel clienteRequestModel) {
		Cliente cliente = clienteModelMapper.toEntity(clienteRequestModel);
		return clienteModelMapper.toModel(clienteService.salvar(cliente));
	}

	@ApiOperation(value = "Atualizar")
	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponseModel> update(@PathVariable("id") Long id,
			@Valid @RequestBody ClienteRequestModel clienteRequestModel) {
		Cliente cliente = clienteModelMapper.toEntity(clienteRequestModel);
		return ResponseEntity.ok(clienteModelMapper.toModel(clienteService.atualizar(id, cliente)));
	}

	@ApiOperation(value = "Excluir")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
