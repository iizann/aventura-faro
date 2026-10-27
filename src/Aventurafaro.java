// Repositorio: https://github.com/iizann/aventura-faro
// Estructura principal para el juego
import java.util.Scanner;

public class Aventurafaro {

    static Scanner sc = new Scanner(System.in);

    static int vidas = 3;

    static boolean tieneLlave = false;
    static boolean tieneNavaja = false;
    static boolean tieneCerillas = false;
    static boolean pistaLeida = false;

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("      AVENTURA FARO");
        System.out.println("================================");
        System.out.println("Vidas disponibles: " + vidas);

        int opcion = leerOpcion(2);

        System.out.println("Has elegido la opcion: " + opcion);
    }

    public static int leerOpcion(int max) {

        int opcion;

        do {
            System.out.println("Elige una opcion entre 1 y " + max + ":");

            while (!sc.hasNextInt()) {
                System.out.println("Error: debes introducir un numero.");
                sc.next();
                System.out.println("Vuelve a intentarlo:");
            }

            opcion = sc.nextInt();

            if (opcion < 1 || opcion > max) {
                System.out.println("Opcion no valida. Intentalo de nuevo.");
            }

        } while (opcion < 1 || opcion > max);

        return opcion;
    }
}
