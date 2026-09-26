package view;

import constants.TipoVehiculo;
import model.Alquiler;
import model.Cotizacion;
import model.RentaMovil;
import model.Vehiculo;

import java.util.List;
import java.util.Scanner;

public class RentaMovilView {

    private final Scanner scanner;

    public RentaMovilView() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println();
        System.out.println("---------- Renta Movil ----------");
        System.out.println("1. Registrar vehículo");
        System.out.println("2. Mostrar vehículos");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolución");
        System.out.println("6. Mostrar reporte general");
        System.out.println("7. Salir");
        System.out.println("-----------------------------------");

        return leerEntero("seleccione una opción: ");
    }

    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                mostrarError("ingrese un número válido");
            }
        }
    }

    public int leerEnteroPositivo( String mensaje) {

        while (true) {
            int numero =leerEntero(mensaje);

            if (numero > 0) {
                return numero;
            }

            mostrarError("el número debe ser mayor que 0");
        }
    }

    public double leerDoublePositivo(String mensaje) {

        while (true) {
            System.out.print(mensaje);

            String entrada = scanner.nextLine().trim().replace(',', '.');
            try {
                double numero = Double.parseDouble(entrada);

                if (Double.isFinite(numero)&& numero > 0) {
                    return numero;
                }

                mostrarError("el número debe ser mayor que 0");

            } catch (NumberFormatException e) {
                mostrarError("ingrese un número decimal válido");
            }
        }
    }

    public String leerTexto(String mensaje) {

        while (true) {
            System.out.print(mensaje);

            String texto = scanner.nextLine().trim();

            if (!texto.isBlank()) {
                return texto;
            }
            mostrarError("el valor no puede ser vacío");
        }
    }

    public boolean solicitarConfirmacion(String mensaje) {

        while (true) {
            String respuesta =leerTexto(mensaje + " (S/N): ");

            if (respuesta.equalsIgnoreCase("S")) {
                return true;
            } 
            if (respuesta.equalsIgnoreCase("N")) {
                return false;
            }

            mostrarError("ingrese S para si o N para no");
        }
    }

    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

        System.out.println();
        System.out.println("---------- Vehiculos ----------");

        if (vehiculos.isEmpty()) {
            System.out.println("no hay vehículos registrados");
            return;
        }

        for (Vehiculo vehiculo: vehiculos) {
            System.out.println(vehiculo);
            System.out.println("---------------------------");
        }
    }

    public void mostrarCotizacion(Cotizacion cotizacion) {

        System.out.println();
        System.out.println("--------- Cotizacion ---------");
        System.out.println(cotizacion.getVehiculo());
        System.out.printf("días solicitados: %d%n",cotizacion.getDias());
        System.out.printf("Costo total: Q%.2f%n",cotizacion.getTotal());
        System.out.println("------------------------------");
    }

    public void mostrarAlquilerConfirmado(Alquiler alquiler) {
        System.out.println();
        System.out.println("Alquiler confirmado correctamente.");
        System.out.printf("Placa: %s%n", alquiler.getVehiculo().getPlaca());
        System.out.printf("Días: %d%n", alquiler.getDias());
        System.out.printf("Total cobrado: Q%.2f%n", alquiler.getTotal());
    }

    public void mostrarReporte(RentaMovil modelo) {

        System.out.println();
        System.out.println("------- Reporte General -------");
        System.out.printf("Total de vehículos: %d%n",modelo.contarVehiculos());
        System.out.printf("Vehículos disponibles: %d%n", modelo.contarDisponibles());
        System.out.printf("Vehículos alquilados: %d%n", modelo.contarAlquilados());

        mostrarConteoPorTipo(modelo, TipoVehiculo.CARRO);
        mostrarConteoPorTipo(modelo, TipoVehiculo.MOTOCICLETA);
        mostrarConteoPorTipo(modelo,TipoVehiculo.CAMIONETA_CARGA);

        System.out.printf("%nIngresos acumulados: Q%.2f%n", modelo.getIngresosAcumulados());
        System.out.println("-------------------------------");
    }

    private void mostrarConteoPorTipo( RentaMovil modelo, TipoVehiculo tipo) {
        System.out.println();
        System.out.println(tipo + ":");
        System.out.printf("  Disponibles: %d%n", modelo.contarPorTipo(tipo, true));
        System.out.printf("  Alquilados: %d%n", modelo.contarPorTipo( tipo, false));
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}