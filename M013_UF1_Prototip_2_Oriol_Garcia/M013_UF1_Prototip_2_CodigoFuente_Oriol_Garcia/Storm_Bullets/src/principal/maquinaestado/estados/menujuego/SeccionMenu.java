package principal.maquinaestado.estados.menujuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.herramientas.DibujoDebug;

public abstract class SeccionMenu {

    protected String nombreSeccion;
    protected final Rectangle etiquetaMenu;

    public SeccionMenu(final String nombreSeccion, final Rectangle etiquetaMenu) {
        this.nombreSeccion = nombreSeccion;
        this.etiquetaMenu = etiquetaMenu;
    }

    public abstract void actualizar();

    public abstract void dibujar(final Graphics g);

    public void dibujarEtiquetaInactiva(final Graphics g) {
       // DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.WHITE);
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x - 100, etiquetaMenu.y + 20, Color.WHITE);
    }

    public void dibujarEtiquetaActiva(final Graphics g) {
       // DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.RED);
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x - 100, etiquetaMenu.y + 20, Color.GREEN);
    }

    public String obtenerNombreSeccion() {
        return nombreSeccion;
    }

    public void ModificarNombre(String nSeccion) {
        this.nombreSeccion = nSeccion;
    }

    public Rectangle obtenerEtiquetaMenu() {
        return etiquetaMenu;
    }

}
