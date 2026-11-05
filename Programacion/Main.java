import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion;
        boolean equipoCreado = false;
        int contador = 0;
        
        do {
            System.out.println("-- BIENVENIDO AL TORNEO DE E-SPORTS --");
            System.out.println("1: Introducir equipo");
            System.out.println("2: Introducir jugadores");
            System.out.println("3: Salir");
            System.out.print("Elige una opcion: ");

            opcion = sc.nextInt();

            if (opcion == 1) {
                System.out.print("Introduce el nombre del equipo: ");
                String equipo = sc.nextLine();
                equipo = sc.nextLine();

                System.out.print("Escribe la fecha de fundacion del equipo: ");
                String fecha_fundacion = sc.nextLine();

                System.out.print("");
                System.out.println("El equipo " + equipo + " se ha guardado correctamente");

                equipoCreado = true;

            } else if (opcion == 2) {

                if (equipoCreado == false) {
                    System.out.println("Primero crea un equipo");

                } else {
                    do {
                        System.out.print("Introduce el nombre del jugador: ");
                        String nombre = sc.nextLine();
                        nombre = sc.nextLine();

                        System.out.print("Introduce el apellido del jugador: ");
                        String apellido = sc.nextLine();

                        System.out.print("Introduce la nacionalidad del jugador: ");
                        String nacionalidad = sc.nextLine();

                        System.out.print("Introduce la fecha de nacimiento del jugador (dd/mm/YYYY) : ");
                        String fecha = sc.nextLine();

                        System.out.print("Escribe el nickname del jugador: ");
                        String nickname = sc.nextLine();

                        System.out.print("Escribe el rol del jugador: ");
                        String rol = sc.nextLine();

                        System.out.print("Escribe el sueldo del jugador: ");
                        int sueldo = sc.nextInt();

                        System.out.println("El jugador de nombre " + nombre + " ha sido guardado correctamente");

                        contador = contador + 1;
                    }

                    while (contador <= 6);
                }
            } else if (opcion == 3) {
                System.out.println("Saliendo del programa... ");
            } else {
                System.out.println("Elige una opcion correcta");
            }
        }

        while (opcion != 3);
    }
}