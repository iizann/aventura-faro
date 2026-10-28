// Repositorio: https://github.com/iizann/aventura-faro
// Estructura principal

import java.util.Random;
import java.util.Scanner;

public class Aventurafaro {

    static Scanner sc = new Scanner(System.in);
    static Random azar = new Random();
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
                Escena3();
                break;

            case 2:
                System.out.println("Has cogido la navaja");
                tieneNavaja = true;
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
        System.out.println("3 - Puerta del fondo, con la madera hinchada");

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
                    Escena6();
                } else {
                    System.out.println("No tienes la llave. Al intentar abrirla, "
                            + "la puerta suelta astillas y pierdes 1 vida.");

                    if (PerderVidas()) {
                        Escena3();
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
                    Escena6();
                } else {
                    // Aquí irá la llamada al final malo.
                }
                break;

            case 2:
                System.out.println("Decides dejar las latas y seguir");
                System.out.println("Continúas hacia la escalera de caracol");
                Escena6();
                break;
        }
    }

    public static void Escena5() {

        System.out.println("\nEntras en la biblioteca. Hay un libro abierto sobre un atril, "
                + "con una página marcada con un trozo de tela.\n"
                + "En una esquina ves un cajón con el cerrojo atascado.\n");

        System.out.println("1 - Leer la página marcada (consigues una pista para el panel de la linterna)");

        if (tieneNavaja) {
            System.out.println("2 - Forzar el cajón con la navaja (solo aparece si tienes la navaja):\n"
                    + "    dentro hay una caja de cerillas, que pasan a tu inventario");
        }

        System.out.println("3 - Ignorarlo todo y seguir adelante");

        int decidir = leerOpcion(3);

        switch (decidir) {
            case 1:
                pistaLeida = true;
                System.out.println("Has leído la pista");
                Escena6();
                break;

            case 2:
                if (tieneNavaja) {
                    tieneNavaja = false;
                    tieneCerillas = true;
                    System.out.println("Al abrir el cajón encuentras una caja de cerillas,\n"
                            + "pero pierdes la navaja");
                    System.out.println("Tienes una caja de cerillas en tu inventario");
                    System.out.println("Ya no tienes navaja en tu inventario");
                    Escena6();
                } else {
                    System.out.println("No tienes nada con lo que forzar el cajón.");
                    Escena5();
                }
                break;

            case 3:
                System.out.println("Has decidido ignorarlo todo y seguir,\n"
                        + "se ve que no te gusta arriesgar");
                Escena6();
                break;
        }
    }

    public static void Escena6() {

        System.out.println("En mitad de la escalera de caracol aparece el fantasma del farero y te corta el paso.\n"
                + "Solo te dejará subir si le ganas a piedra, papel o tijera al mejor de tres.\n");
        System.out.println("Deberás elegir bien si quieres escapar");

        if (jugarDuelo()) {
            System.out.println("Has vencido al fantasma y puedes seguir subiendo.");
            // Aquí irá la llamada a la escena 7.
        } else {
            System.out.println("El fantasma te ha ganado y pierdes una vida.");

            if (PerderVidas()) {
                Escena6();
            } else {
                // Aquí irá la llamada al final malo.
            }
        }
    }

    // Lee una opción del teclado y no la da por buena hasta que es un número entre 1 y max.
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

    // Resta una vida. Devuelve true si el jugador sigue vivo y false si ha muerto.
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

    // Juega UNA ronda de piedra, papel o tijera contra el fantasma.
    // Devuelve: 0 = empate, 1 = gana el jugador, 2 = gana el fantasma.
    public static int jugarRonda() {

        System.out.println("1 - Piedra");
        System.out.println("2 - Papel");
        System.out.println("3 - Tijera");

        int miJugada = leerOpcion(3);
        int jugadaFantasma = azar.nextInt(3) + 1; // da 1, 2 o 3

        System.out.println("Tú sacas: " + nombreJugada(miJugada));
        System.out.println("El fantasma saca: " + nombreJugada(jugadaFantasma));

        if (miJugada == jugadaFantasma) {
            return 0;
        } else if ((miJugada == 1 && jugadaFantasma == 3)
                || (miJugada == 2 && jugadaFantasma == 1)
                || (miJugada == 3 && jugadaFantasma == 2)) {
            return 1;
        } else {
            return 2;
        }
    }

    // Juega el mejor de tres: gana quien llegue antes a 2 rondas ganadas.
    // Devuelve true si gana el jugador y false si gana el fantasma.
    public static boolean jugarDuelo() {

        int misVictorias = 0;
        int victoriasFantasma = 0;

        while (misVictorias < 2 && victoriasFantasma < 2) {
            int resultado = jugarRonda();

            if (resultado == 1) {
                misVictorias++;
                System.out.println("Ganas la ronda. Tú " + misVictorias
                        + " - " + victoriasFantasma + " Fantasma");
            } else if (resultado == 2) {
                victoriasFantasma++;
                System.out.println("Pierdes la ronda. Tú " + misVictorias
                        + " - " + victoriasFantasma + " Fantasma");
            } else {
                System.out.println("Empate, se repite la ronda.");
            }
        }

        return misVictorias == 2;
    }

    // Convierte el número de la jugada en su nombre para mostrarlo.
    public static String nombreJugada(int jugada) {
        switch (jugada) {
            case 1:
                return "piedra";
            case 2:
                return "papel";
            default:
                return "tijera";
        }
    }
}