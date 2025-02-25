package com.projetobancodedados.agregadordeinvestimentos.service;

import com.projetobancodedados.agregadordeinvestimentos.controller.dto.CreateUserDto;
import com.projetobancodedados.agregadordeinvestimentos.entity.User;
import com.projetobancodedados.agregadordeinvestimentos.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Nested
    class CreateUserTest{
        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateUser(){
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@gmail.com",
                    "password",
                    Instant.now(),
                    null

            );
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            var input = new CreateUserDto(
                    "username",
                    "email@email",
                    "password");
            var output = userService.createUser(input);
            assertNotNull(output);
            var userCaptured = userArgumentCaptor.getValue();
            assertEquals(input.username(),userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }
        @Test
        @DisplayName("shouldThorwExeptionErrorOccurs")
        void shouldThorwExeptionErrorOccurs(){
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@gmail.com",
                    "password",
                    Instant.now(),
                    null

            );
            doThrow(new RuntimeException()).when(userRepository).save(any());
            var input = new CreateUserDto(
                    "username",
                    "email@email",
                    "password");
            assertThrows(RuntimeException.class, () -> userService.createUser(input));
        }
    }
}