package org.serratec.agencia.repository;

import java.util.List;

import org.serratec.agencia.model.MetodoPagamento;
import org.serratec.agencia.model.Pedido;
import org.serratec.agencia.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	List<Pedido> findByStatusPedido(StatusPedido status);
	List<Pedido> findByMetodoPagamento(MetodoPagamento metodo);
}
