package com.formulario.encuesta.models.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import com.formulario.encuesta.models.dto.UserDto;
import com.formulario.encuesta.models.entities.Option;
import com.formulario.encuesta.models.entities.User;
import com.formulario.encuesta.models.mapper.UserDtoMapper;
import com.formulario.encuesta.models.repositories.UserRepository;
import com.formulario.encuesta.models.service.impl.UserServiceImpl;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserDtoMapper mapper = Mappers.getMapper(UserDtoMapper.class);

    @InjectMocks
    private UserServiceImpl service ;


    @Before
    public void setup(){
        ReflectionTestUtils.setField(mapper, "service", service);
    }


    @Test
    void testFindByEmail() {
        var simulatedObject = Optional.of(User.builder().email("reymerretamozolopez@gmail.com").firstName("Reymer Nilton").idUser(Long.valueOf(1)).lastName("Retamozo Lopez").build());
        final UserDto espectedObject = UserDto.builder().id(Long.valueOf(1)).email("reymerretamozolopez@gmail.com").build();
        
        Mockito.when(userRepository.findByEmail("reymerretamozolopez@gmail.com")).thenReturn(simulatedObject);
        final UserDto currentObject = service.findByEmail("reymerretamozolopez@gmail.com");
        
        
        Assertions.assertEquals(espectedObject, currentObject);
        assertEquals(espectedObject.getId(), currentObject.getId());
        assertEquals(espectedObject.getEmail(), currentObject.getEmail());

        
        Mockito.verify(userRepository , Mockito.times(1)).findByEmail("reymerretamozolopez@gmail.com");
        
    }

    @Test
    void testUserDetailsService() {
        var simulatedObject = Optional.of(User.builder().email("reymerretamozolopez@gmail.com").firstName("Reymer Nilton").idUser(Long.valueOf(1)).lastName("Retamozo Lopez").build());
        final UserDto espectedObject = UserDto.builder().id(Long.valueOf(1)).email("reymerretamozolopez@gmail.com").build();
        
        Mockito.when(userRepository.findByEmail("reymerretamozolopez@gmail.com")).thenReturn(simulatedObject);
        
        UserDetails currentDetail = service.userDetailsService().loadUserByUsername("reymerretamozolopez@gmail.com");

        assertEquals(espectedObject.getEmail(), currentDetail.getUsername());
    }
}
