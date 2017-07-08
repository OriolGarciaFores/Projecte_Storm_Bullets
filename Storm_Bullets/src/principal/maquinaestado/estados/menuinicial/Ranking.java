package principal.maquinaestado.estados.menuinicial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.guardar_partida.Top;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estados.menujuego.EstructuraMenu;
import principal.maquinaestado.estados.menujuego.SeccionMenu;

public class Ranking implements EstadoJuego {

    private final BufferedImage image = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_PORTADA);

    private final EstructuraMenu estructuraMenu;

    private final SeccionMenu[] secciones;

    private SeccionMenu seccionActual;

    int contador = 50;

    int max = 5;
    int numero = 1;

    public Ranking() {
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[1];

        try {
            Top.leerPartidasFichero();
            Top.ordenarPartidas();
        } catch (Exception ex) {
            System.out.println("No hay partidas.");
        }

        final Rectangle etiqueta = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y + 200, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[0] = new Volver(ElementosPrincipales.idioma.getProperty(Constantes.KEY_VOLVER), etiqueta);

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
        //Dibujar todo los datos del ranking, nombre, puntuacion y numeracion.
        //Bucle de dibujar String.
        DibujoDebug.dibujarImagen(g, image, 0, 0);
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_NOMBRE) + " ", Constantes.CENTRO_VENTANA_X - 300, Constantes.CENTRO_VENTANA_Y - 200, Color.WHITE);
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_PUNTUACION) + " ", Constantes.CENTRO_VENTANA_X - 100, Constantes.CENTRO_VENTANA_Y - 200, Color.WHITE);
        DibujoDebug.dibujarString(g, ElementosPrincipales.idioma.getProperty(Constantes.KEY_TIEMPO) + " ", Constantes.CENTRO_VENTANA_X + 200, Constantes.CENTRO_VENTANA_Y - 200, Color.WHITE);

        try {
            if (Top.partidas.size() <= max) {
                for (int h = 0; h < Top.partidas.size(); h++) {
                    contador += 50;
                    DibujoDebug.dibujarString(g, numero++ + " " + Top.partidas.get(h).obtenerNombreJugador(), Constantes.CENTRO_VENTANA_X - 300, Constantes.CENTRO_VENTANA_Y - 200 + contador, Color.WHITE);
                    DibujoDebug.dibujarString(g, Top.partidas.get(h).obtenerPuntuacion() + "", Constantes.CENTRO_VENTANA_X - 100, Constantes.CENTRO_VENTANA_Y - 200 + contador, Color.WHITE);
                    DibujoDebug.dibujarString(g, Top.partidas.get(h).obtenerTiempoJugado() + "", Constantes.CENTRO_VENTANA_X + 200, Constantes.CENTRO_VENTANA_Y - 200 + contador, Color.WHITE);
                    if (h == Top.partidas.size() - 1) {
                        contador = 50;
                        numero = 1;
                    }
                }
            } else {
                for (int h = 0; h < max; h++) {
                    contador += 50;
                    DibujoDebug.dibujarString(g, numero++ + " " + Top.partidas.get(h).obtenerNombreJugador(), Constantes.CENTRO_VENTANA_X - 300, Constantes.CENTRO_VENTANA_Y - 200 + contador, Color.WHITE);
                    DibujoDebug.dibujarString(g, Top.partidas.get(h).obtenerPuntuacion() + "", Constantes.CENTRO_VENTANA_X - 100, Constantes.CENTRO_VENTANA_Y - 200 + contador, Color.WHITE);
                    DibujoDebug.dibujarString(g, Top.partidas.get(h).obtenerTiempoJugado() + "", Constantes.CENTRO_VENTANA_X + 200, Constantes.CENTRO_VENTANA_Y - 200 + contador, Color.WHITE);
                    if (h == max - 1) {
                        contador = 50;
                        numero = 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("No hay partidas.");
        }

        estructuraMenu.dibujar(g);
        for (int i = 0; i < secciones.length; i++) {
            if (seccionActual == secciones[i]) {
                secciones[i].dibujarEtiquetaActiva(g);
                if (seccionActual == secciones[0] && GestorControles.teclado.accion.estaPulsada()) {
                    GestorControles.teclado.ranking = false;
                    GestorControles.teclado.accion.teclaLiberada();

                }

            } else {
                secciones[i].dibujarEtiquetaInactiva(g);
            }

        }
    }

}
