package ar.edu.unlu.poo.scrabber.controlador;

import ar.edu.unlu.poo.scrabber.modelo.Casilla;
import ar.edu.unlu.poo.scrabber.modelo.Jugador;
import ar.edu.unlu.poo.scrabber.modelo.ModeloScrabble;
import ar.edu.unlu.poo.scrabber.vista.IVista;

public class Controlador {
    private final IVista vista;
    private ModeloScrabble modelo;
    private final Jugador jugador;

    public Controlador(IVista vista, Jugador jugador) {
        this.vista = vista;
        vista.setControlador(this);
        this.jugador = jugador;
    }

    public void setModelo(ModeloScrabble modelo){
        this.modelo = modelo;
    }

    public Casilla[][] BuscarTablero(){
        return modelo.getTablero();
    }

    public void ProcesarEntrada(String entrada,int fila,int columna, boolean esHorizontal) {
        if (modelo.validacionCompleta(entrada,fila,columna,esHorizontal,this.jugador)){
            vista.println("Se ha colocado la palabra correctamente");
        } else {
            vista.println("error,no se puede colocar la palabra solicitada o no existe en el diccionario");
        }
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public boolean EsMiTurno() {
        return modelo.getTurno() == jugador.getId_turno();
    }

    public void PasarTurno() {
        modelo.aumentarPasoTurno();
        modelo.notificar();
    }

    public boolean gameOver() {
        return modelo.getPasoTurnosSeguidos() == modelo.getCantDeJugadores();
    }

    public void EnviarPuntaje() {
        modelo.CalcularGanador(jugador.getPuntos(),jugador.getId_turno());
    }

    public int Ganador() {
        return modelo.getGanador();
    }
}
