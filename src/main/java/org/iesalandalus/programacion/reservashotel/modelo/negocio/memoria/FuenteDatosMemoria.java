package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;

public class FuenteDatosMemoria implements IFuenteDatos {

	// Métodos para gestionar los huespedes, habitaciones y reservas en memoria.
    @Override
    public IHuespedes crearHuespedes(){
        return new Huespedes();
    }

    @Override
    public IHabitaciones crearHabitaciones(){
        return new Habitaciones();
    }

    @Override
    public IReservas crearReservas(){
        return new Reservas();
    }

}