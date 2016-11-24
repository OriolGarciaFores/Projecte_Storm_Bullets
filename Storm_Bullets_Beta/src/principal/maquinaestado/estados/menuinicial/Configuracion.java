package principal.maquinaestado.estados.menuinicial;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estados.menujuego.EstructuraMenu;
import principal.maquinaestado.estados.menujuego.SeccionMenu;

public class Configuracion implements EstadoJuego {

    private final BufferedImage image = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_PORTADA);
    private final BufferedImage titol = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_TITOL);

    private final EstructuraMenu estructuraMenu;

    private final SeccionMenu[] secciones;

    private SeccionMenu seccionActual;
    

    public Configuracion() {
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[2];

        final Rectangle etiquetaVolumen = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[0] = new Volumen("Volumen "+ Constantes.m.obtenerPorcentajeVolumen()+ "%", etiquetaVolumen);

        final Rectangle etiquetaVolver = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaVolumen.y + etiquetaVolumen.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[1] = new Volver("Volver", etiquetaVolver);

        seccionActual = secciones[0];
    }

    public void actualizar() {
        for (int i = 0; i < secciones.length; i++) {
            if (GestorControles.teclado.abajo.estaPulsada() && seccionActual == secciones[0]) {
                seccionActual = secciones[1];
                GestorControles.teclado.abajo.teclaLiberada();
            }
            if (GestorControles.teclado.arriba.estaPulsada() && seccionActual == secciones[1]) {
                seccionActual = secciones[0];
                GestorControles.teclado.arriba.teclaLiberada();

            }
            if (GestorControles.teclado.accion.estaPulsada() && seccionActual == secciones[0]) {

                GestorControles.teclado.accion.teclaLiberada();
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
                if (seccionActual == secciones[0] && GestorControles.teclado.derecha.estaPulsada()) {
                    try {
                        Constantes.m.cambiarVolumen(Constantes.m.subirvolumen());
                        seccionActual.ModificarNombre("Volumen " + Constantes.m.obtenerPorcentajeVolumen() + "%");
                    } catch (Exception ex) {
                        Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //Arreglar cambio de volumen.
                    GestorControles.teclado.derecha.teclaLiberada();
                } else if (seccionActual == secciones[0] && GestorControles.teclado.izquierda.estaPulsada()) {
                    try {
                        Constantes.m.cambiarVolumen(Constantes.m.bajarVolumen());
                        seccionActual.ModificarNombre("Volumen " + Constantes.m.obtenerPorcentajeVolumen() + "%");
                    } catch (Exception ex) {
                        Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    GestorControles.teclado.izquierda.teclaLiberada();
                }

                if (seccionActual == secciones[1] && GestorControles.teclado.accion.estaPulsada()) {
                    GestorControles.teclado.config = false;
                    GestorControles.teclado.accion.teclaLiberada();

                }

            } else {
                secciones[i].dibujarEtiquetaInactiva(g);
            }

        }
    }

}
