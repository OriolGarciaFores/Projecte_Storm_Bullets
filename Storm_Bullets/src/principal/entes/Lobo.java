
package principal.entes;

import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;


public class Lobo extends Enemigo {
    
       private static HojaSprites hojaLobo;
    
    public Lobo(final int idEnemigo, final String nombre, final int vidaMaxima, final double velocidad, final int puntos) {
        super(idEnemigo, nombre, vidaMaxima, velocidad, puntos);
        
        if(hojaLobo == null){
            hojaLobo = new HojaSprites(Constantes.RUTA_LOBO, 61,47, false);
        }
    }
    
        @Override
    public Rectangle obtenerArea() {
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX, puntoY, 61, 47);
    }
    
    public void dibujar(final Graphics g, final int puntoX, final int puntoY){
        DibujoDebug.dibujarImagen(g, hojaLobo.obtenerSprite(estado, direccion).obtenerImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
}
