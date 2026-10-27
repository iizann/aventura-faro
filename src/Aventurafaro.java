// Repositorio: https://github.com/iizann/aventura-faro
// Estructura principal
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
        System.out.println("Reglas del juego: empiezas con 3 vidas. Si llegas a 0 en cualquier escena, vas al final malo " + ". El inventario puede tener 3 objetos");
        System.out.println("¿Estas listo para jugar?");
        System.out.println("1 - Si");
        System.out.println("2 - No");
        int preg = sc.nextInt();
        if (preg == 1) {
            System.out.println("Eso queria ver");
        } else if (preg==2) {
            System.out.println("Vuelve cuando estes listo");
            return;
        }else {System.out.println("introduce una opcion valida");
        return;}

        System.out.println("Vidas disponibles: " + vidas);

        Escena1();
    }

    public static void  Escena1(){
        System.out.println("Despiertas en el sótano de un faro abandonado, con la cabeza dolorida y sin recordar cómo has llegado hasta allí. La linterna está apagada y apenas ves nada,\n pero distingues unas escaleras que suben y un viejo baúl en una esquina.");
        System.out.println("¿Que decides hacer?");
        System.out.println("1 - Subir las escaleras");
        System.out.println("2 - Registrar el baúl");
        int opcion =leerOpcion(2);
        switch (opcion){
            case 1:
                System.out.println("Has elegido subir las escaleras");
                Escena2();
                break;
            case 2:
                System.out.println("Has elegido registrar el baúl");
                break;
        }

    }

    public static void  Escena2(){
        System.out.println("Abres el baúl y dentro encuentras una llave oxidada y una navaja.\n1 La tapa pesa y parece que se va a cerrar de golpe");
        System.out.println("¿Que decides hacer?");
        System.out.println("1 - Coger la llave");
        System.out.println("2 - Coger la navaja");
        System.out.println("3 - Coger las 2");
        int opcion = leerOpcion(3);
        switch (opcion){
            case 1:
                System.out.println("Has cogido la llave");
                tieneLlave = true;
                break;
            case 2:
                System.out.println("Has cogido la navaja*");
                tieneNavaja =true;
                break;
            case 3:
                System.out.println("Has cogido las 2 pero...");
                System.out.println("La tapa te golpea la mano y has perdido una vida");
                tieneLlave = true;
                tieneNavaja = true;
                System.out.println("Has cogido la llave y la navaja");

        }
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
