package principal.graficos;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Ventana extends JFrame {

    private static final long serialVersionUID = 2L;

    private String titulo;
    //private final ImageIcon icono;

    public Ventana(final String titulo, final SuperficieDibujo sd) {
        this.titulo = titulo;

   // BufferedImage imagen = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/iconoVentana.png");
        //this.icono = new ImageIcon(imagen);
        configurarVentana(sd);
    }

    private void configurarVentana(final SuperficieDibujo sd) {
        setTitle(titulo);
        // setIconImage(icono.getImage());//Icono del juego tamaños 128x128 pixeles.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        add(sd, BorderLayout.CENTER);
        setUndecorated(true);//Sin bordes la ventana.
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
