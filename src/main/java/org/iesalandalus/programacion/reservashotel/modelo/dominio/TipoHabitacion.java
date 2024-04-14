package org.iesalandalus.programacion.reservashotel.modelo.dominio;

// Enumerado TipoHabitacion
public enum TipoHabitacion {
    
    // Enumeración de las instancias del enumerado con sus atributos correspondientes

    SIMPLE("Simple"),
    DOBLE("Doble"),
    TRIPLE("Triple"),
    SUITE("Suite");
    // Atributo de cada instancia del enumerado
    private final String cadenaAMostrar;

    // Constructor privado que inicializa el atributo con el parámetro dado
    private TipoHabitacion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Método toString que devuelve la representación en cadena de la instancia del enumerado
    @Override
    public String toString() {
    	return String.format("%d. %s", ordinal() + 1, cadenaAMostrar);
    }

}
