package principal.guardar_partida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import principal.Constantes;

public class GuardarPartida {

    private final static Charset cs = java.nio.charset.StandardCharsets.UTF_8;
    private final static String rutaSave = "recursos/saves/StormBullets.sav";
    private final static String rutaAuxSave = "recursos/saves/StormBulletsAux.sav";

    public static void crearSave() {
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
        String[] info;
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
             bw.write(Constantes.nomJugador + ";" + "punts" + ";" + "temps");
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
