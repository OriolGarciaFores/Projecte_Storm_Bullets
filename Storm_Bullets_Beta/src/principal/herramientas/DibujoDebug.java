package principal.herramientas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import principal.Constantes;

public class DibujoDebug {

    private static int objetosDibujados = 0;

    public static void dibujarImagen(final Graphics g, final BufferedImage img, final int x, final int y) {
        objetosDibujados++;
        g.drawImage(img, x, y, null);
        
    }

    public static void dibujarImagen(final Graphics g, final BufferedImage img, final Point p) {
        objetosDibujados++;
        g.drawImage(img, p.x, p.y, null);
    }
    
    public static BufferedImage imagenRedimensionada(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }

    public static void dibujarString(final Graphics g, final String s, final int x, final int y, final int mida) {
        objetosDibujados++;
        g.setFont(new Font("Arial", Font.PLAIN, mida));
        g.drawString(s, x, y);
    }

    public static void dibujarString(final Graphics g, final String s, final Point p, final int mida) {
        objetosDibujados++;
         g.setFont(new Font("Arial", Font.PLAIN, mida));
        g.drawString(s, p.x, p.y);
    }

    public static void dibujarString(final Graphics g, final String s, final int x, final int y, final Color c, final int mida) {
        objetosDibujados++;
        g.setFont(Constantes.FUENTE_ALMOSNOW);
        g.setColor(c);
        g.drawString(s, x, y);
        
    }
    

    public static void dibujarString(final Graphics g, final String s, final Point p, final Color c) {
        objetosDibujados++;
        g.setColor(c);
        g.drawString(s, p.x, p.y);
    }

    public static void dibujarRectanguloRelleno(final Graphics g, final int x, final int y, final int ancho, final int alto) {
        objetosDibujados++;
        g.fillRect(x, y, ancho, alto);
    }

    public static void dibujarRectanguloRelleno(final Graphics g, final Rectangle r) {
        objetosDibujados++;
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    public static void dibujarRectanguloRelleno(final Graphics g, final int x, final int y, final int ancho, final int alto, final Color c) {
        objetosDibujados++;
        g.setColor(c);
        g.fillRect(x, y, ancho, alto);
    }

    public static void dibujarRectanguloRelleno(final Graphics g, final Rectangle r, final Color c) {
        objetosDibujados++;
        g.setColor(c);
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    public static void dibujarRectanguloContorno(final Graphics g, final int x, final int y, final int ancho, final int alto) {
        objetosDibujados++;
        g.drawRect(x, y, ancho, alto);
    }

    public static void dibujarRectanguloContorno(final Graphics g, final Rectangle r) {
        objetosDibujados++;
        g.drawRect(r.x, r.y, r.width, r.height);
    }

    public static void dibujarRectanguloContorno(final Graphics g, final int x, final int y, final int ancho, final int alto, final Color c) {
        objetosDibujados++;
        g.setColor(c);
        g.drawRect(x, y, ancho, alto);
    }

    public static void dibujarRectanguloContorno(final Graphics g, final Rectangle r, final Color c) {
        objetosDibujados++;
        g.setColor(c);
        g.drawRect(r.x, r.y, r.width, r.height);
    }

    public static int obtenerContadorObjetos() {
        return objetosDibujados;
    }

    public static void reiniciarContadorObjetos() {
        objetosDibujados = 0;
    }
}
