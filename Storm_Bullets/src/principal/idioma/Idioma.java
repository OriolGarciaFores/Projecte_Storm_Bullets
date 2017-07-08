
package principal.idioma;

import java.io.IOException;
import java.util.Properties;


public class Idioma extends Properties {
    
    private String idiomaActual;
    
    public Idioma(String idioma){
        this.idiomaActual = idioma;
          switch(this.idiomaActual){
            case "es":
                    getProperties("/lenguajes/espanol.properties");
                    break;
            case "en":
                    getProperties("/lenguajes/ingles.properties");
                    break;
            default:
                    getProperties("/lenguajes/espanol.properties");
        }
 
    }
 
    private void getProperties(String idioma) {
        try {
            this.load( getClass().getResourceAsStream(idioma) );
        } catch (IOException ex) {
            System.out.println("Error la carga de idiomas. No se ha encontrado el fichero.");
        }
   }
    
    public void cambiarIdioma(String idiomaNuevo){
        this.idiomaActual = idiomaNuevo;
    }
    
    public String getIdiomaActual(){
        return idiomaActual;
    }
}
