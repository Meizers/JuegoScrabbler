import ar.edu.unlu.poo.scrabber.controlador.Controlador;
import ar.edu.unlu.poo.scrabber.modelo.Jugador;
import ar.edu.unlu.poo.scrabber.modelo.ModeloScrabble;
import ar.edu.unlu.poo.scrabber.vista.IVista;
import ar.edu.unlu.poo.scrabber.vista.VistaConsola;


public class Scrabble {
    public static void main(String[] args) {

        ModeloScrabble modelo = new ModeloScrabble(4);

        Jugador jugador1 = new Jugador("tomas",1);
        Jugador jugador2 = new Jugador("lara",2);
        Jugador jugador3 = new Jugador("cj",3);
        Jugador jugador4 = new Jugador("kevin",4);

        jugador1.inicializarSoporteJugador(modelo.getBolsaFichas());
        jugador2.inicializarSoporteJugador(modelo.getBolsaFichas());
        jugador3.inicializarSoporteJugador(modelo.getBolsaFichas());
        jugador4.inicializarSoporteJugador(modelo.getBolsaFichas());

        IVista vista = new VistaConsola();
        IVista vista2 = new VistaConsola();
        IVista vista3 = new VistaConsola();
        IVista vista4 = new VistaConsola();

        modelo.SetObservador(vista);
        modelo.SetObservador(vista2);
        modelo.SetObservador(vista3);
        modelo.SetObservador(vista4);

        Controlador controlador = new Controlador(vista,jugador1);
        Controlador controlador2 = new Controlador(vista2,jugador2);
        Controlador controlador3 = new Controlador(vista3,jugador3);
        Controlador controlador4 = new Controlador(vista4,jugador4);

        controlador.setModelo(modelo);
        controlador2.setModelo(modelo);
        controlador3.setModelo(modelo);
        controlador4.setModelo(modelo);

        vista.MostrarMenuPrincipal(jugador1);
        vista2.MostrarMenuPrincipal(jugador2);
        vista3.MostrarMenuPrincipal(jugador3);
        vista4.MostrarMenuPrincipal(jugador4);
    }
}