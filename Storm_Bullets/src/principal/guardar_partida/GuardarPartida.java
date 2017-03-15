package principal.guardar_partida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import principal.Constantes;
import principal.ElementosPrincipales;

public class GuardarPartida {

    private final static Charset cs = java.nio.charset.StandardCharsets.UTF_8;
    private final static String rutaSave = "saves/StormBullets.sav";
    private final static String rutaAuxSave = "saves/StormBulletsAux.sav";

    public static void crearSave() {
        File directorio = new File("saves");
        directorio.mkdir();
        Path path = Paths.get(rutaSave);       
        try {

            Files.createFile(path);
            
        } catch (IOException ex) {
            System.out.println("El fichero 'StormBullets' ya existe.");
        }
    }

    public static void crearSaveAux() {
        Path path = Paths.get(rutaAuxSave);
        try {

            Files.createFile(path);

        } catch (IOException ex) {
            System.out.println("El fichero 'StormBulletsAux' ya existe.");
        }
    }

    public static void modificarSave() {
        Path path = Paths.get(rutaSave);
        crearSaveAux();
        BufferedReader br = null;
        BufferedWriter bw = null;
        Path aux = Paths.get(rutaAuxSave);

        try {
            bw = Files.newBufferedWriter(aux, cs);
            br = Files.newBufferedReader(path, cs);
            String linea;

            while ((linea = br.readLine()) != null) {

                bw.write(linea);
                bw.newLine();

            }
            bw.write(ElementosPrincipales.jugador.getNomJugador() + ";" + ElementosPrincipales.jugador.obtenerPuntuacionJugador() + ";" + Constantes.minutos + ":" + Constantes.segundos);
            bw.newLine();
            bw.close();
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(GuardarPartida.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                bw = Files.newBufferedWriter(path, cs);
                br = Files.newBufferedReader(aux, cs);
                String linea;

                while ((linea = br.readLine()) != null) {
                    bw.write(linea);
                    bw.newLine();

                }
                bw.close();
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(GuardarPartida.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                try {
                    Files.delete(aux);
                } catch (IOException ex) {
                    Logger.getLogger(GuardarPartida.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }
}
