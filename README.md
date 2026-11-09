# Aventura Faro

Juego de aventura de texto en Java que se juega por consola. Despiertas en el sótano de un faro abandonado sin recordar cómo has llegado. Tienes que explorar el faro, superar tres minijuegos y llegar a la azotea para pedir ayuda, o escapar por el muelle. Empiezas con 3 vidas y hay 4 finales distintos.

## Enlace al repositorio

https://github.com/iizann/aventura-faro

## Requisitos

- JDK 17 o superior
- IntelliJ IDEA (o cualquier consola con `javac` y `java`)

## Cómo ejecutarlo

1. Descarga el proyecto: botón verde **Code → Download ZIP** y descomprímelo, o con `git clone https://github.com/iizann/aventura-faro.git`.
2. Abre IntelliJ, elige **File → Open** y selecciona la carpeta del proyecto.
3. Comprueba que el proyecto usa un JDK instalado (**File → Project Structure → Project SDK**).
4. Abre la clase principal `Aventurafaro.java`.
5. Pulsa el triángulo verde junto a `public static void main` y elige **Run 'Aventurafaro.main()'**.

Desde consola, en la carpeta donde está el archivo:

```
javac Aventurafaro.java
java Aventurafaro
```

## Cómo se juega

Al empezar se pregunta si estás listo (1 = Sí, 2 = No). En cada escena se muestra un menú con opciones numeradas: escribes el número y pulsas Enter. Si escribes algo no válido (una letra, un 0, un número fuera de rango...), el juego te avisa y repite la pregunta hasta que la opción sea correcta.

En el ahorcado se escribe una sola letra cada vez. Si escribes más de una letra, un número o un símbolo, también se vuelve a pedir.

## Historia y finales

Despiertas en el sótano de un faro abandonado. Subes a la planta baja y eliges entre la cocina, la biblioteca o la puerta del fondo. Antes de llegar a la sala de la linterna te cruzas con el fantasma del farero, que solo te deja pasar si le ganas a piedra, papel o tijera. En la sala de la linterna hay que resolver un panel con una palabra secreta. Ya en la azotea, intentas llamar la atención de un barco o bajas al muelle, donde un viejo marinero solo te dejará llevarte su bote si le ganas al blackjack.

Si tienes la llave, la puerta del fondo de la planta baja esconde un atajo secreto que sube directo a la sala de la linterna.

**Finales:**

| Final | Escena | Cómo se llega |
|---|---|---|
| Bueno | 10 | Encender la linterna en la azotea teniendo las cerillas: el barco te ve y te rescata |
| Neutral | 11 | Esperar en el muelle hasta el amanecer: unos pescadores te llevan a tierra, pero sigues sin saber quién eres |
| Malo | 12 | Quedarte sin vidas en cualquier escena, o fallar el ahorcado |
| Secreto | 13 | Abrir el candado del bote en el muelle con la llave y ganar al marinero al blackjack: navegas hasta una isla lejana donde descubres que el farero esperaba a alguien como tú |

## Inventario y vidas

**Vidas:** empiezas con 3. Si llegan a 0, vas al final malo. Pierdes una vida en estos casos:

- Coger la llave y la navaja a la vez en el baúl (la tapa te golpea la mano).
- Intentar abrir la puerta del fondo sin la llave (la puerta suelta astillas y vuelves a elegir puerta).
- Comerte una lata en la cocina (intoxicación).
- Perder el duelo contra el fantasma.
- Intentar encender la linterna sin cerillas.
- Intentar abrir el candado del bote sin la llave.
- Perder la mano de blackjack contra el marinero.

**Inventario:** máximo 3 objetos, que son los tres que existen en el juego.

| Objeto | Cómo se consigue | Para qué sirve |
|---|---|---|
| Llave | En el baúl del sótano | Abre la puerta del fondo (atajo a la sala de la linterna) y el candado del bote (final secreto, tras ganar al blackjack) |
| Navaja | En el baúl del sótano | Forzar el cajón de la biblioteca (se pierde al usarla) |
| Cerillas | En el cajón de la biblioteca (necesitas la navaja) | Encender la linterna en la azotea (final bueno) |

Además, leer la página marcada de la biblioteca da una pista que te regala un intento extra en el ahorcado.

## Minijuegos

**Piedra, papel o tijera** (escena 6, contra el fantasma del farero)
- Eliges 1 - Piedra, 2 - Papel o 3 - Tijera. El ordenador elige al azar.
- Se juega al mejor de tres. Si ganas, subes a la sala de la linterna.
- Si pierdes el duelo, pierdes una vida y se repite. Los empates de una ronda se vuelven a jugar.

**Ahorcado** (escena 7, panel de la linterna)
- Hay que adivinar la palabra secreta letra a letra.
- Tienes 6 intentos (7 si has leído la pista de la biblioteca).
- Repetir una letra ya usada no gasta intento.
- Si aciertas, el mecanismo se abre y sales a la azotea. Si fallas, vas al final malo.

