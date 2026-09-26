package model;

import constants.TipoPlaca;
import constants.TipoTransmision;
import constants.TipoVehiculo;

public class Automovil extends Vehiculo {

    private final int cantidadPasajeros;
    private final TipoTransmision transmision;

    public Automovil(
            TipoPlaca tipoPlaca,
            String placa,
            String marca,
            String modelo,
            double tarifaDiaria,
            int cantidadPasajeros,
            TipoTransmision transmision) {

        super(
                TipoVehiculo.CARRO,
                tipoPlaca,
                placa,
                marca,
                modelo,
                tarifaDiaria
        );

        if (cantidadPasajeros <= 0) {
            throw new IllegalArgumentException("la cantidad de pasajeros debe ser mayor que 0");
        }

        if (transmision == null) {
            throw new IllegalArgumentException("la transmision es obligatoria");
        }

        this.cantidadPasajeros = cantidadPasajeros;
        this.transmision = transmision;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public TipoTransmision getTransmision() {
        return transmision;
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

        double costoBase = getTarifaDiaria() * dias;

        if (transmision == TipoTransmision.AUTOMATICA) {
            return costoBase + (50.0 * dias);
        }
        return costoBase;
    }

    @Override
    public String getDetallesEspecificos() {
        return String.format(
                "Cantidad de pasajeros: %d%n" +
                "Transmisión: %s",
                cantidadPasajeros,
                transmision
        );
    }
}