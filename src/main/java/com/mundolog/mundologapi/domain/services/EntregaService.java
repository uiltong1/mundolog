package com.mundolog.mundologapi.domain.services;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mundolog.mundologapi.domain.exception.NotFoundException;
import com.mundolog.mundologapi.domain.model.Cliente;
import com.mundolog.mundologapi.domain.model.Entrega;
import com.mundolog.mundologapi.domain.model.StatusEntrega;
import com.mundolog.mundologapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {

	private EntregaRepository entregaRepository;
	private ClienteService clienteService;

	public List<Entrega> index() {
		return entregaRepository.findAll();
	}

	public Entrega get(Long id) {
		return entregaRepository.findById(id).orElseThrow(() -> new NotFoundException("Recurso não encontrado."));
	}

	@Transactional
	public Entrega solicitarEntrega(Entrega entrega) {
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());

		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		return entregaRepository.save(entrega);
	}

	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = get(entregaId);
		entrega.finalizar();

		entregaRepository.save(entrega);
	}

	@Transactional
	public void cancelar(Long entregaId) {
		Entrega entrega = get(entregaId);
		entrega.cancelar();

		entregaRepository.save(entrega);
	}

}
