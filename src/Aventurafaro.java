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
        System.out.println("Reglas del juego: empiezas con 3 vidas. "
                + "Si llegas a 0 en cualquier escena, vas al final malo. "
                + "El inventario puede tener 3 objetos");
        System.out.println("¿Estás listo para jugar?");
        System.out.println("1 - Sí");
        System.out.println("2 - No");

        int preg = leerOpcion(2);

        if (preg == 1) {
            System.out.println("Eso quería ver");
        } else {
            System.out.println("Vuelve cuando estés listo");
            return;
        }

        System.out.println("Vidas disponibles: " + vidas);
        Escena1();
    }

    public static void Escena1() {

        System.out.println("Despiertas en el sótano de un faro abandonado, "
                + "con la cabeza dolorida y sin recordar cómo has llegado hasta allí. "
                + "La linterna está apagada y apenas ves nada,\n pero distingues "
                + "unas escaleras que suben y un viejo baúl en una esquina.");

        System.out.println("¿Qué decides hacer?");
        System.out.println("1 - Subir las escaleras");
        System.out.println("2 - Registrar el baúl");

        int camino = leerOpcion(2);

        switch (camino) {
            case 1:
                System.out.println("Has elegido subir las escaleras");
                Escena3();
                break;

            case 2:
                System.out.println("Has elegido registrar el baúl");
                Escena2();
                break;
        }
    }

    public static void Escena2() {

        System.out.println("Abres el baúl y dentro encuentras una llave oxidada "
                + "y una navaja.\nLa tapa pesa y parece que se va a cerrar de golpe.");

        System.out.println("¿Qué decides hacer?");
        System.out.println("1 - Coger la llave");
        System.out.println("2 - Coger la navaja");
        System.out.println("3 - Coger las 2");

        int herramienta = leerOpcion(3);

        switch (herramienta) {
            case 1:
                System.out.println("Has cogido la llave");
                tieneLlave = true;
                tieneNavaja = false;
                tieneCerillas = false;
                Escena3();
                break;

            case 2:
                System.out.println("Has cogido la navaja");
                tieneNavaja = true;
                tieneLlave = false;
                tieneCerillas = false;
                Escena3();
                break;

            case 3:
                System.out.println("Has cogido las 2 pero...");
                System.out.println("La tapa te golpea la mano y pierdes una vida.");
                tieneLlave = true;
                tieneNavaja = true;

                if (PerderVidas()) {
                    Escena3();
                } else {
                    // Aquí irá la llamada al final malo.
                }
                break;
        }
    }

    public static void Escena3() {

        System.out.println("Llegas a la planta baja del faro. Hay tres puertas: "
                + "\nla de la cocina, la de la biblioteca y una puerta pequeña "
                + "al fondo, con la madera hinchada por la humedad.");

        System.out.println("¿Por qué puerta te atreves a pasar?");
        System.out.println("1 - Puerta de la cocina");
        System.out.println("2 - Puerta de la biblioteca");
        System.out.println("3 - Puerta del fondo, con la madera astillada");

        int puerta = leerOpcion(3);

        switch (puerta) {
            case 1:
                System.out.println("Has elegido la puerta de la cocina");
                Escena4();
                break;

            case 2:
                System.out.println("Has elegido la puerta de la biblioteca");
                Escena5();
                break;

            case 3:
                System.out.println("Has elegido la puerta del fondo, "
                        + "con la madera hinchada");

                if (tieneLlave) {
                    System.out.println("Has abierto la puerta");
                    // Aquí irá la llamada a la escena 7.
                } else {
                    System.out.println("No tienes la llave. Al intentar abrirla, "
                            + "la puerta tira astillas  y pierdes 1 vida.");

                    if (PerderVidas()) {
                        Escena4();
                    } else {
                        // Aquí irá la llamada al final malo.
                    }
                }
                break;
        }
    }

    public static void Escena4() {

        System.out.println("Entras en la cocina. Sobre la mesa hay unas latas de comida de hace años.\n"
                + "Tienes hambre, pero no sabes si seguirán en buen estado.");

        System.out.println("¿Qué decides hacer?");
        System.out.println("1 - Comerte una lata (ganas energía, pero pierdes una vida por la intoxicación)");
        System.out.println("2 - Dejar las latas y seguir adelante");

        int comida = leerOpcion(2);

        switch (comida) {
            case 1:
                System.out.println("Decides comerte la lata");
                System.out.println("La comida estaba en mal estado y pierdes una vida por intoxicación.");

                if (PerderVidas()) {
                    Escena5();
                } else {
                    // Aquí irá la llamada al final malo.
                }
                break;

            case 2:
                System.out.println("Decides dejar las latas y seguir");
                System.out.println("Continuas hacia las escaleras de caracol");
                Escena6();
                break;
        }
    }

    public static void Escena5(){
        System.out.println("\n" +
                "Entras en la biblioteca. Hay un libro abierto sobre un atril, con una página marcada con un trozo de tela.\n" +
                " En una esquina ves un cajón con el cerrojo atascado.\n");
        System.out.println("1 - Leer la página marcada (consigues una pista para el panel de la linterna)\n");
        if (tieneNavaja)
        {
            System.out.println("2 - Forzar el cajón con la navaja (solo aparece si tienes la navaja):\n" +
                    " dentro hay una caja de cerillas, que pasan a tu inventario\n");
        }
        System.out.println("3 - Ignorarlo todo y seguir adelante");
        int decidir = leerOpcion(3);

        switch (decidir){
            case 1:
                pistaLeida =true;
                System.out.println("Has leido la pista");
                Escena6();
                break;
            case 2:
                tieneNavaja =false;
                tieneCerillas= true;
                System.out.println("Al abrir el cajon encuentras una caja de cerillas \n pero pierdes la navaja");
                System.out.println("Tienes paquete de cerillas en tu inventario");
                System.out.println("Ya no tienes navaja en tu inventario");
                Escena6();
                break;
            case 3:
                System.out.println("Has decidido ignoralo todo y seguir \n se ve que no te gusta arriesgar");
                Escena6();
                break;


        }
    }
public static void  Escena6(){
    System.out.println("En mitad de la escalera de caracol aparece el fantasma del farero y te corta el paso.\n" +
            " Solo te dejará subir si le ganas a piedra, papel o tijera al mejor de tres.\n");


}
    public static int leerOpcion(int max) {

        int opcion;

        do {
            System.out.println("Elige una opción entre 1 y " + max + ":");

            while (!sc.hasNextInt()) {
                System.out.println("Error: debes introducir un número.");
                sc.next();
                System.out.println("Vuelve a intentarlo:");
            }

            opcion = sc.nextInt();

            if (opcion < 1 || opcion > max) {
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion < 1 || opcion > max);

        return opcion;
    }

    public static boolean PerderVidas() {

        vidas--;
        System.out.println("Te quedan " + vidas + " vidas.");

        if (vidas > 0) {
            return true;
        } else {
            System.out.println("Has muerto");
            return false;
        }
    }
}