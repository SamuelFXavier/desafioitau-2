package br.com.itau.historicofatura.service;

import br.com.itau.historicofatura.model.Lancamento;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService {

    private ObjectMapper objectMapper = new ObjectMapper();
    public List<Lancamento> lancamentos = new ArrayList<>();

    public void carregarDados()throws IOException{
        try {
            URL url = new URL("https://desafio-it-server.herokuapp.com/lancamentos");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            lancamentos = objectMapper.readValue(connection.getInputStream(), new TypeReference<List<Lancamento>>(){});

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Lancamento> listarLancamentos() throws IOException {
        carregarDados();
        return this.lancamentos;
    }

    public Lancamento obterLancamentoPorId(int idlancamento) throws IOException {
        carregarDados();
        for(Lancamento lancamento : lancamentos){
            if(lancamento.getId() == idlancamento){
                return lancamento;
            }
        }
        return null;
    }

    public List<Lancamento> obterLancamentosPorCategoria(int idCategoria) throws IOException {
        carregarDados();

            return lancamentos.stream().filter(lancamento -> lancamento.getCategoria() == idCategoria).collect(Collectors.toList());
        }
}
