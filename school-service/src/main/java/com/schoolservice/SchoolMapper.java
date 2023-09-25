package com.schoolservice;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchoolMapper {

    SchoolMapper INSTANCE = Mappers.getMapper(SchoolMapper.class);

    SchoolDto schoolToDto(School school);

    School dtoToSchool(SchoolDto schoolDto);
}
