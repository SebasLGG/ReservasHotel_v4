



package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;

import java.time.LocalDateTime;
import java.util.List;
import javax.naming.OperationNotSupportedException;

public class Modelo implements IModelo {

	// Atributos para las interfazes.
	private IFuenteDatos fuenteDatos;
    private IHuespedes huespedes;
    private IHabitaciones habitaciones;
    private IReservas reservas;

    // Modelo modificado para que utilice FactoriaFuenteDatos.
    public Modelo(FactoriaFuenteDatos factoriaFuenteDatos) {
        this.fuenteDatos = factoriaFuenteDatos.crear();
        comenzar();
    }

    public void comenzar() {
        huespedes = fuenteDatos.crearHuespedes();
        habitaciones = fuenteDatos.crearHabitaciones();
        reservas = fuenteDatos.crearReservas();
        huespedes.comenzar();
        habitaciones.comenzar();
        reservas.comenzar();
    }

    // Método terminar
    public void terminar() {
        huespedes.terminar();
        habitaciones.terminar();
        reservas.terminar();
        System.out.println("El modelo ha terminado.");
    }

    //Método insertar huesped
    public void insertar(Huesped huesped) {
        try {
            huespedes.insertar(huesped);
        } catch (OperationNotSupportedException e) {
            System.out.println("Error al insertar el huésped: " + e.getMessage());
        }
    }

    //Método buscar huesped
    public Huesped buscar(Huesped huesped) {
        return huespedes.buscar(huesped);
    }

    //Método borrar huesped
    public void borrar(Huesped huesped) {
        try {
            huespedes.borrar(huesped);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    //Método para obtener la lista de huespedes
    public List<Huesped> getHuespedes() {
        return huespedes.get();
    }

    // Método para insertar una habitación en la lista de habitaciones
    public void insertar(Habitacion habitacion) {
        try {
            habitaciones.insertar(habitacion);
        } catch (OperationNotSupportedException e) {
            
            System.out.println(e.getMessage());
        }
    }

    // Método para buscar una habitación en la lista de habitaciones
    public Habitacion buscar(Habitacion habitacion) {
        return habitaciones.buscar(habitacion);
    }

    // Método para borrar una habitación de la lista de habitaciones
    public void borrar(Habitacion habitacion) {
        try {
            habitaciones.borrar(habitacion);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para obtener la lista de habitaciones
    public List<Habitacion> getHabitaciones() {
        return habitaciones.get();
    }

    // Método para obtener la lista de habitaciones de un tipo específico
    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion) {
        return habitaciones.get(tipoHabitacion);
    }

    // Método para insertar una reserva en la lista de reservas
    public void insertar(Reserva reserva) {
        try {
            reservas.insertar(reserva);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para borrar una reserva de la lista de reservas
    public void borrar(Reserva reserva) {
        try {
            reservas.borrar(reserva);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para buscar una reserva en la lista de reservas
    public Reserva buscar(Reserva reserva) {
        return reservas.buscar(reserva);
    }

    // Método para obtener la lista de reservas
    public List<Reserva> getReservas() {
        return reservas.get();
    }

    // Método para obtener la lista de reservas de un huésped específico
    public List<Reserva> getReservas(Huesped huesped) {
        return reservas.getReservas(huesped);
    }

    // Método para obtener la lista de reservas de un tipo de habitación específico
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        return reservas.getReservas(tipoHabitacion);
    }

    public List<Reserva> getReservas(Habitacion habitacion) {
        return reservas.getReservas(habitacion);
    }
    
    // Método para obtener la lista de reservas futuras de una habitación específica
    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        return reservas.getReservasFuturas(habitacion);
    }

    // Método para realizar el check-in de una reserva
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        try {
            reservas.realizarCheckin(reserva, fecha);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para realizar el check-out de una reserva
    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
        try {
            reservas.realizarCheckout(reserva, fecha);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Método setter para la FuenteDatos.
    public void setFuenteDatos(IFuenteDatos fuenteDatos) {
        this.fuenteDatos = fuenteDatos;
    }

}
