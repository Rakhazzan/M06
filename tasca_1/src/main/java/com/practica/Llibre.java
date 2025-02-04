package com.practica;

import java.util.Date;
import java.util.List;

public class Llibre {

    private String id;
    private String isbn;
    private String titol;
    private String autor;
    private Integer anyPublicacio;
    private List<String> generes;
    private String descripcio;
    private List<String> paraulesClau;
    private Date dataAfegit;
    private String estat;
    private String user;
    private Date prestec;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnyPublicacio() {
        return anyPublicacio;
    }

    public void setAnyPublicacio(Integer anyPublicacio) {
        this.anyPublicacio = anyPublicacio;
    }

    public List<String> getGeneres() {
        return generes;
    }

    public void setGeneres(List<String> generes) {
        this.generes = generes;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public List<String> getParaulesClau() {
        return paraulesClau;
    }

    public void setParaulesClau(List<String> paraulesClau) {
        this.paraulesClau = paraulesClau;
    }

    public Date getDataAfegit() {
        return dataAfegit;
    }

    public void setDataAfegit(Date dataAfegit) {
        this.dataAfegit = dataAfegit;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getPrestec() {
        return prestec;
    }

    public void setPrestec(Date prestec) {
        this.prestec = prestec;
    }
}
