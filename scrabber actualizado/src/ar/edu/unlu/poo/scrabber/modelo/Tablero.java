package ar.edu.unlu.poo.scrabber.modelo;


import java.util.ArrayList;

public class Tablero {
    private Casilla[][] tablero;
    private static final int TAM_TABLERO = 15;

    public Tablero(){
        inicializarTablero();
    }

    public Casilla getCasillaTablero(int fila, int columna){
        return tablero[fila][columna];
    }

    public Casilla[][] getMatriz(){
        return tablero;
    }

    private void inicializarTablero() {
        tablero = new Casilla[TAM_TABLERO][TAM_TABLERO];
        for (int i = 0; i < TAM_TABLERO; i++) {
            for (int j = 0; j < TAM_TABLERO; j++) {
                tablero[i][j] = new Casilla(1);
            }
        }
        tablero[0][0].setMultiplicador(3);
        tablero[0][0].setMultiplicadoPalabra(true);
        tablero[0][7].setMultiplicador(3);
        tablero[0][7].setMultiplicadoPalabra(true);
        tablero[0][14].setMultiplicador(3);
        tablero[0][14].setMultiplicadoPalabra(true);
        tablero[7][0].setMultiplicador(3);
        tablero[7][0].setMultiplicadoPalabra(true);
        tablero[14][0].setMultiplicador(3);
        tablero[14][0].setMultiplicadoPalabra(true);
        tablero[14][7].setMultiplicador(3);
        tablero[14][7].setMultiplicadoPalabra(true);
        tablero[14][14].setMultiplicador(3);
        tablero[14][14].setMultiplicadoPalabra(true);
        tablero[7][14].setMultiplicador(3);
        tablero[7][14].setMultiplicadoPalabra(true);
        tablero[1][1].setMultiplicador(2);
        tablero[1][1].setMultiplicadoPalabra(true);
        tablero[2][2].setMultiplicador(2);
        tablero[2][2].setMultiplicadoPalabra(true);
        tablero[3][3].setMultiplicador(2);
        tablero[3][3].setMultiplicadoPalabra(true);
        tablero[4][4].setMultiplicador(2);
        tablero[4][4].setMultiplicadoPalabra(true);
        tablero[13][1].setMultiplicador(2);
        tablero[13][1].setMultiplicadoPalabra(true);
        tablero[12][2].setMultiplicador(2);
        tablero[12][2].setMultiplicadoPalabra(true);
        tablero[11][3].setMultiplicador(2);
        tablero[11][3].setMultiplicadoPalabra(true);
        tablero[10][4].setMultiplicador(2);
        tablero[10][4].setMultiplicadoPalabra(true);
        tablero[1][13].setMultiplicador(2);
        tablero[1][13].setMultiplicadoPalabra(true);
        tablero[2][12].setMultiplicador(2);
        tablero[2][12].setMultiplicadoPalabra(true);
        tablero[3][11].setMultiplicador(2);
        tablero[3][11].setMultiplicadoPalabra(true);
        tablero[4][10].setMultiplicador(2);
        tablero[4][10].setMultiplicadoPalabra(true);
        tablero[13][13].setMultiplicador(2);
        tablero[13][13].setMultiplicadoPalabra(true);
        tablero[12][12].setMultiplicador(2);
        tablero[12][12].setMultiplicadoPalabra(true);
        tablero[11][11].setMultiplicador(2);
        tablero[11][11].setMultiplicadoPalabra(true);
        tablero[10][10].setMultiplicador(2);
        tablero[10][10].setMultiplicadoPalabra(true);
        tablero[7][7].setMultiplicador(2);
        tablero[7][7].setMultiplicadoPalabra(true);
    }

    public void colocarPalabraTablero(String entrada,ArrayList<Ficha> palabra, int fila, int columna, boolean esHorizontal){
        colocar(entrada, palabra,fila, columna, esHorizontal);
    }

