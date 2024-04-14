package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.modelo.*;

import org.iesalandalus.programacion.reservashotel.vista.Vista;
import org.iesalandalus.programacion.reservashotel.controlador.Controlador;

public class MainApp {

    // Método Main
    public static void main(String[] args) {
    	
        // Procesar los argumentos de la fuente de datos.
        FactoriaFuenteDatos factoria = procesarArgumentosFuenteDatos(args);

        // Crear el modelo usando la factoría.
        IModelo modelo = new Modelo(factoria);
        // Crear la vista y el controlador.
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);

        // Comenzar la aplicación.
        controlador.comenzar();
    }

    // Método para procesar los argumentos de la fuente de datos.
    private static FactoriaFuenteDatos procesarArgumentosFuenteDatos(String[] args) {
        if (args.length == 0) {
            System.out.println("Argumento de fuente de datos no proporcionado. Usando MEMORIA por defecto.");
            return FactoriaFuenteDatos.MEMORIA;
        }

        // Verificar el argumento proporcionado
        String argumento = args[0];
        switch (argumento) {
            case "-fdmemoria":
                System.out.println("Conectando a fuente de datos MEMORIA.");
                return FactoriaFuenteDatos.MEMORIA;
            case "-fdmongodb":
                System.out.println("Conectando a fuente de datos MongoDB.");
                return FactoriaFuenteDatos.MONGODB;
            default:
                System.out.println("Argumento de fuente de datos no válido. Usando MEMORIA por defecto.");
                return FactoriaFuenteDatos.MEMORIA;
        }
    }
}
