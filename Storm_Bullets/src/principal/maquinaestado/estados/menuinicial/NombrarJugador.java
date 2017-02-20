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
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.guardar_partida.GuardarPartida;
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
    private final Rectangle r = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y, 110, 45);
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
                GuardarPartida.crearSave();
                //GestorControles.teclado.accion.teclaLiberada();
            }
        }

    }

    public void dibujar(Graphics g) {
        DibujoDebug.dibujarImagen(g, image, 0, 0);
        DibujoDebug.dibujarImagen(g, titol, 120, 0);
        DibujoDebug.dibujarRectanguloRelleno(g, r, Color.DARK_GRAY);
        DibujoDebug.dibujarString(g, escribir(), Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y + 40, Color.BLACK);
        DibujoDebug.dibujarString(g, "Nombre de Usuario: ", Constantes.CENTRO_VENTANA_X - 350, Constantes.CENTRO_VENTANA_Y + 40, Color.WHITE);
        ElementosPrincipales.jugador.setNomJugador(nombre());
        for (int i = 0; i < secciones.length; i++) {
            if (seccionActual == secciones[i]) {

                if (seccionActual == secciones[0] && GestorControles.teclado.accion.estaPulsada() && !"".equals(ElementosPrincipales.jugador.getNomJugador())) {
                    GestorControles.teclado.nombrarJugador = false;
                    GestorControles.teclado.menuActivo = false;
                    GestorControles.teclado.accion.teclaLiberada();

                }

            }

        }

    }

    public String escribir() {

        if (lletra.length() < 6) {
            if (GestorControles.teclado.Q.estaPulsada()) {
                lletra += "Q";
                GestorControles.teclado.Q.teclaLiberada();
            }
            if (GestorControles.teclado.arriba.estaPulsada()) {
                lletra += "W";
                GestorControles.teclado.arriba.teclaLiberada();
            }
            if (GestorControles.teclado.E.estaPulsada()) {
                lletra += "E";
                GestorControles.teclado.E.teclaLiberada();
            }
            if (GestorControles.teclado.R.estaPulsada()) {
                lletra += "R";
                GestorControles.teclado.R.teclaLiberada();
            }
            if (GestorControles.teclado.T.estaPulsada()) {
                lletra += "T";
                GestorControles.teclado.T.teclaLiberada();
            }
            if (GestorControles.teclado.Y.estaPulsada()) {
                lletra += "Y";
                GestorControles.teclado.Y.teclaLiberada();
            }
            if (GestorControles.teclado.U.estaPulsada()) {
                lletra += "U";
                GestorControles.teclado.U.teclaLiberada();
            }
            if (GestorControles.teclado.I.estaPulsada()) {
                lletra += "I";
                GestorControles.teclado.I.teclaLiberada();
            }
            if (GestorControles.teclado.O.estaPulsada()) {
                lletra += "O";
                GestorControles.teclado.O.teclaLiberada();
            }
            if (GestorControles.teclado.P.estaPulsada()) {
                lletra += "P";
                GestorControles.teclado.P.teclaLiberada();
            }
            if (GestorControles.teclado.izquierda.estaPulsada()) {
                lletra += "A";
                GestorControles.teclado.izquierda.teclaLiberada();
            }
            if (GestorControles.teclado.abajo.estaPulsada()) {
                lletra += "S";
                GestorControles.teclado.abajo.teclaLiberada();
            }
            if (GestorControles.teclado.derecha.estaPulsada()) {
                lletra += "D";
                GestorControles.teclado.derecha.teclaLiberada();
            }
            if (GestorControles.teclado.F.estaPulsada()) {
                lletra += "F";
                GestorControles.teclado.F.teclaLiberada();
            }
            if (GestorControles.teclado.G.estaPulsada()) {
                lletra += "G";
                GestorControles.teclado.G.teclaLiberada();
            }
            if (GestorControles.teclado.H.estaPulsada()) {
                lletra += "H";
                GestorControles.teclado.H.teclaLiberada();
            }
            if (GestorControles.teclado.J.estaPulsada()) {
                lletra += "J";
                GestorControles.teclado.J.teclaLiberada();
            }
            if (GestorControles.teclado.K.estaPulsada()) {
                lletra += "K";
                GestorControles.teclado.K.teclaLiberada();
            }
            if (GestorControles.teclado.L.estaPulsada()) {
                lletra += "L";
                GestorControles.teclado.L.teclaLiberada();
            }
            if (GestorControles.teclado.Ñ.estaPulsada()) {
                lletra += "Ñ";
                GestorControles.teclado.Ñ.teclaLiberada();
            }
            if (GestorControles.teclado.Z.estaPulsada()) {
                lletra += "Z";
                GestorControles.teclado.Z.teclaLiberada();
            }
            if (GestorControles.teclado.X.estaPulsada()) {
                lletra += "X";
                GestorControles.teclado.X.teclaLiberada();
            }
            if (GestorControles.teclado.C.estaPulsada()) {
                lletra += "C";
                GestorControles.teclado.C.teclaLiberada();
            }
            if (GestorControles.teclado.V.estaPulsada()) {
                lletra += "V";
                GestorControles.teclado.V.teclaLiberada();
            }
            if (GestorControles.teclado.B.estaPulsada()) {
                lletra += "B";
                GestorControles.teclado.B.teclaLiberada();
            }
            if (GestorControles.teclado.N.estaPulsada()) {
                lletra += "N";
                GestorControles.teclado.N.teclaLiberada();
            }
            if (GestorControles.teclado.M.estaPulsada()) {
                lletra += "M";
                GestorControles.teclado.M.teclaLiberada();
            }


        }

        if (GestorControles.teclado.borrar.estaPulsada() && lletra.length() > 0 && lletra.length() <= 6) {
            int largo;
            largo = lletra.length(); //maxim seran 6.

            lletra = lletra.substring(0, largo - 1);

            GestorControles.teclado.borrar.teclaLiberada();
        }

        if (lletra.length() > 6) {

            lletra = lletra.substring(0, 5);
        }

        return lletra;
    }

    public static String nombre() {

        return lletra;
    }

}

