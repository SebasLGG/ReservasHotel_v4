package org.iesalandalus.programacion.reservashotel.modelo.dominio;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// Clase Reserva
public class Reserva {

	// Constantes para la clase
    public static final int MAX_NUMERO_MESES_RESERVA = 6;
    public static final int MAX_HORAS_POSTERIOR_CHECKOUT = 12;
    public static final String FORMATO_FECHA_RESERVA = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_HORA_RESERVA = "dd/MM/yyyy HH:mm";
    
    // Atributos de la clase
    private Regimen regimen;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double precio;
    private int numeroPersonas;
    
    private Huesped huesped;
    private Habitacion habitacion;

    

    // Constructor con parámetros
    public Reserva(Huesped huesped, Habitacion habitacion, Regimen regimen, LocalDate fechaInicioReserva,
                   LocalDate fechaFinReserva, int numeroPersonas) {
        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
        
    }

    // Constructor copia
    public Reserva(Reserva reserva) {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");
        }
        this.huesped = reserva.huesped;
        this.habitacion = reserva.habitacion;
        this.regimen = reserva.regimen;
        this.fechaInicioReserva = reserva.fechaInicioReserva;
        this.fechaFinReserva = reserva.fechaFinReserva;
        this.numeroPersonas = reserva.numeroPersonas;
        
