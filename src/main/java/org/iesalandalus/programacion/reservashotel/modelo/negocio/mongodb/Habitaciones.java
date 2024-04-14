package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades.*;
import javax.naming.OperationNotSupportedException;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

// Clase Habitaciones
public class Habitaciones implements IHabitaciones {

	// Atributo COLECCION.
	private static final String COLECCION = "habitaciones";

	// Coleccion para mongo de coleccionHabitaciones.
    private MongoCollection<Document> coleccionHabitaciones;

    // Constructor que inicializa la lista de habitaciones.
    public Habitaciones() {
    }


    // Método que devuelve una lista de todas las habitaciones en la colección, ordenadas por identificador.
    public List<Habitacion> get() {
        List<Habitacion> listaHabitaciones = new ArrayList<>();

        Iterator<Document> iterador = coleccionHabitaciones.find().sort(new Document(MongoDB.HABITACION_IDENTIFICADOR, 1)).iterator();
        while (iterador.hasNext()) {
            Document documento = iterador.next();
            Habitacion habitacion = MongoDB.getHabitacion(documento);
            listaHabitaciones.add(habitacion);
        }

        return listaHabitaciones;
    }


    // Método que devuelve una lista de habitaciones por tipo en la colección.
    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        List<Habitacion> listaHabitaciones = new ArrayList<>();

        Iterator<Document> iterador = coleccionHabitaciones.find(new Document(MongoDB.HABITACION_TIPO, tipoHabitacion.toString())).iterator();
        while (iterador.hasNext()) {
            Document documento = iterador.next();
            Habitacion habitacion = MongoDB.getHabitacion(documento);
            listaHabitaciones.add(habitacion);
        }

        return listaHabitaciones;
    }


    // Método que devuelve el tamaño actual de la lista.
    public int getTamano() {
        return (int) coleccionHabitaciones.countDocuments();
    }

    
    // Método que inserta una habitacion en la lista si no existe.
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }

        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }

        coleccionHabitaciones.insertOne(MongoDB.getDocumento(habitacion));
    }

    // Método que busca una habitacion en la lista por identificador y tipo.
    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        }

        Document filtro = new Document(MongoDB.IDENTIFICADOR, habitacion.getIdentificador())
                            .append(MongoDB.TIPO, habitacion.getClass().getSimpleName().toUpperCase());

        Document documento = coleccionHabitaciones.find(filtro).first();
        
        if (documento != null) {
            return MongoDB.getHabitacion(documento);
        } else {
            return null;
        }
    }


    // Método para borrar una habitacion de la lista por planta, puerta y tipo de habitación.
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }

        Document filtro = new Document(MongoDB.IDENTIFICADOR, habitacion.getIdentificador())
                .append(MongoDB.TIPO, habitacion.getClass().getSimpleName().toUpperCase());

        if (coleccionHabitaciones.deleteOne(filtro).getDeletedCount() == 0) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        }
    }


    // Método comenzar para obtener la colección de mongoDB de Habitaciones.
    public void comenzar() {
        coleccionHabitaciones = MongoDB.getBD().getCollection(COLECCION);
    }
    
    // Método para terminar la conexión.
    public void terminar() {
        MongoDB.cerrarConexion();
    }
}
