package com.practica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuari {

    private String id;
    private String nom;
    private String email;
    private Date dataRegistre;
    private List<String> preferencies;
    private List<HistorialPrestec> historialPrestecs;
    public Usuari() {
        this.preferencies = new ArrayList<>();
        this.historialPrestecs = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataRegistre() {
        return dataRegistre;
    }

    public void setDataRegistre(Date dataRegistre) {
        this.dataRegistre = dataRegistre;
    }

    public List<String> getPreferencies() {
        return preferencies;
    }

    public void setPreferencies(List<String> preferencies) {
        this.preferencies = preferencies;
    }

    public List<HistorialPrestec> getHistorialPrestecs() {
        return historialPrestecs;
    }

    public void setHistorialPrestecs(List<HistorialPrestec> historialPrestecs) {
        this.historialPrestecs = historialPrestecs;
    }

    public static class HistorialPrestec {

        private String llibreId;
        private Date dataInici;
        private Date dataFi;
        private String estat;

        // Getters and Setters
        public String getLlibreId() {
            return llibreId;
        }

        public void setLlibreId(String llibreId) {
            this.llibreId = llibreId;
        }

        public Date getDataInici() {
            return dataInici;
        }

        public void setDataInici(Date dataInici) {
            this.dataInici = dataInici;
        }

        public Date getDataFi() {
            return dataFi;
        }

        public void setDataFi(Date dataFi) {
            this.dataFi = dataFi;
        }

        public String getEstat() {
            return estat;
        }

        public void setEstat(String estat) {
            this.estat = estat;
        }
    }
}
