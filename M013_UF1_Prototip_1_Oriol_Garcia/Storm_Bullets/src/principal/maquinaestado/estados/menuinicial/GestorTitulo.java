package principal.maquinaestado.estados.menuinicial;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estados.menujuego.EstructuraMenu;
import principal.maquinaestado.estados.menujuego.MenuConfigurar;
import principal.maquinaestado.estados.menujuego.SeccionMenu;

public class GestorTitulo implements EstadoJuego {

    private final BufferedImage image = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_PORTADA);
    private final BufferedImage titol = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_TITOL);

    private final EstructuraMenu estructuraMenu;

    private final SeccionMenu[] secciones;

    private SeccionMenu seccionActual;
    
    public GestorTitulo() {
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[4];

        final Rectangle etiquetaPlay = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[0] = new Jugar("Jugar", etiquetaPlay);

        final Rectangle etiquetaTop = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaPlay.y + etiquetaPlay.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[1] = new MenuTop("Ranking", etiquetaTop);

        final Rectangle etiquetaConfigurar = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaTop.y + etiquetaTop.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[2] = new MenuConfigurar("Configuración", etiquetaConfigurar);

        final Rectangle etiquetaSalir = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaConfigurar.y + etiquetaConfigurar.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[3] = new Exit("Salir", etiquetaSalir);

        seccionActual = secciones[0];
        ElementosPrincipales.m.reproducir(Constantes.RUTA_AUDIO_TITULO);
    }

    public void actualizar() {
        for (int i = 0; i < secciones.length; i++) {
            if (GestorControles.teclado.abajo.estaPulsada() && seccionActual == secciones[0]) {
                seccionActual = secciones[1];
                GestorControles.teclado.abajo.teclaLiberada();
            }
            if (GestorControles.teclado.abajo.estaPulsada() && seccionActual == secciones[1]) {
                seccionActual = secciones[2];
                GestorControles.teclado.abajo.teclaLiberada();
            }

            if (GestorControles.teclado.abajo.estaPulsada() && seccionActual == secciones[2]) {
                seccionActual = secciones[3];
                GestorControles.teclado.abajo.teclaLiberada();
            }

            if (GestorControles.teclado.arriba.estaPulsada() && seccionActual == secciones[3]) {
                seccionActual = secciones[2];
                GestorControles.teclado.arriba.teclaLiberada();
            }
            if (GestorControles.teclado.arriba.estaPulsada() && seccionActual == secciones[2]) {
                seccionActual = secciones[1];
                GestorControles.teclado.arriba.teclaLiberada();
            }
            if (GestorControles.teclado.arriba.estaPulsada() && seccionActual == secciones[1]) {
                seccionActual = secciones[0];
                GestorControles.teclado.arriba.teclaLiberada();

            }
        }
    }

    public void dibujar(Graphics g) {
        DibujoDebug.dibujarImagen(g, image, 0, 0);
        DibujoDebug.dibujarImagen(g, titol, 120, 0);
        estructuraMenu.dibujar(g);
        for (int i = 0; i < secciones.length; i++) {
            if (seccionActual == secciones[i]) {
                secciones[i].dibujarEtiquetaActiva(g);
                              
                if (seccionActual == secciones[0] && GestorControles.teclado.accion.estaPulsada()) {
                    GestorControles.teclado.nombrarJugador = true;
                    GestorControles.teclado.tituloActivo = false;
                    ElementosPrincipales.m.pararReproducir();
                    ElementosPrincipales.musicaIngame.reproducir(Constantes.RUTA_AUDIO_INGAME);
                }
                if (seccionActual == secciones[2] && GestorControles.teclado.accion.estaPulsada()) {
                   GestorControles.teclado.config = true;
                   GestorControles.teclado.accion.teclaLiberada();
                }
                
                if (seccionActual == secciones[3] && GestorControles.teclado.accion.estaPulsada()) {
                    seccionActual.actualizar();
                }
                              
            } else {
                secciones[i].dibujarEtiquetaInactiva(g);
            }
            
        }
        
    }

}
