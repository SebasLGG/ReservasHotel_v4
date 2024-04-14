package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades;

// Paquetes e importaciones.
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.bson.Document;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MongoDB {

	// Atributos y constantes.
	
	// Formatos de fecha.
	public static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public static final DateTimeFormatter FORMATO_DIA_HORA = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	// Datos de conexión a mongoDB.
	private static final String SERVIDOR = "cluster0.bnlib1m.mongodb.net";
	private static final int PUERTO = 27017;
	private static final String BD = "reservashotel";
	private static final String USUARIO = "reservashotel";
	private static final String CONTRASENA = "reservashotel-2024";

	// Campos para documentos de Huéspedes.
	public static final String HUESPED = "huesped";
	public static final String NOMBRE = "nombre";
	public static final String DNI = "dni";
	public static final String TELEFONO = "telefono";
	public static final String CORREO = "correo";
	public static final String FECHA_NACIMIENTO = "fecha_nacimiento";
	public static final String HUESPED_DNI = HUESPED + "." + DNI;

	// Campos para documentos de Habitaciones.
	public static final String HABITACION = "habitacion";
	public static final String IDENTIFICADOR = "identificador";
	public static final String PLANTA = "planta";
	public static final String PUERTA = "puerta";
	public static final String PRECIO = "precio";
	public static final String HABITACION_IDENTIFICADOR = HABITACION + "." + IDENTIFICADOR;	
	public static final String TIPO = "tipo";
	public static final String HABITACION_TIPO = HABITACION + "." + TIPO;
	public static final String TIPO_SIMPLE = "SIMPLE";
	public static final String TIPO_DOBLE = "DOBLE";
	public static final String TIPO_TRIPLE = "TRIPLE";
	public static final String TIPO_SUITE = "SUITE";
	public static final String CAMAS_INDIVIDUALES = "camas_individuales";
	public static final String CAMAS_DOBLE = "camas_dobles";
	public static final String BANOS = "banos";
	public static final String JACUZZI = "jacuzzi";
	
	// Campos para documentos de Reservas.
	public static final String REGIMEN = "regimen";
	public static final String FECHA_INICIO_RESERVA = "fecha_inicio_reserva";
	public static final String FECHA_FIN_RESERVA = "fecha_fin_reserva";
	public static final String CHECKIN = "checkin";
	public static final String CHECKOUT = "checkout";
	public static final String PRECIO_RESERVA = "precio_reserva";
	public static final String NUMERO_PERSONAS = "numero_personas";
	
	// Cliente MongoDB y conexión.
	private static MongoClient conexion = null;

	private MongoDB() {

	}
	
	// Métodos de conexión y desconexión. 
	public static MongoDatabase getBD() {
        if (conexion == null) {
            establecerConexion();
        }

        return conexion.getDatabase(BD);
    }

    private static void establecerConexion()
    {

        String connectionString;
        ServerApi serverApi;
        MongoClientSettings settings;

        if (!SERVIDOR.equals("localhost"))
        {
            connectionString = "mongodb+srv://"+ USUARIO+ ":" + CONTRASENA + "@"+ SERVIDOR +"/?retryWrites=true&w=majority";
            serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();

            settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(serverApi)
                    .build();
        }
        else
        {
            connectionString="mongodb://" + USUARIO + ":" + CONTRASENA + "@" + SERVIDOR + ":" + PUERTO ;
            MongoCredential credenciales = MongoCredential.createScramSha1Credential(USUARIO, BD, CONTRASENA.toCharArray());

            settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .credential(credenciales)
                    .build();
        }


        
        conexion = MongoClients.create(settings);

        try
        {
            if (!SERVIDOR.equals("localhost"))
            {
                MongoDatabase database = conexion.getDatabase(BD);
                database.runCommand(new Document("ping", 1));
            }
        }
        catch (MongoException e)
        {
            e.printStackTrace();

        }

        System.out.println("Conexión a MongoDB realizada correctamente.");

    }

    public static void cerrarConexion() {
        if (conexion != null) {
            conexion.close();
            conexion = null;
            System.out.println("Conexión a MongoDB cerrada.");
        }
    }
	
    //Métodos de conversión para Huespedes.
    public static Document getDocumento(Huesped huesped) {
        if (huesped == null) {
            return null;
        }

        Document documento = new Document("_id", new ObjectId())
                .append(NOMBRE, huesped.getNombre())
                .append(DNI, huesped.getDni())
                .append(TELEFONO, huesped.getTelefono())
                .append(CORREO, huesped.getCorreo());

        if (huesped.getFechaNacimiento() != null) {
            documento.append(FECHA_NACIMIENTO, huesped.getFechaNacimiento().format(FORMATO_DIA));
        } else {
            documento.append(FECHA_NACIMIENTO, "");
        }

        return documento;
    }

    public static Huesped getHuesped(Document documentoHuesped) {
        if (documentoHuesped == null) {
            return null;
        }

        String nombre = documentoHuesped.getString(NOMBRE);
        String dni = documentoHuesped.getString(DNI);
        String correo = documentoHuesped.getString(CORREO);
        String telefono = documentoHuesped.getString(TELEFONO);

        LocalDate fechaNacimiento = null;
        Object fechaNacimientoObj = documentoHuesped.get(FECHA_NACIMIENTO);
        if (fechaNacimientoObj instanceof String) {
            String fechaNacimientoStr = (String) fechaNacimientoObj;
            if (!fechaNacimientoStr.isEmpty()) {
                try {
                    fechaNacimiento = LocalDate.parse(fechaNacimientoStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } catch (DateTimeParseException e) {
                    System.err.println("ERROR: No se pudo parsear la fecha de nacimiento.");
                    e.printStackTrace();
                }
            }
        }

        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    // Métodos de conversión para Habitaciones.
    public static Document getDocumento(Habitacion habitacion) {
        if (habitacion instanceof Simple) {
            return new Document("_id", new ObjectId())
                    .append(IDENTIFICADOR, habitacion.getIdentificador())
                    .append(TIPO, TIPO_SIMPLE)
                    .append(PRECIO, habitacion.getPrecio())
                    .append(NUMERO_PERSONAS, habitacion.getNumeroMaximoPersonas())
                    .append(PLANTA, habitacion.getPlanta())
                    .append(PUERTA, habitacion.getPuerta());
        } else if (habitacion instanceof Doble) {
            return new Document("_id", new ObjectId())
                    .append(IDENTIFICADOR, habitacion.getIdentificador())
                    .append(TIPO, TIPO_DOBLE)
                    .append(PRECIO, habitacion.getPrecio())
                    .append(NUMERO_PERSONAS, habitacion.getNumeroMaximoPersonas())
                    .append(PLANTA, habitacion.getPlanta())
                    .append(PUERTA, habitacion.getPuerta())
                    .append(CAMAS_INDIVIDUALES, ((Doble) habitacion).getNumCamasIndividuales())
                    .append(CAMAS_DOBLE, ((Doble) habitacion).getNumCamasDobles());
        } else if (habitacion instanceof Triple) {
            return new Document("_id", new ObjectId())
                    .append(IDENTIFICADOR, habitacion.getIdentificador())
                    .append(TIPO, TIPO_TRIPLE)
                    .append(PRECIO, habitacion.getPrecio())
                    .append(NUMERO_PERSONAS, habitacion.getNumeroMaximoPersonas())
                    .append(PLANTA, habitacion.getPlanta())
                    .append(PUERTA, habitacion.getPuerta())
                    .append(CAMAS_INDIVIDUALES, ((Triple) habitacion).getNumCamasIndividuales())
                    .append(CAMAS_DOBLE, ((Triple) habitacion).getNumCamasDobles())
                    .append(BANOS, ((Triple) habitacion).getNumBanos());
        } else if (habitacion instanceof Suite) {
            return new Document("_id", new ObjectId())
                    .append(IDENTIFICADOR, habitacion.getIdentificador())
                    .append(TIPO, TIPO_SUITE)
                    .append(PRECIO, habitacion.getPrecio())
                    .append(NUMERO_PERSONAS, habitacion.getNumeroMaximoPersonas())
                    .append(PLANTA, habitacion.getPlanta())
                    .append(PUERTA, habitacion.getPuerta())
                    .append(BANOS, ((Suite) habitacion).getNumBanos())
                    .append(JACUZZI, ((Suite) habitacion).isTieneJacuzzi());
        } else {
            return null;
        }
    }


    public static Habitacion getHabitacion(Document documentoHabitacion) {
        Habitacion habitacion = null;

        switch (documentoHabitacion.getString(TIPO)) {
            case TIPO_SIMPLE:
                habitacion = new Simple(
                        documentoHabitacion.getInteger(PLANTA),
                        documentoHabitacion.getInteger(PUERTA),
                        documentoHabitacion.getDouble(PRECIO)
                );
                break;
            case TIPO_DOBLE:
                habitacion = new Doble(
                        documentoHabitacion.getInteger(PLANTA),
                        documentoHabitacion.getInteger(PUERTA),
                        documentoHabitacion.getDouble(PRECIO),
                        documentoHabitacion.getInteger(CAMAS_INDIVIDUALES),
                        documentoHabitacion.getInteger(CAMAS_DOBLE)
                );
                break;
            case TIPO_TRIPLE:
                habitacion = new Triple(
                        documentoHabitacion.getInteger(PLANTA),
                        documentoHabitacion.getInteger(PUERTA),
                        documentoHabitacion.getDouble(PRECIO),
                        documentoHabitacion.getInteger(BANOS),
                        documentoHabitacion.getInteger(CAMAS_INDIVIDUALES),
                        documentoHabitacion.getInteger(CAMAS_DOBLE)
                );
                break;
            case TIPO_SUITE:
                habitacion = new Suite(
                        documentoHabitacion.getInteger(PLANTA),
                        documentoHabitacion.getInteger(PUERTA),
                        documentoHabitacion.getDouble(PRECIO),
                        documentoHabitacion.getInteger(BANOS),
                        documentoHabitacion.getBoolean(JACUZZI)
                );
                break;
        }

        return habitacion;
    }


    // Métodos de conversión para Reservas.
    public static Document getDocumento(Reserva reserva) {
        if (reserva == null) {
            return null;
        }

        Document documento = new Document("_id", new ObjectId())
                .append(HUESPED, getDocumento(reserva.getHuesped()))
                .append(HABITACION, getDocumento(reserva.getHabitacion()))
                .append(REGIMEN, reserva.getRegimen().toString())
                .append(FECHA_INICIO_RESERVA, reserva.getFechaInicioReserva().format(FORMATO_DIA))
                .append(FECHA_FIN_RESERVA, reserva.getFechaFinReserva().format(FORMATO_DIA))
                .append(NUMERO_PERSONAS, reserva.getNumeroPersonas())
                .append(CHECKIN, reserva.getCheckIn() != null ? reserva.getCheckIn().format(FORMATO_DIA_HORA) : "No registrado")
                .append(CHECKOUT, reserva.getCheckOut() != null ? reserva.getCheckOut().format(FORMATO_DIA_HORA) : "No registrado");

        return documento;
    }


    public static Reserva getReserva(Document documentoReserva) {
        if (documentoReserva == null) {
            return null;
        }

        Huesped huesped = getHuesped((Document) documentoReserva.get(HUESPED));
        Habitacion habitacion = getHabitacion((Document) documentoReserva.get(HABITACION));

        Regimen regimen = null;
        String regimenString = documentoReserva.getString(REGIMEN);
        for (Regimen r : Regimen.values()) {
            if (r.toString().equals(regimenString)) {
                regimen = r;
                break;
            }
        }

        LocalDate fechaInicio = LocalDate.parse(documentoReserva.getString(FECHA_INICIO_RESERVA), FORMATO_DIA);
        LocalDate fechaFin = LocalDate.parse(documentoReserva.getString(FECHA_FIN_RESERVA), FORMATO_DIA);
        int numeroPersonas = documentoReserva.getInteger(NUMERO_PERSONAS);

        LocalDateTime checkin = null;
        if (documentoReserva.containsKey(CHECKIN) && !"No registrado".equals(documentoReserva.getString(CHECKIN))) {
            checkin = LocalDateTime.parse(documentoReserva.getString(CHECKIN), FORMATO_DIA_HORA);
        }

        LocalDateTime checkout = null;
        if (documentoReserva.containsKey(CHECKOUT) && !"No registrado".equals(documentoReserva.getString(CHECKOUT))) {
            checkout = LocalDateTime.parse(documentoReserva.getString(CHECKOUT), FORMATO_DIA_HORA);
        }

        Reserva reserva = new Reserva(huesped, habitacion, regimen, fechaInicio, fechaFin, numeroPersonas);
        
        if (checkin != null) {
            reserva.setCheckIn(checkin);
        }

        if (checkout != null) {
            reserva.setCheckOut(checkout);
        }

        return reserva;
    }





    
	}
