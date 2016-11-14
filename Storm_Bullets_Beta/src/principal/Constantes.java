
package principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import principal.reproductor.Musicas;

public class Constantes {
   public static final int LADO_SPRITE = 32; 
   public static final int LADO_TILE = 32;
   
   public static final int ANCHO_JUEGO = 896;//850  896
   public static final int ALTO_JUEGO = 504;//480  504
   
   private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
   public static int ANCHO_PANTALLA_COMPLETA = sizeScreen.width; //Resolucion del monitor.
   public static int ALTO_PANTALLA_COMPLETA = sizeScreen.height; //Resolucion del monitor.
   
   public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
   public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;
   
   public static final int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
   public static final int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;
   
   
   public static final String RUTA_MAPA = "/texto/prueba.map";
   public static final String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/player.png";
   public static final String RUTA_PORTADA = "/imagenes/fondos/portada2.jpg";
   public static final String RUTA_AUDIO_TITULO = "/audio/musica_titulo.mp3";
   public static final String RUTA_AVATAR ="/imagenes/fondos/avatar.jpg";
   public static final String RUTA_IMAGEN_CONTROLES = "/imagenes/fondos/wasd.png";
   public static final String RUTA_IMAGEN_ESC = "/imagenes/fondos/esc.jpg";
   
   public static Musicas m = new Musicas();
   
}
