
package principal;

import principal.entes.Jugador;
import principal.mapas.Mapa;
import principal.reproductor.Musicas;


public class ElementosPrincipales {
    
    public static Mapa mapa = new Mapa(Constantes.RUTA_MAPA);
    public static Jugador jugador = new Jugador(160, 170);
    public static Musicas m = new Musicas();
    public static Musicas musicaIngame = new Musicas();
    
}
