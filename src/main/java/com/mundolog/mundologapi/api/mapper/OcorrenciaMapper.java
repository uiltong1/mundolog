package com.mundolog.mundologapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mundolog.mundologapi.api.model.request.Ocorrencia.OcorrenciaRequestModel;
import com.mundolog.mundologapi.api.model.response.Ocorrencia.OcorrenciaResponseModel;
import com.mundolog.mundologapi.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

	private ModelMapper modelMapper;

	public OcorrenciaResponseModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaResponseModel.class);
	}

	public List<OcorrenciaResponseModel> toCollection(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
	}

	public Ocorrencia toEntity(OcorrenciaRequestModel ocorrenciaRequestModel) {
		return modelMapper.map(ocorrenciaRequestModel, Ocorrencia.class);
	}
}
