package com.logapi.service;

import com.logapi.exception.NegocioException;
import com.logapi.model.Cliente;
import com.logapi.model.Entrega;
import com.logapi.model.StatusEntrega;
import com.logapi.repository.ClienteRepository;
import com.logapi.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega){

       Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

       entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
