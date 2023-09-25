package com.classservice;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassMapper {

    ClassMapper INSTANCE = Mappers.getMapper(ClassMapper.class);

    ClassDto classToDto(Class aClass);

    Class dtoToClass(ClassDto classDto);
}
