package org.iesalandalus.programacion.reservashotel.modelo.dominio;

// Clase Habitación
public abstract class Habitacion {

    // Constantes para validaciones y limites
    public static final double MIN_PRECIO_HABITACION = 40.0;
    public static final double MAX_PRECIO_HABITACION = 150.0;
    public static final int MIN_NUMERO_PUERTA = 0;
    public static final int MAX_NUMERO_PUERTA = 14;
    public static final int MIN_NUMERO_PLANTA = 1;
    public static final int MAX_NUMERO_PLANTA = 3;

    // Atributos de la clase habitación consultando el diagrama.
    private String identificador;
    private int planta;
    private int puerta;
    private double precio;

    // Constructor que acepta planta, puerta, precio
    public Habitacion(int planta, int puerta, double precio) {
        setPlanta(planta);
        setPuerta(puerta);
        setIdentificador();
        setPrecio(precio);
    }

    //Constructor copia.
    public Habitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No es posible copiar una habitación nula.");
        }
        this.identificador = habitacion.identificador;
        this.planta = habitacion.planta;
        this.puerta = habitacion.puerta;
        this.precio = habitacion.precio;
    }

    // Método abstracto para obtener el número máximo de personas
    public abstract int getNumeroMaximoPersonas();
    
    // Método getter para obtener el identificador
    public String getIdentificador() {
        return identificador;
    }

    // Método público sin parámetros para establecer el identificador
    public void setIdentificador() {
        this.identificador = planta + "" + puerta;
    }

    // Método público con parámetro para establecer el identificador
    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El identificador no puede ser nulo ni vacío.");
        }
        this.identificador = identificador;
    }


    // Métodos de acceso y modificación de la planta
    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        if (planta < MIN_NUMERO_PLANTA || planta > MAX_NUMERO_PLANTA) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como planta de una habitación un valor menor que 1 ni mayor que 3.");
        }
        this.planta = planta;
        setIdentificador();
    }

    // Métodos de acceso y modificación de la puerta
    public int getPuerta() {
        return puerta;
    }

    public void setPuerta(int puerta) {
        if (puerta < MIN_NUMERO_PUERTA || puerta > MAX_NUMERO_PUERTA) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como puerta de una habitación un valor menor que 0 ni mayor que 14.");
        }
        this.puerta = puerta;
        setIdentificador();
    }

    // Métodos de acceso y modificación del precio
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < MIN_PRECIO_HABITACION || precio > MAX_PRECIO_HABITACION) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como precio de una habitación un valor menor que 40.0 ni mayor que 150.0.");
        }
        this.precio = precio;
    }


    // Método equals para comparar habitaciones por su identificador
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return identificador.equals(that.identificador);
    }

    // Método hashCode para obtener un código hash basado en el identificador
    @Override
    public int hashCode() {
        return identificador.hashCode();
    }

    // Método toString que devuelve la representación en cadena del objeto
    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitación=%s, tipo habitación=%s",
                identificador, planta, puerta, precio, getNumeroMaximoPersonas());
    }
}