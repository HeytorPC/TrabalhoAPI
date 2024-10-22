package org.serratec.agencia.service;

import java.util.List;

import java.util.Optional;

import org.serratec.agencia.dto.ViagemDto;
import org.serratec.agencia.model.Viagem;
import org.serratec.agencia.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {
	
	
	@Autowired
	private ViagemRepository repositorio;
	
	public Optional<ViagemDto> buscarPorId(Long id) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(ViagemDto.toDto(repositorio.findById(id).get()));
	}
	
	public List<ViagemDto> buscarTodos() {
		return repositorio.findAll().stream().map(v -> ViagemDto.toDto(v)).toList();
	}

	public boolean apagarViagem(Long id) {
		if (!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public ViagemDto salvarViagem(ViagemDto viagem) {
		return ViagemDto.toDto(repositorio.save(viagem.toEntity()));
	}
	
	public Optional<ViagemDto> alterarViagem(Long id, ViagemDto viagem) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Viagem viagemEntity = viagem.toEntity();
		viagemEntity.setId(id);
		return Optional.of(ViagemDto.toDto(repositorio.save(viagemEntity)));
	}
}
