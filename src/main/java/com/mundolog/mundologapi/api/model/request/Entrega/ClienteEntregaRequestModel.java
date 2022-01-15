package com.mundolog.mundologapi.api.model.request.Entrega;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteEntregaRequestModel {

	@NotNull
	private Long id;
}
