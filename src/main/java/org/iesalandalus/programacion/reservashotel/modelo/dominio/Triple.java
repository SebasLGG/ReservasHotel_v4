package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Triple extends Habitacion {
	//Constantes para la validación
    public static final int NUM_MAXIMO_PERSONAS = 3;
    public static final int MIN_NUM_BANOS = 1;
    public static final int MAX_NUM_BANOS = 3;
    public static final int MIN_NUM_CAMAS_INDIVIDUALES = 1;
    public static final int MAX_NUM_CAMAS_INDIVIDUALES = 3;
    public static final int MIN_NUM_CAMAS_DOBLES = 0;
    public static final int MAX_NUM_CAMAS_DOBLES = 1;

    //Atributos
    private int numBanos;
    private int numCamasIndividuales;
    private int numCamasDobles;

    // Constructor principal
    public Triple(int planta, int puerta, double precio, int numBanos, int numCamasIndividuales, int numCamasDobles) {
        super(planta, puerta, precio);
        this.numBanos = numBanos;
        this.numCamasIndividuales = numCamasIndividuales;
        this.numCamasDobles = numCamasDobles;
        validaNumCamas();
    }

    //Constructor copia
    public Triple(Triple habitacionTriple) {
        super(habitacionTriple);
        this.numBanos = habitacionTriple.numBanos;
        this.numCamasIndividuales = habitacionTriple.numCamasIndividuales;
        this.numCamasDobles = habitacionTriple.numCamasDobles;
    }

    // Métodos getter y setter para el número de baños
    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos) {
        this.numBanos = numBanos;
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

    //Método para validar el número de camas
    private void validaNumCamas() {
    	if (numBanos < MIN_NUM_BANOS || numBanos > MAX_NUM_BANOS) {
            throw new IllegalArgumentException("ERROR: El número de baños no puede ser inferior a 1 ni superior a 3");
        }
    	
    	if (numCamasIndividuales < MIN_NUM_CAMAS_INDIVIDUALES || numCamasIndividuales > MAX_NUM_CAMAS_INDIVIDUALES) {
            throw new IllegalArgumentException("ERROR: El número de camas individuales de una habitación triple no puede ser inferior a 1 ni mayor que 3");
        }
        if (numCamasDobles < MIN_NUM_CAMAS_DOBLES || numCamasDobles > MAX_NUM_CAMAS_DOBLES) {
            throw new IllegalArgumentException("ERROR: El número de camas dobles de una habitación triple no puede ser inferior a 0 ni mayor que 1");
        }
        if (!((numCamasIndividuales == 3 && numCamasDobles == 0) || (numCamasIndividuales == 1 && numCamasDobles == 1))) {
              throw new IllegalArgumentException("ERROR: La distribución de camas en una habitación triple tiene que ser 3 camas individuales y 0 doble o 1 cama individual y 1 doble");
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
        return String.format("identificador=%s (%d-%d), precio habitación=%s, habitación triple, capacidad=%d personas, " +
                "baños=%d, camas individuales=%d, camas dobles=%d",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(),
                getNumeroMaximoPersonas(), numBanos, numCamasIndividuales, numCamasDobles);
    }
}