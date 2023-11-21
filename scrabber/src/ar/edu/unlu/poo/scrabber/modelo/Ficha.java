package ar.edu.unlu.poo.scrabber.modelo;

public class Ficha {
    private char letra;
    private int puntos;

    public Ficha(char letra, int puntos){
        this.letra = letra;
        this.puntos = puntos;
    }

    public char getLetra() {
        return letra;
    }

    public int getPunto() {
        return puntos;
    }

    @Override
    public String toString() {
        return String.valueOf(this.letra);
    }
}
