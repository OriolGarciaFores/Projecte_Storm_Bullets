
package principal.interfaz_usuario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.guardar_partida.Top;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.inventario.Objeto;


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
    private static final BufferedImage e = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_IMAGEN_E);

    public MenuInferior() {

        int altoMenu = 64;
        areaInventario = new Rectangle(0, Constantes.ALTO_JUEGO - altoMenu, Constantes.ANCHO_JUEGO, altoMenu);
        bordeAreaInventario = new Rectangle(areaInventario.x, areaInventario.y - 1, areaInventario.width, 1);

        negroDesaturado = new Color(23, 23, 23);
        rojoOscuro = new Color(150, 0, 0);
    }

    public void dibujar(final Graphics g) {
        dibujarAreaInventario(g);
        dibujarBarraVitalidad(g);
        dibujarRanurasObjetos(g);
        dibujarPuntuaciones(g);
        dibujarAvatar(g);
        dibujarControles(g);
        dibujarElementosEquipables(g);
        dibujarTiempo(g);
    }

    private void dibujarAreaInventario(final Graphics g) {
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario, negroDesaturado);
        DibujoDebug.dibujarRectanguloRelleno(g, bordeAreaInventario, Color.white);
    }

    private void dibujarBarraVitalidad(final Graphics g) {
        final int medidaVertical = 10;
        final int anchoTotal = 1000;

        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 60, areaInventario.y + medidaVertical, anchoTotal * Integer.parseInt(ElementosPrincipales.jugador.obtenerVidaJugador()) / 300, medidaVertical, rojoOscuro);

        g.setColor(Color.white);
        DibujoDebug.dibujarString(g, "HP", areaInventario.x + 125, areaInventario.y + medidaVertical * 2 - 1, 12);
        DibujoDebug.dibujarString(g, ElementosPrincipales.jugador.getNomJugador(), areaInventario.x + 5, areaInventario.y + 54, 12);
        DibujoDebug.dibujarString(g, ElementosPrincipales.jugador.obtenerVidaJugador(), areaInventario.x + 160, areaInventario.y + medidaVertical * 2 - 1, 12);
    }

    private void dibujarPuntuaciones(final Graphics g) {
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_PUNTUACION) + ": " + ElementosPrincipales.jugador.obtenerPuntuacionJugador(), areaInventario.x + 60, areaInventario.y + 35, 12);
        try{
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_MEJOR_PUNTUACION) + ": " + Top.partidas.get(0).obtenerPuntuacion(), areaInventario.x + 60, areaInventario.y + 50, 12);
        } catch(Exception ex){
            System.out.println("No hay mejor puntuacion.");
            DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_MEJOR_PUNTUACION)  + ": 0", areaInventario.x + 60, areaInventario.y + 50, 12);
        }
    }
    
    private void dibujarTiempo(final Graphics g){
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_TIEMPO) + ": " + Constantes.minutos + ":" + Constantes.segundos, areaInventario.x + 180, areaInventario.y + 35, 12);      
    }

    private void dibujarRanurasObjetos(final Graphics g) {
        if (ElementosPrincipales.inventario.obtenerConsumibles().isEmpty()) {
            return;
        }
        final int anchoRanura = 10;
        final int numeroRanuras = ElementosPrincipales.inventario.obtenerConsumibles().size() - 1;//Se resta el ultimo objeto que es el botiquin para no dibujarlo.
        final int espacioRanuras = 10;
        final int anchoTotal = anchoRanura * numeroRanuras + espacioRanuras * numeroRanuras;
        final int xInicial = Constantes.ANCHO_JUEGO - anchoTotal;
        final int anchoRanuraYespacio = anchoRanura + espacioRanuras;

        for (int i = 0; i < numeroRanuras; i++) {
            int xActual = xInicial + anchoRanuraYespacio * i - areaInventario.y;//530
            DibujoDebug.dibujarImagen(g, ElementosPrincipales.inventario.obtenerConsumibles().get(i).obtenerSprite().obtenerImagen(), xActual - 140, areaInventario.y + 4);
            String cantidad = "" + ElementosPrincipales.inventario.obtenerConsumibles().get(i).obtenerCantidad();
            DibujoDebug.dibujarString(g, cantidad, xActual - 125, areaInventario.y + 40, 10);

        }

    }

    private void dibujarElementosEquipables(final Graphics g) {

        if (ElementosPrincipales.inventario.obtenerArmas().isEmpty()) {
            return;
        }

        final int anchoRanura = 10;
        final int numeroRanuras = 1;
        final int espacioRanuras = 10;
        final int anchoTotal = anchoRanura * numeroRanuras + espacioRanuras * numeroRanuras;
        final int xInicial = Constantes.ANCHO_JUEGO - anchoTotal;
        final int anchoRanuraYespacio = anchoRanura + espacioRanuras;

        for (int i = 0; i < numeroRanuras; i++) {
            int xActual = xInicial + anchoRanuraYespacio * i - areaInventario.y;//530
            
            int idActual = ElementosPrincipales.jugador.obtenerAlmacenEquipo().obtenerArma().obtenerId();
            Objeto objetoActual = ElementosPrincipales.inventario.obtenerObjeto(idActual);

            
            DibujoDebug.dibujarImagen(g, objetoActual.obtenerSprite().obtenerImagen(), xActual - 40, areaInventario.y + 4);
            
            DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_ESPACIO), xActual - 40,  areaInventario.y + 40 , 12);
            

        }

    }

    private void dibujarAvatar(final Graphics g) {

        final int anchoRanura = 32;
        final int espacioRanura = 10;
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(img, anchoRanura, anchoRanura), espacioRanura, areaInventario.y + 4);
    }

    private void dibujarControles(final Graphics g) {
        final int anchoTecla = 32;
        //Arreglar imagen a una resolucion pequeña y añadir mas teclas.
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(w, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 220, areaInventario.y + 10);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(a, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 180, areaInventario.y + 10);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(s, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 140, areaInventario.y + 10);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(d, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 100, areaInventario.y + 10);
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_MOVIMIENTO), Constantes.ANCHO_JUEGO - 160, areaInventario.y + 55, 12);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(esc, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 300, areaInventario.y + 10);
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_PAUSE) + "", Constantes.ANCHO_JUEGO - 310, areaInventario.y + 55, 12);
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(e, anchoTecla, anchoTecla), Constantes.ANCHO_JUEGO - 360, areaInventario.y + 10);
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_RECOGER) + "", Constantes.ANCHO_JUEGO - 360, areaInventario.y + 55, 12);
       // DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(espacio, 128, anchoTecla), Constantes.ANCHO_JUEGO - 500, areaInventario.y + 20);
       // DibujoDebug.dibujarString(g, "Disparar", Constantes.ANCHO_JUEGO - 420, areaInventario.y + 55, 12);
    }
    
}
