package ar.edu.unlu.poo.scrabber.vista.Flujo;

import ar.edu.unlu.poo.scrabber.controlador.Controlador;
import ar.edu.unlu.poo.scrabber.vista.VistaConsola;

public abstract class Flujo {
    protected final VistaConsola vista;
    protected final Controlador controlador;

    public Flujo(VistaConsola vista, Controlador controlador) {
        this.vista = vista;
        this.controlador = controlador;
    }

    public abstract Flujo procesarEntrada(String string);

    public abstract void mostrarSiguienteTexto();
}
