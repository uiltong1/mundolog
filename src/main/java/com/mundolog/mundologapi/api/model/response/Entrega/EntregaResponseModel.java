package com.mundolog.mundologapi.api.model.response.Entrega;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.mundolog.mundologapi.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaResponseModel {

	private Long id;
	private ClienteResumoResponseModel cliente;
	private DestinatarioResponseModel destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
}
