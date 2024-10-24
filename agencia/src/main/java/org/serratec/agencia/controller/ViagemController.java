package org.serratec.agencia.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.model.Viagem;
import org.serratec.agencia.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @GetMapping
    @Operation(summary = "Buscar todas as Viagens", 
    description = "Retorna uma lista de todas as viagens cadastradas.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Viagens retornadas com sucesso"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<Viagem> obterTodas() {
        return viagemService.buscarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Viagem por ID", 
    description = "Retorna os detalhes de uma viagem específica com base no ID fornecido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Viagem encontrada e retornada com sucesso"),
    		@ApiResponse(responseCode = "404", description = "Viagem não encontrada"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Viagem> obterPorId(@PathVariable Long id) {
        Optional<Viagem> viagem = viagemService.buscarPorId(id);
        
        if (!viagem.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(viagem.get());
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova Viagem", 
    description = "Cria uma nova viagem com base nos dados fornecidos e retorna a viagem criada.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "Viagem criada com sucesso"),
    		@ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Viagem cadastrarViagem(@RequestBody @Valid Viagem viagem) {
        return viagemService.salvarViagem(viagem);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma Viagem", 
    description = "Exclui uma viagem existente com base no ID fornecido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Viagem excluída com sucesso"),
    		@ApiResponse(responseCode = "404", description = "Viagem não encontrada"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> excluirViagem(@PathVariable Long id) {
        if (!viagemService.apagarViagem(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar uma Viagem", 
    description = "Altera os dados de uma viagem existente com base no ID fornecido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Viagem alterada com sucesso"),
    		@ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
    		@ApiResponse(responseCode = "404", description = "Viagem não encontrada"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Viagem> alterarViagem(@PathVariable Long id, 
                                                 @RequestBody @Valid Viagem viagem) {
        Optional<Viagem> viagemAlterada = viagemService.modificarViagem(id, viagem);
        
        if (!viagemAlterada.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(viagemAlterada.get());
    }
}
