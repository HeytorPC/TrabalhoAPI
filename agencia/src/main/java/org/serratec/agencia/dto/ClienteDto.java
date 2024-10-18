package org.serratec.agencia.dto;
import org.serratec.agencia.model.Cliente;

public record ClienteDto (
    Long id,
    String nome,
    String cpf,
    Long idade,
    String telefone,
    String email
) {

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setIdade(this.idade);
        cliente.setTelefone(this.telefone);
        cliente.setEmail(this.email);
        return cliente;
    }

    public static ClienteDto toDto(Cliente cliente) {
        return new ClienteDto( cliente.getId(), cliente.getNome(), cliente.getCpf(), 
        		cliente.getIdade(), cliente.getTelefone(), cliente.getEmail());
    }
}
