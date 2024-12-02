package com.springsecurity.springsecurity.services;

import com.springsecurity.springsecurity.EXception.DataInvalid;
import com.springsecurity.springsecurity.entities.Binome;
import com.springsecurity.springsecurity.entities.Groupe;
import com.springsecurity.springsecurity.entities.Role;
import com.springsecurity.springsecurity.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private UserService userService;
    private EtudiantService etudiantService;
    @BeforeEach
    void setUp() {
        etudiantService = new EtudiantService(etudiantRepository, userService);
    }

    @Test
    void canGetAllStudnets() {
        //when
        etudiantService.AfficherListEtudiant();
        //then
        verify(etudiantRepository).findAll();
    }

    @Test
    void canRegisterEtudiant() throws DataInvalid {
        // Given
        Etudiant etudiant = new Etudiant();
        User user = new User();
        user.setEmail("sofiane@gmail.com");
        etudiant.setUser(user);

        when(userService.isEmailTaken(user.getEmail())).thenReturn(false);
        when(userService.registerUser(user)).thenReturn(user);

        // When
        etudiantService.RegisterEtudiant(etudiant, "123");

        // Then
        ArgumentCaptor<Etudiant> etudiantArgumentCaptor = ArgumentCaptor.forClass(Etudiant.class);
        verify(etudiantRepository).save(etudiantArgumentCaptor.capture());

        Etudiant capturedEtudiant = etudiantArgumentCaptor.getValue();
        assertThat(capturedEtudiant).isEqualTo(etudiant);
    }
}
