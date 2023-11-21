package ar.edu.unlu.poo.scrabber.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Diccionario {
    private List<String> palabras;

    public Diccionario(String rutaArchivo) {
        palabras = new ArrayList<>();
        cargarDiccionario(rutaArchivo);
    }

    private void cargarDiccionario(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras.add(linea.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean contienePalabra(String palabra) {
        return palabras.contains(palabra);
    }
}