package com.springsecurity.springsecurity.services;

import com.springsecurity.springsecurity.entities.*;
import com.springsecurity.springsecurity.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TpServiceTest {
    @InjectMocks
    private TpService tpService;

    @Mock
    private TpRepository tpRepository;

    @Mock
    private GroupeRepository groupeRepository;

    @Mock
    private TpGroupeRepository tpGroupeRepository;

    @Mock
    private EtudiantRepository etudiantRepository;


    private Tp tp;
    private Image image;
    private Qst question;
    private Choix choix;
    private Binome binome;
    private Groupe groupe;
    private Tp_Groupe tpGroupe;

    @BeforeEach
    void setUp() {

        tp = new Tp();
        tp.setId(1L);
        tp.setTitre("TP Title");
        tp.setDescription("TP Description");

        image = new Image();
        image.setId(1L);
        image.setSource("image_source");

        question = new Qst();
        question.setId(1L);
        question.setContenu("Question content");
        question.setNote(5.0f);

        choix = new Choix();
        choix.setId(1L);
        choix.setContenu("Choice content");
        choix.setStatus("true");

        binome = new Binome();
        binome.setId(1L);

        groupe = new Groupe();
        groupe.setId(1L);
        groupe.setCode("G1");
        groupe.setNiveau(1);

        tpGroupe = new Tp_Groupe();
        tpGroupe.setGroup(groupe);
        tpGroupe.setTp(tp);
        tpGroupe.setStatut("true");

    }

    @Test
    void activerTpForGroupe_Success() {
        when(tpRepository.findById(1L)).thenReturn(Optional.of(tp));
        when(groupeRepository.findByCodeAndNiveau("G1", 1)).thenReturn(groupe);
        when(tpGroupeRepository.save(any(Tp_Groupe.class))).thenReturn(tpGroupe);

        boolean result = tpService.ActiverTpForGroupe("G1", 1L, 1);
        assertTrue(result);
    }

    @Test
    void activerTpForGroupe_Failure() {
        when(tpRepository.findById(1L)).thenReturn(Optional.empty());
        when(groupeRepository.findByCodeAndNiveau("G1", 1)).thenReturn(null);

        boolean result = tpService.ActiverTpForGroupe("G1", 1L, 1);
        assertFalse(result);
    }

    @Test
    void desactiverTpForGroupe_Success() {
        when(tpGroupeRepository.findByCodeTp("G1", 1L)).thenReturn(tpGroupe);

        boolean result = tpService.DesactiverTpForGroupe("G1", 1L);
        assertTrue(result);
    }

    @Test
    void afficherTpActiver_Success() {

        when(tpGroupeRepository.findByCodeGroup(1L)).thenReturn(Arrays.asList(tpGroupe));

        List<Tp> result = tpService.AfficherTpActiver(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(tp.getId(), result.get(0).getId());
    }



    }