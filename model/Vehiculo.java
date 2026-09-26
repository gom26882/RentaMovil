package model;

import constants.TipoPlaca;
import constants.TipoVehiculo;

public abstract class Vehiculo {

    private final TipoVehiculo tipo;
    private final TipoPlaca tipoPlaca;
    private final String placa;
    private final String marca;
    private final String modelo;
    private final double tarifaDiaria;

    private boolean disponible;

    public Vehiculo(
            TipoVehiculo tipo,
            TipoPlaca tipoPlaca,
            String placa,
            String marca,
            String modelo,
            double tarifaDiaria) {

        if (tipo == null) {
            throw new IllegalArgumentException( "El tipo de vehículo es obligatorio");
        }

        if (tipoPlaca == null) {
            throw new IllegalArgumentException("El tipo de placa es obligatorio");
        }

        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("La placa no puede estar vacía");
        }

        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("La marca no puede estar vacía");
        }

        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío");
        }

        if (!Double.isFinite(tarifaDiaria) || tarifaDiaria <= 0) {
            throw new IllegalArgumentException("La tarifa diaria debe ser mayor que cero");
        }

        this.tipo = tipo;
        this.tipoPlaca = tipoPlaca;
        this.placa = placa.trim().toUpperCase();
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public TipoPlaca getTipoPlaca() {
        return tipoPlaca;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    void marcarAlquilado() {
        if (!disponible) {
            throw new IllegalStateException( "El vehículo ya se encuentra alquilado");
        }
        disponible = false;
    }

    void marcarDisponible() {
        if (disponible) {
            throw new IllegalStateException("El vehículo ya se encuentra disponible");
        }

        disponible = true;
    }

    protected void validarDias(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException("Los días deben ser mayores que cero");
        }
    }

    public abstract double calcularCosto(int dias);
    public abstract String getDetallesEspecificos();

    @Override
    public String toString() {
        return String.format(
                "Tipo: %s%n" +
                "Tipo de placa: %s%n" +
                "Placa: %s%n" +
                "Marca: %s%n" +
                "Modelo: %s%n" +
                "Tarifa diaria: Q%.2f%n" +
                "Estado: %s%n" +
                "%s",
                tipo,
                tipoPlaca,
                placa,
                marca,
                modelo,
                tarifaDiaria,
                disponible ? "Disponible" : "Alquilado",
                getDetallesEspecificos()
        );
    }
}