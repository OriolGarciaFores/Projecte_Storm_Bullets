
package principal.inventario.consumibles;

import principal.Constantes;
import principal.inventario.Objeto;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;


public class Consumible extends Objeto {
    
    public static HojaSprites hojaConsumibles = new HojaSprites(Constantes.RUTA_LLAVES, Constantes.LADO_SPRITE, false);

    public Consumible(int id, String nombre) {
        super(id, nombre);
    }

    public Consumible(int id, String nombre, int cantidad) {
        super(id, nombre, cantidad);
    }

    @Override
    public Sprite obtenerSprite() {
        return hojaConsumibles.obtenerSprite(id);
    }
    
}
