package principal.mapas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {

    private String[] partes;

    private final int ancho;
    private final int alto;

    private final Sprite[] paleta;

    private final boolean[] colisiones;

    public ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();

    private final int[] sprites;

    private final int MARGEN_X = Constantes.ANCHO_VENTANA / 2 - Constantes.LADO_SPRITE / 2;
    private final int MARGEN_Y = Constantes.ALTO_VENTANA / 2 - Constantes.LADO_SPRITE / 2;

    public Mapa(final String ruta) {
        String contenido = CargadorRecursos.leerArchivoTexto(ruta);

        partes = contenido.split("\\*"); //Romper la cadena que lleve este simbolo.

        ancho = Integer.parseInt(partes[0]);
        alto = Integer.parseInt(partes[1]);

        String HojasUtilizadas = partes[2];
        String[] hojasSeparadas = HojasUtilizadas.split(",");

        // Lectura de la paleta de Sprites.
        String paletaEntera = partes[3];
        String[] partesPaleta = paletaEntera.split("#");

        // assignar sprites aqui.
        paleta = asignarSprites(partesPaleta, hojasSeparadas);

        String colisionesEnteras = partes[4];
        colisiones = extraerColisiones(colisionesEnteras);

        String spritesEnteros = partes[5];
        String[] cadenasSprites = spritesEnteros.split(" ");

        sprites = extraerSprites(cadenasSprites);

    }

    private Sprite[] asignarSprites(final String[] partesPaleta, final String[] hojasSeparadas) {
        //asumimos que solo tenemos 1 hoja de sprites.
        Sprite[] paleta = new Sprite[partesPaleta.length];
        HojaSprites hoja = new HojaSprites("/imagenes/hojasTexturas/" + hojasSeparadas[0] + ".png", 32, false);
        for (int i = 0; i < partesPaleta.length; i++) {
            String spriteTemporal = partesPaleta[i];
            String[] partesSprite = spriteTemporal.split("-");

            int indicePaleta = Integer.parseInt(partesSprite[0]);
            int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);//Se ignora el 2n valor que es la id de la hoja, porque solo trabajamos con 1 sola hoja.

            paleta[indicePaleta] = hoja.obtenerSprite(indiceSpriteHoja);

        }
        return paleta;
    }

    private boolean[] extraerColisiones(final String cadenaColisiones) {
        boolean[] colisiones = new boolean[cadenaColisiones.length()];

        for (int i = 0; i < cadenaColisiones.length(); i++) {
            if (cadenaColisiones.charAt(i) == '0') {
                colisiones[i] = false;
            } else {
                colisiones[i] = true;
            }
        }
        return colisiones;
    }

    private int[] extraerSprites(final String[] cadenasSprites) {
        ArrayList<Integer> sprites = new ArrayList<Integer>();

        for (int i = 0; i < cadenasSprites.length; i++) {
            if (cadenasSprites[i].length() == 2) {
                sprites.add(Integer.parseInt(cadenasSprites[i]));
            } else {
                String uno = "";
                String dos = "";

                String error = cadenasSprites[i];

                uno += error.charAt(0);
                uno += error.charAt(1);

                dos += error.charAt(2);
                dos += error.charAt(3);

                sprites.add(Integer.parseInt(uno));
                sprites.add(Integer.parseInt(dos));
            }
        }
        int[] vectorSprites = new int[sprites.size()];

        for (int i = 0; i < sprites.size(); i++) {
            vectorSprites[i] = sprites.get(i);
        }

        return vectorSprites;
    }

    public void actualizar(final int posicionX, final int posicionY) {
        actualizarAreasColision(posicionX, posicionY);
    }

    private void actualizarAreasColision(final int posicionX, final int posicionY) {
        if (!areasColision.isEmpty()) {//isEmpty significa vacio.
            areasColision.clear();//Borras los objetos. Para evitar llenar el array infinitamente.
        }

        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
                int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;
                //Si se encuentra un objeto que sea true, se le envuelve con un rectangulo.
                if (colisiones[x + y * this.ancho]) {
                    final Rectangle r = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
                    areasColision.add(r);
                }
            }
        }

    }

    public void dibujar(Graphics g, final int posicionX, final int posicionY) {

        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                BufferedImage imagen = paleta[sprites[x + y * this.ancho]].obtenerImagen();

                int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
                int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;
                DibujoDebug.dibujarImagen(g, imagen, puntoX, puntoY);
                
               /* g.setColor(Color.green);
                
                for(int r = 0; r<areasColision.size(); r++){
                    Rectangle area = areasColision.get(r);
                    g.drawRect(area.x, area.y, area.width, area.height);
                }*/
            }
        }
    }

    public Rectangle obtenerBordes(final int posicionX, final int posicionY, final int anchoJugador, final int altoJugador) {
        int x = MARGEN_X - posicionX + anchoJugador;
        int y = MARGEN_Y - posicionY + altoJugador;
        int ancho = this.ancho * Constantes.LADO_SPRITE - anchoJugador * 2;
        int alto = this.alto * Constantes.LADO_SPRITE - altoJugador * 2;

        return new Rectangle(x, y, ancho, alto);
    }

}
