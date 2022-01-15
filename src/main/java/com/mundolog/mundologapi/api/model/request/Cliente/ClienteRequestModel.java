package com.mundolog.mundologapi.api.model.request.Cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestModel {

	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotBlank
	@Size(max = 255)
	@Email
	private String email;

	@NotBlank
	@Size(max = 20)
	private String telefone;

}
