import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static StringBuilder registroEquipo = new StringBuilder();
    static int numEquipo = 0;
    public static void main(String[] args) {


        MostrarMenu();
        LeerEquipo();

    }

    public static void MostrarMenu () {
        boolean comprobador = true;
        do {
            comprobador = true;
            try{
                Scanner dc = new Scanner(System.in);
                System.out.println("-- BIENVENIDO AL TORNEO DE E-SPORTS --");
                System.out.println("Primero vamos a introducir equipo \n y despues vamos antroducir un jugador");
            }
            catch (InputMismatchException e){
                System.out.println("Solo puedes poner numero amigo mio " + e.getCause() + e.getMessage());
                comprobador = false;

            }
        }
        while (!comprobador);
    }
    public static void LeerJugador (){
        System.out.println("Nombre");
        String nombre = "";
        System.out.println("Apellidos");
        System.out.println("Nacionalidad");
        System.out.println("Fecha de nacimiento");
        System.out.println("Nickname");
        System.out.println("rol");
        System.out.println("Sueldo");
    }
    public static String nombreJug (String nombre){
        Scanner dc = new Scanner(System.in);
    }

    public static void LeerEquipo(){
        System.out.println("Porfavor elige un nombre para el equipo");
        String nombreEquipo = "";
        nombreEquipo = expresionesNombreEquipo(nombreEquipo);

        System.out.println("Introduce la fecha de fundacion del equipo con este formato yyyy-MM-dd");
        LocalDate tiempo = LocalDate.now();
        tiempo = tomartiempo(tiempo);
        System.out.println("Cuantos jugadores tienes");
        String numeroDeJug = "";
        numeroDeJug = numerojug(numeroDeJug);
        System.out.println("pon el trigrama de tu equipo");
        String trigrama = "";
        trigrama = trigramaFun(trigrama);
        tomarDatosEquipo(nombreEquipo,tiempo,numeroDeJug,trigrama);
    }
    public static void tomarDatosEquipo (String nombreEquipo, LocalDate tiempo, String numeroDeJug, String trigrama) {
        numEquipo = numEquipo + 1;
        registroEquipo.append("Equipo Nº"+ numEquipo+"\n");
        registroEquipo.append("Nombre de equipo: "+nombreEquipo+ "\n"
                + "Fecha de creacion del equipo: " + tiempo + "\n" +
                "Numero de Jugadores: "+numeroDeJug +"\n" +
                "Trigrama del equipo: "+trigrama+"\n" );
        System.out.println(registroEquipo);


    }
    public static String trigramaFun(String trigrama){
        Scanner dc = new Scanner(System.in);
        boolean comprobacion = true;
        do {
            try {


        trigrama = dc.nextLine();
        Pattern pat = Pattern.compile("^[A-ZÁÉÍÓÚÑ]{3}$");
        Matcher mat = pat.matcher(trigrama);
        if (mat.matches()){
            comprobacion = true;
        }
        else {
            throw new Exception();
        }

    }catch (Exception e){
                System.out.println("Solo puedes poner 3 letras Mayusculas");
                comprobacion = false;
            }
    }while (!comprobacion);
        return trigrama;

    }
    public static String numerojug(String numeroDeJug){
        Scanner dc = new Scanner(System.in);
        boolean confirmacion = true;
        do {
            try {
                numeroDeJug = dc.nextLine();
                Pattern pat = Pattern.compile("^(2|4|6)$");
                Matcher mat = pat.matcher(numeroDeJug);
                if (mat.matches()) {
                    confirmacion = true;
                }
                else {
                    throw new Exception();
                }
            }catch (Exception e){
                System.out.println("Acuerdate de que solo pueden ser pares entre 2 y 6 personas");
                confirmacion = false;
            }
        }while (!confirmacion);
        return numeroDeJug;
    }
    public static LocalDate tomartiempo (LocalDate tiempo){
        Scanner dc = new Scanner(System.in);
        boolean confirmacion = true;
        do {
            confirmacion = true;
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fecha = dc.nextLine();
                tiempo = LocalDate.parse(fecha, formato);
            }catch (DateTimeException e){
                System.out.println("Has puesto mal la fecha porfavor vuelve a repetir");
                confirmacion = false;
            }

        }
        while (!confirmacion);
        return tiempo;

    }
    public static String expresionesNombreEquipo(String nombreEquipo){
        boolean comprobador = false;
        Scanner dc = new Scanner(System.in);
        do {
            try{
                nombreEquipo = dc.nextLine();
                Pattern pat = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúñÑ]+$");
                Matcher mat = pat.matcher(nombreEquipo);
                if (mat.matches()){
                    comprobador = true;
                }
                else  {
                    throw new Exception();
                }

            }
            catch (Exception e){
                System.out.println("Has puesto mal el nombre solo puede tener letras sin espacios ni numeros");
            }
        }
        while (!comprobador);
        return nombreEquipo;
    }
}
