
package principal;

import principal.entes.Jugador;
import principal.mapas.MapaTiled;
import principal.reproductor.Musicas;


public class ElementosPrincipales {
   
    public static MapaTiled mapa = new MapaTiled(Constantes.RUTA_MAPA3);
    
    //public static Mapa mapa = new Mapa(Constantes.RUTA_MAPA);
    public static Jugador jugador = new Jugador();
    public static Musicas m = new Musicas();
    public static Musicas musicaIngame = new Musicas();
    
}