
package principal;

import principal.reproductor.Musicas;

public class Constantes {
   public static final int LADO_SPRITE = 32; 
   public static final int LADO_TILE = 32;
   
   public static final int ANCHO_VENTANA = 800;
   public static final int ALTO_VENTANA = 600;
   
   public static final int CENTRO_VENTANA_X = ANCHO_VENTANA / 2;
   public static final int CENTRO_VENTANA_Y = ALTO_VENTANA / 2;
   
   
   public static final String RUTA_MAPA = "/texto/prueba.map";
   public static final String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/player.png";
   public static final String RUTA_PORTADA = "/imagenes/fondos/portada.jpg";
   public static final String RUTA_AUDIO_TITULO = "/audio/musica_titulo.mp3";
   
   public static Musicas m = new Musicas();
   
}
