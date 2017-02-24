
package principal.inventario.armas;

import java.awt.Rectangle;
import java.util.ArrayList;
import principal.entes.Jugador;


public class Desarmado extends Arma {

    public Desarmado(int id, String nombre, int ataqueMin, int ataqueMax, boolean automatica, boolean penetrante, double ataquesPorSegundo) {
        super(id, nombre, ataqueMin, ataqueMax, automatica, penetrante, ataquesPorSegundo);
    }

    
    @Override
    public ArrayList<Rectangle> obtenerAlcance(final Jugador jugador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
