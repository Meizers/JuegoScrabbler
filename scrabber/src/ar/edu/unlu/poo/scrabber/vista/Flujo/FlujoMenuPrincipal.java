package ar.edu.unlu.poo.scrabber.vista.Flujo;

import ar.edu.unlu.poo.scrabber.controlador.Controlador;
import ar.edu.unlu.poo.scrabber.vista.VistaConsola;

public class FlujoMenuPrincipal extends Flujo {

    public FlujoMenuPrincipal(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        switch (string) {
            case "1" -> {
                return new FlujoColocarPalabra(vista, controlador);
            }
            case "2" -> controlador.PasarTurno();
            default -> vista.println("opcion invalida");
        }
        return this;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.imprimirTablero();
        vista.println("\n\t      JUGADOR " + controlador.getJugador().getId_turno() + " : " + controlador.getJugador().getNombre());
        vista.println(" Cantidad de puntos : " + controlador.getJugador().getPuntos());
        vista.imprimirSoporte(controlador.getJugador());
        vista.println("\n\n1. Colocar una palabra");
        vista.println("2. Saltar Turno");
        vista.println("Seleccione una opción: ");
    }


}
