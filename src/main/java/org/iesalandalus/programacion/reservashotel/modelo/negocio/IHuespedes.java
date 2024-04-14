package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import java.util.List;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import javax.naming.OperationNotSupportedException;

//Interfaz de huespedes
public interface IHuespedes {

    //Método para obtener la lista de huéspedes
    List<Huesped> get();
    
    //Método para obtener el tamaño de la lista de huéspedes
    int getTamano();
    
    //Método para insertar un huésped en la lista
    void insertar(Huesped huesped) throws OperationNotSupportedException;
    
    //Método para buscar un huésped en la lista
    Huesped buscar(Huesped huesped);
    
    //Método para borrar un huésped de la lista
    void borrar(Huesped huesped) throws OperationNotSupportedException;
    
    //Método comenzar y terminar.
    void comenzar();
    void terminar();
    
}
