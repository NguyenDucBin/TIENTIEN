package com.example.Evara_Shop.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleServiceHelper {

    public static <T, DTO> List<DTO> mapToDTOList(List<T> entities, Function<T, DTO> converter) {
        return entities.stream().map(converter).collect(Collectors.toList());
    }

    public static <T, DTO> DTO saveAndReturnDTO(
            T entity,
            JpaRepository<T, Long> repository,
            Function<T, DTO> converter
    ) {
        T saved = repository.save(entity);
        return converter.apply(saved);
    }
}

