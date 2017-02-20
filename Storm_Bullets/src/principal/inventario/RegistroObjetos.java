
package principal.inventario;

import principal.inventario.armas.Desarmado;
import principal.inventario.armas.Pistola;
import principal.inventario.consumibles.Consumible;



public class RegistroObjetos {
    
    public static Objeto obtenerObjeto(final int idObjeto){
        
        Objeto objeto = null;
        
        switch(idObjeto){
            //0-499 objetos consumibles
            case 0:
              objeto = new Consumible(idObjeto, "Llave");
                break;
            case 1:
              objeto = new Consumible(idObjeto, "Llave Boss");
                break;
                //500-599 armas.
            case 500:
                objeto = new Pistola(idObjeto, "Pistola", 1, 3);
                break;
            case 599:
                objeto = new Desarmado(idObjeto, "Desarmado", 0, 0);
                break;
        }
        
        return objeto;

}

    
}
