package org.iesalandalus.programacion.reservashotel.modelo;

import java.time.LocalDateTime;
import java.util.List;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;


public interface IModelo {

	// Métodos para iniciar y terminar el modelo de conexión.
    void comenzar();
    void terminar();

    // Métodos para gestionar huéspedes.
    void insertar(Huesped huesped);
    Huesped buscar(Huesped huesped);
    void borrar(Huesped huesped);
    List<Huesped> getHuespedes();
    
    // Métodos para gestionar las habitaciones.
    void insertar(Habitacion habitacion);
    Habitacion buscar(Habitacion habitacion);
    void borrar(Habitacion habitacion);
    List<Habitacion> getHabitaciones();
    List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion);
    
    // Métodos para gestionar las reservas.
    void insertar(Reserva reserva);
    void borrar(Reserva reserva);
    Reserva buscar(Reserva reserva);
    List<Reserva> getReservas();
    List<Reserva> getReservas(Huesped huesped);
    List<Reserva> getReservas(TipoHabitacion tipoHabitacion);
    List<Reserva> getReservas(Habitacion habitacion);
    List<Reserva> getReservasFuturas(Habitacion habitacion);

    // Métodos para realizar el heckin y checkout de las reservas.
    void realizarCheckin(Reserva reserva, LocalDateTime fecha);
    void realizarCheckout(Reserva reserva, LocalDateTime fecha);

}