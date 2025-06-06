package com.lucas.creditos.service.impl;

import com.lucas.creditos.model.Credito;
import com.lucas.creditos.repository.CreditoRepository;
import com.lucas.creditos.service.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository creditoRepository;

    @Autowired
    public CreditoServiceImpl(CreditoRepository creditoRepository) {
        this.creditoRepository = creditoRepository;
    }

    @Override
    public List<Credito> buscarPorNumeroNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse);
    }

    @Override
    public Credito buscarPorNumeroCredito(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Crédito não encontrado"));
    }
}
