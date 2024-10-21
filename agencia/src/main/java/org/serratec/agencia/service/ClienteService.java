package org.serratec.agencia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.agencia.dto.ClienteDto;
import org.serratec.agencia.model.Cliente;
import org.serratec.agencia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorio;
	
	public ClienteDto salvarCliente(ClienteDto cliente) {
		return ClienteDto.toDto(repositorio.save(cliente.toEntity()));
	}
	
	public Optional<ClienteDto> alterarCliente(Long id, ClienteDto cliente) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Cliente clienteEntity = cliente.toEntity();
		clienteEntity.setId(id);
		return Optional.of(ClienteDto.toDto(repositorio.save(clienteEntity)));
	}
	
	public boolean excluirCliente(Long id) {
		if (!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public List<ClienteDto> buscarTodos() {
		return repositorio.findAll().stream().map(v -> ClienteDto.toDto(v)).toList();
	}
	
	public Optional<ClienteDto> buscarPorId(Long id) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(ClienteDto.toDto(repositorio.findById(id).get()));
	}
	
	public List<ClienteDto> buscarPorNome(String nome) {
        List<Cliente> clientes = repositorio.findByNomeIgnoreCase(nome);
        return clientes.stream()
                       .map(ClienteDto::toDto)
                       .collect(Collectors.toList());
    }
	
	  public List<ClienteDto> buscarPorCpf(String cpf) {
	        List<Cliente> clientes = repositorio.findByCpf(cpf);
	        return clientes.stream()
	                       .map(ClienteDto::toDto)
	                       .collect(Collectors.toList());
	    }
	  
	  public List<ClienteDto> buscarPorEmail(String email) {
	        List<Cliente> clientes = repositorio.findByEmailIgnoreCase(email);
	        return clientes.stream()
	                       .map(ClienteDto::toDto)
	                       .collect(Collectors.toList());
	    }
}
