package org.serratec.agencia.repository;

import java.util.List;

import org.serratec.agencia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	  List<Cliente> findByNomeIgnoreCase(String nome);
	  List<Cliente> findByCpf(String cpf);
	  List<Cliente> findByEmailIgnoreCase(String email);

}
