package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import java.util.List;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import javax.naming.OperationNotSupportedException;

//Interfaz de habitaciones
public interface IHabitaciones {

	//Método para obtener la lista de todas las habitaciones
    List<Habitacion> get();
    
    //Método para obtener la lista de todas las habitaciones por tipo
    List<Habitacion> get(TipoHabitacion tipoHabitacion);
    
    //Método para obtener el tamaño de la lista de habitaciones
    int getTamano();
    
    //Método para insertar una nueva habitación en la lista
    void insertar(Habitacion habitacion) throws OperationNotSupportedException;
    
    //Método para buscar una habitación en la lista
    Habitacion buscar(Habitacion habitacion);
    
    //Método para borrar una habitación de la lista
    void borrar(Habitacion habitacion) throws OperationNotSupportedException;
    
    //Método comenzar y terminar.
    void comenzar();
    void terminar();
    
}
