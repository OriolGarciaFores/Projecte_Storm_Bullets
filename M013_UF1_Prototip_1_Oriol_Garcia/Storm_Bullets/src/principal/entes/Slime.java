
package principal.entes;

import java.awt.Graphics;
import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;


public class Slime extends Enemigo {
    
     private static HojaSprites hojaSlime;
    
     public Slime(final int idEnemigo, final String nombre, final int vidaMaxima, final double velocidad) {
        super(idEnemigo, nombre, vidaMaxima, velocidad);
        
        if(hojaSlime == null){
            hojaSlime = new HojaSprites(Constantes.RUTA_SLIME, Constantes.LADO_SPRITE, false);
        }
    }
    
    public void dibujar(final Graphics g, final int puntoX, final int puntoY){
        DibujoDebug.dibujarImagen(g, hojaSlime.obtenerSprite(estado, direccion).obtenerImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
    
}
