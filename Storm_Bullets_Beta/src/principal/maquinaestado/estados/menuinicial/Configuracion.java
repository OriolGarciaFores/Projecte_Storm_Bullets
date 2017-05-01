package principal.maquinaestado.estados.menuinicial;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estados.menujuego.EstructuraMenu;
import principal.maquinaestado.estados.menujuego.SeccionMenu;
import principal.reproductor.Sonido;

public class Configuracion implements EstadoJuego {

    private final BufferedImage image = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_PORTADA);
    private final BufferedImage titol = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_TITOL);

    private final EstructuraMenu estructuraMenu;

    private final SeccionMenu[] secciones;

    private SeccionMenu seccionActual;

    public Configuracion() {
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[3];

        final Rectangle etiquetaVolumen = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        // secciones[0] = new Volumen("Volumen "+ ElementosPrincipales.m.obtenerPorcentajeVolumen()+ "%", etiquetaVolumen);
        secciones[0] = new Volumen("Musica " + Constantes.MUSICA_TITULO.obtenerPorcentaje() + "%", etiquetaVolumen);

        final Rectangle etiquetaVolumenSonido = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaVolumen.y + etiquetaVolumen.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        // secciones[0] = new Volumen("Volumen "+ ElementosPrincipales.m.obtenerPorcentajeVolumen()+ "%", etiquetaVolumen);
        secciones[1] = new Volumen("Sonido " + Constantes.disparo_pistola.obtenerPorcentaje() + "%", etiquetaVolumenSonido);

        final Rectangle etiquetaVolver = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaVolumenSonido.y + etiquetaVolumenSonido.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[2] = new Volver("Volver", etiquetaVolver);

        seccionActual = secciones[0];
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
                if (seccionActual == secciones[0] && GestorControles.teclado.derecha.estaPulsada()) {

                    // ElementosPrincipales.m.cambiarVolumen(ElementosPrincipales.m.subirvolumen());
                    Constantes.MUSICA_TITULO.aumentarVolumen(0.8f);
                    Constantes.MUSICA_INGAME.aumentarVolumen(0.8f);
                    Constantes.MUSICA_GAME_OVER.aumentarVolumen(0.8f);
                    // seccionActual.ModificarNombre("Volumen " + ElementosPrincipales.m.obtenerPorcentajeVolumen() + "%");
                    seccionActual.ModificarNombre("Musica " + Constantes.MUSICA_TITULO.obtenerPorcentaje() + "%");
                    //ElementosPrincipales.musicaIngame.cambiarVolumen(ElementosPrincipales.musicaIngame.subirvolumen());
                    

                    //Arreglar cambio de volumen.
                    GestorControles.teclado.derecha.teclaLiberada();
                } else if (seccionActual == secciones[0] && GestorControles.teclado.izquierda.estaPulsada()) {

                    // ElementosPrincipales.m.cambiarVolumen(ElementosPrincipales.m.bajarVolumen());   
                    Constantes.MUSICA_TITULO.disminuirVolumen(0.8f);
                     Constantes.MUSICA_INGAME.disminuirVolumen(0.8f);
                    //seccionActual.ModificarNombre("Volumen " + ElementosPrincipales.m.obtenerPorcentajeVolumen() + "%");
                    Constantes.MUSICA_GAME_OVER.disminuirVolumen(0.8f);
                    seccionActual.ModificarNombre("Musica " + Constantes.MUSICA_TITULO.obtenerPorcentaje() + "%");
                    //ElementosPrincipales.musicaIngame.cambiarVolumen(ElementosPrincipales.musicaIngame.bajarVolumen());
                   

                    GestorControles.teclado.izquierda.teclaLiberada();
                }


                    if (seccionActual == secciones[1] && GestorControles.teclado.derecha.estaPulsada()) {

                        Constantes.disparo_pistola.aumentarVolumen(0.8f);
                        Constantes.disparo_rifleAsalto.aumentarVolumen(0.8f);
                        Constantes.disparo_francotirador.aumentarVolumen(0.8f);
                        Constantes.bola_fuego.aumentarVolumen(0.8f);
                        Constantes.lanzallamas.aumentarVolumen(0.8f);
                        
                        Constantes.grito_perderVida.aumentarVolumen(0.8f);

                        seccionActual.ModificarNombre("Sonido " + Constantes.disparo_pistola.obtenerPorcentaje() + "%");
                        
                        Constantes.disparo_pistola.reproducir();
                        
                        GestorControles.teclado.derecha.teclaLiberada();
                    } else if (seccionActual == secciones[1] && GestorControles.teclado.izquierda.estaPulsada()) {

                        Constantes.disparo_pistola.disminuirVolumen(0.8f);
                        Constantes.disparo_rifleAsalto.disminuirVolumen(0.8f);
                        Constantes.disparo_francotirador.disminuirVolumen(0.8f);
                        Constantes.bola_fuego.disminuirVolumen(0.8f);
                        Constantes.lanzallamas.disminuirVolumen(0.8f);
                        
                        Constantes.grito_perderVida.disminuirVolumen(0.8f);

                        seccionActual.ModificarNombre("Sonido " + Constantes.disparo_pistola.obtenerPorcentaje() + "%");

                        Constantes.disparo_pistola.reproducir();
                        
                        GestorControles.teclado.izquierda.teclaLiberada();
                    }

                   

                

                if (seccionActual == secciones[2] && GestorControles.teclado.accion.estaPulsada()) {
                    GestorControles.teclado.config = false;
                    GestorControles.teclado.accion.teclaLiberada();

                }

            } else {
                secciones[i].dibujarEtiquetaInactiva(g);
            }

        }
    }

}
