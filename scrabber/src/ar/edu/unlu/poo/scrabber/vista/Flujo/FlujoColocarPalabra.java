package ar.edu.unlu.poo.scrabber.vista.Flujo;

import ar.edu.unlu.poo.scrabber.controlador.Controlador;
import ar.edu.unlu.poo.scrabber.vista.VistaConsola;

public class FlujoColocarPalabra extends Flujo{

    public FlujoColocarPalabra(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }

    enum EstadosPosibles {
        INGRESANDO_PALABRA,
        INGRESANDO_FILA,
        INGRESANDO_COLUMNA,
        INGRESANDO_HORIZONTAL
    }

    private EstadosPosibles estadoActual = EstadosPosibles.INGRESANDO_PALABRA;
    private String palabra;
    private int fila;
    private int columna;

    public Flujo procesarEntrada(String input) {
        switch (estadoActual) {
            case INGRESANDO_PALABRA -> procesarIngresoPalabra(input);
            case INGRESANDO_FILA -> procesarIngresoFila(input);
            case INGRESANDO_COLUMNA -> procesarIngresoColumna(input);
            case INGRESANDO_HORIZONTAL -> {
                return procesarIngresoHorizontal(input);
            }
        }
        return this;
    }

    private Flujo procesarIngresoHorizontal(String input) {
        Boolean esHorizontal;
        try {
            esHorizontal = input.equals("si");
            controlador.ProcesarEntrada(this.palabra,this.fila,this.columna,esHorizontal);
            vista.println("Entrada procesada");
            return new FlujoMenuPrincipal(vista, controlador);
        } catch (NumberFormatException e) {
            vista.println("Ingrese un String valido.");
        }
        return this;
    }

    private void procesarIngresoColumna(String input) {
        Integer columna;
        try {
            columna = Integer.parseInt(input);
            if (columna < 0 || columna >= 15) {
                vista.println("valor invalido");
            } else {
                this.columna = columna;
                estadoActual = EstadosPosibles.INGRESANDO_HORIZONTAL;
            }
        } catch (NumberFormatException e) {
            vista.println("Ingrese un número válido.");
        }
    }


    private void procesarIngresoPalabra(String input) {
        palabra = input;
        estadoActual = EstadosPosibles.INGRESANDO_FILA;
    }

    private void procesarIngresoFila(String input) {
        Integer fila;
        try {
            fila = Integer.parseInt(input);
            if (fila < 0 || fila >= 15) {
                vista.println("valor invalido");
            } else {
                this.fila = fila;
                estadoActual = EstadosPosibles.INGRESANDO_COLUMNA;
            }
        } catch (NumberFormatException e) {
            vista.println("Ingrese un número válido.");
        }
    }



    @Override
    public void mostrarSiguienteTexto() {
        switch (estadoActual) {
            case INGRESANDO_PALABRA -> {
                vista.println("Ingrese la palabra:");
                vista.println("palabra: ");
            }
            case INGRESANDO_FILA -> vista.println("Fila: ");
            case INGRESANDO_COLUMNA -> vista.println("Columna: ");
            case INGRESANDO_HORIZONTAL -> vista.println("Quiere colocarla de forma horizontal? (si/no)");
        }
    }
}
