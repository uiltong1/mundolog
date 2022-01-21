package com.mundolog.mundologapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mundolog.mundologapi.api.mapper.OcorrenciaMapper;
import com.mundolog.mundologapi.api.model.request.Ocorrencia.OcorrenciaRequestModel;
import com.mundolog.mundologapi.api.model.response.Ocorrencia.OcorrenciaResponseModel;
import com.mundolog.mundologapi.domain.model.Entrega;
import com.mundolog.mundologapi.domain.model.Ocorrencia;
import com.mundolog.mundologapi.domain.services.EntregaService;
import com.mundolog.mundologapi.domain.services.OcorrenciaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Api(tags = "Ocorrências")
@RequestMapping("/api/v1/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private EntregaService entregaService;
	private OcorrenciaService ocorrenciaService;
	private OcorrenciaMapper ocorrenciaMapper;

	@ApiOperation(value = "Listar")
	@GetMapping
	public List<OcorrenciaResponseModel> index(@PathVariable Long entregaId) {
		Entrega entrega = entregaService.get(entregaId);
		return ocorrenciaMapper.toCollection(entrega.getOcorrencias());
	}

	@ApiOperation(value = "Registrar")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaResponseModel salvar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaRequestModel ocorrenciaRequestModel) {
		Ocorrencia ocorrencia = ocorrenciaMapper.toEntity(ocorrenciaRequestModel);
		return ocorrenciaMapper.toModel(ocorrenciaService.registrar(entregaId, ocorrencia));
	}
}
