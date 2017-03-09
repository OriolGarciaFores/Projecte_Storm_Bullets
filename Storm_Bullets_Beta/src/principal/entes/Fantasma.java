
package principal.entes;

import java.awt.Graphics;
import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;


public class Fantasma extends Enemigo {
    
      private static HojaSprites hojaFantasma;
    
    public Fantasma(final int idEnemigo, final String nombre, final int vidaMaxima, final double velocidad, final int puntos) {
        super(idEnemigo, nombre, vidaMaxima, velocidad, puntos);
        
        if(hojaFantasma == null){
            hojaFantasma = new HojaSprites(Constantes.RUTA_FANTASMA, Constantes.LADO_SPRITE, false);
        }
    }
    
    public void dibujar(final Graphics g, final int puntoX, final int puntoY){
        DibujoDebug.dibujarImagen(g, hojaFantasma.obtenerSprite(estado, direccion).obtenerImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
    
}
