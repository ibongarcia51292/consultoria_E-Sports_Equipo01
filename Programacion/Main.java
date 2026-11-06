import java.util.Scanner;

public class Main {

    static int opcion;
    static boolean equipoCreado = false;

    public static void main(String[] args) {

        do {
            MostrarMenu();

            if (opcion == 1) {
                CrearEquipo();
            }

            else if (opcion == 2) {
                AñadirJugadores();
            }

            else if (opcion == 3){
                CerrarPrograma();
            }

            else {
                System.out.println("Elige una opcion correcta");
                System.out.println(" ");
            }
        }

        while (opcion != 3);
    }

        public static void MostrarMenu () {
            Scanner sc = new Scanner(System.in);

            System.out.println("-- BIENVENIDO AL TORNEO DE E-SPORTS --");
            System.out.println("1: Introducir equipo");
            System.out.println("2: Introducir jugadores");
            System.out.println("3: Salir");
            System.out.println(" ");

            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();

            System.out.println(" ");


        }

        public static void CrearEquipo () {
            Scanner sc = new Scanner(System.in);

        if (opcion == 1) {

            String equipo;

            do {
                System.out.print("Introduce el nombre del equipo: ");
                equipo = sc.nextLine();

                if (equipo.length() < 4) {
                    System.out.println("El nombre del equipo debe tener al menos 4 caracteres");
                }
            }

            while (equipo.length() < 4);

                System.out.print("Escribe la fecha de fundacion del equipo: ");
                String fecha_fundacion = sc.nextLine();

                System.out.print("");
                System.out.println("El equipo " + equipo + " se ha guardado correctamente");
                System.out.println(" ");

                equipoCreado = true;
            }
        }

            public static void AñadirJugadores () {
                Scanner sc = new Scanner(System.in);

                int contador = 0;
                String nombre;
                String apellido;
                String nacionalidad;
                String nickname;

                if (opcion == 2) {

                    if (equipoCreado == false) {
                        System.out.println("Primero crea un equipo");
                        System.out.println(" ");

                    }

                    else {
                        do {
                            do {
                                System.out.print("Introduce el nombre del jugador: ");
                                nombre = sc.nextLine();

                                if (nombre.length() < 4) {
                                    System.out.println("El nombre debe tener como minimo 4 caracteres");
                                }

                                System.out.print("Introduce el apellido del jugador: ");
                                apellido = sc.nextLine();

                                if (apellido.length() < 4) {
                                    System.out.println("El apellido debe tener como minimo 4 caracteres");
                                }
                            }

                            while (nombre.length() < 4 && apellido.length() < 4);

                            do {
                                System.out.print("Introduce la nacionalidad del jugador: ");
                                nacionalidad = sc.nextLine();

                                if (nacionalidad.length() < 6) {
                                    System.out.println("La nacionalidad del jugador debe tener como minimo 6 caracteres");
                                }
                            }

                            while (nacionalidad.length() < 6);

                            System.out.print("Introduce la fecha de nacimiento del jugador (dd/mm/YYYY) : ");
                            String fecha = sc.nextLine();

                            do {
                                System.out.print("Escribe el nickname del jugador: ");
                                nickname = sc.nextLine();

                                if (nickname.length() < 4) {
                                    System.out.println("El nickname del jugador debe tener como minimo 4 caracteres");
                                }
                            }

                            while (nickname.length() < 4);

                            System.out.print("Escribe el rol del jugador: ");
                            String rol = sc.nextLine();

                            int sueldo;

                            do {

                            System.out.print("Escribe el sueldo del jugador: ");
                            sueldo = sc.nextInt();

                                if (sueldo < 1221) {
                                    System.out.println("El sueldo tiene que ser mayor al salario minimo interprofesional (1221 €) ");
                                }
                            }

                            while (sueldo < 1221);

                            System.out.println("El jugador de nombre " + nombre + " ha sido guardado correctamente");
                            System.out.println(" ");

                            sc.nextLine();

                            contador = contador + 1;
                        }

                        while (contador < 6);
                    }
                }
            }

            public static void CerrarPrograma () {

            if (opcion == 3) {
                System.out.println("Saliendo del programa... ");
            }
            }
}
