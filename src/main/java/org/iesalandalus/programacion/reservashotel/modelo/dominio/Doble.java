package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Doble extends Habitacion {
	
	//Constantes para validaciones
    public static final int NUM_MAXIMO_PERSONAS = 2;
    public static final int MIN_NUM_CAMAS_INDIVIDUALES = 0;
    public static final int MAX_NUM_CAMAS_INDIVIDUALES = 2;
    public static final int MIN_NUM_CAMAS_DOBLES = 0;
    public static final int MAX_NUM_CAMAS_DOBLES = 1;

    //Atributos
    private int numCamasIndividuales;
    private int numCamasDobles;

    //Constructor principal
    public Doble(int planta, int puerta, double precio, int numCamasIndividuales, int numCamasDobles) {
        super(planta, puerta, precio);
        this.numCamasIndividuales = numCamasIndividuales;
        this.numCamasDobles = numCamasDobles;
        validaNumCamas();
    }

    //Constructor copia
    public Doble(Doble habitacionDoble) {
        super(habitacionDoble);
        this.numCamasIndividuales = habitacionDoble.numCamasIndividuales;
        this.numCamasDobles = habitacionDoble.numCamasDobles;
    }

    // Métodos getter y setter para el número de camas individuales
    public int getNumCamasIndividuales() {
        return numCamasIndividuales;
    }

    public void setNumCamasIndividuales(int numCamasIndividuales) {
        this.numCamasIndividuales = numCamasIndividuales;
        validaNumCamas();
    }

    // Métodos getter y setter para el número de camas dobles
    public int getNumCamasDobles() {
        return numCamasDobles;
    }

    public void setNumCamasDobles(int numCamasDobles) {
        this.numCamasDobles = numCamasDobles;
        validaNumCamas();
    }

    // Método para validar el número de camas
    private void validaNumCamas() {
        if (numCamasIndividuales < MIN_NUM_CAMAS_INDIVIDUALES || numCamasIndividuales > MAX_NUM_CAMAS_INDIVIDUALES) {
            throw new IllegalArgumentException("ERROR: El número de camas individuales de una habitación doble no puede ser inferior a 0 ni mayor que 2");
        }
        if (numCamasDobles < MIN_NUM_CAMAS_DOBLES || numCamasDobles > MAX_NUM_CAMAS_DOBLES) {
            throw new IllegalArgumentException("ERROR: El número de camas dobles de una habitación doble no puede ser inferior a 0 ni mayor que 1");
        }
        if (!((numCamasIndividuales == 2 && numCamasDobles == 0) || (numCamasIndividuales == 0 && numCamasDobles == 1))) {
            throw new IllegalArgumentException("ERROR: La distribución de camas en una habitación doble tiene que ser 2 camas individuales y 0 doble o 0 camas individuales y 1 doble");
        }
    }


    //Método para obtener el número máximo de personas
    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    //Método toString
    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitación=%s, habitación doble, capacidad=%d personas, " +
                        "camas individuales=%d, camas dobles=%d",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(),
                getNumeroMaximoPersonas(), getNumCamasIndividuales(), getNumCamasDobles());
    }
}
