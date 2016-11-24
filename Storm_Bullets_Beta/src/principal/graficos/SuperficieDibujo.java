package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.GestorEstados;

public class SuperficieDibujo extends Canvas {

    private static final long serialVersionUID = 2L;

    private final int ancho;
    private final int alto;

    public SuperficieDibujo(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;

        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(GestorControles.teclado);
        setFocusable(true);
        requestFocus();
    }

    public void dibujar(final GestorEstados ge) {
        BufferStrategy buffer = getBufferStrategy();

        if (buffer == null) {
            createBufferStrategy(4);
            return;
        }

        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);

        if (Constantes.FACTOR_ESCALADO_X != 1.0 || Constantes.FACTOR_ESCALADO_Y != 1.0) {
           g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
           
        }
        ge.dibujar(g);
        g.setColor(Color.green);
        g.drawString("FPS: " + GestorPrincipal.obtenerFPS(), 20, 60);
        g.drawString("APS: " + GestorPrincipal.obtenerAPS(), 20, 75);
        g.drawString("ESCALA X: " + Constantes.FACTOR_ESCALADO_X, 20, 90);
        g.drawString("ESCALA Y: " + Constantes.FACTOR_ESCALADO_Y, 20, 110);

        Toolkit.getDefaultToolkit().sync();

        g.dispose();

        buffer.show();
    }

    public int obtenerAncho() {
        return ancho;
    }

    public int obtenerAlto() {
        return alto;
    }

}
