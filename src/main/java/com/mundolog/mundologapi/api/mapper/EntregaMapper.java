package com.mundolog.mundologapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mundolog.mundologapi.api.model.request.Entrega.EntregaRequestModel;
import com.mundolog.mundologapi.api.model.response.Entrega.EntregaResponseModel;
import com.mundolog.mundologapi.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaMapper {

	private ModelMapper modelMapper;

	public EntregaResponseModel toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaResponseModel.class);
	}

	public List<EntregaResponseModel> toCollection(List<Entrega> entregas) {
		return entregas.stream().map(this::toModel).collect(Collectors.toList());
	}

	public Entrega toEntity(EntregaRequestModel entregaRequestModel) {
		return modelMapper.map(entregaRequestModel, Entrega.class);
	}
}
