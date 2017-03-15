
package principal;

import java.util.ArrayList;
import principal.entes.Jugador;
import principal.inventario.Inventario;
import principal.mapas.DatosMapas;
import principal.mapas.MapaTiled;
import principal.reproductor.Musicas;


public class ElementosPrincipales {
    public static ArrayList<DatosMapas> datosMapa = new ArrayList<>();
    public static MapaTiled mapa = new MapaTiled(Constantes.RUTA_MAPA);
    
    //public static Mapa mapa = new Mapa(Constantes.RUTA_MAPA);
    public static Jugador jugador = new Jugador();
    public static Inventario inventario = new Inventario();
    public static Musicas m = new Musicas();
    public static Musicas musicaIngame = new Musicas();
  
}
