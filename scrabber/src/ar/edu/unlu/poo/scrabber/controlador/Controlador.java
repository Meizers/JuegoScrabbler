package ar.edu.unlu.poo.scrabber.controlador;

import ar.edu.unlu.poo.scrabber.modelo.Casilla;
import ar.edu.unlu.poo.scrabber.modelo.Ficha;
import ar.edu.unlu.poo.scrabber.modelo.Jugador;
import ar.edu.unlu.poo.scrabber.modelo.ModeloScrabble;
import ar.edu.unlu.poo.scrabber.vista.IVista;

import java.util.ArrayList;

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
        if (modelo.esPrimeraPalabra()){
            if (!modelo.palabraPasaPorCentro(entrada,fila,columna,esHorizontal)){
                vista.println("La primera Palabra tiene que pasar por el centro del tablero [7][7]");
                return;
            }
        }
        if (modelo.getDiccionario().contienePalabra(entrada)) {
            ArrayList<Ficha> FichasEntrada = modelo.convertirPalabra(entrada,jugador);
            if (modelo.ValidarPalabraEnTablero(entrada,fila,columna,esHorizontal,jugador.getSoporteJugador())) {
                if (modelo.colocarPalabra(entrada,FichasEntrada, fila, columna, esHorizontal)) {
                    jugador.QuitarFichas(FichasEntrada);
                    jugador.llenarSoporte(modelo.getBolsaFichas());
                    modelo.ObtenerPuntos(entrada,fila,columna,esHorizontal,jugador);
                    modelo.setEsPrimera(false);
                    modelo.SetearPasoTurno();
                    modelo.notificar();
                } else {
                    vista.println("Inconsistencia en el tablero, intente denuevo");
                }
            } else {
                vista.println("No se puede colocar la palabra en el tablero");
            }
        } else {
            vista.println("La palabra no se encuentra en el diccionario");
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
