package com.springsecurity.springsecurity.controllers;

import com.springsecurity.springsecurity.entities.Groupe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TpService tpService;

    private List<Groupe> groupeList;

    @BeforeEach
    public void setUp() {
        Groupe groupe1 = new Groupe();
        groupe1.setCode("t");
        groupe1.setNiveau(1);
        Groupe groupe2 = new Groupe();
        groupe2.setCode("y");
        groupe2.setNiveau(1);
        groupeList = Arrays.asList(groupe1, groupe2);
    }

    @Test
    @WithMockUser(authorities = {"PROF"})
    void getGrpsByNiveau() throws Exception {
        int niveau = 1;

        given(tpService.getGrps(niveau)).willReturn(groupeList);

        mockMvc.perform(get("/Tp/getGrps/{niveau}", niveau)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].code", is(groupeList.get(0).getCode())))
                .andExpect(jsonPath("$[1].code", is(groupeList.get(1).getCode())));
    }

    @Test
    @WithMockUser(authorities = {"PROF"})
    void etudiantsNonTp() throws Exception {
        Etudiant etudiant=new Etudiant();
        etudiant.setNom("rihab");
        List<Etudiant> etudiants=Arrays.asList(etudiant);
        given(tpService.EtudiantsNonTp(1L,"t",1)).willReturn(etudiants);

        mockMvc.perform(get("/Tp/EtudiantsNonTp/{id_tp}/{code_grp}/{niveau}", 1L,"t",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

}
