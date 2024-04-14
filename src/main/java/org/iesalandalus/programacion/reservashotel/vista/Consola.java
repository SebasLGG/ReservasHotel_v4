package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;

import org.iesalandalus.programacion.utilidades.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

//Clase Consola
public class Consola {

	// Constructor privado para evitar instancias de la clase.
    private Consola() {
    }

    // Método estático que muestra el menú de opciones.
    public static void mostrarMenu() {
        System.out.println("Menú de opciones:");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.ordinal() + 1 + ". " + opcion);
        }
    }

    // Método estático que permite al usuario elegir una opción del menú.
    public static Opcion elegirOpcion() {
        int numeroOpciones = Opcion.values().length;

        do {
            System.out.print("Elige una opción (1-" + numeroOpciones + "): ");
            int opcion = Entrada.entero();

            if (opcion >= 1 && opcion <= numeroOpciones) {
                return Opcion.values()[opcion - 1];
            } else {
                System.out.println("Opción no válida. Introduce un número entre 1 y " + numeroOpciones + ".");
            }
        } while (true);
    }

    // Método estático que lee y devuelve un objeto Huesped con los datos introducidos por el usuario.
    public static Huesped leerHuesped() {
        System.out.print("Introduce el nombre del huésped: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce el DNI del huésped: ");
        String dni = Entrada.cadena();
        System.out.print("Introduce el número de teléfono del huésped: ");
        String telefono = Entrada.cadena();
        System.out.print("Introduce el correo del huésped: ");
        String correo = Entrada.cadena();
        LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento del huésped (formato dd/MM/yyyy): ");

        Huesped huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
        return huesped;
    }

    // Método estático que busca y devuelve un objeto Huesped por su DNI en la lista de huéspedes.
    public static Huesped getHuespedPorDni() {
        System.out.print("Introduce el DNI del huésped: ");
        String dni = Entrada.cadena();

        String nombre = "Sebas";
        String telefono = "627173879";
        String correo = "sebas@gmail.com";
        LocalDate fechaNacimiento = LocalDate.of(1999, 1, 26);

        Huesped huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
        return huesped;
    }

    // Método estático que lee y devuelve una fecha introducida por el usuario.
    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;

        do {
            try {
                System.out.print(mensaje);
                String fechaStr = Entrada.cadena();
                fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Por favor, utiliza el formato dd/MM/yyyy.");
            }
        } while (!fechaValida);

        return fecha;
    }

    // Método estático que lee y devuelve una fecha y hora introducida por el usuario.
    public static LocalDateTime leerFechaHora(String mensaje) {
        LocalDateTime fechaHora = null;
        boolean fechaHoraValida = false;

        do {
            try {
                System.out.print(mensaje);
                String fechaHoraStr = Entrada.cadena();
                fechaHora = LocalDateTime.parse(fechaHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                fechaHoraValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha y hora incorrecto. Por favor, utiliza el formato dd/MM/yyyy HH:mm.");
            }
        } while (!fechaHoraValida);

        return fechaHora;
    }
    
    // Método estático que lee y devuelve una Habitacion con los datos introducidos por el usuario.
    public static Habitacion leerHabitacion() {
        System.out.print("Introduce el número de planta de la habitación: ");
        int numeroPlanta = Entrada.entero();

        System.out.print("Introduce el número de puerta de la habitación: ");
        int numeroPuerta = Entrada.entero();

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        System.out.print("Introduce el precio de la habitación: ");
        double precio = Entrada.real();

        // Crear la instancia de la habitación según el tipo seleccionado
        switch (tipoHabitacion) {
            case SIMPLE:
                return new Simple(numeroPlanta, numeroPuerta, precio);
            case DOBLE:
                System.out.print("Introduce el número de camas individuales: ");
                int numCamasIndividualesDoble = Entrada.entero();
                System.out.print("Introduce el número de camas dobles: ");
                int numCamasDoblesDoble = Entrada.entero();
                return new Doble(numeroPlanta, numeroPuerta, precio, numCamasIndividualesDoble, numCamasDoblesDoble);
            case TRIPLE:
            	System.out.print("Introduce el número de baños: ");
                int numBanosTriple = Entrada.entero();
            	System.out.print("Introduce el número de camas individuales: ");
                int numCamasIndividualesTriple = Entrada.entero();
                System.out.print("Introduce el número de camas dobles: ");
                int numCamasDoblesTriple = Entrada.entero();
                return new Triple(numeroPlanta, numeroPuerta, precio, numBanosTriple, numCamasIndividualesTriple, numCamasDoblesTriple);
            case SUITE:
            	System.out.print("Introduce el número de baños: ");
                int numBanosSuite = Entrada.entero();
                
            	boolean tieneJacuzzi = false;
                // Solicitar al usuario si la suite tiene jacuzzi
                    System.out.println("¿La suite tiene jacuzzi?");
                    System.out.println("1. No");
                    System.out.println("2. Sí");
                    System.out.print("Seleccione una opción (1/2): ");
                    int opcion = Entrada.entero();
                    tieneJacuzzi = (opcion == 2);
                return new Suite(numeroPlanta, numeroPuerta, precio, numBanosSuite, tieneJacuzzi);
            default:
                System.out.println("Tipo de habitación no válido. Se creará una habitación Simple por defecto.");
                return new Simple(numeroPlanta, numeroPuerta, precio);
        }
    }


 // Método estático que lee y devuelve una Habitacion por su identificador (número de planta y puerta).
    public static Habitacion leerHabitacionPorIdentificador() {
    	System.out.print("Introduce el número de planta de la habitación: ");
        int numeroPlanta = Entrada.entero();

        System.out.print("Introduce el número de puerta de la habitación: ");
        int numeroPuerta = Entrada.entero();

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        
        // Crear la instancia de la habitación según el tipo seleccionado
        switch (tipoHabitacion) {
            case SIMPLE:
                return new Simple(numeroPlanta, numeroPuerta, 100);
            case DOBLE:
                return new Doble(numeroPlanta, numeroPuerta, 90, 2, 0);
            case TRIPLE:
                return new Triple(numeroPlanta, numeroPuerta, 90, 3, 3, 0);
            case SUITE:
                return new Suite(numeroPlanta, numeroPuerta, 90, 1, false);
            default:
                System.out.println("Tipo de habitación no válido. Se creará una habitación Simple por defecto.");
                return new Simple(numeroPlanta, numeroPuerta, 100);
        }
    }


    	// Método estático que lee y devuelve un TipoHabitacion seleccionado por el usuario.
    	public static TipoHabitacion leerTipoHabitacion() {
    		System.out.println("Selecciona el tipo de habitación:");
    		for (TipoHabitacion tipo : TipoHabitacion.values()) {
    			System.out.println(tipo);
    		}
        
    		int opcion = Entrada.entero();
        
    		while (opcion < 1 || opcion > TipoHabitacion.values().length) {
    			System.out.println("Opción no válida. Introduce un número entre 1 y " + TipoHabitacion.values().length + ":");
    			opcion = Entrada.entero();
    		}
    		
    		return TipoHabitacion.values()[opcion - 1];  // Resta 1 al índice seleccionado para obtener el tipo correcto
    	}

    // Método estático que lee y devuelve un Regimen seleccionado por el usuario.
    public static Regimen leerRegimen() {
        System.out.println("Selecciona el tipo de régimen:");
        for (Regimen regimen : Regimen.values()) {
            System.out.println(regimen);
        }

        int opcion = Entrada.entero();

        while (opcion < 1 || opcion > Regimen.values().length) {
            System.out.println("Opción no válida. Introduce un número entre 1 y " + Regimen.values().length + ":");
            opcion = Entrada.entero();
        }

        return Regimen.values()[opcion - 1];  // Resta 1 al índice seleccionado para obtener el régimen correcto
    }


 

    
    
    
}