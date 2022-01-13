package com.mundolog.mundologapi.api.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CamposErro {

	private String nome;
	private String mensagem;
}
