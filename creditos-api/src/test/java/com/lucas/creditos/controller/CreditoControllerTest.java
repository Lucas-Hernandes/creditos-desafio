package com.lucas.creditos.controller;

import com.lucas.creditos.model.Credito;
import com.lucas.creditos.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditoController.class)
public class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService creditoService;

    private Credito credito;

    @BeforeEach
    void setup() {
        credito = new Credito(
            1L,
            "123456",
            "7891011",
            LocalDate.of(2024, 2, 25),
            new BigDecimal("1500.75"),
            "ISSQN",
            true,
            new BigDecimal("5.0"),
            new BigDecimal("30000.00"),
            new BigDecimal("5000.00"),
            new BigDecimal("25000.00")
        );
    }

    @Test
    void deveRetornarListaDeCreditosPorNumeroNfse() throws Exception {
        when(creditoService.buscarPorNumeroNfse("7891011"))
                .thenReturn(List.of(credito));

        mockMvc.perform(get("/api/creditos/7891011")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCredito").value("123456"))
                .andExpect(jsonPath("$[0].tipoCredito").value("ISSQN"));
    }

    @Test
    void deveRetornarCreditoPorNumeroCredito() throws Exception {
        when(creditoService.buscarPorNumeroCredito("123456"))
                .thenReturn(credito);

        mockMvc.perform(get("/api/creditos/credito/123456")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroNfse").value("7891011"))
                .andExpect(jsonPath("$.valorIssqn").value(1500.75));
    }
}
