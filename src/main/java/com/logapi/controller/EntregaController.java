package com.logapi.controller;

import com.logapi.Mapper.EntregaMapper;
import com.logapi.dto.EntregaDto;
import com.logapi.input.EntregaInput;
import com.logapi.model.Entrega;
import com.logapi.repository.EntregaRepository;
import com.logapi.service.SolicitacaoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

    @Autowired
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaMapper entregalMapper;

    @GetMapping
    public List<EntregaDto> listar(){
        return entregalMapper.toCollectionDto(entregaRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDto solicitar(@Valid @RequestBody EntregaInput entregaInput){
       Entrega novaEntrega = entregalMapper.toEntity(entregaInput);
       return entregalMapper.toModel(solicitacaoEntregaService.solicitar(novaEntrega));
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDto> buscar (@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregalMapper.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

}
