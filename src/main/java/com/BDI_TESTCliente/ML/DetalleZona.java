package com.BDI_TESTCliente.ML;

public class DetalleZona {

    private int idDetalleZona;

    private Zona zonaInyeccion;

    private Zona zonaExtraccion;

    public int getIdDetalleZona() {
        return idDetalleZona;
    }

    public void setIdDetalleZona(int idDetalleZona) {
        this.idDetalleZona = idDetalleZona;
    }

    public Zona getZonaInyeccion() {
        return zonaInyeccion;
    }

    public void setZonaInyeccion(Zona zonaInyeccion) {
        this.zonaInyeccion = zonaInyeccion;
    }

    public Zona getZonaExtraccion() {
        return zonaExtraccion;
    }

    public void setZonaExtraccion(Zona zonaExtraccion) {
        this.zonaExtraccion = zonaExtraccion;
    }

}
