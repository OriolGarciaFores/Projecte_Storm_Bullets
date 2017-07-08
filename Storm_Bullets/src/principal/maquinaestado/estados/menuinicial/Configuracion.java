package principal.maquinaestado.estados.menuinicial;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.idioma.Idioma;
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
        secciones = new SeccionMenu[4];

        final Rectangle etiquetaVolumen = new Rectangle(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[0] = new Volumen(ElementosPrincipales.idioma.getProperty(Constantes.KEY_MUSICA) + " " + Constantes.MUSICA_TITULO.obtenerPorcentaje() + "%", etiquetaVolumen);

        final Rectangle etiquetaVolumenSonido = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaVolumen.y + etiquetaVolumen.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[1] = new Volumen(ElementosPrincipales.idioma.getProperty(Constantes.KEY_SONIDO) + " " + Constantes.disparo_pistola.obtenerPorcentaje() + "%", etiquetaVolumenSonido);

        final Rectangle etiquetaIdioma = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaVolumenSonido.y + etiquetaVolumenSonido.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        if (ElementosPrincipales.settings.getProperty(Constantes.KEY_IDIOMA).equals("es")) {

            secciones[2] = new CambioIdioma(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + ElementosPrincipales.idioma.getProperty(Constantes.KEY_ESPANOL), etiquetaIdioma);
        } else if (ElementosPrincipales.settings.getProperty(Constantes.KEY_IDIOMA).equals("en")) {
            secciones[2] = new CambioIdioma(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + ElementosPrincipales.idioma.getProperty(Constantes.KEY_INGLES), etiquetaIdioma);
        } else {
            secciones[2] = new CambioIdioma(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + ElementosPrincipales.idioma.getProperty(Constantes.KEY_CATALAN), etiquetaIdioma);

        }

        final Rectangle etiquetaVolver = new Rectangle(Constantes.CENTRO_VENTANA_X, etiquetaIdioma.y + etiquetaIdioma.height, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[3] = new Volver(ElementosPrincipales.idioma.getProperty(Constantes.KEY_VOLVER), etiquetaVolver);

        seccionActual = secciones[0];
    }

    public void actualizar() {
        for (int i = 0; i < secciones.length; i++) {
            if (GestorControles.teclado.abajo.estaPulsada() && seccionActual == secciones[0]) {
                seccionActual = secciones[1];
                GestorControles.teclado.abajo.teclaLiberada();
            }

            if (!ElementosPrincipales.enPartida) {
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
            } else {

                if (GestorControles.teclado.abajo.estaPulsada() && seccionActual == secciones[1]) {
                    seccionActual = secciones[3];
                    GestorControles.teclado.abajo.teclaLiberada();
                }

                if (GestorControles.teclado.arriba.estaPulsada() && seccionActual == secciones[3]) {
                    seccionActual = secciones[1];
                    GestorControles.teclado.arriba.teclaLiberada();

                }

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

                    Constantes.MUSICA_TITULO.aumentarVolumen(0.8f);
                    Constantes.MUSICA_INGAME.aumentarVolumen(0.8f);
                    Constantes.MUSICA_GAME_OVER.aumentarVolumen(0.8f);
                    seccionActual.ModificarNombre(ElementosPrincipales.idioma.getProperty(Constantes.KEY_MUSICA) + " " + Constantes.MUSICA_TITULO.obtenerPorcentaje() + "%");

                    GestorControles.teclado.derecha.teclaLiberada();
                } else if (seccionActual == secciones[0] && GestorControles.teclado.izquierda.estaPulsada()) {

                    Constantes.MUSICA_TITULO.disminuirVolumen(0.8f);
                    Constantes.MUSICA_INGAME.disminuirVolumen(0.8f);
                    Constantes.MUSICA_GAME_OVER.disminuirVolumen(0.8f);
                    seccionActual.ModificarNombre(ElementosPrincipales.idioma.getProperty(Constantes.KEY_MUSICA) + " " + Constantes.MUSICA_TITULO.obtenerPorcentaje() + "%");

                    GestorControles.teclado.izquierda.teclaLiberada();
                }

                if (seccionActual == secciones[1] && GestorControles.teclado.derecha.estaPulsada()) {

                    Constantes.disparo_pistola.aumentarVolumen(0.8f);
                    Constantes.disparo_rifleAsalto.aumentarVolumen(0.8f);
                    Constantes.disparo_francotirador.aumentarVolumen(0.8f);
                    Constantes.bola_fuego.aumentarVolumen(0.8f);
                    Constantes.lanzallamas.aumentarVolumen(0.8f);

                    Constantes.grito_perderVida.aumentarVolumen(0.8f);

                    seccionActual.ModificarNombre(ElementosPrincipales.idioma.getProperty(Constantes.KEY_SONIDO) + " " + Constantes.disparo_pistola.obtenerPorcentaje() + "%");

                    Constantes.disparo_pistola.reproducir();

                    GestorControles.teclado.derecha.teclaLiberada();
                } else if (seccionActual == secciones[1] && GestorControles.teclado.izquierda.estaPulsada()) {

                    Constantes.disparo_pistola.disminuirVolumen(0.8f);
                    Constantes.disparo_rifleAsalto.disminuirVolumen(0.8f);
                    Constantes.disparo_francotirador.disminuirVolumen(0.8f);
                    Constantes.bola_fuego.disminuirVolumen(0.8f);
                    Constantes.lanzallamas.disminuirVolumen(0.8f);

                    Constantes.grito_perderVida.disminuirVolumen(0.8f);

                    seccionActual.ModificarNombre(ElementosPrincipales.idioma.getProperty(Constantes.KEY_SONIDO) + " " + Constantes.disparo_pistola.obtenerPorcentaje() + "%");

                    Constantes.disparo_pistola.reproducir();

                    GestorControles.teclado.izquierda.teclaLiberada();
                }

                if (seccionActual == secciones[2] && GestorControles.teclado.derecha.estaPulsada() && !ElementosPrincipales.enPartida) {

                    if (seccionActual.obtenerNombreSeccion().equals(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + "Català")) {
                        seccionActual.ModificarNombre(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + ElementosPrincipales.idioma.getProperty(Constantes.KEY_ESPANOL));
                        ElementosPrincipales.idioma.cambiarIdioma("es");
                    } else if (seccionActual.obtenerNombreSeccion().equals(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + "Español")) {
                        seccionActual.ModificarNombre(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + ElementosPrincipales.idioma.getProperty(Constantes.KEY_INGLES));
                        ElementosPrincipales.idioma.cambiarIdioma("en");
                    }

                    GestorControles.teclado.derecha.teclaLiberada();
                } else if (seccionActual == secciones[2] && GestorControles.teclado.izquierda.estaPulsada() && !ElementosPrincipales.enPartida) {

                    if (seccionActual.obtenerNombreSeccion().equals(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + "Español")) {
                        seccionActual.ModificarNombre(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + ElementosPrincipales.idioma.getProperty(Constantes.KEY_CATALAN));
                        ElementosPrincipales.idioma.cambiarIdioma("cat");
                    } else if (seccionActual.obtenerNombreSeccion().equals(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + "English")) {

                        seccionActual.ModificarNombre(ElementosPrincipales.idioma.getProperty(Constantes.KEY_TEXT_IDIOMA) + " " + ElementosPrincipales.idioma.getProperty(Constantes.KEY_ESPANOL));
                        ElementosPrincipales.idioma.cambiarIdioma("es");
                    }

                    GestorControles.teclado.izquierda.teclaLiberada();
                }

                if (seccionActual == secciones[3] && GestorControles.teclado.accion.estaPulsada()) {
                    
                    ElementosPrincipales.settings.modificarSettings(Constantes.KEY_MUSICA_VOLUMEN, "" + Constantes.MUSICA_TITULO.obtenerVolumen(), "MUSICA MODIFICADA");
                    ElementosPrincipales.settings.modificarSettings(Constantes.KEY_MUSICA_PORCENTAJE, "" + Constantes.MUSICA_TITULO.obtenerPorcentaje(), "MUSICA MODIFICADA");
                    ElementosPrincipales.settings.modificarSettings(Constantes.KEY_SONIDO_VOLUMEN, "" + Constantes.disparo_pistola.obtenerVolumen(), "SONIDO MODIFICADO");
                    ElementosPrincipales.settings.modificarSettings(Constantes.KEY_SONIDO_PORCENTAJE, "" + Constantes.disparo_pistola.obtenerPorcentaje(), "SONIDO MODIFICADO");

                    if (ElementosPrincipales.settings.getProperty(Constantes.KEY_IDIOMA).equals(ElementosPrincipales.idioma.getIdiomaActual())) {

                    } else {
                        ElementosPrincipales.settings.modificarSettings(Constantes.KEY_IDIOMA, ElementosPrincipales.idioma.getIdiomaActual(), "IDIOMA MODIFICADO");
                        ElementosPrincipales.idioma = new Idioma(ElementosPrincipales.settings.getProperty(Constantes.KEY_IDIOMA));
                        GestorPrincipal.main(null);
                    }

                    GestorControles.teclado.config = false;
                    GestorControles.teclado.accion.teclaLiberada();
                }

            } else {
                secciones[i].dibujarEtiquetaInactiva(g);
                if (ElementosPrincipales.enPartida) {
                    secciones[2].dibujarrEtiquetaBloqueada(g);
                }
            }

        }
    }

}
