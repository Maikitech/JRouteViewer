package br.edu.ifrs.poo2.prova.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class PontoDeRota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;
    private double elevacao;

    @ManyToOne
    @JoinColumn(name = "rota_id")
    private Rota rota;

    public PontoDeRota() {
    }

    public PontoDeRota(double lat, double lon, double ele) {
        this.latitude = lat;
        this.longitude = lon;
        this.elevacao = ele;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public double getElevacao() { return elevacao; }
    public void setElevacao(double elevacao) { this.elevacao = elevacao; }
    public Rota getRota() { return rota; }
    public void setRota(Rota rota) { this.rota = rota; }
}