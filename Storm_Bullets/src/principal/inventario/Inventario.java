package principal.inventario;

import java.util.ArrayList;
import principal.inventario.armas.Arma;
import principal.inventario.consumibles.Consumible;

public class Inventario {

    public final ArrayList<Objeto> objetos;

    public Inventario() {
        objetos = new ArrayList<Objeto>();

        objetos.add(RegistroObjetos.obtenerObjeto(0));
        objetos.add(RegistroObjetos.obtenerObjeto(1));

        objetos.add(RegistroObjetos.obtenerObjeto(500));

    }

    public void recogerObjetos(final ContenedorObjetos co) {
        for (Objeto objeto : co.obtenerObjetos()) {
            if (objetoExiste(objeto)) {
                incrementarObjeto(objeto, objeto.obtenerCantidad());
            } else {
                objetos.add(objeto);
            }
        }
    }

    public void recogerObjetoUnico(final ObjetoUnicoTiled out) {
        //Objeto objeto = out.obtenerObjeto();
        if (objetoExiste(out.obtenerObjeto())) {
                incrementarObjeto(out.obtenerObjeto(), out.obtenerObjeto().obtenerCantidad());
            } else {
                objetos.add(out.obtenerObjeto());
            }
     /*  if (objetoExiste(out.obtenerConsumible())) {
                incrementarObjeto(out.obtenerConsumible(), out.obtenerConsumible().obtenerCantidad());
            } else {
                objetos.add(out.obtenerConsumible());
            }*/
       
    }

    public boolean incrementarObjeto(final Objeto objeto, final int cantidad) {
        boolean incrementado = false;

        for (Objeto objetoActual : objetos) {
            if (objetoActual.obtenerId() == objeto.obtenerId()) {
                objetoActual.incrementarCantidad(cantidad);
                incrementado = true;
                break;
            }

        }
        return incrementado;
    }

    public boolean objetoExiste(final Objeto objeto) {
        boolean existe = false;

        for (Objeto objetoActual : objetos) {
            if (objetoActual.obtenerId() == objeto.obtenerId()) {
                existe = true;
                break;
            }
        }

        return existe;
    }

    public ArrayList<Objeto> obtenerConsumibles() {
        ArrayList<Objeto> consumibles = new ArrayList<>();

        for (Objeto objeto : objetos) {
            if (objeto instanceof Consumible) {//Buscamos si el objeto pertenece a la clase consumibles.
                consumibles.add(objeto);
            }
        }

        return consumibles;
    }

    public ArrayList<Objeto> obtenerArmas() {
        ArrayList<Objeto> armas = new ArrayList<>();

        for (Objeto objeto : objetos) {
            if (objeto instanceof Arma) {//Buscamos si el objeto pertenece a la clase arma.
                armas.add(objeto);
            }
        }

        return armas;
    }

    public Objeto obtenerObjeto(final int id) {
        for (Objeto objetoActual : objetos) {
            if (objetoActual.obtenerId() == id) {
                return objetoActual;
            }
        }

        return null;
    }

}
