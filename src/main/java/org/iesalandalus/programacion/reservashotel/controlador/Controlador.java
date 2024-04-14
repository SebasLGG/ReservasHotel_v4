package org.iesalandalus.programacion.reservashotel.controlador;

import org.iesalandalus.programacion.reservashotel.modelo.IModelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.vista.*;

import java.time.LocalDateTime;
import java.util.List;


public class Controlador {

    private IModelo modelo;
    private Vista vista;

    // Constructor que recibe instancias de Modelo y Vista
    public Controlador(IModelo modelo, Vista vista) {
        if (modelo == null || vista == null) {
            throw new IllegalArgumentException("ERROR: El modelo y la vista no pueden ser nulos.");
        }
        this.modelo = modelo;
        this.vista = vista;
        vista.setControlador(this);
    }

    // Método para iniciar y terminar la aplicación.
    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    // Método para insertar un huésped en el modelo.
    public void insertar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: El huésped no puede ser nulo.");
        }
        modelo.insertar(huesped);
    }

    // Método para buscar un huésped en el modelo.
    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: El huésped a buscar no puede ser nulo.");
        }
        return modelo.buscar(huesped);
    }

    // Método para borrar un huésped del modelo.
    public void borrar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: El huésped a borrar no puede ser nulo.");
        }
        modelo.borrar(huesped);
    }

    // Método para obtener la lista de huéspedes del modelo.
    public List<Huesped> getHuespedes() {
        if (modelo.getHuespedes() == null) {
            throw new NullPointerException("ERROR: La lista de huéspedes es nula.");
        }
        return modelo.getHuespedes();
    }

    // Método para insertar una habitación en el modelo.
    public void insertar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: La habitación no puede ser nula.");
        }
        modelo.insertar(habitacion);
    }

    // Método para buscar una habitación en el modelo.
    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: La habitación a buscar no puede ser nula.");
        }
        return modelo.buscar(habitacion);
    }

    // Método para borrar una habitación del modelo.
    public void borrar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: La habitación a borrar no puede ser nula.");
        }
        modelo.borrar(habitacion);
    }

    // Método para obtener la lista de habitaciones del modelo
    public List<Habitacion> getHabitaciones() {
        return modelo.getHabitaciones();
    }

    // Método para obtener la lista de habitaciones por tipo del modelo
    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion) {
        return modelo.getHabitaciones(tipoHabitacion);
    }

    // Método para insertar una reserva en el modelo
    public void insertar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("ERROR: La reserva no puede ser nula.");
        }
        modelo.insertar(reserva);
    }

    // Método para borrar una reserva del modelo
    public void borrar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("ERROR: La reserva a borrar no puede ser nula.");
        }
        modelo.borrar(reserva);
    }

    // Método para buscar una reserva en el modelo
    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("ERROR: La reserva a buscar no puede ser nula.");
        }
        return modelo.buscar(reserva);
    }

    // Método para obtener la lista de reservas del modelo
    public List<Reserva> getReservas() {
        return modelo.getReservas();
    }

    // Método para obtener la lista de reservas de un huésped del modelo
    public List<Reserva> getReservas(Huesped huesped) {
        return modelo.getReservas(huesped);
    }

    // Método para obtener la lista de reservas por tipo de habitación del modelo
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        return modelo.getReservas(tipoHabitacion);
    }

    // Método para obtener una lista de reservas por habitación del modelo.
    public List<Reserva> getReservas(Habitacion habitacion) {
        return modelo.getReservas(habitacion);
    }

    // Método para obtener la lista de reservas futuras de una habitación del modelo
    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        return modelo.getReservasFuturas(habitacion);
    }

    // Método para realizar el check-in de una reserva en el modelo
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null || fecha == null) {
            throw new IllegalArgumentException("ERROR: La reserva y la fecha no pueden ser nulas.");
        }
        modelo.realizarCheckin(reserva, fecha);
    }

    // Método para realizar el check-out de una reserva en el modelo
    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null || fecha == null) {
            throw new IllegalArgumentException("ERROR: La reserva y la fecha no pueden ser nulas.");
        }
        modelo.realizarCheckout(reserva, fecha);
    }

}
