package com.mundolog.mundologapi.domain.services;

import java.util.List;

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

	public List<Cliente> index() {
		return clienteRepository.findAll();
	}

	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new GenericException("Cliente não encontrado"));
	}

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
		Cliente cliente = buscar(id);
		clienteRepository.delete(cliente);
	}

	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteExist = buscar(id);
		cliente.setId(clienteExist.getId());
		return clienteRepository.save(cliente);
	}
}
