package data;

import constants.TipoPlaca;
import constants.TipoTransmision;
import model.Automovil;
import model.CamionetaCarga;
import model.Motocicleta;
import model.RentaMovil;

public class DatosIniciales {

    private DatosIniciales() {
    }

    public static void cargar(RentaMovil rentaMovil) {
        if (rentaMovil == null) {
            throw new IllegalArgumentException(
                    "El modelo de RentaMovil es obligatorio.");
        }

        cargarAutomoviles(rentaMovil);
        cargarMotocicletas(rentaMovil);
        cargarCamionetas(rentaMovil);
    }

    private static void cargarAutomoviles(
            RentaMovil rentaMovil) {

        rentaMovil.registrarVehiculo(
                new Automovil(
                        TipoPlaca.P,
                        "P001ABC",
                        "Toyota",
                        "Corolla",
                        250.00,
                        5,
                        TipoTransmision.AUTOMATICA
                )
        );

        rentaMovil.registrarVehiculo(
                new Automovil(
                        TipoPlaca.P,
                        "P002ABC",
                        "Honda",
                        "Civic",
                        225.00,
                        5,
                        TipoTransmision.MANUAL
                )
        );
    }

    private static void cargarMotocicletas(
            RentaMovil rentaMovil) {

        rentaMovil.registrarVehiculo(
                new Motocicleta(
                        TipoPlaca.M,
                        "M001ABC",
                        "Honda",
                        "CB150",
                        100.00,
                        150
                )
        );

        rentaMovil.registrarVehiculo(
                new Motocicleta(
                        TipoPlaca.M,
                        "M002ABC",
                        "Yamaha",
                        "MT-03",
                        150.00,
                        321
                )
        );
    }

    private static void cargarCamionetas(
            RentaMovil rentaMovil) {

        rentaMovil.registrarVehiculo(
                new CamionetaCarga(
                        TipoPlaca.C,
                        "C001ABC",
                        "Toyota",
                        "Hilux",
                        200.00,
                        1.5
                )
        );

        rentaMovil.registrarVehiculo(
                new CamionetaCarga(
                        TipoPlaca.C,
                        "C002ABC",
                        "Ford",
                        "Ranger",
                        275.00,
                        2.0
                )
        );
    }
}