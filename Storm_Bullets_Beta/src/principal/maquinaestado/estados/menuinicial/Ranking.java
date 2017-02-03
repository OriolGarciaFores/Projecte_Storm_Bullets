
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
import principal.maquinaestado.estados.menujuego.SeccionMenu;


public class Ranking implements EstadoJuego {
    
    private final BufferedImage image = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_PORTADA);
    private final BufferedImage titol = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_TITOL);
    
   private final EstructuraMenu estructuraMenu;

    private final SeccionMenu[] secciones;

    private SeccionMenu seccionActual;
    
    public Ranking(){
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[1];

        final Rectangle etiqueta = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[0] = new Volumen("Volver", etiqueta);


        seccionActual = secciones[0];
    }

    @Override
    public void actualizar() {
         for (int i = 0; i < secciones.length; i++) {
            if (GestorControles.teclado.abajo.estaPulsada() && seccionActual == secciones[0]) {
                //Volveriamos al menu principal.
                GestorControles.teclado.abajo.teclaLiberada();
               
            }
        }
    }

    @Override
    public void dibujar(Graphics g) {
        //Dibujar todo los datos del ranking, nombre, puntuacion y numeracion. FALTA.
        DibujoDebug.dibujarImagen(g, image, 0, 0);
        DibujoDebug.dibujarImagen(g, titol, 120, 0);
        estructuraMenu.dibujar(g);
        for (int i = 0; i < secciones.length; i++) {
            if (seccionActual == secciones[i]) {
                secciones[i].dibujarEtiquetaActiva(g);
                if (seccionActual == secciones[0] && GestorControles.teclado.accion.estaPulsada()) {
                    GestorControles.teclado.ranking = false;
                    GestorControles.teclado.accion.teclaLiberada();

                }

            }else{
            secciones[i].dibujarEtiquetaInactiva(g);
            }

        }
    }
    
}
