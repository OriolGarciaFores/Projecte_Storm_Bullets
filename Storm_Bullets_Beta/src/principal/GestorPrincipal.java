package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaestado.GestorEstados;

public class GestorPrincipal {

    private boolean enFuncionamiento = false;
    private final String titulo;
    private final int ancho;
    private final int alto;

    private SuperficieDibujo sd;
    private Ventana ventana;
    private GestorEstados ge;

    private static int fps = 0;
    private static int aps = 0;

    private GestorPrincipal(final String titulo, final int ancho, final int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public static void main(String[] args) {
        //  if (!System.getProperty("os.name").startsWith("Windows")) {
         System.setProperty("sun.java2d.opengl", "True");
        // }
        //Para mejorar rendimiento en sistemas operativos como en Linux que no usan direx y con eso ejecutamos la grafica.
        
       // System.setProperty("sun.java2d.d3d", "True");
       // System.setProperty("sun.java2d.ddforcevram", "True");
        
        System.setProperty("sun.java2d.transaccel", "True");

        GestorPrincipal gp = new GestorPrincipal("Storm Bullets Beta", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);

        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();

    }

    private void iniciarJuego() {
        enFuncionamiento = true;
        inicializar();
    }

    private void inicializar() {
        sd = new SuperficieDibujo(ancho, alto);
        ventana = new Ventana(titulo, sd);
        ge = new GestorEstados();
    }

    private void iniciarBuclePrincipal() {
        int actualizacionesAcumuladas = 0;
        int framesAcumulados = 0;

        final int NS_POR_SEGUNDO = 1000000000;
        final int APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0;

        while (enFuncionamiento) {

            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            while (delta >= 1) {
                actualizar();
                actualizacionesAcumuladas++;

                delta--;

            }
            dibujar();
            framesAcumulados++;

            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                aps = actualizacionesAcumuladas;
                fps = framesAcumulados;
                actualizacionesAcumuladas = 0;
                framesAcumulados = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }

    private void actualizar() {
        if (!GestorControles.teclado.tituloActivo && !GestorControles.teclado.menuActivo && !GestorControles.teclado.config && !GestorControles.teclado.nombrarJugador && !GestorControles.teclado.ranking && !GestorControles.teclado.muerto) {
            ge.cambiarEstadoActual(1);
            
            

        } else if (!GestorControles.teclado.tituloActivo && GestorControles.teclado.menuActivo && !GestorControles.teclado.nombrarJugador && !GestorControles.teclado.ranking && !GestorControles.teclado.muerto) {
            ge.cambiarEstadoActual(2);
        }
        if (GestorControles.teclado.ranking && !GestorControles.teclado.nombrarJugador && GestorControles.teclado.tituloActivo && !GestorControles.teclado.menuActivo && !GestorControles.teclado.config) {

            ge.cambiarEstadoActual(5);
            if (GestorControles.teclado.menuActivo) {
                GestorControles.teclado.menuActivo = false;
            }
        }
        if (GestorControles.teclado.tituloActivo && GestorControles.teclado.config && !GestorControles.teclado.nombrarJugador && !GestorControles.teclado.ranking
                || !GestorControles.teclado.tituloActivo && GestorControles.teclado.config && !GestorControles.teclado.nombrarJugador && !GestorControles.teclado.ranking) {
            ge.cambiarEstadoActual(3);
            if (!GestorControles.teclado.menuActivo) {
                GestorControles.teclado.menuActivo = true;
            }
        } else if (GestorControles.teclado.tituloActivo && !GestorControles.teclado.config && !GestorControles.teclado.nombrarJugador && !GestorControles.teclado.ranking && !GestorControles.teclado.muerto) {
            ge.cambiarEstadoActual(0);
            if (GestorControles.teclado.menuActivo) {
                GestorControles.teclado.menuActivo = false;
            }
        }
        if (GestorControles.teclado.nombrarJugador && !GestorControles.teclado.tituloActivo && !GestorControles.teclado.menuActivo && !GestorControles.teclado.config && !GestorControles.teclado.ranking) {
            ge.cambiarEstadoActual(4);
        }
        
        if(GestorControles.teclado.muerto){
                ge.cambiarEstadoActual(6);
            }

        ge.actualizar();
    }

    private void dibujar() {
        sd.dibujar(ge);
    }

    public static int obtenerFPS() {
        return fps;
    }

    public static int obtenerAPS() {
        return aps;
    }

}
