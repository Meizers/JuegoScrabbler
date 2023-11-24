package ar.edu.unlu.poo.scrabber.vista;

import ar.edu.unlu.poo.scrabber.controlador.Controlador;
import ar.edu.unlu.poo.scrabber.modelo.Jugador;
import ar.edu.unlu.poo.scrabber.vista.Flujo.Flujo;
import ar.edu.unlu.poo.scrabber.vista.Flujo.FlujoMenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaConsola implements  IVista,Observador {
    private final JFrame frame;
    private JTextArea textSalidaConsola;
    private JPanel menu;
    private JTextField textEntrada;
    private JButton btnEnter;
    private Flujo FlujoActual;


    public VistaConsola() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(menu);
        frame.pack();
        textSalidaConsola.setFont(new Font("Monospaced", Font.PLAIN, 27));

        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textSalidaConsola.append(textEntrada.getText() + "\n");
                ProcesarEntrada(textEntrada.getText());
                textEntrada.setText("");
            }
        });
    }

    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }


    @Override
    public void imprimirTablero() {
        textSalidaConsola.append("    ");
        for (int k = 0; k < 9; k++){
            textSalidaConsola.append(k + "  ");
        }
        for (int k = 9; k < 15; k++){
            textSalidaConsola.append(k + " ");
        }
        textSalidaConsola.append("\n");
        for (int i = 0; i < 15; i++) {
            if (i < 10){
                textSalidaConsola.append(i + "  ");
            } else {
                textSalidaConsola.append(i + " ");
            }
            for (int j = 0; j < 15; j++) {
                textSalidaConsola.append("[" + controlador.BuscarTablero()[i][j].toString() + "]");
                if (j < controlador.BuscarTablero()[i].length - 1) {
                    textSalidaConsola.append("");
                }
            }
            textSalidaConsola.append("\n");

        }
    }

    @Override
    public void imprimirSoporte(Jugador jugador) {
        textSalidaConsola.append("Tu soporte: " + jugador.getSoporteJugador());
    }

    @Override
    public void MostrarMenuPrincipal(Jugador jugador) {
        if (!controlador.gameOver()){
            mostrar();
            FlujoActual = new FlujoMenuPrincipal(this,controlador);
            FlujoActual.mostrarSiguienteTexto();
        } else {
            textSalidaConsola.setText("");
            controlador.EnviarPuntaje();
            textSalidaConsola.append("\nJuego Termina por pasos De turnos seguidos, El ganador es el jugador numero " + controlador.Ganador());
        }
    }

    private void ProcesarEntrada(String input) {
        if (controlador.EsMiTurno()){
            input = input.trim();
            if (input.isEmpty())
                return;
            FlujoActual = FlujoActual.procesarEntrada(input);
            FlujoActual.mostrarSiguienteTexto();
        }else {
            textSalidaConsola.append("Aun no es tu turno");
        }
    }

    public void println(String texto) {
        textSalidaConsola.append(texto + "\n");
    }


    public void mostrar(){
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void update() {
        textSalidaConsola.setText("");
        MostrarMenuPrincipal(controlador.getJugador());
    }
}
