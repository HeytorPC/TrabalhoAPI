package org.serratec.agencia.service;

import java.util.List;

import java.util.Optional;

import org.serratec.agencia.dto.PedidoDto;
import org.serratec.agencia.model.MetodoPagamento;
import org.serratec.agencia.model.Pedido;
import org.serratec.agencia.model.StatusPedido;
import org.serratec.agencia.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;
	
	@Autowired
	private EmailService emailService;
	
	public Optional<PedidoDto> buscarPorId(Long id) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(PedidoDto.toDto(repositorio.findById(id).get()));
	}
	
	public List<PedidoDto> buscarPorStatus(StatusPedido status) {
	    return repositorio.findByStatusPedido(status)
	                      .stream()
	                      .map(PedidoDto::toDto)
	                      .toList();
	}
	
	public List<PedidoDto> buscarTodos() {
		return repositorio.findAll().stream().map(v -> PedidoDto.toDto(v)).toList();
	}
	
	public boolean apagarVeiculo(Long id) {
		if (!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public PedidoDto salvarPedido(PedidoDto dto) {
		Pedido pedidoEntity = repositorio.save(dto.toEntity());
		emailService.enviarEmail("mateusazevedofaria@gmail.com", "Novo Pedido", pedidoEntity.toString());
		return PedidoDto.toDto(pedidoEntity);
	}
	
	public Optional<PedidoDto> alterarPedido(Long id, PedidoDto pedido) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Pedido pedidoEntity = pedido.toEntity();
		pedidoEntity.setId(id);
		return Optional.of(PedidoDto.toDto(repositorio.save(pedidoEntity)));
	}
	
	public List<PedidoDto> buscarPorStatus(MetodoPagamento metodo) {
	    return repositorio.findByMetodoPagamento(metodo)
	                      .stream()
	                      .map(PedidoDto::toDto)
	                      .toList();
	}

}
