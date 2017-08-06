
package principal.entes;

import java.awt.Graphics;
import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;


public class Lobo extends Enemigo {
    
       private static HojaSprites hojaLobo;
    
    public Lobo(final int idEnemigo, final String nombre, final int vidaMaxima, final double velocidad, final int puntos) {
        super(idEnemigo, nombre, vidaMaxima, velocidad, puntos);
        
        if(hojaLobo == null){
            hojaLobo = new HojaSprites(Constantes.RUTA_LOBO, Constantes.LADO_SPRITE, false);
        }
    }
    
    public void dibujar(final Graphics g, final int puntoX, final int puntoY){
        DibujoDebug.dibujarImagen(g, hojaLobo.obtenerSprite(estado, direccion).obtenerImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
}
