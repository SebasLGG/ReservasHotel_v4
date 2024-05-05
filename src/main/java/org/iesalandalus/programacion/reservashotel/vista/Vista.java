package org.iesalandalus.programacion.reservashotel.vista;


import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

import org.iesalandalus.programacion.reservashotel.controlador.*;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.*;


public class Vista {

	// Controlador para interactuar con el modelo de datos
	private Controlador controlador;

	
	// Método para establecer el controlador
	public void setControlador(Controlador controlador) {
	    if (controlador == null) {
	        throw new IllegalArgumentException("El controlador no puede ser nulo.");
	    }
	    this.controlador = controlador;
	}


	// Método para comenzar la aplicación
	public void comenzar() {
	    Opcion opcion;
	    do {
	        Consola.mostrarMenu();
	        opcion = Consola.elegirOpcion();
	        
	        // Llamar directamente a los métodos correspondientes según la opción elegida
	        switch (opcion) {
	            case INSERTAR_HUESPED:
	                insertarHuesped();
	                break;
	            case BUSCAR_HUESPED:
	                buscarHuesped();
	                break;
	            case BORRAR_HUESPED:
	                borrarHuesped();
	                break;
	            case MOSTRAR_HUESPEDES:
	                mostrarHuespedes();
	                break;
	            case INSERTAR_HABITACION:
	                insertarHabitacion();
	                break;
	            case BUSCAR_HABITACION:
	                buscarHabitacion();
	                break;
	            case BORRAR_HABITACION:
	                borrarHabitacion();
	                break;
	            case MOSTRAR_HABITACIONES:
	                mostrarHabitaciones();
	                break;
	            case INSERTAR_RESERVA:
	                insertarReserva();
	                break;
	            case ANULAR_RESERVA:
	                anularReserva();
	                break;
	            case MOSTRAR_RESERVAS:
	                mostrarReservas();
	                break;
	            case LISTAR_RESERVAS_HUESPED:
	                mostrarReservasHuesped();
	                break;
	            case LISTAR_RESERVAS_TIPO_HABIACION:
	                mostrarReservasTipoHabitacion();
	                break;
	            case CONSULTAR_DISPONIBILIDAD:
	                consultarDisponibilidad();
	                break;
	            case REALIZAR_CHECKIN:
	                realizarCheckin();
	                break;
	            case REALIZAR_CHECKOUT:
	                realizarCheckout();
	                break;
	            case SALIR:
	                terminar();
	                break;
	            default:
	                System.out.println("Opción no válida.");
	        }
	    } while (opcion != Opcion.SALIR);
	}

	// Método para terminar la aplicación
	public void terminar() {
		System.out.println("Hasta luego!");
	}


	// Método para insertar un huésped
	public void insertarHuesped() {
	    try {
	        Huesped huesped = Consola.leerHuesped();
	        this.controlador.insertar(huesped);
	        System.out.println("Huésped insertado correctamente.");
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al insertar el huésped: " + e.getMessage());
	    }
	}

