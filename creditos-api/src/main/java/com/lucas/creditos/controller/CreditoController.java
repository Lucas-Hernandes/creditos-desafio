package com.lucas.creditos.controller;

import com.lucas.creditos.kafka.KafkaProducerService;
import com.lucas.creditos.model.Credito;
import com.lucas.creditos.service.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    private final CreditoService creditoService;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public CreditoController(CreditoService creditoService, KafkaProducerService kafkaProducerService) {
        this.creditoService = creditoService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<Credito>> getPorNumeroNfse(@PathVariable String numeroNfse) {
        List<Credito> creditos = creditoService.buscarPorNumeroNfse(numeroNfse);

        kafkaProducerService.enviarMensagem("Consulta NFS-e realizada: " + numeroNfse);

        if (creditos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum crédito encontrado para a nota fiscal: " + numeroNfse);
        }
        return ResponseEntity.ok(creditos);
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<Credito> getPorNumeroCredito(@PathVariable String numeroCredito) {
        Credito credito = creditoService.buscarPorNumeroCredito(numeroCredito);

        kafkaProducerService.enviarMensagem("Consulta Crédito realizada: " + numeroCredito);

        if (credito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum crédito encontrado com número: " + numeroCredito);
        }
        return ResponseEntity.ok(credito);
    }
}
