package com.dani.Task42.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new tools.jackson.databind.ObjectMapper();

    @BeforeEach
    void setUp() {
        UserController.usersList.clear();
    }

    @Test
    void getUsers_returnsEmptyListInitially() throws Exception {
        // Simula GET /users
        // Espera un array buit
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void createUser_returnsUserWithId() throws Exception {
        // Simula POST /users amb JSON
        // Espera que torni el mateix usuari amb UUID no nul
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content("{\"name\":\"Dani\",\"email\":\"dani@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Dani"));

    }

    @Test
    void getUserById_returnsCorrectUser() throws Exception {
        String response = mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content("{\"name\":\"Dani\",\"email\":\"dani@example.com\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String id = com.jayway.jsonpath.JsonPath.read(response, "$.id");

        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dani"))
                .andExpect(jsonPath("$.email").value("dani@example.com"));
    }

    @Test
    void getUserById_returnsNotFoundIfMissing() throws Exception {
        // Simula GET /users/{id} amb un id aleatori
        // Espera 404
        mockMvc.perform(get("/users/{id}", "00000000-0000-0000-0000-000000000000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUsers_withNameParam_returnsFilteredUsers() throws Exception {
        // Afegeix dos usuaris amb POST
        // Fa GET /users?name=jo i comprova que només torni els que contenen "jo"
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content("{\"name\":\"Joan\",\"email\":\"joan@example.com\"}"));
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content("{\"name\":\"Maria\",\"email\":\"maria@example.com\"}"));

        mockMvc.perform(get("/users").param("name", "jo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Joan"));
    }


}
