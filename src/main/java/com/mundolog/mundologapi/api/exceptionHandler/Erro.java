package com.mundolog.mundologapi.api.exceptionHandler;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class Erro {

	private Integer status;
	private String mensagem;
	private Date data;
	private List<CamposErro> campos;

	public Erro(Integer status, String mensagem, Date data) {
		this.status = status;
		this.mensagem = mensagem;
		this.data = data;
	}

}
