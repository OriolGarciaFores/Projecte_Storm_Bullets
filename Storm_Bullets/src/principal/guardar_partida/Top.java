package principal.guardar_partida;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Top  {

    public static ArrayList<Partida> partidas = new ArrayList<>();

    private static Partida partida;

    private final static Charset cs = java.nio.charset.StandardCharsets.UTF_8;
    private final static String rutaSave = "saves/StormBullets.sav";

    public static void leerPartidasFichero() {
        File fichero = new File(rutaSave);
        String[] info;
        Path path = Paths.get(fichero.getAbsolutePath());
        BufferedReader br = null;

        try {

            br = Files.newBufferedReader(path, cs);
            String linea;

            while ((linea = br.readLine()) != null) {
                info = linea.split(";");
                partida = new Partida(info[0], Integer.parseInt(info[1]), info[2]);
                partidas.add(partida);

            }

        } catch (IOException ex) {
            System.out.println("Error al leer el fichero de partidas.");
        }
    }
    
    public static void ordenarPartidas(){
    Collections.sort(partidas);
    }
}
