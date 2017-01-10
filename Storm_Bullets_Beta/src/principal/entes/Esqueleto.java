
package principal.entes;

import java.awt.Graphics;
import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;


public class Esqueleto extends Enemigo {
    
    private static HojaSprites hojaEsqueleto;
    
    public Esqueleto(final int idEnemigo, final String nombre, final int vidaMaxima) {
        super(idEnemigo, nombre, vidaMaxima);
        
        if(hojaEsqueleto == null){
            hojaEsqueleto = new HojaSprites(Constantes.RUTA_ENEMIGOS, Constantes.LADO_SPRITE, false);
        }
    }
    
    public void dibujar(final Graphics g, final int puntoX, final int puntoY){
        DibujoDebug.dibujarImagen(g, hojaEsqueleto.obtenerSprite(0).obtenerImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
    
}
