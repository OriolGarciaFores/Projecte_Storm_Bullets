package principal.mapas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.entes.Enemigo;
import principal.entes.RegistroEnemigos;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class MapaTiled {

    private int anchoMapaTiles;
    private int altoMapaTiles;
    private String rutaMapa;

    private int contador = 0;
    
    private Point puntoInicial;

    private ArrayList<CapaSprites> capasSprites;
    private ArrayList<CapaColisiones> capasColisiones;

    public ArrayList<Rectangle> areasColisionesOriginales;
    public ArrayList<Rectangle> areasColisionPorActualizacion;
    public ArrayList<PuertaSalida> puertas;

    private Sprite[] paletaSprites;

    private ArrayList<Enemigo> enemigosMapa;

    public MapaTiled(String ruta) {
        this.rutaMapa = ruta;
        String contenido = CargadorRecursos.leerArchivoTexto(this.rutaMapa);

        //ANCHO, ALTO del Mapa.
        JSONObject globalJSON = obtenerObjetoJSON(contenido);
        anchoMapaTiles = obtenerIntDesdeJSON(globalJSON, "width");
        altoMapaTiles = obtenerIntDesdeJSON(globalJSON, "height");

        //PUNTO INICIAL
        JSONObject puntoInicial = obtenerObjetoJSON(globalJSON.get("start").toString());
        this.puntoInicial = new Point(obtenerIntDesdeJSON(puntoInicial, "x"), obtenerIntDesdeJSON(puntoInicial, "y"));

        //CAPAS
        JSONArray capas = obtenerArrayJSON(globalJSON.get("layers").toString());

        this.capasSprites = new ArrayList<>();
        this.capasColisiones = new ArrayList<>();

        //INICIAR CAPAS
        for (int i = 0; i < capas.size(); i++) {
            JSONObject datosCapa = obtenerObjetoJSON(capas.get(i).toString());

            int anchoCapa = obtenerIntDesdeJSON(datosCapa, "width");
            int altoCapa = obtenerIntDesdeJSON(datosCapa, "height");
            int xCapa = obtenerIntDesdeJSON(datosCapa, "x");
            int yCapa = obtenerIntDesdeJSON(datosCapa, "y");
            String tipo = datosCapa.get("type").toString();

            switch (tipo) {
                case "tilelayer":
                    JSONArray sprites = obtenerArrayJSON(datosCapa.get("data").toString());
                    int[] spritesCapa = new int[sprites.size()];
                    for (int j = 0; j < sprites.size(); j++) {
                        int codigoSprite = Integer.parseInt(sprites.get(j).toString());
                        spritesCapa[j] = codigoSprite - 1;// Modificamos el valor de los sprites. Proque en json 0 = vacio. y 1 el 1r sprite. En el codigo 1r sprite tiene que ser 0.

                    }
                    this.capasSprites.add(new CapaSprites(anchoCapa, altoCapa, xCapa, yCapa, spritesCapa));
                    break;
                case "objectgroup":
                    JSONArray rectangulos = obtenerArrayJSON(datosCapa.get("objects").toString());
                    Rectangle[] rectangulosCapa = new Rectangle[rectangulos.size()];
                    for (int j = 0; j < rectangulos.size(); j++) {
                        JSONObject datosRectangulo = obtenerObjetoJSON(rectangulos.get(j).toString());
                        int x = obtenerIntDesdeJSON(datosRectangulo, "x");
                        int y = obtenerIntDesdeJSON(datosRectangulo, "y");
                        int ancho = obtenerIntDesdeJSON(datosRectangulo, "width");
                        int alto = obtenerIntDesdeJSON(datosRectangulo, "height");

                        //Modificacion en las colisiones en el caso de colisiones de rectangulos muy pequeños.
                        if (x == 0) {
                            x = 1;
                        }
                        if (y == 0) {
                            y = 1;
                        }
                        if (ancho == 0) {
                            ancho = 1;
                        }
                        if (alto == 0) {
                            alto = 1;
                        }

                        Rectangle rectangulo = new Rectangle(x, y, ancho, alto);
                        rectangulosCapa[j] = rectangulo;
                    }
                    this.capasColisiones.add(new CapaColisiones(anchoCapa, altoCapa, xCapa, yCapa, rectangulosCapa));
                    break;

            }
        }

        //Combinar colisiones en un solo arraylist por eficencia.
        areasColisionesOriginales = new ArrayList<>();
        for (int i = 0; i < capasColisiones.size(); i++) {
            Rectangle[] rectangulos = capasColisiones.get(i).obtenerColisionables();
            for (int j = 0; j < rectangulos.length; j++) {
                areasColisionesOriginales.add(rectangulos[j]);
            }
        }

        //AVERIGUAR TOTAL DE SPRITES EXISTENTES EN TODAS LAS CAPAS.
        JSONArray coleccionesSprites = obtenerArrayJSON(globalJSON.get("tilesets").toString());
        int totalSprites = 0;
        for (int i = 0; i < coleccionesSprites.size(); i++) {
            JSONObject datosGrupo = obtenerObjetoJSON(coleccionesSprites.get(i).toString());
            totalSprites += obtenerIntDesdeJSON(datosGrupo, "tilecount");
        }
        paletaSprites = new Sprite[totalSprites];//ASSIGNO EL TAMAÑO DEL ARRAY QUE TIENE QUE SER.
        //ASIGNAR SPRITES NECESARIOS A LA PALETA A PARTIR DE LAS CAPAS.
        for (int i = 0; i < coleccionesSprites.size(); i++) {
            JSONObject datosGrupo = obtenerObjetoJSON(coleccionesSprites.get(i).toString());

            String nombreImagen = datosGrupo.get("image").toString();
            int anchoTiles = obtenerIntDesdeJSON(datosGrupo, "tilewidth");
            int altoTiles = obtenerIntDesdeJSON(datosGrupo, "tileheight");
            HojaSprites hoja = new HojaSprites("/imagenes/hojasTexturas/" + nombreImagen, anchoTiles, altoTiles, false);

            int primerSpriteColeccion = obtenerIntDesdeJSON(datosGrupo, "firstgid") - 1;//Se resta 1 porque en json el 1r sprite es 1 y no 0.
            int ultimoSpriteColeccion = primerSpriteColeccion + obtenerIntDesdeJSON(datosGrupo, "tilecount") - 1;

            for (int j = 0; j < this.capasSprites.size(); j++) {
                CapaSprites capaActual = this.capasSprites.get(j);
                //Numeros de los sprites.
                int[] spritesCapa = capaActual.obtenerArraySprites();

                for (int k = 0; k < spritesCapa.length; k++) {
                    int idSpriteActual = spritesCapa[k];
                    //Comprobando el sprite que pertenezca a la 1r hoja de sprites.
                    //ORDENAMOS LOS SPRITES POR HOJA.
                    if (idSpriteActual >= primerSpriteColeccion && idSpriteActual <= ultimoSpriteColeccion) {
                        if (paletaSprites[idSpriteActual] == null) {
                            paletaSprites[idSpriteActual] = hoja.obtenerSprite(idSpriteActual - primerSpriteColeccion);
                        }
                    }
                }
            }
        }
        //OBJETOS QUE ESTARAN EN EL MAPA. MEJORA MAS ADELANTE.

        //ENEMIGOS EN EL MAPA.
        enemigosMapa = new ArrayList<>();
        JSONArray coleccionEnemigos = null;
        //BUSCA Y LEE EN JSON EL APARTADO enemigos SI NO LO ENCUENTRA, PUES SALTA UN NULL, PERO CONTINUA EL PROGRAMA. EN ESTE CASO NO SE CREARAN ENEMIGOS.
        try {
            coleccionEnemigos = obtenerArrayJSON(globalJSON.get("enemigos").toString());
        } catch (Exception ex) {
            System.out.println("No hay enemigos.");
        }
        if (coleccionEnemigos != null) {
            for (int i = 0; i < coleccionEnemigos.size(); i++) {
                JSONObject datosEnemigo = obtenerObjetoJSON(coleccionEnemigos.get(i).toString());

                int idEnemigo = obtenerIntDesdeJSON(datosEnemigo, "id");
                int xEnemigo = obtenerIntDesdeJSON(datosEnemigo, "x");
                int yEnemigo = obtenerIntDesdeJSON(datosEnemigo, "y");

                Point posicionEnemigo = new Point(xEnemigo, yEnemigo);
                Enemigo enemigo = RegistroEnemigos.obtenerEnemigo(idEnemigo);
                enemigo.establecerPosicion(posicionEnemigo.x, posicionEnemigo.y);

                enemigosMapa.add(enemigo);
            }
        }
        //ZONAS SALIDA DE MAPA. Y APARICIONES DEL MAPA.
        puertas = new ArrayList<>();
        JSONArray coleccionSalidas = obtenerArrayJSON(globalJSON.get("salidas").toString());
        for (int i = 0; i < coleccionSalidas.size(); i++) {
            JSONObject datosSalida = obtenerObjetoJSON(coleccionSalidas.get(i).toString());

            String nomMapa = datosSalida.get("mapa").toString();
            int xInicial = obtenerIntDesdeJSON(datosSalida, "xinicial");
            int yInicial = obtenerIntDesdeJSON(datosSalida, "yinicial");
            int xFinal = obtenerIntDesdeJSON(datosSalida, "xfinal");
            int yFinal = obtenerIntDesdeJSON(datosSalida, "yfinal");
            int xAparicion = obtenerIntDesdeJSON(datosSalida, "xaparicion");
            int yAparicion = obtenerIntDesdeJSON(datosSalida, "yaparicion");

            Point posicionSalidaInicial = new Point(xInicial, yInicial);
            Point posicionSalidaFinal = new Point(xFinal, yFinal);
            Point pAparicion = new Point(xAparicion, yAparicion);

            PuertaSalida ps = new PuertaSalida(nomMapa, posicionSalidaInicial, posicionSalidaFinal, pAparicion);
            puertas.add(ps);

        }
        System.out.println("Cantidad de salidas (Array): " + puertas.size());

        areasColisionPorActualizacion = new ArrayList<>();
    }

    public void actualizar() {
        actualizarAreasColision();
        actualizarEnemigos();
        contador++;
        if(contador == 60){
            contador = 0;
            Constantes.segundos += 1;
            if(Constantes.segundos == 60){
                Constantes.segundos = 0;
                Constantes.minutos += 1;
                
            }
        }
    }

    private void actualizarEnemigos() {
        if (!enemigosMapa.isEmpty()) {
            for (Enemigo enemigo : enemigosMapa) {

                enemigo.actualizar();
            }
        }
    }

    private void actualizarAreasColision() {
        if (!areasColisionPorActualizacion.isEmpty()) {
            areasColisionPorActualizacion.clear();
        }

        for (int i = 0; i < areasColisionesOriginales.size(); i++) {
            Rectangle rInicial = areasColisionesOriginales.get(i);

            int puntoX = rInicial.x - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
            int puntoY = rInicial.y - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;

            final Rectangle rFinal = new Rectangle(puntoX, puntoY, rInicial.width, rInicial.height);

            areasColisionPorActualizacion.add(rFinal);
        }
    }

    public void dibujar(Graphics g) {
        for (int i = 0; i < capasSprites.size(); i++) {
            int[] spritesCapa = capasSprites.get(i).obtenerArraySprites();

            for (int y = 0; y < altoMapaTiles; y++) {
                for (int x = 0; x < anchoMapaTiles; x++) {
                    int idSpriteActual = spritesCapa[x + y * anchoMapaTiles];
                    if (idSpriteActual != -1) {
                        int puntoX = x * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
                        int puntoY = y * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
                        //Evitar dibujar sprites que no se muestran, para ganar rendimiento.
                        if (puntoX < 0 - Constantes.LADO_SPRITE || puntoX > Constantes.ANCHO_JUEGO || puntoY < 0 - Constantes.LADO_SPRITE || puntoY > Constantes.ALTO_JUEGO - 65) {
                            continue;
                        }

                        DibujoDebug.dibujarImagen(g, paletaSprites[idSpriteActual].obtenerImagen(), puntoX, puntoY);
                    }
                }
            }
        }

        for (int i = 0; i < enemigosMapa.size(); i++) {
            Enemigo enemigo = enemigosMapa.get(i);

            final int puntoX = (int) enemigo.obtenerPosicionX() - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
            final int puntoY = (int) enemigo.obtenerPosicionY() - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
            //Evitar dibujar sprites que no se muestran, para ganar rendimiento.
            if (puntoX < 0 - Constantes.LADO_SPRITE || puntoX > Constantes.ANCHO_JUEGO || puntoY < 0 - Constantes.LADO_SPRITE || puntoY > Constantes.ALTO_JUEGO - 65) {
                continue;
            }
            enemigo.dibujar(g, puntoX, puntoY);
        }
    }

    private JSONObject obtenerObjetoJSON(final String codigoJSON) {
        JSONParser lector = new JSONParser();
        JSONObject objetoJSON = null;

        try {
            Object recuperado = lector.parse(codigoJSON);
            objetoJSON = (JSONObject) recuperado;
        } catch (ParseException e) {
            System.out.println("Posicion: " + e.getPosition() + " " + e);

        }

        return objetoJSON;
    }

    private JSONArray obtenerArrayJSON(final String codigoJSON) {
        JSONParser lector = new JSONParser();
        JSONArray arrayJSON = null;

        try {
            Object recuperado = lector.parse(codigoJSON);
            arrayJSON = (JSONArray) recuperado;
        } catch (ParseException e) {
            System.out.println("Posicion: " + e.getPosition() + " " + e);

        }

        return arrayJSON;
    }

    private int obtenerIntDesdeJSON(final JSONObject objetoJSON, final String clave) {
        return (int) Double.parseDouble(objetoJSON.get(clave).toString());
    }

    public Point obtenerPosicionInicial() {
        return puntoInicial;
    }

    public Rectangle obtenerBordes(final int posicionX, final int posicionY) {
        int x = Constantes.MARGEN_X - posicionX + ElementosPrincipales.jugador.obtenerAncho();
        int y = Constantes.MARGEN_Y - posicionY + ElementosPrincipales.jugador.obtenerAlto();
        int ancho = this.anchoMapaTiles * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerAncho() * 2;
        int alto = this.altoMapaTiles * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerAlto() * 2;

        return new Rectangle(x, y, ancho, alto);
    }

    public String obtenerMapaActual() {
        return rutaMapa;
    }

    public ArrayList<Enemigo> getEnemigosMapa() {
        return enemigosMapa;
    }

}
