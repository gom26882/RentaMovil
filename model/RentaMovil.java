package model;

import constants.TipoVehiculo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RentaMovil {

    private final Map<String, Vehiculo> vehiculos;
    private double ingresosAcumulados;

    public RentaMovil() {
        vehiculos = new LinkedHashMap<>();
        ingresosAcumulados = 0.0;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("el vehículo es obligatorio");
        }

        String placa = vehiculo.getPlaca();

        if (vehiculos.containsKey(placa)) {
            throw new IllegalArgumentException("ya existe un vehículo con esa placa");
        }

        vehiculos.put(placa, vehiculo);
    }

    public Vehiculo buscarVehiculo(String placa) {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("la placa no puede estar vacía");
        }

        Vehiculo vehiculo = vehiculos.get(placa.trim().toUpperCase());

        if (vehiculo == null) {
            throw new IllegalArgumentException("no existe un vehículo con esa placa");
        }

        return vehiculo;
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.values());
    }

    public Cotizacion cotizar( String placa, int dias) {

        validarDias(dias);
        Vehiculo vehiculo =buscarVehiculo(placa);
        return new Cotizacion( vehiculo, dias );
    }

    public Alquiler confirmarAlquiler(String placa, int dias) {

        validarDias(dias);

        Vehiculo vehiculo = buscarVehiculo(placa);

        if (!vehiculo.isDisponible()) {
            throw new IllegalStateException("el vehículo no está disponible.");
        }

        Alquiler alquiler = new Alquiler(vehiculo, dias );

        vehiculo.marcarAlquilado();
        ingresosAcumulados += alquiler.getTotal();
        return alquiler;
    }

    public void registrarDevolucion(
            String placa) {

        Vehiculo vehiculo =buscarVehiculo(placa);

        if (vehiculo.isDisponible()) {
            throw new IllegalStateException("el vehículo ya está disponible");
        }

        vehiculo.marcarDisponible();
    }

    public int contarVehiculos() {
        return vehiculos.size();
    }

    public int contarDisponibles() {
        int cantidad = 0;

        for (Vehiculo vehiculo: vehiculos.values()) {
            if (vehiculo.isDisponible()) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int contarAlquilados() {
        return contarVehiculos() - contarDisponibles();
    }

    public int contarPorTipo( TipoVehiculo tipo, boolean disponible) {

        int cantidad = 0;
        
        for (Vehiculo vehiculo : vehiculos.values()) {
            if (vehiculo.getTipo() == tipo && vehiculo.isDisponible() == disponible) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public double getIngresosAcumulados() {
        return ingresosAcumulados;
    }

    private void validarDias(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException("los días deben ser mayor que 0");
        }
    }
}