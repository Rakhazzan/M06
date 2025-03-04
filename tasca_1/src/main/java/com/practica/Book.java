package com.practica;


import java.util.List;

import org.json.JSONObject;

public class Book {
    private String isbn;
    private String titol;
    private String autor;
    private int anyPublicacio;
    private List<Object> generes;
    private String descripcio;
    private List<Object> paraulesClau;
    private String estat;
    private String dataAfegit;

    public Book(String isbn, String titol, String autor, int anyPublicacio, List<Object> generes,
                String descripcio, List<Object> paraulesClau, String estat, String dataAfegit) {
        this.isbn = isbn;
        this.titol = titol;
        this.autor = autor;
        this.anyPublicacio = anyPublicacio;
        this.generes = generes;
        this.descripcio = descripcio;
        this.paraulesClau = paraulesClau;
        this.estat = estat;
        this.dataAfegit = dataAfegit;
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitol() { return titol; }
    public void setTitol(String titol) { this.titol = titol; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAnyPublicacio() { return anyPublicacio; }
    public void setAnyPublicacio(int anyPublicacio) { this.anyPublicacio = anyPublicacio; }

    public List<Object> getGeneres() { return generes; }
    public void setGeneres(List<Object> generes) { this.generes = generes; }

    public String getDescripcio() { return descripcio; }
    public void setDescripcio(String descripcio) { this.descripcio = descripcio; }

    public List<Object> getParaulesClau() { return paraulesClau; }
    public void setParaulesClau(List<Object> paraulesClau) { this.paraulesClau = paraulesClau; }

    public String getEstat() { return estat; }
    public void setEstat(String estat) { this.estat = estat; }

    public String getDataAfegit() { return dataAfegit; }
    public void setDataAfegit(String dataAfegit) { this.dataAfegit = dataAfegit; }

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("isbn", isbn);
        json.put("titol", titol);
        json.put("autor", autor);
        json.put("anyPublicacio", anyPublicacio);
        json.put("generes", generes);
        json.put("descripcio", descripcio);
        json.put("paraulesClau", paraulesClau);
        json.put("estat", estat);
        json.put("dataAfegit", dataAfegit);
        return json.toString();
    }
}