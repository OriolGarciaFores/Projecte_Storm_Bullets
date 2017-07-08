
package principal;

import java.util.ArrayList;
import principal.entes.Jugador;
import principal.herramientas.Settings;
import principal.idioma.Idioma;
import principal.inventario.Inventario;
import principal.mapas.DatosMapas;
import principal.mapas.MapaTiled;


public class ElementosPrincipales {
    public static Settings settings = new Settings();
    public static Idioma idioma = new Idioma(settings.getProperty("idioma"));
    public static ArrayList<DatosMapas> datosMapa = new ArrayList<>();
    public static MapaTiled mapa = new MapaTiled(Constantes.RUTA_MAPA_HIELO);
    
    public static Jugador jugador = new Jugador();
    public static Inventario inventario = new Inventario();
    
    public static boolean enPartida = false;
}
