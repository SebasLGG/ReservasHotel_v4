package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.FuenteDatosMemoria;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.FuenteDatosMongoDB;

public enum FactoriaFuenteDatos {

	// Métodos para crear una instancia de FuenteDatosMemoria.
    MEMORIA{
        public IFuenteDatos crear(){
            return new FuenteDatosMemoria();
        }
    },
    
    // Métodos para crear una instancia de FuenteDatosMongoDB.
    MONGODB{
        public IFuenteDatos crear(){
            return new FuenteDatosMongoDB();
        }
    };

	// Método abstracto para crear una instancia de IFuenteDatos.
    public abstract IFuenteDatos crear();

}