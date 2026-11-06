import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static double SMI = 1221.0;
    static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== GESTION DE E-SPORTS (FASE 1) ===\n");

        solicitarDatosEquipo(sc);
        solicitarDatosJugador(sc);

        sc.close();
    }

    public static void solicitarDatosEquipo(Scanner sc) {
        System.out.println("--- INTRODUCIR DATOS DEL EQUIPO ---");

        String nombreEquipo = leerTextoObligatorio(sc, "Nombre del equipo: ");
        LocalDate fechaFundacionEquipo = leerFechaValida(sc, "Fecha de fundacion (dd/MM/yyyy): ");

        System.out.println();

        mostrarDatosEquipo(nombreEquipo, fechaFundacionEquipo.format(FORMATO_FECHA));
    }

    public static void solicitarDatosJugador(Scanner sc) {
        System.out.println("--- INTRODUCIR DATOS DEL JUGADOR ---");

        String nombreJugador = leerTextoObligatorio(sc, "Nombre: ");
        String apellidoJugador = leerTextoObligatorio(sc, "Apellido: ");
        String nacionalidadJugador = leerTextoObligatorio(sc, "Nacionalidad: ");
        LocalDate fechaNacimientoJugador = leerFechaValida(sc, "Fecha de nacimiento (dd/MM/yyyy): ");
        String nicknameJugador = leerTextoObligatorio(sc, "Nickname: ");
        String rolJugador = leerTextoObligatorio(sc, "Rol en el equipo: ");

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