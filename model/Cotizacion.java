package model;

public class Cotizacion {

    private final Vehiculo vehiculo;
    private final int dias;
    private final double total;

    public Cotizacion(
            Vehiculo vehiculo,
            int dias) {

        if (vehiculo == null) {
            throw new IllegalArgumentException("el vehiculo es obligatorio");
        }

        if (dias <= 0) {
            throw new IllegalArgumentException("los días deben ser mayores que 0");
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