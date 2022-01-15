package com.mundolog.mundologapi.api.model.response.Ocorrencia;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaResponseModel {

	private Long id;
	private String descricao;
	private OffsetDateTime dataRegistro;
}
