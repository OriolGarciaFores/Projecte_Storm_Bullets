package principal.herramientas;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class CargadorRecursos {

    public static BufferedImage cargarImagenCompatibleOpaca(final String ruta) {
        Image imagen = null;

        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.OPAQUE);

        Graphics g = imagenAcelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();

        return imagenAcelerada;
    }

    public static BufferedImage cargarImagenCompatibleTranslucida(final String ruta) {
        Image imagen = null;

        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.TRANSLUCENT);

        Graphics g = imagenAcelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();

        return imagenAcelerada;
    }

    public static String leerArchivoTexto(final String ruta) {
        String contenido = "";

        InputStream entradaBytes = ClassLoader.class.getResourceAsStream(ruta);
        BufferedReader lector = new BufferedReader(new InputStreamReader(entradaBytes));

        String linea;

        //Lectura de lineas.
        try {
            while ((linea = lector.readLine()) != null) {
                //sumamos lineas una tras otra.
                contenido += linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Si se ha leido el archivo, se cierra para tener mejor rendimiento.
                if (entradaBytes != null) {
                    entradaBytes.close();
                }
                if (lector != null) {
                    lector.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return contenido;
    }
    
    public static Font cargarFuente(final String ruta){
        Font fuente = null;
        
        InputStream entradaBytes = ClassLoader.class.getResourceAsStream(ruta);
        
        try {
            fuente = Font.createFont(Font.TRUETYPE_FONT, entradaBytes);
            
        } catch (FontFormatException ex) {
            System.out.println("Error de formato: " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(CargadorRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fuente = fuente.deriveFont(45f);
        
        return fuente;
    }
    
     public static Clip cargarSonidoCambiarVolumen(final String ruta, final float reduccionVolumenDecibelios) {
		Clip clip = null;
		
		try {
			InputStream is = ClassLoader.class.getResourceAsStream(ruta);
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(reduccionVolumenDecibelios);
			//OJO NO FUNCIONA CON OPENJDK Y PULSEAUDIO EN NUCLEOS UBUNTU
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clip;
	}
}
