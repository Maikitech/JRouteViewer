package br.edu.ifrs.poo2.prova.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    @Column(length = 1000)
    private String descricao;

    @OneToMany(mappedBy = "rota", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PontoDeRota> pontos = new ArrayList<>();

    public void adicionarPonto(PontoDeRota ponto) {
        ponto.setRota(this);
        this.pontos.add(ponto);
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public List<PontoDeRota> getPontos() { return pontos; }
    public void setPontos(List<PontoDeRota> pontos) { this.pontos = pontos; }

    @Override
    public String toString() {
        return nome;
    }
}