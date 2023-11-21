package ar.edu.unlu.poo.scrabber.modelo;

public class Casilla {
    private Ficha ficha = new Ficha(' ',1);
    private int multiplicador;
    private boolean palabraMultiplicador;
    private boolean ocupada = false;

    public Casilla(int multuplicador){
        this.multiplicador = multiplicador;
        this.palabraMultiplicador = false;
    }

    public void setMultiplicadoPalabra(boolean palabraMultiplicador) {
        this.palabraMultiplicador = palabraMultiplicador;
    }

        public boolean getPalabraMultiplicador(){
        return this.palabraMultiplicador;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Ficha getficha() {
        return ficha;
    }

    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }

    public int getMultiplicador() {
        return multiplicador;
    }

    public void setOcupada() {
        this.ocupada = true;
    }

    @Override
    public String toString() {
        if (!ocupada && palabraMultiplicador) {
            return "x" + multiplicador;
        } else if(!ocupada){
            return "    ";
        } else {
            return " " + this.ficha + " " ;
        }
    }
}
