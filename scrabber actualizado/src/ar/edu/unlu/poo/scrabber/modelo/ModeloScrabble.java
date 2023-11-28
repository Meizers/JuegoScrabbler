package ar.edu.unlu.poo.scrabber.modelo;

import ar.edu.unlu.poo.scrabber.vista.Observador;
import ar.edu.unlu.poo.scrabber.vista.SujetoObservable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ModeloScrabble implements SujetoObservable {
    private ArrayList<Observador> observadores;
    private Diccionario diccionario;
    private Tablero tablero;
    private ArrayList<Ficha> bolsaFichas = new ArrayList<>();
    private GestorDePuntajes gestorDePuntajes;
    private int turno;
    private final int CantDeJugadores;
    private boolean primeraPalabra;
    private int pasoTurnosSeguidos;

    public ModeloScrabble(int cantDeJugadores) {
        inicializarDiccionario();
        inicializarBolsaFichas();
        tablero = new Tablero();
        observadores = new ArrayList<>();
        this.turno = 1;
        this.CantDeJugadores = cantDeJugadores;
        gestorDePuntajes = new GestorDePuntajes(cantDeJugadores);
        this.primeraPalabra = true;
    }

    public boolean esPrimeraPalabra() {
        return primeraPalabra;
    }

    public Diccionario getDiccionario() {
        return diccionario;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ArrayList<Ficha> getBolsaFichas() {
        return bolsaFichas;
    }

    public int getTurno() {
        return turno;
    }

    public GestorDePuntajes getGestorDePuntajes(){
        return gestorDePuntajes;
    }

    public int getSumados(){
        return gestorDePuntajes.getSumados();
    }

    private void inicializarDiccionario() {
        diccionario = new Diccionario("C:/Users/Tomas Resnik/Documents/Unlu/Programacion orientada a objetos/scrabber/palabras.txt");
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
            if (!tablero.palabraPasaPorCentro(palabra,fila,columna,esHorizontal)){
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
                    this.primeraPalabra = false;
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
            char letraTablero = esHorizontal ? tablero.getCasillaTablero(fila,columna+i).getficha().getLetra() : tablero.getCasillaTablero(fila+i,columna).getficha().getLetra();
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
        return verificarColocar(entrada,palabra,fila,columna,esHorizontal);
    }

    private boolean verificarColocar(String entrada,ArrayList<Ficha> palabra, int fila, int columna, boolean esHorizontal) {
        Tablero auxTablero = new Tablero();
        CopiarContenidoTablero(auxTablero.getMatriz(),tablero.getMatriz()); // creo una copia para probar
        auxTablero.colocarPalabraTablero(entrada,palabra,fila,columna,esHorizontal); // coloco la palabra en la copia
        if (auxTablero.verificarConsistenciaTablero(this.diccionario)){ // verifico si en el tablero copia todo quedo consistente
            tablero.colocarPalabraTablero(entrada,palabra,fila,columna,esHorizontal); // si es asi, coloco la palabra en el original
            return true;
        } else {
            return false;
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
                puntos = puntos + tablero.getCasillaTablero(fila,columna + i).getficha().getPunto();
                if (tablero.getCasillaTablero(fila,columna + i).getPalabraMultiplicador()){
                    multiplicadorPalabra = multiplicadorPalabra + tablero.getCasillaTablero(fila,columna+i).getMultiplicador();
                }
            } else {
                puntos = puntos + tablero.getCasillaTablero(fila + i, columna).getficha().getPunto();
                if (tablero.getCasillaTablero(fila+i,columna).getPalabraMultiplicador()){
                    if (multiplicadorPalabra == 1){
                        multiplicadorPalabra = tablero.getCasillaTablero(fila+i, columna).getMultiplicador();
                    } else {
                        multiplicadorPalabra = multiplicadorPalabra + tablero.getCasillaTablero(fila+i, columna).getMultiplicador();
                    }
                }
            }
        }
        puntos = puntos * multiplicadorPalabra;
        jugador.SumarPuntos(puntos);
    }

    public void SetObservador(Observador observador){
        observadores.add(observador);
    }

    private void cambioDeTurno() {
        if (turno == CantDeJugadores){
            this.turno = 1;
        } else {
            this.turno++;
        }
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

    @Override
    public void notificar() {
        cambioDeTurno();
        for (Observador observador : observadores){
            observador.update();
        }
    }

    @Override
    public void terminoElJuego() {
        for (Observador observador : observadores){
            observador.mostrarGameOver();
        }
    }


}
