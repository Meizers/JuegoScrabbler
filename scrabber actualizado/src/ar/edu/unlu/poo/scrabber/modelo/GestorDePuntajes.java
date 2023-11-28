package ar.edu.unlu.poo.scrabber.modelo;

public class GestorDePuntajes {
    private int[] puntajeJugadores;
    private int sumados = 0;

    public GestorDePuntajes(int cantidadDeJugadores){
        puntajeJugadores = new int[cantidadDeJugadores];
    }

    public int[] getPuntajeJugadores(){
        return puntajeJugadores;
    }

    public int getSumados() {
        return sumados;
    }

    public void sumarTotalPuntos(int puntos, int idTurno) {
        puntajeJugadores[idTurno-1] = puntos;
        sumados++;
    }

    public int getGanador() {
        int indiceMaximo = 0;
        int valorMaximo = puntajeJugadores[0];

        for (int i = 1; i < puntajeJugadores.length; i++) {
            if (puntajeJugadores[i] > valorMaximo) {
                valorMaximo = puntajeJugadores[i];
                indiceMaximo = i;
            }
        }
        return indiceMaximo+1;
    }
}
