package ar.edu.unlu.poo.scrabber.modelo;

import ar.edu.unlu.poo.scrabber.vista.Observador;
import ar.edu.unlu.poo.scrabber.vista.SujetoObservable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ModeloScrabble implements SujetoObservable {
    private ArrayList<Observador> observadores;
    private static final int TAM_TABLERO = 15;
    private Diccionario diccionario;
    private Casilla[][] tablero;
    private ArrayList<Ficha> bolsaFichas = new ArrayList<>();
    private int turno;
    private final int CantDeJugadores;
    private boolean primeraPalabra;
    private int pasoTurnosSeguidos;
    private int[] PuntajeJugadores;

    public ModeloScrabble(int cantDeJugadores) {
        // Inicializar el diccionario, el tablero, la bolsa de fichas y el soporte del jugador
        inicializarDiccionario();
        inicializarTablero();
        inicializarBolsaFichas();
        observadores = new ArrayList<>();
        this.turno = 1;
        this.CantDeJugadores = cantDeJugadores;
        this.primeraPalabra = true;
        this.PuntajeJugadores = new int[cantDeJugadores];
    }

    public boolean esPrimeraPalabra() {
        return primeraPalabra;
    }

    public Diccionario getDiccionario() {
        return diccionario;
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public ArrayList<Ficha> getBolsaFichas() {
        return bolsaFichas;
    }

    public int getTurno() {
        return turno;
    }

    private void inicializarDiccionario() {
        diccionario = new Diccionario("C:/Users/Tomas Resnik/Documents/Unlu/Programacion orientada a objetos/scrabber/palabras.txt");
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

    private void inicializarBolsaFichas() {
        bolsaFichas = new ArrayList<>();
        char letra;
        for (int i = 0; i < 12; i++) {
            letra = 'a';
            bolsaFichas.add(new Ficha(letra,1));
            letra = 'e';
            bolsaFichas.add(new Ficha(letra,1));
        }
        for (int i = 0; i < 6; i++) {
            letra = 'i';
            bolsaFichas.add(new Ficha(letra,1));
            letra = 's';
            bolsaFichas.add(new Ficha(letra,1));
        }
        for (int i = 0; i < 9; i++) {
            letra = 'o';
            bolsaFichas.add(new Ficha(letra,1));
        }
        for (int i = 0; i < 5; i++) {
            letra = 'u';
            bolsaFichas.add(new Ficha(letra,1));
            letra = 'n';
            bolsaFichas.add(new Ficha(letra,1));
            letra = 'r';
            bolsaFichas.add(new Ficha(letra,1));
        }
        for (int i = 0; i < 4; i++) {
            letra = 'l';
            bolsaFichas.add(new Ficha(letra,1));
            letra = 't';
            bolsaFichas.add(new Ficha(letra,1));
        }
        for (int i = 0; i < 5; i++) {
            letra = 'd';
            bolsaFichas.add(new Ficha(letra,2));
        }
        for (int i = 0; i < 2; i++) {
            letra = 'g';
            bolsaFichas.add(new Ficha(letra,2));
        }
        for (int i = 0; i < 4; i++) {
            letra = 'c';
            bolsaFichas.add(new Ficha(letra,3));
        }
        for (int i = 0; i < 2; i++) {
            letra = 'b';
            bolsaFichas.add(new Ficha(letra,3));
            letra = 'm';
            bolsaFichas.add(new Ficha(letra,3));
            letra = 'p';
            bolsaFichas.add(new Ficha(letra,3));
        }
        for (int i = 0; i < 2; i++) {
            letra = 'h';
            bolsaFichas.add(new Ficha(letra,4));
        }
        letra = 'f';
        bolsaFichas.add(new Ficha(letra,4));
        letra = 'v';
        bolsaFichas.add(new Ficha(letra,4));
        letra = 'y';
        bolsaFichas.add(new Ficha(letra,4));
        letra = 'q';
        bolsaFichas.add(new Ficha(letra,5));
        letra = 'j';
        bolsaFichas.add(new Ficha(letra,8));
        letra = 'x';
        bolsaFichas.add(new Ficha(letra,8));
        letra = 'z';
        bolsaFichas.add(new Ficha(letra,10));
        long semilla = 123;
        Random random = new Random(semilla);
        Collections.shuffle(bolsaFichas, random);
    }

    public boolean validacionCompleta(String palabra, int fila, int columna, boolean esHorizontal, Jugador jugador){
        if (esPrimeraPalabra()){
            if (!palabraPasaPorCentro(palabra,fila,columna,esHorizontal)){
                return false;
            }
        }
        if (getDiccionario().contienePalabra(palabra)) {
            ArrayList<Ficha> fichasEntrada = convertirPalabra(palabra,jugador);
            if (validarPalabraEnTablero(palabra,fila,columna,esHorizontal,jugador.getSoporteJugador())) {
                if (colocarPalabra(palabra,fichasEntrada, fila, columna, esHorizontal)) {
                    jugador.QuitarFichas(fichasEntrada);
                    jugador.llenarSoporte(getBolsaFichas());
                    ObtenerPuntos(palabra,fila,columna,esHorizontal,jugador);
                    setEsPrimera(false);
                    setearPasoTurno();
                    notificar();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validarPalabraEnTablero(String palabra, int fila, int columna, boolean esHorizontal, ArrayList<Ficha> soporteJugador){
        return esPalabraValida(palabra,fila,columna,esHorizontal,soporteJugador);
    }

    private boolean esPalabraValida(String palabra, int fila, int columna, boolean esHorizontal, ArrayList<Ficha> soporteJugador) {
        boolean hayFicha = esPrimeraPalabra();
        for (int i = 0; i < palabra.length(); i++) {
            if (columna + i > 14){
                return false;
            }else if(fila + i > 14){
                return false;
            }
            char letraTablero = esHorizontal ? tablero[fila][columna + i].getficha().getLetra() : tablero[fila + i][columna].getficha().getLetra();
            char letraPalabra = palabra.charAt(i);
            if (letraTablero != ' '){
                hayFicha = true;
            }
            if (letraTablero != ' ' && letraTablero != palabra.charAt(i)) {
                return false; // Colisión con otra letra en la matriz
            } else if (letraTablero == ' ' && !tieneLetraEnSoporte(letraPalabra, soporteJugador)) {
                return false; // Casilla vacía sin la letra correspondiente en el soporte del jugador
            }
        }
        return hayFicha;
    }

    private boolean tieneLetraEnSoporte(char letra, ArrayList<Ficha> soporteJugador) {
        for (Ficha ficha : soporteJugador) {
            if (ficha.getLetra() == letra) {
                return true;
            }
        }
        return false; // La letra no está en el soporte del jugador
    }

    public boolean colocarPalabra(String entrada,ArrayList<Ficha> palabra, int fila, int columna, boolean esHorizontal){
        return colocarPalabraEnTablero(entrada,palabra,fila,columna,esHorizontal);
    }

    private boolean colocarPalabraEnTablero(String entrada,ArrayList<Ficha> palabra, int fila, int columna, boolean esHorizontal) {
        Casilla[][] AuxTablero = new Casilla[15][15];
        CopiarContenidoTablero(AuxTablero,tablero);
        ColocarPalabraTablero(entrada,palabra,fila,columna,esHorizontal,AuxTablero);
        if (verificarConsistenciaTablero(AuxTablero)){
            ColocarPalabraTablero(entrada,palabra,fila,columna,esHorizontal,tablero);
            return true;
        } else {
            return false;
        }
    }

    private void ColocarPalabraTablero(String entrada,ArrayList<Ficha> palabra, int fila, int columna, boolean esHorizontal ,Casilla[][] tablero){
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

    public ArrayList<Ficha> convertirPalabra(String palabra, Jugador jugador){
        return convertirPalabraAFichas(palabra,jugador);
    }

    private ArrayList<Ficha> convertirPalabraAFichas(String palabra, Jugador jugador) {
        ArrayList<Ficha> fichasDePalabra = new ArrayList<>();

        for (char letra : palabra.toCharArray()) {
            // Buscar la ficha correspondiente en el soporte del jugador
            Ficha fichaEnSoporte = null;
            for (Ficha ficha : jugador.getSoporteJugador()) {
                if (ficha.getLetra() == letra) {
                    fichaEnSoporte = ficha;
                    break;
                }
            }

            // Si se encuentra la ficha, agregarla a la lista
            if (fichaEnSoporte != null) {
                fichasDePalabra.add(fichaEnSoporte);
            }
        }

        return fichasDePalabra;
    }

    public boolean verificarConsistenciaTablero(Casilla[][] AuxTablero){
        // Revisar columna por columna de arriba a abajo
        if (!ConsistenciaDeColumnas(AuxTablero)){
            return false;
        }
        return ConsistenciaDeFilas(AuxTablero);
    }

    private boolean ConsistenciaDeFilas(Casilla[][] auxTablero) {
        boolean verificando = false;
        String Palabra = "";
        for (int fila = 0; fila < TAM_TABLERO; fila++) {
            for (int columna = 0; columna < TAM_TABLERO; columna++) {
                char letra = auxTablero[fila][columna].getficha().getLetra();

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

    private boolean ConsistenciaDeColumnas(Casilla[][] auxTablero) {
        boolean verificando = false;
        String Palabra = "";
        for (int columna = 0; columna < TAM_TABLERO; columna++) {
            for (int fila = 0; fila < TAM_TABLERO; fila++) {
                char letra = auxTablero[fila][columna].getficha().getLetra();

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

    private void CopiarContenidoTablero(Casilla[][] AuxTablero,Casilla[][] tablero){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                // Crear una nueva instancia de Casilla con el mismo multiplicador
                AuxTablero[i][j] = new Casilla(tablero[i][j].getMultiplicador());
                // Copiar la ficha
                AuxTablero[i][j].setFicha(new Ficha(tablero[i][j].getficha().getLetra(),tablero[i][j].getficha().getPunto()));
            }
        }
    }

    public void ObtenerPuntos(String entrada, int fila, int columna, boolean esHorizontal, Jugador jugador) {
        int puntos = 0;
        int multiplicadorPalabra = 1;
        for (int i = 0; i < entrada.length(); i++) {
            if (esHorizontal) {
                puntos = puntos + tablero[fila][columna + i].getficha().getPunto();
                if (tablero[fila][columna + i].getPalabraMultiplicador()){
                    multiplicadorPalabra = multiplicadorPalabra + tablero[fila][columna + i].getMultiplicador();
                }
            } else {
                puntos = puntos + tablero[fila + i][columna].getficha().getPunto();
                if (tablero[fila + i][columna].getPalabraMultiplicador()){
                    if (multiplicadorPalabra == 1){
                        multiplicadorPalabra = tablero[fila + i][columna].getMultiplicador();
                    } else {
                        multiplicadorPalabra = multiplicadorPalabra + tablero[fila + i][columna].getMultiplicador();
                    }
                }
            }
        }
        puntos = puntos * multiplicadorPalabra;
        jugador.SumarPuntos(puntos);
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

    public void SetObservador(Observador observador){
        observadores.add(observador);
    }

    @Override
    public void notificar() {
        cambioDeTurno();
        for (Observador observador : observadores){
            observador.update();
        }
    }

    private void cambioDeTurno() {
        if (turno == CantDeJugadores){
            this.turno = 1;
        } else {
            this.turno++;
        }
    }

    public void setEsPrimera(boolean b) {
        this.primeraPalabra = b;
    }

    public void aumentarPasoTurno() {
        this.pasoTurnosSeguidos++;
    }

    public void setearPasoTurno(){
        this.pasoTurnosSeguidos = 0;
    }

    public int getPasoTurnosSeguidos(){
        return pasoTurnosSeguidos;
    }

    public int getCantDeJugadores(){
        return CantDeJugadores;
    }


    public void CalcularGanador(int puntos, int idTurno) {
        PuntajeJugadores[idTurno-1] = puntos;
    }

    public int getGanador() {
        int indiceMaximo = 0;
        int valorMaximo = PuntajeJugadores[0];

        for (int i = 1; i < PuntajeJugadores.length; i++) {
            if (PuntajeJugadores[i] > valorMaximo) {
                valorMaximo = PuntajeJugadores[i];
                indiceMaximo = i;
            }
        }
        return indiceMaximo+1;
    }
}
