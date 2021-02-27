package com.thunv.converter;


public interface IConverter<E, T> {
    E toEntity(T dto);

    T toDTO(E entity);

    E toEntity(E entity, T dto);
}