	// Método para buscar un huésped
	public void buscarHuesped() {
	    try {
	    	Huesped huesped = Consola.getHuespedPorDni();
	        Huesped huespedEncontrado = this.controlador.buscar(huesped);

	        if (huespedEncontrado != null) {
	            System.out.println("Huésped encontrado:\n" + huespedEncontrado);
	        } else {
	            System.out.println("Huésped no encontrado.");
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}

	// Método para borrar un huésped
	public void borrarHuesped() {
	    try {
	    	Huesped huesped = Consola.getHuespedPorDni();

	        // Verifica si el huésped existe antes de intentar eliminarlo
	        if (this.controlador.buscar(huesped) != null) {
	            this.controlador.borrar(huesped);
	        } else {
	            System.out.println("No se encontró un huésped con los datos proporcionados.");
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al borrar el huésped: " + e.getMessage());
	    }
	}

	// Método para mostrar todos los huéspedes
	public void mostrarHuespedes() {
        List<Huesped> listaHuespedes = this.controlador.getHuespedes();

        if (listaHuespedes.isEmpty()) {
            System.out.println("No hay huéspedes para mostrar.");
        } else {
            // Ordenar la lista de huéspedes alfabéticamente por nombre
            Collections.sort(listaHuespedes, (h1, h2) -> h1.getNombre().compareToIgnoreCase(h2.getNombre()));

            System.out.println("------ LISTA DE HUÉSPEDES ------");
            for (Huesped huesped : listaHuespedes) {
                if (huesped != null) {
                    System.out.println(huesped);
                }
            }
        }
    }

	// Método para insertar una habitación
	public void insertarHabitacion() {
	    try {
	        Habitacion habitacion = Consola.leerHabitacion();
	        this.controlador.insertar(habitacion);
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al insertar la habitación: " + e.getMessage());
	    
	    }
	}

	// Método para buscar una habitación
	public void buscarHabitacion() {
	    try {
	        Habitacion habitacion = Consola.leerHabitacionPorIdentificador();
	        Habitacion habitacionEncontrada = this.controlador.buscar(habitacion);
	        
	        if (habitacionEncontrada != null) {
	            System.out.println("Habitación encontrada: " + habitacionEncontrada);
	        } else {
	            System.out.println("No se encontró una habitación con el identificador proporcionado.");
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al buscar la habitación: " + e.getMessage());
	    }
	}
	
	// Método para borrar una habitación
	public void borrarHabitacion() {
	    try {
	        Habitacion habitacion = Consola.leerHabitacionPorIdentificador();

	        // Verifica si la habitación existe antes de intentar eliminarla
	        if (this.controlador.buscar(habitacion) != null) {
	            this.controlador.borrar(habitacion);
	        } else {
	            System.out.println("No se encontró una habitación con el identificador proporcionado.");
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al borrar la habitación: " + e.getMessage());
	    }
	}

	// Método para mostrar todas las habitaciones
	public void mostrarHabitaciones() {
	    List<Habitacion> habitaciones = this.controlador.getHabitaciones();
	    
	    if (habitaciones.isEmpty()) {
	        System.out.println("No hay habitaciones para mostrar.");
	    } else {
	        System.out.println("------ LISTA DE HABITACIONES ------");

	        // Ordenar las habitaciones por planta y puerta
	        Collections.sort(habitaciones, Comparator.comparing(Habitacion::getPlanta)
	                                                   .thenComparing(Habitacion::getPuerta));

	        for (Habitacion habitacion : habitaciones) {
	            if (habitacion != null) {
	                System.out.println(habitacion);
	            }
	        }
	    }
	}

	// Método para insertar una reserva
	public void insertarReserva() {
	    try {
	        System.out.println("Introduce los datos de la reserva:");

	        Huesped huesped = Consola.leerHuesped();
	        Habitacion habitacionReserva = Consola.leerHabitacion(); 

	        System.out.print("Introduce la fecha de inicio de la reserva (formato dd/MM/yyyy): ");
	        LocalDate fechaInicioReserva = Consola.leerFecha(" ");

	        System.out.print("Introduce la fecha de fin de la reserva (formato dd/MM/yyyy): ");
	        LocalDate fechaFinReserva = Consola.leerFecha(" ");

	        System.out.print("Introduce el número de personas: ");
	        int numeroPersonas = Entrada.entero();

	        Regimen regimen = Consola.leerRegimen();

	        Reserva reserva = new Reserva(huesped, habitacionReserva, regimen, fechaInicioReserva, fechaFinReserva, numeroPersonas);

	        // Obtener la habitación de la reserva
	        Habitacion habitacion = reserva.getHabitacion();

	        // Obtener el tipo de habitación de la habitación asociada a la reserva
	        TipoHabitacion tipoHabitacion = null;
	        if (habitacion instanceof Simple) {
	            tipoHabitacion = TipoHabitacion.SIMPLE;
	        } else if (habitacion instanceof Doble) {
	            tipoHabitacion = TipoHabitacion.DOBLE;
	        } else if (habitacion instanceof Triple) {
	            tipoHabitacion = TipoHabitacion.TRIPLE;
	        } else if (habitacion instanceof Suite) {
	            tipoHabitacion = TipoHabitacion.SUITE;
	        }

	        // Obtener la fecha de inicio y fin de la reserva
	        LocalDate fechaInicio = reserva.getFechaInicioReserva();
	        LocalDate fechaFin = reserva.getFechaFinReserva();

	        // Obtener la lista de reservas para el tipo de habitación
	        List<Reserva> reservas = controlador.getReservas(tipoHabitacion);

	        // Verificar si hay alguna reserva para el rango de fechas especificado
	        boolean hayReservas = reservas.stream()
	                .anyMatch(r ->
	                        // Verificar si la reserva coincide con alguna fecha dentro del rango
	                        (r.getFechaInicioReserva().isBefore(fechaFin) || r.getFechaInicioReserva().isEqual(fechaFin))
	                                && (r.getFechaFinReserva().isAfter(fechaInicio) || r.getFechaFinReserva().isEqual(fechaInicio)));

	        if (!hayReservas) {
	            // La habitación está disponible, insertar la reserva
	            this.controlador.insertar(reserva);
	            System.out.println("Reserva insertada correctamente.");
	        } else {
	            // La habitación no está disponible
	            System.out.println("Lo siento, la habitación de tipo " + tipoHabitacion + " no está disponible para el rango de fechas especificado!");
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al insertar la reserva: " + e.getMessage());
	    }
	}



	// Método para mostrar todas las reservas de un huésped
	public void mostrarReservasHuesped() {
	    try {
	        // Obtener el huésped usando getHuespedPorDni
	        Huesped huesped = Consola.getHuespedPorDni();

	        // Llamar al método listarReservasHuesped con el huésped obtenido
	        listarReservas(huesped);
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al mostrar las reservas del huésped: " + e.getMessage());
	    }
	}

	// Método para listar todas las reservas de un huésped
	public void listarReservas(Huesped huesped) {
	    try {
	        // Llamar al método del controlador para obtener la lista de reservas del huésped
	        List<Reserva> reservasHuesped = new ArrayList<>(this.controlador.getReservas(huesped));

	        // Ordenar las reservas por fecha de inicio en orden descendente y luego por planta y puerta en orden ascendente
	        reservasHuesped.sort(
	                Comparator.comparing(Reserva::getFechaInicioReserva)
	                          .thenComparing((r1, r2) -> {
	                              int comparacionPlanta = Integer.compare(r1.getHabitacion().getPlanta(), r2.getHabitacion().getPlanta());
	                              if (comparacionPlanta != 0) {
	                                  return comparacionPlanta;
	                              } else {
	                                  return Integer.compare(r1.getHabitacion().getPuerta(), r2.getHabitacion().getPuerta());
	                              }
	                          })
	        );

	        // Mostrar las reservas del huésped ordenadas
	        if (reservasHuesped.isEmpty()) {
	            System.out.println("No hay reservas para el huésped indicado.");
	        } else {
	            System.out.println("------ RESERVAS DEL HUÉSPED ------");
	            for (Reserva reserva : reservasHuesped) {
	                System.out.println(reserva);
	            }
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al listar reservas del huésped: " + e.getMessage());
	    }
	}

	// Método para mostrar todas las reservas de un tipo de habitación
	public void mostrarReservasTipoHabitacion() {
	    try {
	        // Solicitar al usuario que ingrese el tipo de habitación
	        TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();

	        // Llamar al método para listar las reservas del tipo de habitación
	        listarReservas(tipoHabitacion);
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al mostrar las reservas del tipo de habitación: " + e.getMessage());
	    }
	}


	

	// Método para comprobar la disponibilidad de habitaciones
	public void comprobarDisponibilidad() {
	    
	}

	
	
	
	// Método para mostrar todas las reservas de un tipo de habitación
	public void listarReservas(TipoHabitacion tipoHabitacion) {
	    try {
	        // Llamar al método del controlador para obtener la lista de reservas del tipo de habitación
	        List<Reserva> reservasTipoHabitacion = new ArrayList<>(this.controlador.getReservas(tipoHabitacion));

	        // Ordenar las reservas por fecha de inicio en orden descendente
	        reservasTipoHabitacion.sort(
	            Comparator.comparing(Reserva::getFechaInicioReserva)
	                .thenComparing((r1, r2) -> {
	                    if (r1.getFechaInicioReserva().equals(r2.getFechaInicioReserva())) {
	                        return r1.getHuesped().getNombre().compareTo(r2.getHuesped().getNombre());
	                    } else {
	                        return 0;
	                    }
	                })
	        );

	        // Mostrar las reservas del tipo de habitación ordenadas
	        if (reservasTipoHabitacion.isEmpty()) {
	            System.out.println("No hay reservas para el tipo de habitación indicado.");
	        } else {
	            System.out.println("------ RESERVAS DEL TIPO DE HABITACIÓN ------");
	            for (Reserva reserva : reservasTipoHabitacion) {
	                System.out.println(reserva);
	            }
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al listar reservas del tipo de habitación: " + e.getMessage());
	    }
	}


	// Método para obtener reservas anulables
	public List<Reserva> getReservasAnulables(List<Reserva> reservasAAnular) {
	    List<Reserva> reservasAnulables = new ArrayList<>();
	    try {
	        // Filtrar las reservas que aún no han llegado a la fecha
	        reservasAnulables = reservasAAnular.stream()
	                .filter(reserva -> reserva.getFechaInicioReserva().isAfter(LocalDate.now()))
	                .collect(Collectors.toList());
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al obtener reservas anulables: " + e.getMessage());
	    }
	    return reservasAnulables;
	}

	// Método para anular una reserva
	public void anularReserva() {
	    try {
	        // Solicitar al usuario el dni del huésped
	    	Huesped huesped = Consola.getHuespedPorDni();
	        
	        // Obtener la lista de reservas del huésped
	        List<Reserva> reservasHuesped = new ArrayList<>(this.controlador.getReservas(huesped));

	        // Mostrar las reservas del huésped
	        if (reservasHuesped.isEmpty()) {
	            System.out.println("El huésped no tiene reservas.");
	            return;
	        }
	        
	        System.out.println("Reservas del huésped:");
	        for (int i = 0; i < reservasHuesped.size(); i++) {
	            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
	        }

	        // Solicitar al usuario el número de reserva a anular
	        System.out.print("Ingrese el número de reserva a anular: ");
	        int numeroReserva = Entrada.entero();

	        // Verificar si el número de reserva es válido
	        if (numeroReserva < 1 || numeroReserva > reservasHuesped.size()) {
	            System.out.println("Número de reserva no válido.");
	            return;
	        }

	        // Obtener la reserva seleccionada
	        Reserva reservaSeleccionada = reservasHuesped.get(numeroReserva - 1);

	        // Llamar al método del controlador para anular la reserva
	        this.controlador.borrar(reservaSeleccionada);
	        System.out.println("Reserva anulada correctamente.");
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al anular la reserva: " + e.getMessage());
	    }
	}

	// Método para mostrar todas las reservas
	public void mostrarReservas() {
	    System.out.println("------ MENÚ DE RESERVAS ------");
	    System.out.println("1. Ver todas las reservas");
	    System.out.println("2. Listar reservas anulables");
	    
	    int opcionMenuReservas = Entrada.entero(); 

	    switch (opcionMenuReservas) {
	    	case 1:
	    		// Mostrar todas las reservas ordenadas por fecha de inicio en orden descendente
	    		List<Reserva> listaReservas = this.controlador.getReservas();
	    		if (listaReservas.isEmpty()) {
	            System.out.println("No hay reservas para mostrar.");
	    		} else {
	            // Ordenar las reservas por fecha de inicio en orden descendente
	    			// Ordenar las reservas por fecha de inicio en orden descendente y luego por habitación en orden ascendente
	    			listaReservas.sort(
	    			    Comparator.comparing(Reserva::getFechaInicioReserva).reversed()
	    			        .thenComparing((r1, r2) -> {
	    			            if (r1.getFechaInicioReserva().equals(r2.getFechaInicioReserva())) {
	    			                // Comparar por número de planta
	    			                int comparacionPlanta = Integer.compare(r1.getHabitacion().getPlanta(), r2.getHabitacion().getPlanta());
	    			                if (comparacionPlanta == 0) {
	    			                    // Si las plantas son iguales, comparar por número de puerta
	    			                    return Integer.compare(r1.getHabitacion().getPuerta(), r2.getHabitacion().getPuerta());
	    			                } else {
	    			                    return comparacionPlanta;
	    			                }
	    			            } else {
	    			                return 0; // No hay empate en la fecha de inicio
	    			            }
	    			        })
	    			);

	    			// Mostrar las reservas ordenadas
	    			for (Reserva reserva : listaReservas) {
	    			    System.out.println(reserva);
	    			}

	    	}
	        break;
       
	    	case 2:
	    	    // Listar reservas anulables
	    	    List<Reserva> reservasAnulables = this.getReservasAnulables(this.controlador.getReservas());
	    	    if (reservasAnulables.isEmpty()) {
	    	        System.out.println("No hay reservas anulables.");
	    	    } else {
	    	        // Mostrar las reservas anulables
	    	        System.out.println("Reservas anulables:");
	    	        for (Reserva reserva : reservasAnulables) {
	    	            System.out.println(reserva);
	    	        }
	    	    }
	    	    break;
	        default:
	            System.out.println("Opción no válida.");
	            break;
	    }
	}

	// Método para consultar disponibilidad de habitaciones
	public void consultarDisponibilidad() {
        try {
            // Solicitar al usuario el tipo de habitación
            TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();

            // Solicitar al usuario la fecha de inicio
            LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio (dd/MM/yyyy): ");

            // Solicitar al usuario la fecha de fin
            LocalDate fechaFin = Consola.leerFecha("Introduce la fecha de fin (dd/MM/yyyy): ");

            // Obtener la lista de reservas para el tipo de habitación
            List<Reserva> reservas = controlador.getReservas(tipoHabitacion);

            // Verificar si hay alguna reserva para el rango de fechas especificado
            boolean hayReservas = reservas.stream()
                    .anyMatch(reserva ->
                            // Verificar si la reserva coincide con alguna fecha dentro del rango
                            (reserva.getFechaInicioReserva().isEqual(fechaInicio) || reserva.getFechaInicioReserva().isAfter(fechaInicio))
                                    && (reserva.getFechaInicioReserva().isEqual(fechaFin) || reserva.getFechaInicioReserva().isBefore(fechaFin)));

            if (!hayReservas) {
                System.out.println("¡La habitación de tipo " + tipoHabitacion + " está disponible para el rango de fechas especificado!");
            } else {
                System.out.println("¡Lo siento, la habitación de tipo " + tipoHabitacion + " no está disponible para el rango de fechas especificado!");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error al consultar disponibilidad: " + e.getMessage());
        }
    }

	

	// Método para realizar el checkin
	public void realizarCheckin() {
	    try {
	        // Solicitar al usuario el dni del huésped
	    	Huesped huesped = Consola.getHuespedPorDni();
	        // Llamar al método del controlador para obtener la lista de reservas del huésped
	        List<Reserva> reservasHuesped = this.controlador.getReservas(huesped);

	        if (reservasHuesped.isEmpty()) {
	            System.out.println("No hay reservas para el huésped indicado.");
	        } else {
	            // Mostrar las reservas del huésped
	            System.out.println("------ RESERVAS DEL HUÉSPED ------");
	            for (int i = 0; i < reservasHuesped.size(); i++) {
	                System.out.println((i + 1) + ". " + reservasHuesped.get(i));
	                System.out.println("Introduce el número de reserva para realizar el Checkin");
	            }

	            // Solicitar al usuario el número de reserva para realizar el checkin
	            int numeroReserva = Entrada.entero();

	            // Verificar que el número de reserva está en el rango correcto
	            if (numeroReserva >= 1 && numeroReserva <= reservasHuesped.size()) {
	                // Obtener la reserva seleccionada
	                Reserva reservaSeleccionada = reservasHuesped.get(numeroReserva - 1);

	                // Obtener la fecha y hora actual
	                LocalDateTime fechaActual = LocalDateTime.now();

	                // Llamar al método del controlador para realizar el checkin con la fecha actual
	                this.controlador.realizarCheckin(reservaSeleccionada, fechaActual);

	                // Establecer la fecha y hora del check-in en la reserva usando setCheckIn
	                reservaSeleccionada.setCheckIn(fechaActual);

	                System.out.println("Checkin realizado correctamente para la reserva seleccionada:");
	                System.out.println(reservaSeleccionada);
	            } else {
	                System.out.println("Número de reserva no válido.");
	            }
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al realizar el checkin: " + e.getMessage());
	    }
	}

	// Método para realizar el checkout
	public void realizarCheckout() {
	    try {
	        // Solicitar al usuario el dni del huésped
	    	Huesped huesped = Consola.getHuespedPorDni();

	        // Llamar al método del controlador para obtener la lista de reservas del huésped
	        List<Reserva> reservasHuesped = this.controlador.getReservas(huesped);

	        if (reservasHuesped.isEmpty()) {
	            System.out.println("No hay reservas para el huésped indicado.");
	        } else {
	            // Mostrar las reservas del huésped
	            System.out.println("------ RESERVAS DEL HUÉSPED ------");
	            for (int i = 0; i < reservasHuesped.size(); i++) {
	                System.out.println((i + 1) + ". " + reservasHuesped.get(i));
	                System.out.println("Introduce el número de reserva para realizar el Checkout");
	            }

	            // Solicitar al usuario el número de reserva para realizar el checkout
	            int numeroReserva = Entrada.entero();

	            // Verificar que el número de reserva está en el rango correcto
	            if (numeroReserva >= 1 && numeroReserva <= reservasHuesped.size()) {
	                // Obtener la reserva seleccionada
	                Reserva reservaSeleccionada = reservasHuesped.get(numeroReserva - 1);

	                // Verificar si se ha realizado el checkin
	                if (reservaSeleccionada.getCheckIn() == null) {
	                    System.out.println("Error: primero debes realizar el checkin.");
	                    return;
	                }
	                
	                // Obtener la fecha y hora actual
	                LocalDateTime fechaActual = LocalDateTime.now();

	                // Llamar al método del controlador para realizar el checkout con la fecha actual
	                this.controlador.realizarCheckout(reservaSeleccionada, fechaActual);

	                // Establecer la fecha y hora del checkout en la reserva usando setCheckout
	                reservaSeleccionada.setCheckOut(fechaActual);

	                System.out.println("Checkout realizado correctamente para la reserva seleccionada:");
	                System.out.println(reservaSeleccionada);
	            } else {
	                System.out.println("Número de reserva no válido.");
	            }
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        System.out.println("Error al realizar el checkout: " + e.getMessage());
	    }
	}

}
