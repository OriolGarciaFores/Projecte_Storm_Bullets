package principal.maquinaestado.estados.menujuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import principal.Constantes;
import principal.ElementosPrincipales;
import static principal.ElementosPrincipales.datosMapa;
import static principal.ElementosPrincipales.inventario;
import principal.control.GestorControles;
import principal.entes.Jugador;
import principal.guardar_partida.GuardarPartida;
import principal.guardar_partida.Top;
import principal.herramientas.DibujoDebug;
import principal.inventario.Inventario;
import principal.mapas.MapaTiled;
import principal.maquinaestado.EstadoJuego;
import principal.reproductor.Musicas;

public class GameOver implements EstadoJuego {

    private final EstructuraMenu estructuraMenu;
    private final SeccionMenu[] secciones;
    private SeccionMenu seccionActual;

    public GameOver() {
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[1];

        secciones[0] = new MenuSalir("Continuar", null);

        seccionActual = secciones[0];
    }

    @Override
    public void actualizar() {
        for (int i = 0; i < secciones.length; i++) {
            if (GestorControles.teclado.accion.estaPulsada() && seccionActual == secciones[0]) {
                

                //GestorControles.teclado.accion.teclaLiberada();
            }
        }
    }

    @Override
    public void dibujar(Graphics g) {
        DibujoDebug.dibujarString(g, "GAME OVER", Constantes.CENTRO_VENTANA_X - 100, Constantes.CENTRO_VENTANA_Y, Color.WHITE);
        for (int i = 0; i < secciones.length; i++) {
            if (seccionActual == secciones[i]) {

                if (seccionActual == secciones[0] && GestorControles.teclado.accion.estaPulsada()) {
                    //Poner false para volver.
                   /* actualizarRanking();
                    
                    ElementosPrincipales.datosMapa = new ArrayList<>();
                    ElementosPrincipales.mapa = new MapaTiled(Constantes.RUTA_MAPA);
                    ElementosPrincipales.jugador = new Jugador();
                    ElementosPrincipales.inventario = new Inventario();
                    ElementosPrincipales.musicaIngame.pararReproducir();
                    ElementosPrincipales.m.reproducir(Constantes.RUTA_AUDIO_TITULO);
                    GestorControles.teclado.tituloActivo = true;
                    GestorControles.teclado.muerto = false;*/
                    //Intentar volver al menu principal y reiniciar juego.
                    GuardarPartida.modificarSave();
                    System.exit(0);
                    GestorControles.teclado.accion.teclaLiberada();

                }

            }

        }
    }

   /* private void actualizarRanking() {
        GuardarPartida.modificarSave();
        try {
            Top.leerPartidasFichero();
            Top.ordenarPartidas();
        } catch (Exception ex) {
            System.out.println("No hay partidas.");
        }
    }*/

}
