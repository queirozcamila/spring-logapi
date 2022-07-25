package com.logapi.Mapper;


import com.logapi.dto.EntregaDto;
import com.logapi.input.EntregaInput;
import com.logapi.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntregaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public EntregaDto toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDto.class);
    }

    public List<EntregaDto> toCollectionDto(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }
}

