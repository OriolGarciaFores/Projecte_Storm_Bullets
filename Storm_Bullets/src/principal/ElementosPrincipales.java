
package principal;

import java.util.ArrayList;
import principal.entes.Jugador;
import principal.inventario.Inventario;
import principal.mapas.DatosMapas;
import principal.mapas.MapaTiled;


public class ElementosPrincipales {
    public static ArrayList<DatosMapas> datosMapa = new ArrayList<>();
    public static MapaTiled mapa = new MapaTiled(Constantes.RUTA_MAPA);
    
    public static Jugador jugador = new Jugador();
    public static Inventario inventario = new Inventario(); 
}
