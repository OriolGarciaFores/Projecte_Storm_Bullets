
package principal.inventario.armas;

import java.awt.Rectangle;
import java.util.ArrayList;
import principal.Constantes;
import principal.entes.Jugador;


public class Francotirador extends Arma {
    
    
     public Francotirador(int id, String nombre, int ataque, boolean automatica, boolean penetrante, double ataquesPorSegundo) {
        super(id, nombre, ataque, automatica, penetrante, ataquesPorSegundo);
    }

     @Override
    public ArrayList<Rectangle> obtenerAlcance(final Jugador jugador) {
        final ArrayList<Rectangle> alcance = new ArrayList<>();

        final Rectangle alcance1 = new Rectangle();

        final int spritesAlcance = 8;
        //0 - abajo 1 - izquierda 2 - derecha 3 - arriba.
        //Abajo o arriba.
        if (jugador.obtenerDireccion() == 0 || jugador.obtenerDireccion() == 3) {
            alcance1.width = 1;
            alcance1.height = spritesAlcance * Constantes.LADO_SPRITE;
            alcance1.x = Constantes.CENTRO_VENTANA_X + 5;
            if (jugador.obtenerDireccion() == 0) {
                //ABAJO
                alcance1.y = Constantes.CENTRO_VENTANA_Y + 9;
            } else {
                //ARRIBA
                alcance1.y = Constantes.CENTRO_VENTANA_Y - 9 - alcance1.height;
            }
        } else {
            //IZQUIERDA O DERECHA.
            alcance1.width = spritesAlcance * Constantes.LADO_SPRITE;
            alcance1.height = 1;

            alcance1.y = Constantes.CENTRO_VENTANA_Y + 5;

            if (jugador.obtenerDireccion() == 1) {
                //IZQUIERDA
                alcance1.x = Constantes.CENTRO_VENTANA_X - alcance1.width;

            } else {
                //DERECHA
                alcance1.x = Constantes.CENTRO_VENTANA_X + 3;
            }

        }

        alcance.add(alcance1);

        return alcance;
    }

    @Override
    public Rectangle obtenerBala(Jugador jugador, ArrayList<Rectangle> alcance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
