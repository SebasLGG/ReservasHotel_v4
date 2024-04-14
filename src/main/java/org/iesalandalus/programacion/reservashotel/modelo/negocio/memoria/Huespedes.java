package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;

import javax.naming.OperationNotSupportedException;

// Clase Huespedes
public class Huespedes implements IHuespedes {

    // Atributos para la clase.
    private ArrayList<Huesped> coleccionHuespedes;

    // Constructor que inicializa la lista de huespedes.
    public Huespedes() {
        coleccionHuespedes = new ArrayList<>();
    }

    // Método que devuelve una copia profunda de la lista.
    public List<Huesped> get() {
        return copiaProfundaHuespedes();
    }

    // Método privado que realiza una copia de la lista.
    private List<Huesped> copiaProfundaHuespedes() {
        List<Huesped> copia = new ArrayList<>();
        Iterator<Huesped> iterador = coleccionHuespedes.iterator();
        while (iterador.hasNext()) {
            Huesped huesped = iterador.next();
            copia.add(new Huesped(huesped));
        }
        return copia;
    }


    // Método que devuelve el tamaño actual de la lista.
    public int getTamano() {
        return coleccionHuespedes.size();
    }

    // Método que inserta un huesped en la lista si no existe.
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }

        // Verificar si el huésped ya existe
        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        }

        coleccionHuespedes.add(huesped);
    }

    // Método que busca un huesped en la lista.
    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede buscar un huésped nulo.");
        }

        Iterator<Huesped> iterador = coleccionHuespedes.iterator();
        while (iterador.hasNext()) {
            Huesped h = iterador.next();
            if (h.equals(huesped)) {
                return h;
            }
        }
        return null;
    }


    // Método para borrar un huesped de la lista.
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }

        if (!coleccionHuespedes.remove(huesped)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
        }
    }
    
    // Métodos comenzar y terminar.
    @Override
    public void comenzar() {
    }

    @Override
    public void terminar() {
    }
}