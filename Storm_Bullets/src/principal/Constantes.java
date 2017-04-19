package principal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import principal.herramientas.CargadorRecursos;
import principal.reproductor.Sonido;

public class Constantes {
   public static final int LADO_SPRITE = 32; 
   public static final int LADO_TILE = 32;
   
   public static final int ANCHO_JUEGO = 896;//  896
   public static final int ALTO_JUEGO = 504;// 504
   
   private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
   public static int ANCHO_PANTALLA_COMPLETA = sizeScreen.width; //Resolucion del monitor.
   public static int ALTO_PANTALLA_COMPLETA = sizeScreen.height; //Resolucion del monitor.
   
   public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
   public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;
   
   public static final int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
   public static final int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;

   public static int MARGEN_X = ANCHO_JUEGO / 2 - LADO_SPRITE / 2;
   public static int MARGEN_Y = ALTO_JUEGO / 2 - LADO_SPRITE / 2;

   //MAPAS
   public static final String RUTA_MAPA = "/texto/mapa1.csv";
   public static final String RUTA_MAPA2 = "/texto/mapa2.csv";
   public static final String RUTA_MAPA3 = "/texto/mapa3.csv";
   public static final String RUTA_MAPA4 = "/texto/mapa4.csv";
   public static final String RUTA_MAPA5 = "/texto/mapa5.csv";
   public static final String RUTA_MAPA6 = "/texto/mapa6.csv";
   public static final String RUTA_MAPA7 = "/texto/mapa7.csv";
   public static final String RUTA_MAPA_BOSS = "/texto/mapa_boss.csv";
   public static final String RUTA_MAPA_SALIDA = "/texto/mapa_salida.csv";
   //FI MAPAS.
   
   public static final String RUTA_BALA = "/imagenes/hojasObjetos/bala.png";
   public static final String RUTA_CANDADO = "/imagenes/hojasTexturas/candado.png";
   
   public static final String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/pj.png";
   public static final String RUTA_PORTADA = "/imagenes/fondos/portada.jpg";
   public static final String RUTA_AVATAR ="/imagenes/fondos/avatar.jpg";
   public static final String RUTA_TITOL = "/imagenes/fondos/titol.png";
   
   //IMAGENES DE OBJETOS
   public static final String RUTA_CONSUMIBLES = "/imagenes/hojasObjetos/consumibles.png";
   public static final String RUTA_ARMAS = "/imagenes/hojasObjetos/armas.png";
   
   //Enemigos
   public static String RUTA_ENEMIGOS = "/imagenes/hojasPersonajes/esqueleto.png";
   public static String RUTA_SLIME = "/imagenes/hojasPersonajes/slime.png";
   public static String RUTA_FANTASMA = "/imagenes/hojasPersonajes/fantasma.png";
   
   //Imagenes de los controles del juego.
   public static final String RUTA_IMAGEN_W = "/imagenes/fondos/w.jpg";
   public static final String RUTA_IMAGEN_A = "/imagenes/fondos/a.jpg";
   public static final String RUTA_IMAGEN_S = "/imagenes/fondos/s.jpg";
   public static final String RUTA_IMAGEN_D = "/imagenes/fondos/d.jpg";
   public static final String RUTA_IMAGEN_ESC = "/imagenes/fondos/esc2.jpg";
   public static final String RUTA_IMAGEN_E = "/imagenes/fondos/e.jpg";
   
    
   public static Font FUENTE_PIXEL = CargadorRecursos.cargarFuente("/fuentes/FreePixel.ttf");
   
   public static int segundos = 0;
   public static int minutos = 0;
   
   
   //SONIDOS.
   
   public static final Sonido disparo_pistola = new Sonido("/audio/disparo_pistola.wav");
   public static final Sonido disparo_rifleAsalto = new Sonido("/audio/AK.wav");
   public static final Sonido disparo_francotirador = new Sonido("/audio/disparo_francotirador.wav");
   public static final Sonido grito_perderVida = new Sonido("/audio/grito.wav");
   
   
   //MUSICA FONDO.
   
   public static final Sonido MUSICA_TITULO = new Sonido("/audio/musica_titulo.wav");
   public static final Sonido MUSICA_INGAME = new Sonido("/audio/musica_ingame.wav");
   public static final Sonido MUSICA_GAME_OVER = new Sonido("/audio/gameOver.wav");
   
}
