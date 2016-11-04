package principal.maquinaestado.estados.menuinicial;

import java.awt.Graphics;
import java.awt.Rectangle;
import principal.maquinaestado.estados.menujuego.SeccionMenu;

public class Exit extends SeccionMenu {

    public Exit(String nombreSeccion, Rectangle etiquetaMenu) {
        super(nombreSeccion, etiquetaMenu);
    }

    public void actualizar() {
        System.exit(0);
    }

    public void dibujar(Graphics g) {
        
    }

}
