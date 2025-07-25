package com.BDI_TESTCliente.ML;

import java.math.BigDecimal;

public class Transaccion {

    private int idOperacion;
    private Contrato contrato;

    private NodoRecepccion nodoRecepcion;

    private NodoEntrega nodoEntrega;

    private BigDecimal cantidadAsignadaEntrega;

    private BigDecimal cantidadAsignadaRecepcion;

    private BigDecimal cantidadNominadaEntrega;

    private BigDecimal cantidadNominadaRecepcion;

    private BigDecimal gasEnExceso;

    private BigDecimal cargoUso;

    private BigDecimal cargoGasEnExceso;

    private BigDecimal tarifaExcesoFirme;

    private BigDecimal tarifaUsoInterrumpible;

    private BigDecimal totalAFacturar;

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public NodoRecepccion getNodoRecepcion() {
        return nodoRecepcion;
    }

    public void setNodoRecepcion(NodoRecepccion nodoRecepcion) {
        this.nodoRecepcion = nodoRecepcion;
    }

    public NodoEntrega getNodoEntrega() {
        return nodoEntrega;
    }

    public void setNodoEntrega(NodoEntrega nodoEntrega) {
        this.nodoEntrega = nodoEntrega;
    }

    public BigDecimal getCantidadAsignadaEntrega() {
        return cantidadAsignadaEntrega;
    }

    public void setCantidadAsignadaEntrega(BigDecimal cantidadAsignadaEntrega) {
        this.cantidadAsignadaEntrega = cantidadAsignadaEntrega;
    }

    public BigDecimal getCantidadAsignadaRecepcion() {
        return cantidadAsignadaRecepcion;
    }

    public void setCantidadAsignadaRecepcion(BigDecimal cantidadAsignadaRecepcion) {
        this.cantidadAsignadaRecepcion = cantidadAsignadaRecepcion;
    }

    public BigDecimal getCantidadNominadaEntrega() {
        return cantidadNominadaEntrega;
    }

    public void setCantidadNominadaEntrega(BigDecimal cantidadNominadaEntrega) {
        this.cantidadNominadaEntrega = cantidadNominadaEntrega;
    }

    public BigDecimal getCantidadNominadaRecepcion() {
        return cantidadNominadaRecepcion;
    }

    public void setCantidadNominadaRecepcion(BigDecimal cantidadNominadaRecepcion) {
        this.cantidadNominadaRecepcion = cantidadNominadaRecepcion;
    }

    public BigDecimal getGasEnExceso() {
        return gasEnExceso;
    }

    public void setGasEnExceso(BigDecimal gasEnExceso) {
        this.gasEnExceso = gasEnExceso;
    }

    public BigDecimal getCargoUso() {
        return cargoUso;
    }

    public void setCargoUso(BigDecimal cargoUso) {
        this.cargoUso = cargoUso;
    }

    public BigDecimal getCargoGasEnExceso() {
        return cargoGasEnExceso;
    }

    public void setCargoGasEnExceso(BigDecimal cargoGasEnExceso) {
        this.cargoGasEnExceso = cargoGasEnExceso;
    }

    public BigDecimal getTarifaExcesoFirme() {
        return tarifaExcesoFirme;
    }

    public void setTarifaExcesoFirme(BigDecimal tarifaExcesoFirme) {
        this.tarifaExcesoFirme = tarifaExcesoFirme;
    }

    public BigDecimal getTarifaUsoInterrumpible() {
        return tarifaUsoInterrumpible;
    }

    public void setTarifaUsoInterrumpible(BigDecimal tarifaUsoInterrumpible) {
        this.tarifaUsoInterrumpible = tarifaUsoInterrumpible;
    }

    public BigDecimal getTotalAFacturar() {
        return totalAFacturar;
    }

    public void setTotalAFacturar(BigDecimal totalAFacturar) {
        this.totalAFacturar = totalAFacturar;
    }

}
