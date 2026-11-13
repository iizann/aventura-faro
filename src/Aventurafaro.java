// Repositorio: https://github.com/iizann/aventura-faro
// Estructura principal

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    static int velocidad = 120; // milisegundos entre palabras al escribir la historia

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
        escena1();
    }

    public static void escena1() {

        escribirLento("Despiertas en el sótano de un faro abandonado, "
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
                escena3();
                break;

            case 2:
                System.out.println("Has elegido registrar el baúl");
                escena2();
                break;
        }
    }

    public static void escena2() {

        escribirLento("Abres el baúl y dentro encuentras una llave oxidada "
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
                escena3();
                break;

            case 2:
                System.out.println("Has cogido la navaja");
                tieneNavaja = true;
                escena3();
                break;

            case 3:
                System.out.println("Has cogido las 2 pero...");
                escribirLento("La tapa te golpea la mano y pierdes una vida.");
                tieneLlave = true;
                tieneNavaja = true;

                if (perderVidas()) {
                    escena3();
                } else {
                    escena12();
                }
                break;
        }
    }

    public static void escena3() {

        escribirLento("Llegas a la planta baja del faro. Hay tres puertas: "
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
                escena4();
                break;

            case 2:
                System.out.println("Has elegido la puerta de la biblioteca");
                escena5();
                break;

            case 3:
                System.out.println("Has elegido la puerta del fondo, "
                        + "con la madera hinchada");

                if (tieneLlave) {
                    System.out.println("Has abierto la puerta");
                    escena7(); // atajo secreto: sube directo a la sala de la linterna
                } else {
                    escribirLento("No tienes la llave. Al intentar abrirla, "
                            + "la puerta suelta astillas y pierdes 1 vida.");

                    if (perderVidas()) {
                        escena3();
                    } else {
                        escena12();
                    }
                }
                break;
        }
    }

    public static void escena4() {

        escribirLento("Entras en la cocina. Sobre la mesa hay unas latas de comida de hace años.\n"
                + "Tienes hambre, pero no sabes si seguirán en buen estado.");

        System.out.println("¿Qué decides hacer?");
        System.out.println("1 - Comerte una lata (ganas energía, pero pierdes una vida por la intoxicación)");
        System.out.println("2 - Dejar las latas y seguir adelante");

        int comida = leerOpcion(2);

        switch (comida) {
            case 1:
                System.out.println("Decides comerte la lata");
                escribirLento("La comida estaba en mal estado y pierdes una vida por intoxicación.");

                if (perderVidas()) {
                    escena6();
                } else {
                    escena12();
                }
                break;

            case 2:
                System.out.println("Decides dejar las latas y seguir");
                escribirLento("Continúas hacia la escalera de caracol");
                escena6();
                break;
        }
    }

    public static void escena5() {

        escribirLento("\nEntras en la biblioteca. Hay un libro abierto sobre un atril, "
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
                escena6();
                break;

            case 2:
                if (tieneNavaja) {
                    tieneNavaja = false;
                    tieneCerillas = true;
                    escribirLento("Al abrir el cajón encuentras una caja de cerillas,\n"
                            + "pero pierdes la navaja");
                    System.out.println("Tienes una caja de cerillas en tu inventario");
                    System.out.println("Ya no tienes navaja en tu inventario");
                    escena6();
                } else {
                    escribirLento("No tienes nada con lo que forzar el cajón.");
                    escena5();
                }
                break;

            case 3:
                escribirLento("Has decidido ignorarlo todo y seguir,\n"
                        + "se ve que no te gusta arriesgar");
                escena6();
                break;
        }
    }

    public static void escena6() {

        escribirLento("En mitad de la escalera de caracol aparece el fantasma del farero y te corta el paso.\n"
                + "Solo te dejará subir si le ganas a piedra, papel o tijera al mejor de tres.\n");
        System.out.println("Deberás elegir bien si quieres escapar");

        if (jugarDuelo()) {
            escribirLento("Has vencido al fantasma y puedes seguir subiendo.");
            escena7();
        } else {
            escribirLento("El fantasma te ha ganado y pierdes una vida.");

            if (perderVidas()) {
                escena6();
            } else {
                escena12();
            }
        }
    }

    public static void escena7() {

        escribirLento("Llegas a la sala de la linterna. Un panel metálico con huecos "
                + "para letras bloquea el mecanismo.\nHay que adivinar la palabra secreta para activarlo.");

        System.out.println("¿Qué decides hacer?");
        System.out.println("1 - Intentar descifrar el panel");
        System.out.println("2 - Dar media vuelta y bajar a la planta baja");

        int opcion = leerOpcion(2);

        switch (opcion) {
            case 1:
                if (jugarAhorcado()) {
                    escribirLento("El mecanismo se abre con un chasquido.");
                    escena8();
                } else {
                    escribirLento("El panel se bloquea para siempre.");
                    escena12();
                }
                break;

            case 2:
                escena3();
                break;
        }
    }
    public static void escena8(){
        escribirLento("Sales a la azotea. A lo lejos ves un barco que navega hacia la costa");
        System.out.println("¿Qué quieres hacer?");
        System.out.println("1 - Encender la linterna para llamar su atención");
        System.out.println("2 - Bajar corriendo hasta el muelle");

        int local = leerOpcion(2);

        switch (local){
            case 1:
                if (tieneCerillas){
                    escribirLento("La luz recorre el mar y el barco gira hacia ti.");
                    escena10();
                } else {
                    escribirLento("No tienes cómo prenderla, el barco se aleja.");
                    if (perderVidas()) {
                        escena8();
                    } else {
                        escena12();
                    }
                }
                break;
            case 2:
                escribirLento("Bajas corriendo hacia el muelle...");
                escena9();
                break;
        }
    }
    public static void escena9(){
        escribirLento("Llegas al muelle y ves un pequeño bote atado con una cadena y un candado.\n Empieza a amanecer.");
        System.out.println("1 - Abrir el candado");
        System.out.println("2 - Sentarte a esperar en el muelle hasta que amanezca del todo");
        System.out.println("3 - Volver a la azotea");
        int decision = leerOpcion(3);

        switch (decision) {
            case 1:
                if (tieneLlave) {
                    escribirLento("La llave gira en el candado y la cadena cae al agua.\n"
                            + "Pero dentro del bote está sentado un viejo marinero que baraja unas cartas.\n"
                            + "\"Este bote es mío. Si lo quieres, gánamelo al Blackjack.\"");

                    // ÚLTIMA PRUEBA: el blackjack contra el marinero
                    if (jugarBlackjack()) {
                        escribirLento("El marinero sonríe y te cede los remos.");
                        escena13();
                    } else {
                        escribirLento("El marinero recoge las cartas: \"Otra vez será\". Pierdes una vida.");
                        if (perderVidas()) {
                            escena9();
                        } else {
                            escena12();
                        }
                    }
                } else {
                    escribirLento("No tienes la llave. Tiras del candado y te cortas con la cadena oxidada.");
                    if (perderVidas()) {
                        escena9();
                    } else {
                        escena12();
                    }
                }
                break;

            case 2:
                escribirLento("Te sientas en el muelle a esperar...");
                escena11();
                break;

            case 3:
                escribirLento("Vuelves a subir a la azotea.");
                escena8();
                break;
        }
    }

    // ===================== FINALES =====================

    public static void escena10() {
        System.out.println("\n===== FINAL BUENO =====");
        escribirLento("El barco ve la luz del faro y te rescata. Despiertas a salvo, con el sol saliendo sobre el mar.\n");
    }

    public static void escena11() {
        System.out.println("\n===== FINAL NEUTRAL =====");
        escribirLento("Unos pescadores te encuentran en el muelle por la mañana y te llevan a tierra. Estás vivo,\n" +
                " pero sigues sin saber quién eres ni qué pasó en el faro.");
    }

    public static void escena12() {
        System.out.println("\n===== FINAL MALO =====");
        escribirLento("Te quedas sin fuerzas, sin vidas o sin salida. El faro se queda de nuevo en silencio.");
    }

    public static void escena13() {
        System.out.println("\n===== FINAL SECRETO =====");
        escribirLento("Navegas en el bote hasta una isla lejana donde descubres que el farero \n" +
                " llevaba años esperando a alguien como tú.");
    }



    // Juega manos de Blackjack contra el marinero hasta que alguien gane (los empates se repiten).
    // Devuelve true si gana el jugador y false si gana el marinero.
    public static boolean jugarBlackjack() {

        int resultado;

        do {
            resultado = jugarManoBlackjack();
            if (resultado == 0) {
                System.out.println("Empate. El marinero vuelve a repartir.");
            }
        } while (resultado == 0);

        return resultado == 1;
    }

    // Juega UNA mano. Devuelve: 0 = empate, 1 = gana el jugador, 2 = gana el marinero.
    public static int jugarManoBlackjack() {

        Baraja baraja = new Baraja();
        Mano jugador = new Mano();
        Mano marinero = new Mano();

        // 1. Reparto inicial: 2 cartas para cada uno
        jugador.agregarCarta(baraja.robar());
        marinero.agregarCarta(baraja.robar());
        jugador.agregarCarta(baraja.robar());
        marinero.agregarCarta(baraja.robar());

        System.out.println("\n--- NUEVA MANO ---");
        System.out.println("Carta visible del marinero: " + marinero.getPrimeraCarta());
        System.out.println("Tu mano: " + jugador);

        // 2. Comprobamos si alguien tiene Blackjack nada más empezar
        if (jugador.esBlackjack() || marinero.esBlackjack()) {
            System.out.println("Mano del marinero: " + marinero);
            if (jugador.esBlackjack() && marinero.esBlackjack()) {
                System.out.println("¡Los dos tenéis Blackjack!");
                return 0;
            } else if (jugador.esBlackjack()) {
                System.out.println("¡BLACKJACK! Has ganado la mano.");
                return 1;
            } else {
                System.out.println("El marinero tiene Blackjack.");
                return 2;
            }
        }

        // 3. Turno del jugador: pide cartas hasta que se plante o se pase
        while (jugador.getPuntos() < 21) {
            System.out.println("1 - Pedir carta");
            System.out.println("2 - Plantarte");

            if (leerOpcion(2) == 1) {
                Carta nueva = baraja.robar();
                jugador.agregarCarta(nueva);
                System.out.println("Has robado: " + nueva);
                System.out.println("Tu mano: " + jugador);
            } else {
                break;
            }
        }

        if (jugador.getPuntos() > 21) {
            System.out.println("Te has pasado de 21.");
            return 2;
        }

        // 4. Turno del marinero: roba obligatoriamente hasta tener 17 o más
        System.out.println("\nTurno del marinero...");
        System.out.println("Mano del marinero: " + marinero);
        while (marinero.getPuntos() < 17) {
            Carta nueva = baraja.robar();
            marinero.agregarCarta(nueva);
            System.out.println("El marinero roba: " + nueva);
            System.out.println("Mano del marinero: " + marinero);
        }

        // 5. Comparamos resultados
        int puntosJugador = jugador.getPuntos();
        int puntosMarinero = marinero.getPuntos();

        System.out.println("\nTú: " + puntosJugador + " | Marinero: " + puntosMarinero);

        if (puntosMarinero > 21) {
            System.out.println("El marinero se ha pasado de 21.");
            return 1;
        } else if (puntosJugador > puntosMarinero) {
            return 1;
        } else if (puntosJugador < puntosMarinero) {
            return 2;
        } else {
            return 0;
        }
    }
    // Escribe un texto palabra a palabra (solo para la historia), con una pausa entre palabras.
    // Cada palabra se imprime entera, así que el salto de línea que provoca pulsar Intro
    // cae siempre entre dos palabras y nunca las parte.
    // Si el jugador pulsa Intro, se muestra el resto del texto de golpe.
    public static void escribirLento(String texto) {

        int inicio = 0;

        while (inicio < texto.length()) {
            int fin = texto.indexOf(' ', inicio);

            if (fin == -1) {
                fin = texto.length();
            } else {
                fin = fin + 1; // incluye el espacio detrás de la palabra
            }

            System.out.print(texto.substring(inicio, fin));
            inicio = fin;

            try {
                if (System.in.available() > 0) {
                    // Descarta lo que ha tecleado para que no se cuele en la siguiente pregunta
                    while (System.in.available() > 0) {
                        System.in.read();
                    }
                    System.out.print(texto.substring(inicio));
                    break;
                }
                Thread.sleep(velocidad);
            } catch (IOException e) {
                // si no se puede leer el teclado, seguimos escribiendo normal
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println();
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

    // Resta 1 vida. Devuelve true si el jugador sigue vivo y false si ha muerto.
    public static boolean perderVidas() {
        return perderVidas(1);
    }

    // Resta n vidas (sobrecarga). Devuelve true si el jugador sigue vivo y false si ha muerto.
    public static boolean perderVidas(int n) {

        vidas = vidas - n;

        if (vidas < 0) {
            vidas = 0;
        }

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

        // Matriz de resultados: fila = tu jugada, columna = la del fantasma
        // (orden: piedra, papel, tijera). 0 = empate, 1 = ganas tú, 2 = gana el fantasma.
        int[][] resultados = {
                {0, 2, 1},  // tú sacas piedra
                {1, 0, 2},  // tú sacas papel
                {2, 1, 0}   // tú sacas tijera
        };

        return resultados[miJugada - 1][jugadaFantasma - 1];
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

    public static boolean jugarAhorcado() {

        String palabra = "linterna";
        String usadas = "";
        int intentos = 6;
        boolean completada = false;

        if (pistaLeida) {
            intentos = 7;
            System.out.println("Recuerdas la página de la biblioteca: tienes un intento extra.");
        }

        while (intentos > 0 && !completada) {

            // Construye el progreso: la letra si ya se ha probado y "_" si no.
            String progreso = "";
            completada = true;

            for (int i = 0; i < palabra.length(); i++) {
                char letraPalabra = palabra.charAt(i);

                if (usadas.indexOf(letraPalabra) >= 0) {
                    progreso = progreso + letraPalabra + " ";
                } else {
                    progreso = progreso + "_ ";
                    completada = false;
                }
            }

            System.out.println("\nPalabra: " + progreso);

            if (!completada) {
                System.out.println("Intentos restantes: " + intentos);
                System.out.println("Letras usadas: " + usadas);

                char letra = leerLetra();

                if (usadas.indexOf(letra) >= 0) {
                    System.out.println("Ya habías probado esa letra.");
                } else {
                    usadas = usadas + letra + " ";

                    if (palabra.indexOf(letra) >= 0) {
                        System.out.println("¡Bien! Esa letra está en la palabra.");
                    } else {
                        intentos--;
                        System.out.println("Esa letra no está.");
                    }
                }
            }
        }

        if (completada) {
            System.out.println("¡Lo has conseguido! La palabra era: " + palabra);
        } else {
            System.out.println("Te has quedado sin intentos. La palabra era: " + palabra);
        }

        return completada;
    }

    public static char leerLetra() {

        String texto;

        do {
            System.out.println("Introduce una letra:");
            texto = sc.next();

            if (texto.length() != 1 || !Character.isLetter(texto.charAt(0))) {
                System.out.println("Debes escribir una sola letra.");
            }

        } while (texto.length() != 1 || !Character.isLetter(texto.charAt(0)));

        return Character.toLowerCase(texto.charAt(0));
    }
}

// ===================== CLASES DEL BLACKJACK =====================

// Representa una carta de la baraja: tiene un palo y un nombre (A, 2, 3... J, Q, K)
class Carta {
    private String palo;
    private String nombre;

    public Carta(String palo, String nombre) {
        this.palo = palo;
        this.nombre = nombre;
    }

    // Devuelve cuánto vale la carta en el Blackjack
    public int getValor() {
        if (nombre.equals("A")) {
            return 11; // El As vale 11 (luego lo bajamos a 1 si nos pasamos)
        } else if (nombre.equals("J") || nombre.equals("Q") || nombre.equals("K")) {
            return 10; // Las figuras valen 10
        } else {
            return Integer.parseInt(nombre); // Del 2 al 10 valen su número
        }
    }

    public boolean esAs() {
        return nombre.equals("A");
    }

    @Override
    public String toString() {
        return nombre + " de " + palo;
    }
}

// Contiene las 52 cartas, las baraja y permite robar
class Baraja {
    private ArrayList<Carta> cartas = new ArrayList<>();

    public Baraja() {
        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        String[] nombres = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Creamos una carta por cada combinación de palo y nombre (4 x 13 = 52)
        for (String palo : palos) {
            for (String nombre : nombres) {
                cartas.add(new Carta(palo, nombre));
            }
        }
        Collections.shuffle(cartas); // Mezclamos la baraja
    }

    // Saca la primera carta de la baraja y la devuelve
    public Carta robar() {
        return cartas.remove(0);
    }
}

// Las cartas que tiene un jugador (tú o el marinero)
class Mano {
    private ArrayList<Carta> cartas = new ArrayList<>();

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    // Calcula los puntos de la mano teniendo en cuenta los Ases
    public int getPuntos() {
        int puntos = 0;
        int ases = 0;

        for (Carta carta : cartas) {
            puntos += carta.getValor();
            if (carta.esAs()) {
                ases++;
            }
        }

        // Si nos pasamos de 21 y tenemos Ases, cada As pasa de valer 11 a valer 1
        while (puntos > 21 && ases > 0) {
            puntos -= 10;
            ases--;
        }
        return puntos;
    }

    // Blackjack = 21 con solo 2 cartas
    public boolean esBlackjack() {
        return cartas.size() == 2 && getPuntos() == 21;
    }

    public Carta getPrimeraCarta() {
        return cartas.get(0);
    }

    @Override
    public String toString() {
        return cartas.toString() + " -> " + getPuntos() + " puntos";
    }
}