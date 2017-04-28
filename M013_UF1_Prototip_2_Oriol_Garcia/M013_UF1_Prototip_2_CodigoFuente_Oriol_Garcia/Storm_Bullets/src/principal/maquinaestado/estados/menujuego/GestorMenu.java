package principal.maquinaestado.estados.menujuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.EstadoJuego;

public class GestorMenu implements EstadoJuego {

    private final EstructuraMenu estructuraMenu;

    private final SeccionMenu[] secciones;

    private SeccionMenu seccionActual;
    private final BufferedImage image = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_PORTADA);
    private final BufferedImage titol = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_TITOL);

    public GestorMenu() {
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[2];

        final Rectangle etiquetaMenu = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[0] = new MenuConfigurar("Opciones", etiquetaMenu);

        final Rectangle etiquetaSalir = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaMenu.y + etiquetaMenu.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[1] = new MenuSalir("Salir", etiquetaSalir);

        seccionActual = secciones[0];       
    }

    public void actualizar() {

    }

    public void dibujar(Graphics g) {
        estructuraMenu.dibujar(g);
        DibujoDebug.dibujarImagen(g, image, 0, 0);
        DibujoDebug.dibujarImagen(g, titol, 120, 0);
        cambiarseccion();
        for (int i = 0; i < secciones.length; i++) {
            if (seccionActual == secciones[i]) {
                secciones[i].dibujarEtiquetaActiva(g);
                if (seccionActual == secciones[0] && GestorControles.teclado.accion.estaPulsada()) {
                    GestorControles.teclado.config = true;
                   GestorControles.teclado.accion.teclaLiberada();
                    
                }
                if (seccionActual == secciones[1] && GestorControles.teclado.accion.estaPulsada()) {
                    //ElementosPrincipales.musicaIngame.pararReproducir();
                    Constantes.MUSICA_INGAME.detener();
                    System.exit(0);
                }
            } else {
                secciones[i].dibujarEtiquetaInactiva(g);
            }

        }
    }

    private void cambiarseccion() {
        if (GestorControles.teclado.arriba.estaPulsada()) {
            seccionActual = secciones[0];

        }
        if (GestorControles.teclado.abajo.estaPulsada()) {
            seccionActual = secciones[1];
        }

        if (!GestorControles.teclado.menuActivo) {
            seccionActual = secciones[0];
        }
    }

}
