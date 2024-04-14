package org.iesalandalus.programacion.reservashotel.modelo.dominio;

//Enumerado que representa los diferentes regímenes de alojamiento disponibles.
public enum Regimen {
    SOLO_ALOJAMIENTO("Solo Alojamiento", 0.0),
    ALOJAMIENTO_DESAYUNO("Alojamiento y Desayuno", 15.0),
    MEDIA_PENSION("Media Pensión", 30.0),
    PENSION_COMPLETA("Pensión Completa", 50.0);

	//Atributos
    private final String cadenaAMostrar;
    private final double incrementoPrecio;

    //Constructor que inicializa los atributos con los datos.
    private Regimen(String cadenaAMostrar, double incrementoPrecio) {
        this.cadenaAMostrar = cadenaAMostrar;
        this.incrementoPrecio = incrementoPrecio;
    }

    // Método getter para obtener el incremento de precio por persona alojada.
    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    // Método toString que devuelve la cadena a mostrar para el tipo de régimen.
    @Override
    public String toString() {
        return String.format("%d.- %s", ordinal() + 1, cadenaAMostrar);
    }

}
