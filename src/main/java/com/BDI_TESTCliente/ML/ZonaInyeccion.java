package com.BDI_TESTCliente.ML;

import java.util.List;

public class ZonaInyeccion {

    private int idZonaInyeccion;

    private String nombreZona;

    private List<Contrato> contratos;

    public int getIdZonaInyeccion() {
        return idZonaInyeccion;
    }

    public void setIdZonaInyeccion(int idZonaInyeccion) {
        this.idZonaInyeccion = idZonaInyeccion;
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
