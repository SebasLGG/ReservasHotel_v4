package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;

public class Reservas implements IReservas {

	// Atributo COLECCION.
    private static final String COLECCION = "reservas";

    // Atributo para la colección de reservas.
    private MongoCollection<Document> coleccionReservas;

    public Reservas() {
    }

    // Método que devuelve una lista de reservas en la colección ordenadas por fecha de inicio de reserva.
    public List<Reserva> get() {
        List<Reserva> listaReservas = new ArrayList<>();

        Iterator<Document> iterador = coleccionReservas.find().sort(new Document(MongoDB.FECHA_INICIO_RESERVA, 1)).iterator();
        while (iterador.hasNext()) {
            Document documento = iterador.next();
            Reserva reserva = MongoDB.getReserva(documento);
            listaReservas.add(reserva);
        }

        return listaReservas;
    }

    // Método que devuelve el tamaño actual de la lista.
    public int getTamano() {
        return (int) coleccionReservas.countDocuments();
    }

    // Método que inserta una reserva en la lista si no existe.
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }

        if (buscar(reserva) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva con ese identificador.");
        }

        Document documentoReserva = MongoDB.getDocumento(reserva);

        coleccionReservas.insertOne(documentoReserva);
    }


    // Método para buscar una reserva en la lista por identificador.
    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        }

        Document documento = coleccionReservas.find(new Document("habitacion.identificador", reserva.getHabitacion().getIdentificador())).first();
        return MongoDB.getReserva(documento);
    }


    // Método para borrar una habitación.
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }

        Document documento = new Document("habitacion.identificador", reserva.getHabitacion().getIdentificador());
        if (coleccionReservas.deleteOne(documento).getDeletedCount() == 0) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
    }

    // Método para obtener las reservas de un huésped por su DNI
    public List<Reserva> getReservas(Huesped huesped) {
        List<Reserva> reservasHuesped = new ArrayList<>();

        Iterator<Document> iterador = coleccionReservas.find(new Document("huesped.dni", huesped.getDni())).iterator();
        while (iterador.hasNext()) {
            Document documento = iterador.next();
            Reserva reserva = MongoDB.getReserva(documento);
            reservasHuesped.add(reserva);
        }

        return reservasHuesped;
    }




    // Método para obtener las reservas por tipo de habitación
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        List<Reserva> reservasTipoHabitacion = new ArrayList<>();

        String tipo = null;
        switch (tipoHabitacion) {
            case SIMPLE:
                tipo = MongoDB.TIPO_SIMPLE;
                break;
            case DOBLE:
                tipo = MongoDB.TIPO_DOBLE;
                break;
            case TRIPLE:
                tipo = MongoDB.TIPO_TRIPLE;
                break;
            case SUITE:
                tipo = MongoDB.TIPO_SUITE;
                break;
            default:
                break;
        }

        Iterator<Document> iterador = coleccionReservas.find(new Document(MongoDB.HABITACION_TIPO, tipo)).iterator();
        while (iterador.hasNext()) {
            Document documento = iterador.next();
            Reserva reserva = MongoDB.getReserva(documento);
            reservasTipoHabitacion.add(reserva);
        }

        return reservasTipoHabitacion;
    }



    public List<Reserva> getReservas(Habitacion habitacion) {
        List<Reserva> reservasHabitacion = new ArrayList<>();

        Iterator<Document> iterador = coleccionReservas.find(new Document(MongoDB.HABITACION_IDENTIFICADOR, habitacion.getIdentificador())).iterator();
        while (iterador.hasNext()) {
            Document documento = iterador.next();
            Reserva reserva = MongoDB.getReserva(documento);
            reservasHabitacion.add(reserva);
        }

        return reservasHabitacion;
    }

    // Método para obtener las reservas futuras de una habitación específica
    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        List<Reserva> reservasFuturas = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();

        Iterator<Document> iterador = coleccionReservas.find(
                new Document(MongoDB.HABITACION_IDENTIFICADOR, habitacion.getIdentificador())
                        .append(MongoDB.FECHA_FIN_RESERVA, new Document("$gt", fechaActual.format(MongoDB.FORMATO_DIA))))
                .iterator();

        while (iterador.hasNext()) {
            Document documento = iterador.next();
            Reserva reserva = MongoDB.getReserva(documento);
            reservasFuturas.add(reserva);
        }

        return reservasFuturas;
    }

    // Método para realizar el check-in de una reserva
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
        reserva.setCheckIn(fecha);
        Document filter = new Document("habitacion.identificador", reserva.getHabitacion().getIdentificador());
        Document update = new Document("$set", new Document(MongoDB.CHECKIN, fecha.format(MongoDB.FORMATO_DIA_HORA)));
        coleccionReservas.updateOne(filter, update);

        reserva.setCheckIn(fecha);
    }

    // Método para realizar el check-out de una reserva
    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
        reserva.setCheckOut(fecha);
        Document filter = new Document("habitacion.identificador", reserva.getHabitacion().getIdentificador());
        Document update = new Document("$set", new Document(MongoDB.CHECKOUT, fecha.format(MongoDB.FORMATO_DIA_HORA)));
        coleccionReservas.updateOne(filter, update);

        reserva.setCheckOut(fecha);
    }



    // Método para comenzar la colección de huespedes.
    public void comenzar() {
        coleccionReservas = MongoDB.getBD().getCollection(COLECCION);
    }

    // Método para terminar la conexión.
    public void terminar() {
        MongoDB.cerrarConexion();
    }
}
