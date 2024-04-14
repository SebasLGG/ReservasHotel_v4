package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb;

import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;



public class FuenteDatosMongoDB implements IFuenteDatos {

	// Métodos para gestionar los huespedes, habitaciones y reservas en mongoDB.
    @Override
    public IHuespedes crearHuespedes() {
        return new Huespedes();
    }

    @Override
    public IHabitaciones crearHabitaciones() {
        return new Habitaciones();
    }

    @Override
    public IReservas crearReservas() {
        return new Reservas();
    }
}
