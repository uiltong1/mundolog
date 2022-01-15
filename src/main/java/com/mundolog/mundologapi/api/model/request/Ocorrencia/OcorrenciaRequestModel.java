package com.mundolog.mundologapi.api.model.request.Ocorrencia;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaRequestModel {

	@NotBlank
	private String descricao;
}
