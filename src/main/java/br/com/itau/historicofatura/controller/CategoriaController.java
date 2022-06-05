package br.com.itau.historicofatura.controller;
import br.com.itau.historicofatura.model.Categoria;
import br.com.itau.historicofatura.service.CategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() throws IOException {
        return ResponseEntity.ok().body(categoriaService.listarcategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> listarCategorias(@PathVariable int id) throws IOException {
        return ResponseEntity.ok().body(categoriaService.obterCategoriaPorId(id));
    }
}
