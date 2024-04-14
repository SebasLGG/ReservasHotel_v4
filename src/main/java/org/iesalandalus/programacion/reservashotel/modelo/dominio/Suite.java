package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Suite extends Habitacion {

	//Constantes para validar
	public static final int NUM_MAXIMO_PERSONAS = 4;
	public static final int MIN_NUM_BANOS = 1;
	public static final int MAX_NUM_BANOS = 3;

	//Atributos
    private int numBanos;
    private boolean tieneJacuzzi;

    //Constructor principal
    public Suite(int planta, int puerta, double precio, int numBanos, boolean tieneJacuzzi) {
        super(planta, puerta, precio);
        setNumBanos(numBanos);
        setTieneJacuzzi(tieneJacuzzi);
    }

    //Constructor copia
    public Suite(Suite habitacionSuite) {
        super(habitacionSuite);
        this.numBanos = habitacionSuite.numBanos;
        this.tieneJacuzzi = habitacionSuite.tieneJacuzzi;
    }

    // Métodos getter y setter para el número de baños
    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos) {
        if (numBanos < MIN_NUM_BANOS || numBanos > MAX_NUM_BANOS) {
            throw new IllegalArgumentException("ERROR: El número de baños no puede ser inferior a 1 ni superior a 3");
        }
        this.numBanos = numBanos;
    }

    //Método tipo boolean para el jacuzzi
    public boolean isTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(boolean tieneJacuzzi) {
        this.tieneJacuzzi = tieneJacuzzi;
    }

    //Método para obtener el número máximo de las personas
    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    //Método toString
    @Override
    public String toString() {
        String jacuzziDescripcion = tieneJacuzzi ? "con Jacuzzi" : "sin Jacuzzi";
        return String.format("identificador=%s (%d-%d), precio habitación=%s, habitación suite, capacidad=%d personas, " +
                "baños=%d, %s", getIdentificador(), getPlanta(), getPuerta(), getPrecio(), getNumeroMaximoPersonas(), 
                getNumBanos(), jacuzziDescripcion);
    }

}