        if (reserva.checkIn != null) {
            setCheckIn(reserva.checkIn);
        }
        if (reserva.checkOut != null) {
            setCheckOut(reserva.checkOut);
        }
        this.precio = reserva.precio;
    }

    // Métodos de acceso y modificación
    
    // Método para obtener el huésped de la reserva
    public Huesped getHuesped() {
        return new Huesped(huesped);
    }

    // Método para establecer el huésped de la reserva
    public void setHuesped(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: El huésped de una reserva no puede ser nulo.");
        }
        this.huesped = new Huesped(huesped);
    }

    // Método para obtener la habitación de la reserva
    public Habitacion getHabitacion() {
    	return habitacion;	
    }
    
 // Método para establecer la habitación de la reserva
    public void setHabitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: La habitación de una reserva no puede ser nula.");
        }
        this.habitacion = habitacion;
        setPrecio();
    }

    // Método para obtener el régimen de la reserva
    public Regimen getRegimen() {
        return regimen;
    }

    // Método para establecer el régimen de la reserva
    public void setRegimen(Regimen regimen) {
        if (regimen == null) {
            throw new NullPointerException("ERROR: El régimen de una reserva no puede ser nulo.");
        }
        this.regimen = regimen;
        
    }

    // Método para obtener la fecha de inicio de la reserva
    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    // Método para establecer la fecha de inicio de la reserva
    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        if (fechaInicioReserva == null) {
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        }
        if (fechaInicioReserva.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser anterior al día de hoy.");
        }
        if (fechaInicioReserva.isAfter(LocalDate.now().plusMonths(MAX_NUMERO_MESES_RESERVA))) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser posterior a seis meses.");
        }
        this.fechaInicioReserva = fechaInicioReserva;
        setPrecio();
    }

    // Método para obtener la fecha de fin de la reserva
    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }

    // Método para establecer la fecha de fin de la reserva
    public void setFechaFinReserva(LocalDate fechaFinReserva) {
        if (fechaFinReserva == null) {
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");
        }
        if (fechaFinReserva.isBefore(fechaInicioReserva.plusDays(1))) {
            throw new IllegalArgumentException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");
        }
        this.fechaFinReserva = fechaFinReserva;
        setPrecio();
    }

    // Método para obtener el check-in de la reserva
    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    // Método para establecer el check-in de la reserva
    public void setCheckIn(LocalDateTime checkIn) {
        if (checkIn == null) {
            throw new NullPointerException("ERROR: El checkin de una reserva no puede ser nulo.");
        }
        //EL CHECKIN SOLO DEJA HACERLO SI LA FECHA ACTUAL ESTA EN EL RANGO DE LA FECHA DE INICIO Y FECHA DE FIN DE LA RESERVA
        if (checkIn.isBefore(fechaInicioReserva.atStartOfDay())) {
            throw new IllegalArgumentException("ERROR: El checkin de una reserva no puede ser anterior a la fecha de inicio de la reserva.");
        }
        this.checkIn = checkIn;
    }

    // Método para obtener el check-out de la reserva
    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    //EL CHECKOUT SOLO DEJA HACERLO SI LA FECHA ACTUAL ESTA EN EL RANGO DE LA FECHA DE INICIO Y FECHA DE FIN DE LA RESERVA
    // Método para establecer el check-out de la reserva
    public void setCheckOut(LocalDateTime checkOut) {
        if (checkOut == null) {
            throw new NullPointerException("ERROR: El checkout de una reserva no puede ser nulo.");
        }
        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("ERROR: El checkout de una reserva no puede ser anterior al checkin.");
        }
        if (checkOut.isAfter(fechaFinReserva.atStartOfDay().plusHours(MAX_HORAS_POSTERIOR_CHECKOUT))) {
            throw new IllegalArgumentException("ERROR: El checkout de una reserva puede ser como máximo 12 horas después de la fecha de fin de la reserva.");
        }
        this.checkOut = checkOut;
        setPrecio();
    }

    public double getPrecio() {
        return precio;
    }

    // Método privado para calcular el precio
    private void setPrecio() {
        if (fechaInicioReserva == null || fechaFinReserva == null) {
            return;
        }

        double precioHabitacion = habitacion.getPrecio();
        double precioRegimenPorPersona = regimen.getIncrementoPrecio();

        // Calcular el precio del régimen para todas las personas
        double precioRegimenTotal = precioRegimenPorPersona * numeroPersonas;

        // Calcular la duración total de la reserva en días
        long diasReserva = Period.between(fechaInicioReserva, fechaFinReserva).getDays();

        // Calcular el precio total
        this.precio = (precioHabitacion + precioRegimenTotal) * diasReserva;
    }

    // Método para obtener el número de personas de la reserva
    public int getNumeroPersonas() {
        return numeroPersonas;
    }

 // Método para establecer el número de personas de la reserva
    public void setNumeroPersonas(int numeroPersonas) {
        if (numeroPersonas <= 0) {
            throw new IllegalArgumentException("ERROR: El número de personas de una reserva no puede ser menor o igual a 0.");
        }
        if (numeroPersonas > habitacion.getNumeroMaximoPersonas()) {
            throw new IllegalArgumentException("ERROR: El número de personas de una reserva no puede superar al máximo de personas establacidas para el tipo de habitación reservada.");
        }
        this.numeroPersonas = numeroPersonas;
        setPrecio();
    }
 
    //Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(habitacion, reserva.habitacion) &&
                Objects.equals(fechaInicioReserva, reserva.fechaInicioReserva);
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(habitacion, fechaInicioReserva);
    }

    // Método toString
    @Override
    public String toString() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA);
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA_RESERVA);

        String infoCheckIn = (checkIn != null) ? ", Checkin: " + checkIn.format(formatoFechaHora) : ", Checkin: No registrado";
        String infoCheckout = (checkOut != null) ? ", Checkout: " + checkOut.format(formatoFechaHora) : ", Checkout: No registrado";

        infoCheckIn = infoCheckIn.replace(", ", " ");
        infoCheckout = infoCheckout.replace(", ", " ");

        String precioFormato = String.format("%.1f", habitacion.getPrecio()).replace(",", ".");

        String habitacionInfo = String.format("identificador=%s (%d-%d), precio habitación=%s, habitación %s, capacidad=%d personas",
                	    habitacion.getIdentificador(), habitacion.getPlanta(), habitacion.getPuerta(),
                	    precioFormato, habitacion.getClass().getSimpleName().toLowerCase(), habitacion.getNumeroMaximoPersonas());



        return String.format("Huesped: %s %s Habitación:%s Fecha Inicio Reserva: %s Fecha Fin Reserva: %s%s%s Precio: %.2f Personas: %d",
                huesped.getNombre(), huesped.getDni(), habitacionInfo,
                fechaInicioReserva.format(formatoFecha),
                fechaFinReserva.format(formatoFecha), infoCheckIn, infoCheckout, precio, numeroPersonas);
    }

}
