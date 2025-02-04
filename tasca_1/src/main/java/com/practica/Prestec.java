package com.practica;
import java.util.Date;
public class Prestec {
    private String id;
    private String llibreId;
    private String usuariId;
    private Date dataInici;
    private Date dataFi;
    private String estat;
    private Date dataCreacio;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLlibreId() { return llibreId; }
    public void setLlibreId(String llibreId) { this.llibreId = llibreId; }
    public String getUsuariId() { return usuariId; }
    public void setUsuariId(String usuariId) { this.usuariId = usuariId; }
    public Date getDataInici() { return dataInici; }
    public void setDataInici(Date dataInici) { this.dataInici = dataInici; }
    public Date getDataFi() { return dataFi; }
    public void setDataFi(Date dataFi) { this.dataFi = dataFi; }
    public String getEstat() { return estat; }
    public void setEstat(String estat) { this.estat = estat; }
    public Date getDataCreacio() { return dataCreacio; }
    public void setDataCreacio(Date dataCreacio) { this.dataCreacio = dataCreacio; }
}