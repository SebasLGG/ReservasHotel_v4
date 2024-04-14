package org.iesalandalus.programacion.reservashotel.modelo.negocio;


import java.util.List;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

//Interfaz de reservas
public interface IReservas {
	
    //Método para obtener todas las reservas
    List<Reserva> get();
    
    //Método para obtener el tamaño
    int getTamano();
    
    // Método para insertar una nueva reserva
    void insertar(Reserva reserva) throws OperationNotSupportedException;
    
    // Método para buscar una reserva por sus datos
    Reserva buscar(Reserva reserva);
    
    // Método para borrar una reserva
    void borrar(Reserva reserva) throws OperationNotSupportedException;
    
    //Método para obtener las reservas de un huésped
    List<Reserva> getReservas(Huesped huesped);
  //Método para obtener las reservas de un tipo de habitación
    List<Reserva> getReservas(TipoHabitacion tipoHabitacion);
    List<Reserva> getReservas(Habitacion habitacion);
    
    
    //Método para obtener las reservas futuras de una habitación
    List<Reserva> getReservasFuturas(Habitacion habitacion);
    
    //Método para realizar el check-in de una reserva en una fecha
    void realizarCheckin(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException;
    
    //Método para realizar el check-out de una reserva en una fecha
    void realizarCheckout(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException;
    
    //Método comenzar y terminar.
    void comenzar();
    void terminar();
    
}
