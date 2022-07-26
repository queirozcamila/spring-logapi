package com.logapi.controller;

import com.logapi.model.Cliente;
import com.logapi.repository.ClienteRepository;
import com.logapi.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
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
import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
                                             @Valid @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = catalogoClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){

        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }

    //Metodo exclusivo para teste
    @PostMapping(value = "/retornar")
    public ResponseEntity<String> retornar(@RequestBody String s){

        if(s != null){
            return ResponseEntity.ok(s);
        }
        return ResponseEntity.badRequest().build();
    }
}
