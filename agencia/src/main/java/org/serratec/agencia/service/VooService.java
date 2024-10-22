package org.serratec.agencia.service;

import java.util.List;

import java.util.Optional;

import org.serratec.agencia.dto.VooDto;
import org.serratec.agencia.model.Voo;
import org.serratec.agencia.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VooService {

	@Autowired
	private VooRepository repositorio;
	
	public Optional<VooDto> buscarPorId(Long id) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(VooDto.toDto(repositorio.findById(id).get()));
	}
	
	public List<VooDto> buscarTodos() {
		return repositorio.findAll().stream().map(v -> VooDto.toDto(v)).toList();
	}

	public boolean apagarVoo(Long id) {
		if (!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public VooDto salvarVoo(VooDto voo) {
		return VooDto.toDto(repositorio.save(voo.toEntity()));
	}
	
	public Optional<VooDto> alterarVoo(Long id, VooDto voo) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Voo vooEntity = voo.toEntity();
		vooEntity.setId(id);
		return Optional.of(VooDto.toDto(repositorio.save(vooEntity)));
	}
}
