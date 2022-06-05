package br.com.itau.historicofatura.service;

import br.com.itau.historicofatura.model.Categoria;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private ObjectMapper objectMapper = new ObjectMapper();
    public List<Categoria> categorias = new ArrayList<>();

    public void carregarDados() throws IOException{
        try {
            URL url = new URL("https://desafio-it-server.herokuapp.com/categorias");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
                categorias = objectMapper.readValue(connection.getInputStream(), new TypeReference<List<Categoria>>(){});

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Categoria> listarcategorias() throws IOException {
     carregarDados();
     return this.categorias;
    }

    public Categoria obterCategoriaPorId(int idCategoria) throws IOException {
      carregarDados();
      for(Categoria categoria : categorias){
          if(categoria.getId() == idCategoria){
            return categoria;
          }
      }
          return null;
    }
}
