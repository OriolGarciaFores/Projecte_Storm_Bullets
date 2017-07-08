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
    public static final String RUTA_MAPA_HIELO = "/texto/mapa_hielo.csv";
    //FI MAPAS.

    public static final String RUTA_BALA = "/imagenes/hojasObjetos/bala.png";
    public static final String RUTA_CANDADO = "/imagenes/hojasTexturas/candado.png";

    public static final String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/pj.png";
    public static final String RUTA_PORTADA = "/imagenes/fondos/portada.jpg";
    public static final String RUTA_AVATAR = "/imagenes/fondos/avatar.jpg";
    public static final String RUTA_TITOL = "/imagenes/fondos/titol.png";
    public static final String RUTA_SANGRE = "/imagenes/fondos/sangre.png";

    //IMAGENES DE OBJETOS
    public static final String RUTA_CONSUMIBLES = "/imagenes/hojasObjetos/consumibles.png";
    public static final String RUTA_ARMAS = "/imagenes/hojasObjetos/armas.png";

    //Enemigos
    public static String RUTA_ENEMIGOS = "/imagenes/hojasPersonajes/esqueleto.png";
    public static String RUTA_SLIME = "/imagenes/hojasPersonajes/slime.png";
    public static String RUTA_FANTASMA = "/imagenes/hojasPersonajes/fantasma.png";
    public static String RUTA_DEMONIO = "/imagenes/hojasPersonajes/boss.png";

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
    public static final Sonido bola_fuego = new Sonido("/audio/bolaFuego.wav");
    public static final Sonido lanzallamas = new Sonido("/audio/lanzallamas.wav");

    //MUSICA FONDO.
    public static final Sonido MUSICA_TITULO = new Sonido("/audio/musica_titulo.wav");
    public static final Sonido MUSICA_INGAME = new Sonido("/audio/musica_ingame.wav");
    public static final Sonido MUSICA_GAME_OVER = new Sonido("/audio/gameOver.wav");

    //HABILIDADES
    public static final String BOLA_FUEGO = "/imagenes/hojasObjetos/bola_fuego.png";

    //CLAVES DE PROPIEDADES SETTINGS
    public static final String KEY_IDIOMA = "idioma";
    public static final String KEY_MUSICA_VOLUMEN = "musica_volumen";
    public static final String KEY_MUSICA_PORCENTAJE = "musica_porcentaje";
    public static final String KEY_SONIDO_VOLUMEN = "sonido_volumen";
    public static final String KEY_SONIDO_PORCENTAJE = "sonido_porcentaje";

    //CLAVES DE PROPIEDADES IDIOMAS 
    public static final String KEY_JUGAR = "jugar";
    public static final String KEY_RANKING = "ranking";
    public static final String KEY_OPCIONES = "opciones";
    public static final String KEY_SALIR = "salir";
    public static final String KEY_ESPANOL = "espanol";
    public static final String KEY_INGLES = "ingles";
    public static final String KEY_MUSICA = "musica";
    public static final String KEY_SONIDO = "sonido";
    public static final String KEY_VOLVER = "volver";
    public static final String KEY_TEXT_IDIOMA = "titulo_idioma";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_PUNTUACION = "puntuacion";
    public static final String KEY_MEJOR_PUNTUACION = "mejor_puntuacion";
    public static final String KEY_MOVIMIENTO = "movimiento";
    public static final String KEY_PAUSE = "pause";
    public static final String KEY_ESPACIO = "espacio";
    public static final String KEY_TIEMPO = "tiempo";
    public static final String KEY_RECOGER = "recoger";
    public static final String KEY_CATALAN = "catalan";
    

}
