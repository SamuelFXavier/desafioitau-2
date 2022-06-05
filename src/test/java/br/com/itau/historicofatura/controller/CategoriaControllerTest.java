package br.com.itau.historicofatura.controller;

import br.com.itau.historicofatura.model.Categoria;
import br.com.itau.historicofatura.service.CategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private MockMvc mockMvc;

    Categoria categoria;
    Categoria categoria1;

    @BeforeEach
    void doBefore(){
        categoria = new Categoria(1,"Transporte");
        categoria1 = new Categoria(2,"Compras Online");

    }

    @Test
    @DisplayName("GET /categoria - Teste para listar todas as categorias")
    void testListarCategorias() throws Exception {
        doReturn(Lists.newArrayList(categoria,categoria1)).when(categoriaService).listarcategorias();
        mockMvc.perform(get("/categoria"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", hasSize(2)));
    }

    @Test
    @DisplayName("GET /categoria/{id} - Teste para listar categoria por ID")
    void testListarCategoriasPorId() throws Exception {
        doReturn(categoria).when(categoriaService).obterCategoriaPorId(1);
        mockMvc.perform(get("/categoria/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION,nullValue()))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.nome",is("Transporte")));
    }
}