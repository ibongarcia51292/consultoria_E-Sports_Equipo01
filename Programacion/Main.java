import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    // variables globales con el Sueldo Mínimo Interprofesional y el formato para la fecha
    static final double SMI = 1221.0;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean equipoCreado = false; // te obliga a crear equipo primero y luego añades los jugadores que quieras

        do {
            opcion = mostrarMenu(sc); // llamada a la función del menú

            if (opcion == 1) {
                solicitarDatosEquipo(sc);
                equipoCreado = true; // se marca como creado tras ejecutar la función
            } else if (opcion == 2) {
                if (equipoCreado) {
                    solicitarDatosJugador(sc);
                } else {
                    System.out.println("Error: Primero debes crear un equipo (Opcion 1) antes de añadir jugadores.");
                    System.out.println(" ");
                }
            } else if (opcion == 3) {
                System.out.println("Cerrando programa...");
            } else {
                System.out.println("Error: Elige una opción correcta.");
                System.out.println(" ");
            }
        } while (opcion != 3);

        sc.close();
    }

    public static int mostrarMenu(Scanner sc) {
        int opcion = 0;
        System.out.println("-- BIENVENIDO AL TORNEO DE E-SPORTS --");
        System.out.println("1: Introducir equipo");
        System.out.println("2: Introducir jugadores");
        System.out.println("3: Salir");
        System.out.println(" ");
        System.out.print("Elige una opción: ");

        try {
            opcion = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            // si el usuario introduce una letra en vez de un número, devolvemos 0 para que salte el "Elige una opción correcta"
            sc.nextLine();
            opcion = 0;
        }

        System.out.println(" ");
        return opcion;
    }

    public static void solicitarDatosEquipo(Scanner sc) {
        System.out.println("--- INTRODUCIR DATOS DEL EQUIPO ---");

        String nombreEquipo = leerNombreEquipo(sc, "Nombre del equipo: ");
        LocalDate fechaFundacionEquipo = leerFechaValida(sc, "Fecha de fundacion (dd/MM/yyyy): ");

        System.out.println();
        mostrarDatosEquipo(nombreEquipo, fechaFundacionEquipo.format(FORMATO_FECHA));
    }

    public static void solicitarDatosJugador(Scanner sc) {
        System.out.println("--- INTRODUCIR DATOS DEL JUGADOR ---");

        String nombreJugador = leerNombrePropio(sc, "Nombre: ");
        String apellidoJugador = leerNombrePropio(sc, "Apellido: ");
        String nacionalidadJugador = leerNacionalidad(sc);
        LocalDate fechaNacimientoJugador = leerFechaValida(sc, "Fecha de nacimiento (dd/MM/yyyy): ");
        String nicknameJugador = leerTextoObligatorio(sc, "Nickname: ");
        String rolJugador = leerRol(sc);

        double sueldoJugador = 0.0;
        boolean sueldoValido = false;

        while (!sueldoValido) {
            try {
                System.out.print("Sueldo mensual (€): ");
                sueldoJugador = sc.nextDouble();
                sc.nextLine();

                if (sueldoJugador > SMI) {
                    sueldoValido = true;
                } else {
                    System.out.println("El sueldo debe ser mayor al SMI (" + SMI + "€). Vuelve a introducirlo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un valor numérico válido.");
                sc.nextLine();
            }
        }

        System.out.println();
        mostrarDatosJugador(nombreJugador, apellidoJugador, nicknameJugador, nacionalidadJugador, fechaNacimientoJugador.format(FORMATO_FECHA), rolJugador, sueldoJugador);
    }

    public static String leerRol(Scanner sc) {
        System.out.println("¿De qué rol jugará este jugador? (Opciones: blue, green, yellow, jungla, carry)");
        String rol = "";
        boolean comprobador = false;
        Pattern pat = Pattern.compile("^(blue|green|yellow|jungla|carry)$");

        do {
            System.out.print("Rol: ");
            rol = sc.nextLine().toLowerCase();
            Matcher mat = pat.matcher(rol);

            if (mat.matches()) {
                comprobador = true;
            } else {
                System.out.println("Error, opción no válida. Elige tu rol: blue, green, yellow, jungla y carry.");
            }
        } while (!comprobador);

        return rol;
    }

    public static String leerNacionalidad(Scanner sc) {
        String nacionalidad = "";
        boolean comprobador = false;
        Pattern pat = Pattern.compile("^(ALB|DEU|AND|ARM|AUT|AZE|BEL|BLR|BIH|BGR|CYP|HRV|DNK|SVK|SVN|ESP|EST|FIN|FRA|GEO|GRC|HUN|IRL|ISL|ITA|XKX|LVA|LIE|LTU|LUX|MLT|MDA|MCO|MNE|NOR|NLD|POL|PRT|GBR|CZE|MKD|ROU|RUS|SMR|SRB|SWE|CHE|TUR|UKR|VAT|OTR)$");

        System.out.println("Introduce el trigrama de la nacionalidad (ej. ESP para España):");
        do {
            System.out.print("Nacionalidad: ");
            nacionalidad = sc.nextLine().toUpperCase();
            Matcher mat = pat.matcher(nacionalidad);

            if (mat.matches()) {
                comprobador = true;
            } else {
                System.out.println("""
                        Trigrama incorrecto, introduce 3 letras mayúsculas:
                        Albania (ALB), Alemania (DEU), Andorra (AND), Armenia (ARM), Austria (AUT),
                        Azerbaiyan (AZE), Belgica (BEL), Bielorrusia (BLR), Bosnia y Herzegovina (BIH), Bulgaria (BGR),
                        Chipre (CYP), Croacia (HRV), Dinamarca (DNK), Eslovaquia (SVK), Eslovenia (SVN),
                        Espana (ESP), Estonia (EST), Finlandia (FIN), Francia (FRA), Georgia (GEO),
                        Grecia (GRC), Hungria (HUN), Irlanda (IRL), Islandia (ISL), Italia (ITA),
                        Kosovo (XKX), Letonia (LVA), Liechtenstein (LIE), Lituania (LTU), Luxemburgo (LUX),
                        Malta (MLT), Moldavia (MDA), Monaco (MCO), Montenegro (MNE), Noruega (NOR),
                        Paises Bajos (NLD), Polonia (POL), Portugal (PRT), Reino Unido (GBR), Republica Checa (CZE),
                        Macedonia del Norte (MKD), Rumania (ROU), Rusia (RUS), San Marino (SMR), Serbia (SRB),
                        Suecia (SWE), Suiza (CHE), Ucrania (UKR), Otro pais (OTR)
                        """);
            }
        } while (!comprobador);

        return nacionalidad;
    }

    public static String leerTextoObligatorio(Scanner sc, String mensaje) {
        String texto = "";
        while (texto.trim().isEmpty()) {
            System.out.print(mensaje);
            texto = sc.nextLine();
            if (texto.trim().isEmpty()) {
                System.out.println("Error: Este campo no puede estar vacío.");
            }
        }
        return texto;
    }

    // te obliga a que el nombre del equipo tenga mínimo 3 letras
    public static String leerNombreEquipo(Scanner sc, String mensaje) {
        String texto = "";
        while (texto.trim().length() < 3) {
            System.out.print(mensaje);
            texto = sc.nextLine();
            if (texto.trim().length() < 3) {
                System.out.println("Error: El nombre del equipo debe tener al menos 3 caracteres.");
            }
        }
        return texto;
    }

    public static String leerNombrePropio(Scanner sc, String mensaje) {
        String texto = "";
        boolean textoValido = false;
        // solo admite nombres con letras (mayusculas, minusculas, acentos) y espacios
        Pattern pat = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");

        while (!textoValido) {
            System.out.print(mensaje);
            texto = sc.nextLine();
            Matcher mat = pat.matcher(texto.trim());

            if (texto.trim().isEmpty()) {
                System.out.println("Error: Este campo no puede estar vacío.");
            } else if (!mat.matches() || texto.trim().length() < 3) {
                System.out.println("Error: El nombre/apellido debe tener al menos 3 caracteres y no puede contener números ni caracteres especiales.");
            } else {
                textoValido = true;
            }
        }
        return texto.trim();
    }

    public static LocalDate leerFechaValida(Scanner sc, String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.print(mensaje);
            String entrada = sc.nextLine();
            try {
                fecha = LocalDate.parse(entrada, FORMATO_FECHA);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Fecha inválida o formato incorrecto. Usa el formato dd/MM/yyyy.");
            }
        }
        return fecha;
    }

    public static void mostrarDatosEquipo(String nombre, String fechaFundacion) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== DATOS DEL EQUIPO REGISTRADO ===\n")
                .append(" - Nombre: ").append(nombre).append("\n")
                .append(" - Fecha de fundacion: ").append(fechaFundacion).append("\n");

        System.out.println(sb.toString());
    }

    public static void mostrarDatosJugador(String nombre, String apellido, String nickname, String nacionalidad, String fechaNac, String rol, double sueldo) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== DATOS DEL JUGADOR REGISTRADO ===\n")
                .append(" - Nombre completo: ").append(nombre).append(" ").append(apellido).append("\n")
                .append(" - Nickname: ").append(nickname).append("\n")
                .append(" - Nacionalidad: ").append(nacionalidad).append("\n")
                .append(" - Fecha de nacimiento: ").append(fechaNac).append("\n")
                .append(" - Rol: ").append(rol).append("\n")
                .append(" - Sueldo: ").append(sueldo).append("€\n");

        System.out.println(sb.toString());
    }
}