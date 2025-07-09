package com.BDI_TESTCliente.ML;

import java.util.Date;


public class Contrato {

    private int idContrato;

    private String codigoContrato;

    private Date fecha;

    private ZonaExtraccion zonaExtraccion;

    private ZonaInyeccion zonaInyeccion;

    private Usuario usuario;

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(String codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ZonaExtraccion getZonaExtraccion() {
        return zonaExtraccion;
    }

    public void setZonaExtraccion(ZonaExtraccion zonaExtraccion) {
        this.zonaExtraccion = zonaExtraccion;
    }

    public ZonaInyeccion getZonaInyeccion() {
        return zonaInyeccion;
    }

    public void setZonaInyeccion(ZonaInyeccion zonaInyeccion) {
        this.zonaInyeccion = zonaInyeccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
