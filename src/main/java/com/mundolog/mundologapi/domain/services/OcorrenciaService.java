package com.mundolog.mundologapi.domain.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mundolog.mundologapi.domain.exception.NotFoundException;
import com.mundolog.mundologapi.domain.model.Entrega;
import com.mundolog.mundologapi.domain.model.Ocorrencia;
import com.mundolog.mundologapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OcorrenciaService {

	private EntregaRepository entregaRepository;

	@Transactional
	public Ocorrencia registrar(Long id, Ocorrencia ocorrencia) {
		Entrega entrega = entregaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Entrega não econtrada."));

		return entrega.adicionarOcorrencia(ocorrencia.getDescricao());
	}
}
