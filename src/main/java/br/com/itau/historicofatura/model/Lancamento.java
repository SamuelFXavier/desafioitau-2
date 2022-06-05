package br.com.itau.historicofatura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Lancamento {

    private Integer id;
    private Double valor;
    private String origem;
    private int categoria;
    @JsonProperty("mes_lancamento")
    private int mesLancamento;

    public Lancamento(Integer id, Double valor, String origem, int categoria, int mesLancamento) {
        this.id = id;
        this.valor = valor;
        this.origem = origem;
        this.categoria = categoria;
        this.mesLancamento = mesLancamento;
    }

    public Integer getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public String getOrigem() {
        return origem;
    }

    public int getCategoria() {
        return categoria;
    }

    public int getMesLancamento() {
        return mesLancamento;
    }

    @Override
    public String toString() {
        return "Lancamento{" +
                "id=" + id +
                ", valor=" + valor +
                ", origem='" + origem + '\'' +
                ", categoria=" + categoria +
                ", mesLancamento=" + mesLancamento +
                '}';
    }
}
