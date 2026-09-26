package model;

import constants.TipoPlaca;
import constants.TipoVehiculo;

public class Motocicleta extends Vehiculo {

    private final int cilindraje;

    public Motocicleta(
            TipoPlaca tipoPlaca,
            String placa,
            String marca,
            String modelo,
            double tarifaDiaria,
            int cilindraje) {

        super(
                TipoVehiculo.MOTOCICLETA,
                tipoPlaca,
                placa,
                marca,
                modelo,
                tarifaDiaria
        );

        if (cilindraje <= 0) {
            throw new IllegalArgumentException("el cc no debe ser 0");
        }

        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

        double total = getTarifaDiaria() * dias;

        if (cilindraje > 250) {
            total += 75.0;
        }

        return total;
    }

    @Override
    public String getDetallesEspecificos() {
        return String.format(
                "Cilindraje: %d cc",
                cilindraje
        );
    }
}