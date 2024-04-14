package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;


import javax.naming.OperationNotSupportedException;

public class Reservas implements IReservas {

    // Colección para almacenar las reservas
    private ArrayList<Reserva> coleccionReservas;

    // Constructor para inicializar la colección de reservas
    public Reservas() {
        coleccionReservas = new ArrayList<>();
    }

    // Método para obtener una copia de la lista de reservas
    public List<Reserva> get() {
        return new ArrayList<>(coleccionReservas);
    }

    // Método para obtener el tamaño de la colección de reservas
    public int getTamano() {
        return coleccionReservas.size();
    }

    // Método para insertar una reserva en la colección
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }

        Reserva reservaExistente = buscar(reserva);
        if (reservaExistente == null) {
            coleccionReservas.add(reserva);
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }
    }

    // Método para buscar una reserva en la colección
    public Reserva buscar(Reserva reservaBuscada) {
        if (reservaBuscada == null) {
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        }
        Iterator<Reserva> iterador = coleccionReservas.iterator();
        while (iterador.hasNext()) {
            Reserva reserva = iterador.next();
            if (reserva.equals(reservaBuscada)) {
                return reserva;
            }
        }
        return null;
    }

    // Método para borrar una reserva de la colección
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }

        if (!coleccionReservas.remove(reserva)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
    }

    // Método para obtener las reservas de un huésped específico
    public List<Reserva> getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huésped nulo.");
        }

        List<Reserva> reservasHuesped = new ArrayList<>();
        Iterator<Reserva> iterador = coleccionReservas.iterator();
        while (iterador.hasNext()) {
            Reserva reserva = iterador.next();
            if (reserva.getHuesped().equals(huesped)) {
                reservasHuesped.add(reserva);
            }
        }
        return reservasHuesped;
    }

 // Método para obtener las reservas de un tipo de habitación específico
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }

        List<Reserva> reservasTipo = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion != null && habitacion instanceof Simple && tipoHabitacion == TipoHabitacion.SIMPLE) {
                reservasTipo.add(reserva);
            } else if (habitacion != null && habitacion instanceof Doble && tipoHabitacion == TipoHabitacion.DOBLE) {
                reservasTipo.add(reserva);
            } else if (habitacion != null && habitacion instanceof Triple && tipoHabitacion == TipoHabitacion.TRIPLE) {
                reservasTipo.add(reserva);
            } else if (habitacion != null && habitacion instanceof Suite && tipoHabitacion == TipoHabitacion.SUITE) {
                reservasTipo.add(reserva);
            }
        }
        return reservasTipo;
    }

    
    public List<Reserva> getReservas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }

        List<Reserva> reservasHabitacion = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            if (reserva.getHabitacion().equals(habitacion)) {
                reservasHabitacion.add(reserva);
            }
        }
        return reservasHabitacion;
    }

    
    // Método para obtener las reservas futuras de una habitación específica
    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }

        List<Reserva> reservasFuturas = new ArrayList<>();
        Iterator<Reserva> iterador = coleccionReservas.iterator();
        LocalDate fechaActual = LocalDate.now();

        while (iterador.hasNext()) {
            Reserva reserva = iterador.next();
            if (reserva.getHabitacion().equals(habitacion) &&
                    reserva.getFechaInicioReserva().isAfter(fechaActual)) {
                reservasFuturas.add(reserva);
            }
        }
        return reservasFuturas;
    }

    // Método para realizar el check-in de una reserva
    public void realizarCheckin(Reserva reserva, LocalDateTime fechaCheckin) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        }

        Reserva reservaEncontrada = buscar(reserva);
        if (reservaEncontrada == null) {
            throw new OperationNotSupportedException("ERROR: No existe la reserva indicada.");
        }
        
        if (fechaCheckin.isBefore(LocalDateTime.now())) {
            reservaEncontrada.setCheckIn(fechaCheckin);
        } 
            
    }



    // Método para realizar el check-out de una reserva
    public void realizarCheckout(Reserva reserva, LocalDateTime fechaCheckout) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        }

        Reserva reservaEncontrada = buscar(reserva);
        if (reservaEncontrada == null) {
            throw new OperationNotSupportedException("ERROR: No existe la reserva indicada.");
        }

        if (fechaCheckout.isAfter(LocalDateTime.now())) {
            reservaEncontrada.setCheckOut(fechaCheckout);
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
