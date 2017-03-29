
package principal.inventario;

import principal.inventario.armas.Desarmado;
import principal.inventario.armas.Pistola;
import principal.inventario.armas.RifleAsalto;
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
            case 2:
                objeto = new Consumible(idObjeto, "Botiquin");
                break;
                //500-599 armas.
            case 500:
                objeto = new Pistola(idObjeto, "Pistola", 5, false, false, 30);
                break;
            case 501:
                objeto = new RifleAsalto(idObjeto, "Rifle Asalto", 5, false, false, 5);
                break;
            case 502:
                //FRANCOTIRADOR.
                break;
            case 599:
                objeto = new Desarmado(idObjeto, "Desarmado", 0, false, false, 0);
                break;
        }
        
        return objeto;

}

    
}