    private void colocar(String entrada,ArrayList<Ficha> palabra, int fila, int columna, boolean esHorizontal){
        int j = 0;
        for (int i = 0; i < entrada.length(); i++) {
            if (esHorizontal) {
                if (tablero[fila][columna + i].getficha().getLetra() == ' '){
                    tablero[fila][columna + i].setFicha(palabra.get(j));
                    tablero[fila][columna + i].setOcupada();
                    j++;
                } else if (tablero[fila][columna + i].getficha().getLetra() == palabra.get(j).getLetra()){
                    j++;
                }
            } else {
                if (tablero[fila + i][columna].getficha().getLetra() == ' '){
                    tablero[fila + i][columna].setFicha(palabra.get(j));
                    tablero[fila + i][columna].setOcupada();
                    j++;
                } else if (tablero[fila][columna + i].getficha().getLetra() == palabra.get(j).getLetra()){
                    j++;
                }
            }
        }
    }

    public boolean palabraPasaPorCentro(String entrada, int fila, int columna, boolean esHorizontal) {
        if (fila < 0 || fila >= tablero.length || columna < 0 || columna >= tablero[0].length) {
            return false;
        }

        for (int i = 0; i < entrada.length(); i++) {
            int filaActual = esHorizontal ? fila : fila + i;
            int columnaActual = esHorizontal ? columna + i : columna;

            if (filaActual == 7 && columnaActual == 7) {
                return true; // la palabra pasa por el punto (7, 7)
            }
        }

        return false; // Ninguna letra de la palabra pasa por el punto (7, 7)
    }

    public boolean verificarConsistenciaTablero(Diccionario diccionario){
        // Revisar columna por columna de arriba a abajo
        if (!ConsistenciaDeColumnas(diccionario)){
            return false;
        }
        return ConsistenciaDeFilas(diccionario);
    }

    private boolean ConsistenciaDeFilas(Diccionario diccionario) {
        boolean verificando = false;
        String Palabra = "";
        for (int fila = 0; fila < TAM_TABLERO; fila++) {
            for (int columna = 0; columna < TAM_TABLERO; columna++) {
                char letra = tablero[fila][columna].getficha().getLetra();

                if (letra != ' ' && verificando){ //encuentra una casilla con una ficha y esta construyendo la palabra
                    Palabra = Palabra + letra;
                }

                if (letra != ' ' && !verificando) { //encuentra una casilla con una ficha y no estaba coonstruyendo la palabra
                    verificando = true;
                    Palabra = Palabra + letra;
                }

                if ( (letra == ' ' && verificando) || ( columna == TAM_TABLERO - 1 ) ){ // llega al final de la palabra o al final de la matriz
                    verificando = false;
                    if (!diccionario.contienePalabra(Palabra) && !Palabra.isEmpty() && Palabra.length() != 1){ // verifico si no esta la palabra dentro del diccionario
                        return false;
                    } else {
                        Palabra = ""; // si esta dentro, reinicio palabra y continuo recorriendo
                    }
                }
            }
        }
        return true;
    }

    private boolean ConsistenciaDeColumnas(Diccionario diccionario) {
        boolean verificando = false;
        String Palabra = "";
        for (int columna = 0; columna < TAM_TABLERO; columna++) {
            for (int fila = 0; fila < TAM_TABLERO; fila++) {
                char letra = tablero[fila][columna].getficha().getLetra();

                if (letra != ' ' && verificando){ //encuentra una casilla con una ficha y esta construyendo la palabra
                    Palabra = Palabra + letra;
                }

                if (letra != ' ' && !verificando) { //encuentra una casilla con una ficha y no estaba coonstruyendo la palabra
                    verificando = true;
                    Palabra = Palabra + letra;
                }

                if ( (letra == ' ' && verificando) || ( fila == TAM_TABLERO - 1 ) ){ // llega al final de la palabra o al final de la matriz
                    verificando = false;
                    if (!diccionario.contienePalabra(Palabra) && !Palabra.isEmpty() && Palabra.length() != 1){ // verifico si no esta la palabra dentro del diccionario
                        return false;
                    } else {
                        Palabra = ""; // si esta dentro, reinicio palabra y continuo recorriendo
                    }
                }
            }
        }
        return true;
    }


}
