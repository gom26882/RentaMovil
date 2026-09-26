import controller.RentaMovilController;
import data.DatosIniciales;
import model.RentaMovil;
import view.RentaMovilView;

public class Main {

    public static void main(String[] args) {
        RentaMovil modelo = new RentaMovil();

        DatosIniciales.cargar(modelo);
        RentaMovilView vista =new RentaMovilView();
        RentaMovilController controlador =new RentaMovilController( modelo, vista );

        controlador.iniciar();
    }
}