import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    static boolean equipocreado = false;
    static String nom;
    static String apellido;
    static String nacionalidad;
    static String fecha;
    static String apodo;
    static String rol;
    static int sueldo;
    static String nombrequipo;
    static int numjugadores;

    public static StringBuilder jugador(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechanacimiento = LocalDate.parse(fecha, formatter);

        //Aqui para calcular la edad
        int edad = Period.between(fechanacimiento, LocalDate.now()).getYears();

        StringBuilder sb = new StringBuilder();
        sb.append("El jugador ").append(nom).append(" ").append(apellido).append(", ").append("tiene ").append(edad).append(", ").append("tiene como apodo ").append(apodo).append(", ").append("juega con el rol de ").append(rol).append( "y cobra ").append(sueldo).append("€");

        return sb;
    }
    public static void main(String[] args) {

        MostrarMenu();

    }
    
    public static void MostrarMenu () {
        boolean salir = true;
        do {
            try {
                System.out.println("\n-- BIENVENIDO AL TORNEO DE E-SPORTS --");
                System.out.println("1: Introducir equipo");
                System.out.println("2: Introducir jugadores");
                System.out.println("3: Salir");
                System.out.print("Elige una opcion: ");
                int opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        IntroduceEquipo();
                        salir = false;
                        break;
                    case 2:
                        IntroduceJugadores();
                        salir = false;
                        break;
                    case 3:
                        System.out.println("Gracias por venir!");
                        salir = true;
                        break;
                    default:
                        System.out.println("¡Introduce una opcion valida!");
                        salir = false;
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("Introduce un numero entero porfavor.");
                salir= false;
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
                salir=false;
                    }
            }while (salir==false);
        }
        public static void IntroduceJugadores() {
            if (equipocreado == false){
                System.out.println("\nPara poder introducir los jugadores tienes que crear el equipo.");
                MostrarMenu();
            }else {
                System.out.println("Porfavor introduce el nombre del jugador:");
                nom = sc.nextLine();
                System.out.println("Ahora introduce el apellido:");
                apellido = sc.nextLine();
                System.out.println("Ahora la nacionalidad:");
                nacionalidad = sc.nextLine();
                System.out.println("La fecha de nacimiento del jugador: (dd/MM/yyyy)");
                fecha = sc.nextLine();
                System.out.println("Porfavor ingresa el apodo del jugador:");
                apodo = sc.nextLine();
                System.out.println("Ahora el rol: (Capitan o jugador)");
                rol = sc.nextLine();
                System.out.println("Por ultimo el sueldo: (Minimo 1300€)");
                sueldo = sc.nextInt();

                System.out.println("\n ===JUGADOR REGISTRADO=== ");
                System.out.println(jugador().toString());
            }
        }

        public static void IntroduceEquipo(){
            System.out.println("Dime porfavor el nombre del equipo: ");
                nombrequipo = sc.nextLine();
            System.out.println("Fecha de creacion del equipo: "+LocalDate.now());
            sc.nextLine();
            System.out.println("Dime el numero de jugadores: ");
            numjugadores = sc.nextInt();
            equipocreado = true;
        }

}
