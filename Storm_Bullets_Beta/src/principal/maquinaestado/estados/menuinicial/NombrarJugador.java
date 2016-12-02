/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.maquinaestado.estados.menuinicial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estados.menujuego.EstructuraMenu;
import principal.maquinaestado.estados.menujuego.SeccionMenu;

/**
 *
 * @author Tebrase
 */
public class NombrarJugador implements EstadoJuego {

    private final BufferedImage image = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_PORTADA);
    private final BufferedImage titol = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_TITOL);

    private final EstructuraMenu estructuraMenu;
    private final Rectangle r = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y, 100, 25);
    private final SeccionMenu[] secciones;
    private static String lletra = "";
    private SeccionMenu seccionActual;

    public NombrarJugador() {
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[1];

        
        secciones[0] = new Volumen("Continuar", null);

        seccionActual = secciones[0];
    }

    public void actualizar() {
        for (int i = 0; i < secciones.length; i++) {
            if (GestorControles.teclado.accion.estaPulsada() && seccionActual == secciones[0]) {

                GestorControles.teclado.accion.teclaLiberada();
            }
        }

    }

    public void dibujar(Graphics g) {
        DibujoDebug.dibujarImagen(g, image, 0, 0);
        DibujoDebug.dibujarImagen(g, titol, 120, 0);
        DibujoDebug.dibujarRectanguloRelleno(g, r, Color.white);
        DibujoDebug.dibujarString(g, escribir(), Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y + 10, Color.BLACK, 12);
        Constantes.nomJugador = nombre();
        for (int i = 0; i < secciones.length; i++) {
            if (seccionActual == secciones[i]) {

                if (seccionActual == secciones[0] && GestorControles.teclado.accion.estaPulsada()) {
                    GestorControles.teclado.nombrarJugador = false;
                    GestorControles.teclado.menuActivo = false;
                    GestorControles.teclado.accion.teclaLiberada();

                }

            }

        }

    }

    public String escribir() {
        if (GestorControles.teclado.abajo.estaPulsada()) {
            lletra += "S";
            GestorControles.teclado.abajo.teclaLiberada();
        }
        if (GestorControles.teclado.arriba.estaPulsada()) {
            lletra += "W";
            GestorControles.teclado.arriba.teclaLiberada();
        }
        if (GestorControles.teclado.Ñ.estaPulsada()) {
            lletra += "Ñ";
            GestorControles.teclado.Ñ.teclaLiberada();
        }
        
        return lletra;
    }
    
    public static String nombre(){
        
        return lletra;
    }

}
