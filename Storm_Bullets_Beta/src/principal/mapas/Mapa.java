package principal.mapas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.entes.Enemigo;
import principal.entes.RegistroEnemigos;
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
    
    public final ArrayList<Enemigo> enemigos;
    
    private final int[] sprites;

    private final int MARGEN_X = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
    private final int MARGEN_Y = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;

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
        
        String informacionEnemigos = partes[6];
        enemigos = assignarEnemigos(informacionEnemigos);
    }
    
    private ArrayList<Enemigo> assignarEnemigos(final String informacionEnemigos){
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        
        String[] informacionEnemigosSeperada = informacionEnemigos.split("#");
        for(int i = 0; i<informacionEnemigosSeperada.length; i++){
            String[] informacionEnemigoActual = informacionEnemigosSeperada[i].split(":");
            String[] coordenadas = informacionEnemigoActual[0].split(",");
            String idEnemigo = informacionEnemigoActual[1];
            
            Enemigo enemigo = RegistroEnemigos.obtenerEnemigo(Integer.parseInt(idEnemigo));
            enemigo.establecerPosicion(Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1]));
            enemigos.add(enemigo);
        }
        
        return enemigos;
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

    public void actualizar() {
        actualizarAreasColision();
    }

    private void actualizarAreasColision() {
        if (!areasColision.isEmpty()) {//isEmpty significa vacio.
            areasColision.clear();//Borras los objetos. Para evitar llenar el array infinitamente.
        }

        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                int puntoX = x * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXint() + MARGEN_X;
                int puntoY = y * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYint() + MARGEN_Y;
                //Si se encuentra un objeto que sea true, se le envuelve con un rectangulo.
                if (colisiones[x + y * this.ancho]) {
                    final Rectangle r = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
                    areasColision.add(r);
                }
            }
        }

    }

    public void dibujar(Graphics g) {
       // int intentosDibujo = 0;
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                BufferedImage imagen = paleta[sprites[x + y * this.ancho]].obtenerImagen();

                int puntoX = x * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXint() + MARGEN_X;
                int puntoY = y * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYint() + MARGEN_Y;
                //Este if evita cargar todas las imagenes que no se muestran por pantalla.
               if(puntoX < 0 - Constantes.LADO_SPRITE || puntoX > Constantes.ANCHO_JUEGO || puntoY < 0 - Constantes.LADO_SPRITE || puntoY > Constantes.ALTO_JUEGO - 65){
                   continue;
                }
               // intentosDibujo++;
                DibujoDebug.dibujarImagen(g, imagen, puntoX, puntoY);
                
               /* g.setColor(Color.green);                  Comprobación de margen de colisiones.
                
                for(int r = 0; r<areasColision.size(); r++){
                    Rectangle area = areasColision.get(r);
                    g.drawRect(area.x, area.y, area.width, area.height);
                }*/
            }
        }
        //System.out.println(intentosDibujo);
        
        if(!enemigos.isEmpty()){
            for(Enemigo enemigo : enemigos){
            final int puntoX = (int)enemigo.obtenerPosicionX() * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXint() + MARGEN_X;
            final int puntoY = (int)enemigo.obtenerPosicionY() * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYint() + MARGEN_Y;
      
             enemigo.dibujar(g, puntoX, puntoY);
            }
        }
    }

    public Rectangle obtenerBordes(final int posicionX, final int posicionY) {
        int x = MARGEN_X - posicionX + ElementosPrincipales.jugador.obtenerAncho();
        int y = MARGEN_Y - posicionY + ElementosPrincipales.jugador.obtenerAlto();
        int ancho = this.ancho * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerAncho() * 2;
        int alto = this.alto * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerAlto() * 2;

        return new Rectangle(x, y, ancho, alto);
    }

}
