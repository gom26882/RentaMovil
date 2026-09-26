package model;

import constants.TipoPlaca;
import constants.TipoVehiculo;

public class CamionetaCarga extends Vehiculo {

    private final double capacidadToneladas;

    public CamionetaCarga(
            TipoPlaca tipoPlaca,
            String placa,
            String marca,
            String modelo,
            double tarifaDiaria,
            double capacidadToneladas) {

        super(
                TipoVehiculo.CAMIONETA_CARGA,
                tipoPlaca,
                placa,
                marca,
                modelo,
                tarifaDiaria
        );

        if (!Double.isFinite(capacidadToneladas) || capacidadToneladas <= 0) {

            throw new IllegalArgumentException("la capacidad no debe ser 0");
        }
        this.capacidadToneladas = capacidadToneladas;
    }

    public double getCapacidadToneladas() {
        return capacidadToneladas;
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

        double recargoDiario = 100.0 * capacidadToneladas;

        return (getTarifaDiaria() + recargoDiario) * dias;
    }

    @Override
    public String getDetallesEspecificos() {
        return String.format(
                "Capacidad máxima: %.2f toneladas",
                capacidadToneladas
        );
    }
}