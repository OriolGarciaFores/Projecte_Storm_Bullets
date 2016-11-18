
package principal.interfaz_usuario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;


public class MenuInferior {
    private Rectangle areaInventario;
    private Rectangle bordeAreaInventario;
    
    private Color negroDesaturado;
    private Color rojoOscuro;

    private static final BufferedImage img = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_AVATAR);
    private static final BufferedImage w = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_IMAGEN_W);
    private static final BufferedImage a = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_IMAGEN_A);
    private static final BufferedImage s = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_IMAGEN_S);
    private static final BufferedImage d = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_IMAGEN_D);
    private static final BufferedImage esc = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_IMAGEN_ESC);
    public MenuInferior(final Jugador jugador){
        
        int altoMenu = 64;
        areaInventario = new Rectangle(0, Constantes.ALTO_JUEGO - altoMenu, Constantes.ANCHO_JUEGO, altoMenu);
        bordeAreaInventario = new Rectangle(areaInventario.x, areaInventario.y - 1, areaInventario.width, 1);
        
        negroDesaturado = new Color(23, 23, 23);
        rojoOscuro = new Color(150, 0, 0);
    }
    
    public void dibujar(final Graphics g, final Jugador jugador){
        dibujarAreaInventario(g);
        dibujarBarraVitalidad(g, jugador);
        dibujarRanurasObjetos(g);
        dibujarPuntuaciones(g, jugador);
        dibujarAvatar(g);
        dibujarControles(g);
    }
    
    private void dibujarAreaInventario(final Graphics g){
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario, negroDesaturado);
        DibujoDebug.dibujarRectanguloRelleno(g, bordeAreaInventario, Color.white);
    }
    
    private void dibujarBarraVitalidad(final Graphics g, Jugador jugador){
        final int medidaVertical = 10;
        final int anchoTotal = 200;
       
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 60, areaInventario.y + medidaVertical, anchoTotal, medidaVertical, rojoOscuro);
        
        g.setColor(Color.white);
        DibujoDebug.dibujarString(g, "HP", areaInventario.x + 125, areaInventario.y + medidaVertical * 2);
        DibujoDebug.dibujarString(g, "Jugador", areaInventario.x + 5 , areaInventario.y + 54);
        DibujoDebug.dibujarString(g, jugador.obtenerVidaJugador(), areaInventario.x + 160, areaInventario.y + medidaVertical * 2);
       }
    
     private void dibujarPuntuaciones(final Graphics g, Jugador jugador){
        DibujoDebug.dibujarString(g, "Puntuación: " + jugador.obtenerPuntuacionJugador(), areaInventario.x + 60 , areaInventario.y + 35);
        DibujoDebug.dibujarString(g, "Mejor Puntuación: 0", areaInventario.x + 60 , areaInventario.y + 50);
    }
    
    private void dibujarRanurasObjetos(final Graphics g){
        final int anchoRanura = 32;
        final int numeroRanuras = 1;
        final int espacioRanuras = 10;
        final int anchoTotal = anchoRanura * numeroRanuras + espacioRanuras * numeroRanuras;
        final int xInicial = Constantes.ANCHO_JUEGO - anchoTotal;
        final int anchoRanuraYespacio = anchoRanura + espacioRanuras;
        
        g.setColor(Color.WHITE);
        for(int i = 0; i < numeroRanuras; i++){
            int xActual = xInicial + anchoRanuraYespacio * i - areaInventario.y;//530
            Rectangle ranura = new Rectangle(xActual - 140, areaInventario.y + 4, anchoRanura, anchoRanura);
            DibujoDebug.dibujarRectanguloRelleno(g, ranura);
            DibujoDebug.dibujarString(g, "Q", xActual - 130, areaInventario.y + 54);
        }
        
        
    }
    
     private void dibujarAvatar(final Graphics g){
        
        final int anchoRanura = 32;
        final int espacioRanura = 10;
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(img, anchoRanura, anchoRanura), espacioRanura, areaInventario.y + 4);
    }
    
    private void dibujarControles(final Graphics g){
        final int anchoTecla = 32;
        //Arreglar imagen a una resolucion pequeña y añadir mas teclas.
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(w, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 220, areaInventario.y + 10);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(a, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 180, areaInventario.y+ 10);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(s, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 140, areaInventario.y + 10);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(d, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 100, areaInventario.y + 10);
        DibujoDebug.dibujarString(g, "Movimientos", Constantes.ANCHO_JUEGO - 160, areaInventario.y + 55);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(esc, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 300, areaInventario.y + 10);
        DibujoDebug.dibujarString(g, "Menu o pause", Constantes.ANCHO_JUEGO - 330, areaInventario.y + 55);
    }
    
}
