package br.com.itau.historicofatura.controller;

import br.com.itau.historicofatura.model.Categoria;
import br.com.itau.historicofatura.model.Lancamento;
import br.com.itau.historicofatura.service.CategoriaService;
import br.com.itau.historicofatura.service.LancamentoService;
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

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class LancamentoControllerTest {


    @MockBean
    private LancamentoService lancamentoService;

    @Autowired
    private MockMvc mockMvc;

    Lancamento lancamento;
    Lancamento lancamento1;
    Lancamento lancamento2;

    @BeforeEach
    void doBefore(){
        lancamento = new Lancamento(1, 13.3, "Uber", 1, 1);
        lancamento1 = new Lancamento(2, 130.5, "Ps Store", 2, 2);
        lancamento2 = new Lancamento(6, 12.5, "Uber", 1, 3);

    }

    @Test
    @DisplayName("GET /lancamento - Teste para listar todos os lancamentos")
    void listarLancamentos() throws Exception {
        doReturn(Lists.newArrayList(lancamento,lancamento1)).when(lancamentoService).listarLancamentos();
        mockMvc.perform(get("/lancamento"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", hasSize(2)));
    }

    @Test
    @DisplayName("GET /lancamento/{id} - Teste para listar lancamentos por ID")
    void listaLancamentosPorId() throws Exception {
        doReturn(lancamento).when(lancamentoService).obterLancamentoPorId(1);
        mockMvc.perform(get("/lancamento/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION,nullValue()))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.valor",is(13.3)));
    }

    @Test
    @DisplayName("GET /lancamento/categoria/{id} - Teste para listar lancamentos por Categoria")
    void listaLancamentosPorCategoria() throws Exception {
        doReturn(Lists.newArrayList(lancamento,lancamento2)).when(lancamentoService).obterLancamentosPorCategoria(1);
        mockMvc.perform(get("/categoria/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", hasSize(2)));
    }
}