package com.mundolog.mundologapi.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mundolog.mundologapi.domain.exception.GenericException;
import com.mundolog.mundologapi.domain.model.Cliente;
import com.mundolog.mundologapi.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar(Cliente cliente) {

		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

		if (emailEmUso) {
			throw new GenericException("O e-mail já está em uso.");
		}

		return clienteRepository.save(cliente);
	}

	@Transactional
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}
}