**Blackjack** (escena 9, contra el marinero del muelle)
- Solo se juega si abres el candado del bote con la llave.
- Se reparten dos cartas a cada uno; ves tus dos cartas y una del marinero.
- Pides carta (1) o te plantas (2). Si pasas de 21, pierdes la mano.
- Las figuras valen 10 y el As vale 11, o 1 si te pasarías de 21. Un 21 con las dos primeras cartas es blackjack.
- Cuando te plantas, el marinero roba cartas hasta tener 17 o más. Gana quien se acerque más a 21 sin pasarse.
- Si empatáis, se vuelve a repartir. Si ganas, vas al final secreto; si pierdes, pierdes una vida y vuelves al muelle.

## Control de la entrada

- `leerOpcion(int max)`: pide un número por teclado y no lo da por bueno hasta que está entre 1 y `max`. Si escribes una letra o un número fuera de rango, muestra un aviso y vuelve a preguntar.
- `leerLetra()`: pide una única letra. Rechaza textos de más de un carácter, números y símbolos, y devuelve la letra en minúscula.

## Estructura del código

El archivo `Aventurafaro.java` contiene cuatro clases:

- `Aventurafaro`: la clase principal, con el juego y estas variables estáticas: `sc` (Scanner), `azar` (Random), `vidas`, `tieneLlave`, `tieneNavaja`, `tieneCerillas` y `pistaLeida`.
- `Carta`, `Baraja` y `Mano`: las clases del blackjack.

Funciones de la clase `Aventurafaro`:

| Función | Qué hace |
|---|---|
| `main` | Muestra el título y las reglas, pregunta si estás listo y lanza la escena 1 |
| `escena1` | Sótano: subir las escaleras o registrar el baúl |
| `escena2` | Baúl: coger la llave, la navaja o las dos (pierdes una vida) |
| `escena3` | Planta baja: cocina, biblioteca o puerta del fondo |
| `escena4` | Cocina: comer una lata (pierdes una vida) o seguir |
| `escena5` | Biblioteca: leer la pista, forzar el cajón con la navaja o seguir |
| `escena6` | Fantasma del farero: lanza el duelo de piedra, papel o tijera |
| `escena7` | Sala de la linterna: intentar el panel (ahorcado) o bajar |
| `escena8` | Azotea: encender la linterna (necesita cerillas) o bajar al muelle |
| `escena9` | Muelle: abrir el candado, esperar al amanecer o volver a la azotea |
| `escena10` a `escena13` | Finales: bueno, neutral, malo y secreto |
| `leerOpcion` | Lee y valida un número entre 1 y `max` |
| `leerLetra` | Lee y valida una única letra |
| `jugarRonda` | Juega una ronda de piedra, papel o tijera; devuelve empate, victoria o derrota |
| `jugarDuelo` | Piedra, papel o tijera al mejor de tres; devuelve si ganas |
| `nombreJugada` | Convierte el número de la jugada (1, 2, 3) en su nombre para mostrarlo |
| `perderVidas` | Resta una vida y devuelve si sigues vivo |
| `jugarAhorcado` | Ahorcado con la palabra "linterna"; devuelve si lo completas |
| `jugarBlackjack` | Juega manos de blackjack contra el marinero hasta que alguien gane; devuelve si ganas |
| `jugarManoBlackjack` | Juega una sola mano; devuelve empate, victoria o derrota |

Clases del blackjack:

| Clase | Qué representa |
|---|---|
| `Carta` | Una carta con su palo y su nombre; calcula su valor (el As vale 11) |
| `Baraja` | Las 52 cartas mezcladas; permite robar una carta |
| `Mano` | Las cartas de un jugador; calcula los puntos (el As pasa a valer 1 si te pasas) y detecta el blackjack |

## Pruebas

| Camino probado | Resultado esperado |
|---|---|
| Escribir una letra o un 0 en un menú numérico | Aviso y vuelve a preguntar |
| Escribir un número mayor que las opciones | Aviso y vuelve a preguntar |
| Coger las dos cosas del baúl | Pierdes una vida y tienes llave y navaja |
| Puerta del fondo sin llave | Pierdes una vida y vuelves a la planta baja |
| Puerta del fondo con llave | Atajo directo a la sala de la linterna |
| Forzar el cajón sin navaja | No aparece la opción 2 en el menú |
| Forzar el cajón con navaja | Consigues cerillas y pierdes la navaja |
| Ahorcado: repetir una letra | Avisa y no resta intento |
| Ahorcado: escribir dos letras o un número | Pide de nuevo una sola letra |
| Ahorcado con la pista leída | Empieza con 7 intentos en vez de 6 |
| Azotea con cerillas | Final bueno (escena 10) |
| Azotea sin cerillas | Pierdes una vida y vuelves a la azotea |
| Muelle: esperar al amanecer | Final neutral (escena 11) |
| Muelle: abrir el candado sin la llave | Pierdes una vida y vuelves al muelle |
| Muelle: abrir el candado con la llave y ganar al blackjack | Final secreto (escena 13) |
| Muelle: perder al blackjack | Pierdes una vida y vuelves al muelle |
| Blackjack: empate | Se vuelve a repartir una mano nueva |
| Quedarte sin vidas o fallar el ahorcado | Final malo (escena 12) |

## Autores

- Yeray del Rosario 
- Izan Lopez  
- David Beltre

