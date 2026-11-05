import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MostrarMenu();

    }

    public static void MostrarMenu () {
        System.out.println("-- BIENVENIDO AL TORNEO DE E-SPORTS --");
        System.out.println("1: Introducir equipo");
        System.out.println("2: Introducir jugadores");
        System.out.println("3: Salir");
        System.out.print("Elige una opcion: ");
        int opcion = sc.nextInt();

        sc.nextLine();

        switch (opcion) {
            case 1 -> IntroducirEquipo();
            case 2 -> IntroducirJugadores();
            case 3 -> System.out.println("Equipos registrados:\n" + registro);
            default -> System.out.println("Opcion no valida");
        }

        if (opcion != 3) {
            MostrarMenu();
        }

    }

    static Scanner sc = new Scanner(System.in);
    static StringBuilder registro = new StringBuilder();
    static String nombreEquipo = "";

    public static void IntroducirEquipo () {
        System.out.print("Nombre del equipo: ");
        nombreEquipo = sc.nextLine();
    }

    public static void IntroducirJugadores () {
        if (nombreEquipo.equals("")) {
            System.out.println("Primero introduce un equipo");
        } else {
            int jugadores = leerEntero("Numero de jugadores de " + nombreEquipo + " (1-10): ", 1, 10);

            registro.append("Equipo: " + nombreEquipo + " (" + jugadores + " jugadores)\n");

            for (int i = 1; i <= jugadores; i++) {
                System.out.println("\n-- Jugador " + i + " de " + jugadores + " --");

                String nombre = leerTexto("Nombre: ", "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]{2,30}");
                String apellido = leerTexto("Apellido: ", "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]{2,30}");
                String nickname = leerTexto("Nickname: ", "[\\w.\\-]{2,20}");
                int edad = leerEntero("Edad (10-99): ", 10, 99);
                String rol = leerTexto("Rol: ", "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]{2,30}");

                registro.append("  " + i + ". " + nombre + " " + apellido + " (" + nickname + ") - "
                        + edad + " años - " + rol + "\n");
            }

            registro.append("\n");
            nombreEquipo = "";
        }
    }

    // Pide un número entero entre min y max. Si el dato es erróneo, lo vuelve a pedir.
    public static int leerEntero (String mensaje, int min, int max) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = Integer.parseInt(sc.nextLine().trim());
                if (numero < min || numero > max) {
                    throw new IllegalArgumentException();
                }
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Caracter erroneo, introduzcalo de nuevo.");
            } catch (IllegalArgumentException e) {
                System.out.println("Valor fuera de rango (debe estar entre " + min + " y " + max + "), introduzcalo de nuevo.");
            }
        }
    }

    // Pide un texto que cumpla el patrón indicado. Si es erróneo, lo vuelve a pedir.
    public static String leerTexto (String mensaje, String patron) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (!texto.matches(patron)) {
                System.out.println("Caracter erroneo, introduzcalo de nuevo.");
            }
        } while (!texto.matches(patron));
        return texto;
    }
}