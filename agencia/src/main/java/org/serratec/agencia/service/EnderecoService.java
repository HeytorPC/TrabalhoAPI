package org.serratec.agencia.service;

import java.util.List;

import java.util.Optional;

import org.serratec.agencia.dto.EnderecoDto;
import org.serratec.agencia.model.Endereco;
import org.serratec.agencia.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
 
	@Autowired
	private EnderecoRepository repositorio;
	
	public Optional<EnderecoDto> buscarPorId(Long id) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(EnderecoDto.toDto(repositorio.findById(id).get()));
	}
	
	public List<EnderecoDto> buscarTodos() {
		return repositorio.findAll().stream().map(v -> EnderecoDto.toDto(v)).toList();
	}

	public boolean apagarEndereco(Long id) {
		if (!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public EnderecoDto salvarEndereco(EnderecoDto endereco) {
		return EnderecoDto.toDto(repositorio.save(endereco.toEntity()));
	}
	
	public Optional<EnderecoDto> alterarEndereco(Long id, EnderecoDto endereco) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Endereco enderecoEntity = endereco.toEntity();
		enderecoEntity.setId(id);
		return Optional.of(EnderecoDto.toDto(repositorio.save(enderecoEntity)));
	}
}
