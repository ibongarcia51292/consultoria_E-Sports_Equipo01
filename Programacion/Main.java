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
    static StringBuilder registrojugador = new StringBuilder();
    static int salarioMinimoInterprofesional = 1221;
    public static void main(String[] args) {

try {
    MostrarMenu();
    LeerJugador();
    leerEquipo();
    listaFinal();
}catch (Exception e){
    System.out.println("Ha ocurrido un error inexperado");
}
    }

    public static void MostrarMenu () {
        boolean comprobador = true;
        do {
            comprobador = true;
            try{
                Scanner dc = new Scanner(System.in);
                System.out.println("-- BIENVENIDO AL TORNEO DE E-SPORTS --");
                System.out.println("Primero vamos a introducir el jugador y despues vamos a introducir su equipo");
            }
            catch (InputMismatchException e){
                System.out.println("Solo puedes poner numero amigo mio " + e.getCause() + e.getMessage());
                comprobador = false;

            }
        }
        while (!comprobador);
    }
    public static void listaFinal (){
        System.out.println(registrojugador);
        System.out.println(registroEquipo);
    }
    public static void LeerJugador (){
        System.out.println("Pon tu nombre");
        String nombre = "";
        nombre = nombreJug(nombre);
        System.out.println("Pon tus dos apellidos si solo tienes uno pon solo 1");
        String apellidos = "";
        apellidos = apellidosJug(apellidos);
        System.out.println("Pon tu nacionalidad con un trigrama si no eres europeo pon OTR");
        String nacionalidad = "";
        nacionalidad = nacionalidadfun(nacionalidad);
        System.out.println("pon la fecha de nacimiento con este formato yyyy");
        LocalDate fechaNac = LocalDate.now();
        fechaNac = leerFechaDeNacimiento(fechaNac);
        System.out.println("Cual es el nickname de tu jugador?");
        String nickname = "";
        nickname = nicknameFun(nickname);
        System.out.println("De que rol jugara este jugador entre las opciones tenemos blue,green,yellow,jungla y carry");
        String rol = "";
        rol = rolFun(rol);
        System.out.println("Cuanto quieres pagarle este jugador al mes tiene que respetar el salario minimo interprofesional (1221)");
        int sueldo  = 0;
        sueldo = sueldoFun(sueldo);
        tomarDatosDeJugadores(nombre,apellidos,nacionalidad,fechaNac,nickname,rol,sueldo);
    }
    public static void tomarDatosDeJugadores(String nombre,String apellidos,String nacionalidad, LocalDate fechanaz, String nickname, String rol, int sueldo){
        registrojugador.append("--Jugador Registrado--\n");
        registrojugador.append("Nombre: " + nombre + "\n" +
                "Apellidos: "+apellidos+"\n" +
                "Nacionalidad: "+nacionalidad+"\n" +
                "Fecha de nacimiento: " + fechanaz +"\n" +
                "Rol seleccionado: " +rol+ "\n" +
                "sueldo del jugador: " +sueldo+"€ \n");
    }
    public static int sueldoFun(int sueldo){
        Scanner dc = new Scanner(System.in);
        boolean comprobador = false;
        do {
            try {
                sueldo = dc.nextInt();
                if (sueldo >= salarioMinimoInterprofesional){
                    comprobador = true;
                }
                else{
                  System.out.println("tiene que ser un sueldo mayor a " + salarioMinimoInterprofesional);
                }
            }catch (InputMismatchException e){
                System.out.println("porfavor pon un numero y sin ningun simbolo");
            }

        }while (!comprobador);

        return sueldo;
    }
    public static String rolFun (String rol){
        Scanner dc = new Scanner(System.in);
        boolean comprobador = false;
        do {
            try{
                rol = dc.nextLine();
                rol = rol.toLowerCase();
                Pattern pat = Pattern.compile("^(blue|green|yellow|jungla|carry)$");
                Matcher mat = pat.matcher(rol);
                if (mat.matches()){
                    comprobador = true;
                }
                else  {
                    throw new Exception();
                }

            }
            catch (Exception e){
                System.out.println("Has puesto mal el equipo acuerdate que solo tienes de opciones blue,green,yellow, jungla y carry");
            }
        }
        while (!comprobador);
        return rol;
    }
    public static String nicknameFun (String nickname){
        Scanner dc = new Scanner(System.in);
        boolean comprobador = false;
        do {
            try{
                nickname = dc.nextLine();
                Pattern pat = Pattern.compile("^[0-9A-Za-zÁÉÍÓÚáéíóúñÑ]+( ?[0-9A-Za-zÁÉÍÓÚáéíóúñÑ]+)$");
                Matcher mat = pat.matcher(nickname);
                if (mat.matches()){
                    comprobador = true;
                }
                else  {
                    throw new Exception();
                }

            }
            catch (Exception e){
                System.out.println("el nickname tiene mas de 1 espacio");
            }
        }
        while (!comprobador);
        return nickname;
    }
    public static LocalDate leerFechaDeNacimiento (LocalDate fechaNac){
        Scanner dc = new Scanner(System.in);
        boolean confirmacion = true;
        do {
            confirmacion = true;
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fecha = dc.nextLine();
                fechaNac = LocalDate.parse(fecha, formato);
            }catch (DateTimeException e){
                System.out.println("Has puesto mal la fecha de nacimiento porfavor vuelve a repetir");
                confirmacion = false;
            }

        }
        while (!confirmacion);
        return fechaNac;
    }
    public static String nacionalidadfun (String nacionalidad){
        Scanner dc = new Scanner(System.in);
        boolean comprobador = false;
        do {
            try{
                nacionalidad = dc.nextLine();
                Pattern pat = Pattern.compile("^(ALB|GER|AND|ARM|AUT|AZE|BEL|BLR|BIH|BUL|CYP|CRO|DEN|SVK|SLO|ESP|EST|FIN|FRA|GEO|GRE|HUN|IRL|ISL|ITA|KOS|LAT|LIE|LTU|LUX|MLT|MDA|MON|MNE|NOR|NED|POL|POR|GBR|CZE|MKD|ROU|RUS|SMR|SRB|SWE|SUI|TUR|UKR|VAT|OTR])$");
                Matcher mat = pat.matcher(nacionalidad);
                if (mat.matches()){
                    comprobador = true;
                }
                else  {
                    throw new Exception();
                }

            }
            catch (Exception e){
                System.out.println("""
                        Has puesto mal el trigrama acuerdate que tienes que ponerlo en mayusculas, aqui tienes todas tus opciones:
                        Albania (ALB), Alemania (DEU), Andorra (AND), Armenia (ARM), Austria (AUT),
                        Azerbaiyán (AZE), Bélgica (BEL), Bielorrusia (BLR), Bosnia y Herzegovina (BIH), Bulgaria (BGR),
                        Chipre (CYP), Croacia (HRV), Dinamarca (DNK), Eslovaquia (SVK), Eslovenia (SVN),
                        España (ESP), Estonia (EST), Finlandia (FIN), Francia (FRA), Georgia (GEO),
                        Grecia (GRC), Hungría (HUN), Irlanda (IRL), Islandia (ISL), Italia (ITA),
                        Kosovo (XKX), Letonia (LVA), Liechtenstein (LIE), Lituania (LTU), Luxemburgo (LUX),
                        Malta (MLT), Moldavia (MDA), Mónaco (MCO), Montenegro (MNE), Noruega (NOR),
                        Países Bajos (NLD), Polonia (POL), Portugal (PRT), Reino Unido (GBR), República Checa (CZE),
                        Macedonia del Norte (MKD), Rumanía (ROU), Rusia (RUS), San Marino (SMR), Serbia (SRB),
                        Suecia (SWE), Suiza (CHE), Turquía (TUR), Ucrania (UKR), Ciudad del Vaticano (VAT)
                       U Otro pais (OTR)
                        """);

            }
        }
        while (!comprobador);
        return nacionalidad;
    }
    public static String apellidosJug (String apellidos){
        Scanner dc = new Scanner(System.in);
        boolean comprobador = false;
        do {
            try{
                apellidos = dc.nextLine();
                Pattern pat = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúñÑ]+( ?[A-Za-zÁÉÍÓÚáéíóúñÑ]+$)");
                Matcher mat = pat.matcher(apellidos);
                if (mat.matches()){
                    comprobador = true;
                }
                else  {
                    throw new Exception();
                }

            }
            catch (Exception e){
                System.out.println("Has puesto mal los apellidos acuerdate que es 2 palabras separadas por un espacio sin numeros");
            }
        }
        while (!comprobador);
        return apellidos;
    }
    public static String nombreJug (String nombre){
        Scanner dc = new Scanner(System.in);
        boolean comprobador = false;
        do {
            try{
                nombre = dc.nextLine();
                Pattern pat = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúñÑ]+$");
                Matcher mat = pat.matcher(nombre);
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
        return nombre;
    }
    public static void leerEquipo(){
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
        registroEquipo.append("--Equipo Registrado--\n");
        registroEquipo.append("Nombre de equipo: "+nombreEquipo+ "\n"
                + "Fecha de creacion del equipo: " + tiempo + "\n" +
                "Numero de Jugadores: "+numeroDeJug +"\n" +
                "Trigrama del equipo: "+trigrama+"\n" );
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
