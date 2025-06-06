package com.lucas.creditos.service;

import com.lucas.creditos.model.Credito;

import java.util.List;

public interface CreditoService {
    List<Credito> buscarPorNumeroNfse(String numeroNfse);
    Credito buscarPorNumeroCredito(String numeroCredito);
}
