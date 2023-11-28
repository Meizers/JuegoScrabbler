package ar.edu.unlu.poo.scrabber.vista;


import ar.edu.unlu.poo.scrabber.controlador.Controlador;
import ar.edu.unlu.poo.scrabber.modelo.Jugador;

public interface IVista extends Observador {

    void imprimirTablero();

    void imprimirSoporte(Jugador jugador);

    void MostrarMenuPrincipal(Jugador jugador);

    void setControlador(Controlador controlador);

    void println(String s);


}
