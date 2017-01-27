
package principal.entes;

import java.awt.Graphics;
import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;


public class Esqueleto extends Enemigo {
    
    private static HojaSprites hojaEsqueleto;
    
    public Esqueleto(final int idEnemigo, final String nombre, final int vidaMaxima, final double velocidad) {
        super(idEnemigo, nombre, vidaMaxima, velocidad);
        
        if(hojaEsqueleto == null){
            hojaEsqueleto = new HojaSprites(Constantes.RUTA_ENEMIGOS, Constantes.LADO_SPRITE, false);
        }
    }
    
    public void dibujar(final Graphics g, final int puntoX, final int puntoY){
        DibujoDebug.dibujarImagen(g, hojaEsqueleto.obtenerSprite(estado, direccion).obtenerImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
    
    
}
