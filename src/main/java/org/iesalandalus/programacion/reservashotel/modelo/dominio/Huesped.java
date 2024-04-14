// Importación de los paquetes necesarios.
package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

// Clase Huesped
public class Huesped {

    // Expresiones regulares para las validaciones de teléfono, correo, dni y fecha.
    private static final String ER_TELEFONO = "^(\\+\\d{1,3}[- ]?)?\\d{9}$";
    private static final String ER_CORREO = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$";
    private static final String ER_DNI = "^(\\d{8})([-]?)([A-Za-z])$";
    private static final String FORMATO_FECHA = "dd/MM/yyyy";

    // Atributos de la clase Huesped que indica el diagrama.
    private String nombre;
    private String telefono;
    private String correo;
    private LocalDate fechaNacimiento;
    private String dni;
    

    // Constructor principal que hace uso de los métodos de modificación
    public Huesped(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    // Constructor de copia
    public Huesped(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No es posible copiar un huésped nulo.");
        }
        this.nombre = huesped.nombre;
        this.dni = huesped.dni;
        this.correo = huesped.correo;
        this.telefono = huesped.telefono;
        this.fechaNacimiento = huesped.fechaNacimiento;
    }

    // Métodos de acceso y modificación del atributo 'nombre'
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un huésped no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un huésped no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }


    // Método privado para formatear el nombre según las especificaciones
    private String formateaNombre(String nombre) {
        // Elimina espacios en blanco de sobra y pone en mayúsculas la primera letra de cada palabra
        StringBuilder nombreFormateado = new StringBuilder();
        String[] palabras = nombre.trim().split("\\s+");
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                nombreFormateado.append(Character.toUpperCase(palabra.charAt(0)))
                        .append(palabra.substring(1).toLowerCase()).append(" ");
            }
        }
        return nombreFormateado.toString().trim();
    }

    // Métodos de acceso y modificación del atributo 'telefono'
    public String getTelefono() {
        return telefono;
    }
 
    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un huésped no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del huésped no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    // Métodos de acceso y modificación del atributo 'correo'
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un huésped no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del huésped no tiene un formato válido.");
        }
        this.correo = correo;
    }

    // Métodos de acceso y modificación del atributo 'dni'
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un huésped no puede ser nulo.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del huésped no es correcta.");
        }
        this.dni = dni;
    }

 // Método privado que utiliza expresiones regulares para comprobar la letra del DNI
    private boolean comprobarLetraDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El DNI de un huésped no puede ser nulo.");
        }
        
        Pattern patron = Pattern.compile(ER_DNI);
        Matcher matcher = patron.matcher(dni);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");
        }

        String numero = matcher.group(1);
        String letra = matcher.group(3).toUpperCase();

        if (!letra.matches("[A-Z]")) {
            throw new IllegalArgumentException("ERROR: La letra del DNI del huésped no es válida.");
        }

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numDni;
        try {
            numDni = Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR: El número del DNI del huésped no es válido.");
        }

        int resto = numDni % 23;
        String letraCalculada = String.valueOf(letras.charAt(resto));

        return letra.equals(letraCalculada);
    }


    // Métodos de acceso y modificación del atributo 'fechaNacimiento'
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        Objects.requireNonNull(fechaNacimiento, "ERROR: La fecha de nacimiento de un huésped no puede ser nula.");
        // Comprobar si la fecha de nacimiento es posterior a hoy
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de nacimiento no puede ser posterior a la fecha actual.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    // Método que devuelve las iniciales del nombre
    public String getIniciales() {
        StringBuilder iniciales = new StringBuilder();
        String[] palabras = nombre.split("\\s+");
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales.append(palabra.charAt(0));
            }
        }
        return iniciales.toString();
    }

    // Métodos equals y hashCode para comparar huéspedes por su DNI
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huesped huesped = (Huesped) o;
        return Objects.equals(dni, huesped.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    // Método toString que devuelve la representación en cadena del objeto
    @Override
    public String toString() {
        return String.format("nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s",
                nombre, getIniciales(), dni, correo, telefono, fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
    }
}
