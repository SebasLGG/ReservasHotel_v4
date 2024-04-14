package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Simple extends Habitacion {

	// Constante para validar el número de personas
	public static final int NUM_MAXIMO_PERSONAS = 1;

	//Constructor principal
    public Simple(int planta, int puerta, double precio) {
        super(planta, puerta, precio);
    }

    //Constructor copia
    public Simple(Simple habitacionSimple) {
        super(habitacionSimple);
    }

    //Método para obtener el número máximo de las personas
    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    //Método toString
    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitación=%s, habitación simple, capacidad=%d personas",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(), getNumeroMaximoPersonas());
    }

}
