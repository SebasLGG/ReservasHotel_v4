package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;
import javax.naming.OperationNotSupportedException;
import java.util.Iterator;

public class Habitaciones implements IHabitaciones {

    private ArrayList<Habitacion> coleccionHabitaciones;

    // Constructor que inicializa la lista de habitaciones
    public Habitaciones() {
        coleccionHabitaciones = new ArrayList<>();

    }

    // Método para obtener una copia de la lista de habitaciones
    public List<Habitacion> get() {
        return new ArrayList<>(coleccionHabitaciones);

    }

    // Método para obtener una lista de habitaciones filtradas por tipo
    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        List<Habitacion> habitacionesFiltradas = new ArrayList<>();
        Iterator<Habitacion> iterador = coleccionHabitaciones.iterator();
        while (iterador.hasNext()) {
            Habitacion habitacion = iterador.next();
            if (tipoHabitacion == TipoHabitacion.SIMPLE && habitacion instanceof Simple) {
                habitacionesFiltradas.add(habitacion);
            } else if (tipoHabitacion == TipoHabitacion.DOBLE && habitacion instanceof Doble) {
                habitacionesFiltradas.add(habitacion);
            } else if (tipoHabitacion == TipoHabitacion.TRIPLE && habitacion instanceof Triple) {
                habitacionesFiltradas.add(habitacion);
            } else if (tipoHabitacion == TipoHabitacion.SUITE && habitacion instanceof Suite) {
                habitacionesFiltradas.add(habitacion);
            }
        }
        return habitacionesFiltradas;
    }

    // Método para obtener el tamaño de la lista de habitaciones
    public int getTamano() {
        return coleccionHabitaciones.size();
    }

    
    // Método para insertar una habitación en la lista
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }
        if (coleccionHabitaciones.contains(habitacion)) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }
        coleccionHabitaciones.add(habitacion);
    }

    // Método para buscar una habitación en la lista
    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        }
        Iterator<Habitacion> iterador = coleccionHabitaciones.iterator();
        while (iterador.hasNext()) {
            Habitacion h = iterador.next();
            if (h.equals(habitacion)) {
                return h;
            }
        }
        return null;
    }

    // Método para borrar una habitación de la lista
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }
        if (!coleccionHabitaciones.remove(habitacion)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
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