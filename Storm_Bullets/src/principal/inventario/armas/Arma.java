
package principal.inventario.armas;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import principal.Constantes;
import principal.entes.Jugador;
import principal.inventario.Objeto;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;


public abstract class Arma extends Objeto {

    public static HojaSprites hojaArmas = new HojaSprites(Constantes.RUTA_ARMAS, Constantes.LADO_SPRITE, false);
    
    protected int ataqueMin;
    protected int ataqueMax;
    
    public Arma(int id, String nombre, int ataqueMin, int ataqueMax) {
        super(id, nombre);
        
        this.ataqueMin = ataqueMin;
        this.ataqueMax = ataqueMax;
    }

    public Arma(int id, String nombre, int cantidad, int ataqueMin, int ataqueMax) {
        super(id, nombre, cantidad);
        
        this.ataqueMin = ataqueMin;
        this.ataqueMax = ataqueMax;
    }
    
    public abstract ArrayList<Rectangle> obtenerAlcance(final Jugador jugador);

    @Override
    public Sprite obtenerSprite() {
        return hojaArmas.obtenerSprite(id-500);//Se resta porque las armas van de la id 500 a 599.
    }
    
    protected int obtenerAtaqueMedio(final int ataqueMin, final int ataqueMax){
        Random r = new Random();
        
        return r.nextInt(ataqueMax - ataqueMin) + ataqueMin;
    }
    
    
    
}
