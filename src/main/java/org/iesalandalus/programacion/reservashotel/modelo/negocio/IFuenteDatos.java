package org.iesalandalus.programacion.reservashotel.modelo.negocio;


public interface IFuenteDatos {

	// Métodos para crear huespedes, habitaciones y reservas.
    IHuespedes crearHuespedes();
    
    IHabitaciones crearHabitaciones();
    
    IReservas crearReservas();
    
}
