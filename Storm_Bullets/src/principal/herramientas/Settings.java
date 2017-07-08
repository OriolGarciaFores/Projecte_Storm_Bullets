package principal.herramientas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Settings extends Properties {

    private final String rutaSettings = "data/setting.properties";

    public Settings() {

        crearFichero();
        getProperties(rutaSettings);
    }

    private void crearFichero() {
        File directorio = new File("data");
        directorio.mkdir();
        Path path = Paths.get(rutaSettings);
        try {
            Files.createFile(path);

        } catch (IOException ex) {
            System.out.println("El fichero 'data' ya existe.");
        }
    }

    private void getProperties(String dataSettingsproperties) {
        try {
            this.load(new FileReader(dataSettingsproperties));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarSettings(String key, String value, String comentario){
        this.setProperty(key, value);
        try {
            this.store(new FileWriter(rutaSettings),comentario);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}