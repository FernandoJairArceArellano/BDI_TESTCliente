package com.BDI_TESTCliente.ML;

import java.util.List;

public class ZonaExtraccion {

    private int idZonaExtraccion;

    private String nombreZona;

    private List<Contrato> contratos;

    public int getIdZonaExtraccion() {
        return idZonaExtraccion;
    }

    public void setIdZonaExtraccion(int idZonaExtraccion) {
        this.idZonaExtraccion = idZonaExtraccion;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

}
