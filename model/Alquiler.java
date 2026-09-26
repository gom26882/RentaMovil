package model;

public class Alquiler {

    private final Vehiculo vehiculo;
    private final int dias;
    private final double total;

    public Alquiler(
            Vehiculo vehiculo,
            int dias) {

        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo es obligatorio.");
        }
        if (dias <= 0) {
            throw new IllegalArgumentException("Los días deben ser mayores que cero.");
        }

        this.vehiculo = vehiculo;
        this.dias = dias;
        this.total = vehiculo.calcularCosto(dias);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public double getTotal() {
        return total;
    }
}