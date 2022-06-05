package br.com.itau.historicofatura.controller;

import br.com.itau.historicofatura.model.Lancamento;
import br.com.itau.historicofatura.service.LancamentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {

    @Autowired
    LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<List<Lancamento>> listarLancamentos() throws IOException {
        return ResponseEntity.ok().body(lancamentoService.listarLancamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> listarLancamentosPorId(@PathVariable int id) throws IOException {
        return ResponseEntity.ok().body(lancamentoService.obterLancamentoPorId(id));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Lancamento>> listarLancamentosPorCategoria(@PathVariable int id) throws IOException {
        return ResponseEntity.ok().body(lancamentoService.obterLancamentosPorCategoria(id));
    }

}
