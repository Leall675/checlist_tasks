package com.checklist.tasks.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    private final ModelMapper modelMapper;

    public MapperService (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
