package org.iesalandalus.programacion.reservashotel.vista;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

//Enumerado opcion llamando cada opcion a la instancia estática de `Vista`correspondiente
public enum Opcion {

    SALIR("Salir") {
        public void ejecutar() {
            vista.terminar();
        }
    },
    INSERTAR_HUESPED("Insertar huésped") {
        public void ejecutar() {

            vista.insertarHuesped();
        }
    },
    BUSCAR_HUESPED("Buscar huésped") {
        public void ejecutar() {
            vista.buscarHuesped();
        }
    },
    BORRAR_HUESPED("Borrar huésped") {
        public void ejecutar() {
            vista.borrarHuesped();
        }
    },
    MOSTRAR_HUESPEDES("Mostrar huéspedes") {
        public void ejecutar() {
            vista.mostrarHuespedes();
        }
    },
    INSERTAR_HABITACION("Insertar habitación") {
        public void ejecutar() {
            vista.insertarHabitacion();
        }
    },
    BUSCAR_HABITACION("Buscar habitación") {
        public void ejecutar() {
            vista.buscarHabitacion();
        }
    },
    BORRAR_HABITACION("Borrar habitación") {
        public void ejecutar() {
            vista.borrarHabitacion();
        }
    },
    MOSTRAR_HABITACIONES("Mostrar habitaciones") {
        public void ejecutar() {
            vista.mostrarHabitaciones();
        }
    },
    INSERTAR_RESERVA("Insertar reserva") {
        public void ejecutar() {
            vista.insertarReserva();
        }
    },
    ANULAR_RESERVA("Anular reserva") {
        public void ejecutar() {
            vista.anularReserva();
        }
    },
    MOSTRAR_RESERVAS("Mostrar reservas") {
        public void ejecutar() {
            vista.mostrarReservas();
        }
    },
    LISTAR_RESERVAS_HUESPED("Listar reservas por huésped") {
        public void ejecutar() {
            vista.mostrarReservasHuesped();
        }
    },
    LISTAR_RESERVAS_TIPO_HABIACION("Listar reservas por tipo de habitación") {
        public void ejecutar() {
            TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();

            vista.listarReservas(tipoHabitacion);
        }
    },

    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad") {
        public void ejecutar() {
            vista.consultarDisponibilidad();
        }
    },
    REALIZAR_CHECKIN("Realizar check-in") {
        public void ejecutar() {
            vista.realizarCheckin();
        }
    },
    REALIZAR_CHECKOUT("Realizar check-out") {
        public void ejecutar() {
            vista.realizarCheckout();
        }
    };
    

    private String mensajeAMostrar;
    private static Vista vista;

    private Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }
    
    // Método para establecer la instancia de Vista
    public static void setVista(Vista vista) {
        Opcion.vista = vista;
    }
    
    //Método abstracto ejecutar
    public abstract void ejecutar();

    //Método toString
    @Override
    public String toString() {
        return mensajeAMostrar;
    }

}
