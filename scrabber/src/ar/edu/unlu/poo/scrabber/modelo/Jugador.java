package ar.edu.unlu.poo.scrabber.modelo;


import java.util.ArrayList;
import java.util.Iterator;

public class Jugador {
    private final String nombre;
    private int puntos = 0;
    private ArrayList<Ficha> soporteJugador;
    private final int id_turno;

    public Jugador(String nombre,int id_turno){
        this.nombre = nombre;
        this.id_turno = id_turno;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void inicializarSoporteJugador(ArrayList<Ficha> bolsaFichas) {
        soporteJugador = new ArrayList<>();
        llenarSoporte(bolsaFichas);
    }

    public void llenarSoporte(ArrayList<Ficha> bolsaFichas) {
        while (soporteJugador.size() < 7 && !bolsaFichas.isEmpty()) {
            soporteJugador.add(bolsaFichas.remove(0));
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void SumarPuntos(int puntos) {
        this.puntos = this.puntos + puntos;
    }

    public ArrayList<Ficha> getSoporteJugador() {
        return soporteJugador;
    }

    public void QuitarFichas(ArrayList<Ficha> FichasEntrada){
        vaciarSoporte(FichasEntrada);
    }

    private void vaciarSoporte(ArrayList<Ficha> FichasEntrada) {
        for (Ficha fichaEntrada : FichasEntrada) {
            char letra = Character.toLowerCase(fichaEntrada.getLetra());

            // Verificar si la letra está presente en el ArrayList de fichas del soporteJugador
            Iterator<Ficha> iteradorSoporte = this.soporteJugador.iterator();
            while (iteradorSoporte.hasNext()) {
                Ficha ficha = iteradorSoporte.next();
                if (Character.toLowerCase(ficha.getLetra()) == letra) {
                    iteradorSoporte.remove();  // Retirar la ficha del soporteJugador
                    break;
                }
            }
        }
    }
}
