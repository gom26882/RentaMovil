package controller;

import constants.TipoPlaca;
import constants.TipoTransmision;
import model.Alquiler;
import model.Automovil;
import model.CamionetaCarga;
import model.Cotizacion;
import model.Motocicleta;
import model.RentaMovil;
import model.Vehiculo;
import view.RentaMovilView;

public class RentaMovilController {

    private final RentaMovil modelo;
    private final RentaMovilView vista;

    public RentaMovilController(RentaMovil modelo, RentaMovilView vista) {

        if (modelo == null || vista == null) {
            throw new IllegalArgumentException("el modelo y la vista son obligatorios");
        }

        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            try {
                int opcion =
                        vista.mostrarMenu();

                switch (opcion) {
                    case 1:
                        registrarVehiculo();
                        break;

                    case 2:
                        mostrarVehiculos();
                        break;

                    case 3:
                        cotizarAlquiler();
                        break;

                    case 4:
                        confirmarAlquiler();
                        break;

                    case 5:
                        registrarDevolucion();
                        break;

                    case 6:
                        mostrarReporte();
                        break;

                    case 7:
                        continuar = false;
                        vista.mostrarMensaje("saliendo :)");
                        break;

                    default:
                        vista.mostrarError("seleccione una opción válida");
                        break;
                }

            } catch (IllegalArgumentException| IllegalStateException e) {

                vista.mostrarError(e.getMessage());
            } catch (Exception e) {
                vista.mostrarError("error general");
            }
        }
        vista.cerrarScanner();
    }

    private void registrarVehiculo() {
        vista.mostrarMensaje("");
        vista.mostrarMensaje(
                "1. Automóvil");
        vista.mostrarMensaje(
                "2. Motocicleta");
        vista.mostrarMensaje(
                "3. Camioneta de carga");

        int opcion = vista.leerEntero("seleccione el tipo de vehículo: ");

        if (opcion < 1 || opcion > 3) {
            vista.mostrarError("tipo de vehículo invalido");
            return;
        }

        TipoPlaca tipoPlaca = solicitarTipoPlaca();
        String placa = vista.leerTexto("Placa: ");
        String marca = vista.leerTexto("Marca: ");
        String modeloVehiculo = vista.leerTexto("Modelo: ");
        double tarifa = vista.leerDoublePositivo("Tarifa diaria: Q");
        Vehiculo vehiculo;

        switch (opcion) {
            case 1:
                vehiculo = crearAutomovil(tipoPlaca, placa, marca, modeloVehiculo, tarifa);
                break;
            case 2:
                vehiculo = crearMotocicleta( tipoPlaca, placa, marca, modeloVehiculo, tarifa );
                break;
            case 3:
                vehiculo = crearCamioneta(tipoPlaca, placa, marca, modeloVehiculo, tarifa );
                break;
            default:
                return;
        }

        modelo.registrarVehiculo(vehiculo);
        vista.mostrarMensaje("vehículo registrado correctamente");
    }

    private Automovil crearAutomovil(
            TipoPlaca tipoPlaca,
            String placa,
            String marca,
            String modeloVehiculo,
            double tarifa) {

        int pasajeros = vista.leerEnteroPositivo( "cantidad de pasajeros: ");

        TipoTransmision transmision = solicitarTipoTransmision();

        return new Automovil(
                tipoPlaca,
                placa,
                marca,
                modeloVehiculo,
                tarifa,
                pasajeros,
                transmision
        );
    }

    private Motocicleta crearMotocicleta(
            TipoPlaca tipoPlaca,
            String placa,
            String marca,
            String modeloVehiculo,
            double tarifa) {

        int cilindraje = vista.leerEnteroPositivo("Cilindraje en cc: ");

        return new Motocicleta(
                tipoPlaca,
                placa,
                marca,
                modeloVehiculo,
                tarifa,
                cilindraje
        );
    }

    private CamionetaCarga crearCamioneta(
            TipoPlaca tipoPlaca,
            String placa,
            String marca,
            String modeloVehiculo,
            double tarifa) {

        double capacidad = vista.leerDoublePositivo( "Capacidad en toneladas: ");

        return new CamionetaCarga(
                tipoPlaca,
                placa,
                marca,
                modeloVehiculo,
                tarifa,
                capacidad
        );
    }

    private TipoPlaca solicitarTipoPlaca() {
        while (true) {
            vista.mostrarMensaje("");

            vista.mostrarMensaje( "1. P - Particular");
            vista.mostrarMensaje( "2. M - Motocicleta");
            vista.mostrarMensaje("3. C - Comercial");

            int opcion = vista.leerEntero("seleccione el tipo de placa: ");

            switch (opcion) {
                case 1:
                    return TipoPlaca.P;
                case 2:
                    return TipoPlaca.M;
                case 3:
                    return TipoPlaca.C;
                default:
                    vista.mostrarError("Seleccione un tipo de placa válido.");
                    break;
            }
        }
    }

    private TipoTransmision
    solicitarTipoTransmision() {

        while (true) {
            vista.mostrarMensaje("1. Automática");
            vista.mostrarMensaje("2. Manual");

            int opcion = vista.leerEntero("Seleccione el tipo de transmisión: ");

            switch (opcion) {
                case 1:
                    return TipoTransmision.AUTOMATICA;
                case 2:
                    return TipoTransmision.MANUAL;
                default:
                    vista.mostrarError("seleccione una transmisión válida");
                    break;
            }
        }
    }

    private void mostrarVehiculos() {
        vista.mostrarVehiculos(modelo.getVehiculos());
    }

    private void cotizarAlquiler() {
        String placa = vista.leerTexto("ingrese la placa: ");
        int dias = vista.leerEnteroPositivo("ingrese la cantidad de días: ");
        Cotizacion cotizacion = modelo.cotizar( placa, dias);
        vista.mostrarCotizacion(cotizacion);
        vista.mostrarMensaje("La cotización no modifica los ingresos ni la disponibilidad");
    }

    private void confirmarAlquiler() {
        String placa = vista.leerTexto( "Ingrese la placa: ");
        int dias = vista.leerEnteroPositivo( "Ingrese la cantidad de días: ");
        
        Cotizacion cotizacion =modelo.cotizar(placa, dias);

        vista.mostrarCotizacion(cotizacion);

        if (!cotizacion.getVehiculo().isDisponible()) {
            vista.mostrarError("el vehículo está ocupado");
            return;
        }

        boolean confirmado = vista.solicitarConfirmacion("desea confirmar el alquiler?");

        if (!confirmado) {
            vista.mostrarMensaje("operación cancelada, no se hicieron cambios");
            return;
        }

        Alquiler alquiler = modelo.confirmarAlquiler( placa, dias);
        vista.mostrarAlquilerConfirmado(alquiler);
    }

    private void registrarDevolucion() {
        String placa = vista.leerTexto("ingrese la placa: ");

        modelo.registrarDevolucion(placa);
        vista.mostrarMensaje("devolución registrada correctamente");
    }

    private void mostrarReporte() {
        vista.mostrarReporte(modelo);
    }
}