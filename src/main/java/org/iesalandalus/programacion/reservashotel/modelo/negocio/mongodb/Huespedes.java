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

// Clase Huespedes
public class Huespedes implements IHuespedes {

	// Atributo COLECCION.
	private static final String COLECCION = "huespedes";

	// Coleccion de Huespedes.
    private MongoCollection<Document> coleccionHuespedes;

    // Constructor que inicializa la lista de huespedes.
    public Huespedes() {
    }


    // Método que devuelve una lista de todos los huéspedes en la colección ordenados por DNI.
    public List<Huesped> get() {
        List<Huesped> listaHuespedes = new ArrayList<>();

        Iterator<Document> iterador = coleccionHuespedes.find().sort(new Document(MongoDB.HUESPED_DNI, 1)).iterator();
        while (iterador.hasNext()) {
            Document documento = iterador.next();
            Huesped huesped = MongoDB.getHuesped(documento);
            listaHuespedes.add(huesped);
        }

        return listaHuespedes;
    }


    // Método que devuelve el tamaño actual de la lista.
    public int getTamano() {
        return (int) coleccionHuespedes.countDocuments();
    }

    
    // Método que inserta un huesped en la lista si no existe.
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }

        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        }

        coleccionHuespedes.insertOne(MongoDB.getDocumento(huesped));
    }

    
    // Método que busca un huesped en la lista.
    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede buscar un huésped nulo.");
        }

        Document documento = coleccionHuespedes.find(new Document("dni", huesped.getDni())).first();
        return MongoDB.getHuesped(documento);
    }


    // Método para borrar un huesped de la lista.
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }

        Document documento = new Document("dni", huesped.getDni());
        if (coleccionHuespedes.deleteOne(documento).getDeletedCount() == 0) {
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
        }
    }
    
    // Método para comenzar la colección de huespedes.
    public void comenzar() {
        coleccionHuespedes = MongoDB.getBD().getCollection(COLECCION);
    }
    
    // Método para terminar la conexión.
    public void terminar() {
        MongoDB.cerrarConexion();
    }
}
