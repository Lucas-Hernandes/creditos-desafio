package com.lucas.creditos.service.impl;

import com.lucas.creditos.model.Credito;
import com.lucas.creditos.repository.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditoServiceImplTest {

    @Mock
    private CreditoRepository creditoRepository;

    @InjectMocks
    private CreditoServiceImpl creditoService;

    @Test
    void deveRetornarCreditosPorNumeroNfse() {
        Credito c1 = new Credito(1L, "123456", "7891011", LocalDate.now(),
                new BigDecimal("1500.75"), "ISSQN", true,
                new BigDecimal("5.0"), new BigDecimal("30000.00"),
                new BigDecimal("5000.00"), new BigDecimal("25000.00"));

        when(creditoRepository.findByNumeroNfse("7891011")).thenReturn(List.of(c1));

        List<Credito> result = creditoService.buscarPorNumeroNfse("7891011");

        assertEquals(1, result.size());
        assertEquals("123456", result.get(0).getNumeroCredito());
    }

    @Test
    void deveRetornarCreditoPorNumeroCredito() {
        Credito c1 = new Credito(1L, "123456", "7891011", LocalDate.now(),
                new BigDecimal("1500.75"), "ISSQN", true,
                new BigDecimal("5.0"), new BigDecimal("30000.00"),
                new BigDecimal("5000.00"), new BigDecimal("25000.00"));

        when(creditoRepository.findByNumeroCredito("123456")).thenReturn(Optional.of(c1));

        Credito result = creditoService.buscarPorNumeroCredito("123456");

        assertNotNull(result);
        assertEquals("123456", result.getNumeroCredito());
        assertEquals("7891011", result.getNumeroNfse());
    }

    @Test
    void deveLancarExcecaoSeCreditoNaoEncontrado() {
        when(creditoRepository.findByNumeroCredito("000000")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            creditoService.buscarPorNumeroCredito("000000");
        });

        String expectedMessage = "Crédito não encontrado";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
