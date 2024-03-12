package com.formulario.encuesta.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.formulario.encuesta.models.dto.UserDto;
import com.formulario.encuesta.models.entities.User;

@Mapper(componentModel = "spring") 
public interface UserDtoMapper {
    
    @Mapping(source = "idUser" , target =  "id")
    UserDto toDomain(User user);
}
