package com.mundolog.mundologapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mundolog.mundologapi.api.model.request.Cliente.ClienteRequestModel;
import com.mundolog.mundologapi.api.model.response.Cliente.ClienteResponseModel;
import com.mundolog.mundologapi.domain.model.Cliente;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteModelMapper {

	private ModelMapper modelMapper;

	public ClienteResponseModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteResponseModel.class);
	}

	public List<ClienteResponseModel> toCollection(List<Cliente> clientes) {
		return clientes.stream().map(this::toModel).collect(Collectors.toList());
	}

	public Cliente toEntity(ClienteRequestModel clienteRequestModel) {
		return modelMapper.map(clienteRequestModel, Cliente.class);
	}
}
