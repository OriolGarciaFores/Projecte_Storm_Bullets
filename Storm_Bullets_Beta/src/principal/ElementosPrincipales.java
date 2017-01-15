
package principal;

import principal.entes.Jugador;
import principal.mapas.MapaTiled;
import principal.reproductor.Musicas;


public class ElementosPrincipales {
    //CODIGO PRUEBAS
    public static MapaTiled mapa = new MapaTiled(Constantes.RUTA_MAPA);
    
    //public static Mapa mapa = new Mapa(Constantes.RUTA_MAPA);
    public static Jugador jugador = new Jugador();
    public static Musicas m = new Musicas();
    public static Musicas musicaIngame = new Musicas();
    
}
