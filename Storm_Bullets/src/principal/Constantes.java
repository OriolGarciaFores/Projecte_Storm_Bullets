
package principal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import principal.herramientas.CargadorRecursos;
import principal.reproductor.Musicas;

public class Constantes {
   public static final int LADO_SPRITE = 32; 
   public static final int LADO_TILE = 32;
   
   public static final int ANCHO_JUEGO = 896;//  896
   public static final int ALTO_JUEGO = 504;//  504
   
   private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
   public static int ANCHO_PANTALLA_COMPLETA = sizeScreen.width; //Resolucion del monitor.
   public static int ALTO_PANTALLA_COMPLETA = sizeScreen.height; //Resolucion del monitor.
   
   public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
   public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;
   
   public static final int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
   public static final int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;
   
   
   public static final String RUTA_MAPA = "/texto/prueba.map";
   public static final String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/player.png";
   public static final String RUTA_PORTADA = "/imagenes/fondos/portada.jpg";
   public static final String RUTA_AUDIO_TITULO = "/audio/musica_titulo.mp3";
   public static final String RUTA_AVATAR ="/imagenes/fondos/avatar.jpg";
   public static final String RUTA_TITOL = "/imagenes/fondos/titol.png";
   
   
   //Imagenes de los controles del juego.
   public static final String RUTA_IMAGEN_W = "/imagenes/fondos/w.jpg";
   public static final String RUTA_IMAGEN_A = "/imagenes/fondos/a.jpg";
   public static final String RUTA_IMAGEN_S = "/imagenes/fondos/s.jpg";
   public static final String RUTA_IMAGEN_D = "/imagenes/fondos/d.jpg";
   public static final String RUTA_IMAGEN_ESC = "/imagenes/fondos/esc2.jpg";
   
   public static Musicas m = new Musicas();
   
   public static Font FUENTE_ALMOSNOW = CargadorRecursos.cargarFuente("/fuentes/almosnow.ttf");
}
