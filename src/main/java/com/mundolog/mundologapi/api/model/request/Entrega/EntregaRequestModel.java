package com.mundolog.mundologapi.api.model.request.Entrega;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaRequestModel {

	@NotNull
	@Valid
	private ClienteEntregaRequestModel cliente;

	@NotNull
	@Valid
	private DestinatarioRequestModel destinatario;

	@NotNull
	private BigDecimal taxa;

}
