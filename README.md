Apartado	Qué se cuenta	Quién lo puede escribir mejor
1. Descripción	2-3 líneas: qué es y de qué trata	Cualquiera
2. Enlace	La URL del repositorio (el enunciado la exige aquí y en la clase principal)	Quien creó el repo
3. Requisitos	JDK (versión que usáis) y un IDE o la consola	Cualquiera
4. Cómo ejecutarlo	Pasos exactos para alguien que no conoce el proyecto: descargar, abrir, compilar y qué clase ejecutar	Lo más importante: que lo pruebe alguien que no lo escribió
5. Cómo se juega	Se eligen opciones con números, qué pasa si escribes algo no válido	Cualquiera
6. Historia y finales	Resumen sin destripar, y cuántos finales hay (bueno, neutral, malo y secreto)	Quien escribió la historia
7. Inventario y vidas	Objetos, para qué sirven y cuántas vidas hay	Quien hizo las escenas
8. Minijuegos	Reglas de cada uno	Quien hizo cada minijuego
9. Control de la entrada	Qué pasa con letras o números fuera de rango	Quien hizo leerOpcion
10. Estructura del código	Lista de funciones con una frase cada una	Cada uno, de sus funciones
11. Pruebas	Caminos probados y qué esperabais	Todos
12. Autores	Nombres, curso y fecha	Todos
AQUI ESTA:
# Aventura Faro

Juego de aventura de texto en Java que se juega por consola. Despiertas en el sótano de un faro abandonado sin recordar cómo has llegado y tienes que explorar sus estancias, superar dos minijuegos y llegar hasta la azotea para pedir ayuda. Empiezas con 3 vidas: si las pierdes todas, vas al final malo.

## Enlace al repositorio

https://github.com/iizann/aventura-faro

## Requisitos

- JDK [versión, por ejemplo 17 o 21]
- IntelliJ IDEA (o cualquier consola con `javac` y `java`)

## Cómo ejecutarlo

1. Descarga el proyecto: botón verde **Code → Download ZIP** y descomprímelo, o con `git clone https://github.com/iizann/aventura-faro.git`.
2. Abre IntelliJ y elige **File → Open** y selecciona la carpeta del proyecto.
3. Comprueba que el proyecto usa un JDK instalado (**File → Project Structure → Project SDK**).
4. Abre la clase principal `Aventurafaro.java`.
5. Pulsa el triángulo verde junto a `public static void main` y elige **Run 'Aventurafaro.main()'**.

Desde consola, en la carpeta donde está el archivo:

```
javac Aventurafaro.java
java Aventurafaro
```

## Cómo se juega

En cada escena se muestra un menú con opciones numeradas. Escribes el número de la opción y pulsas Enter. Si escribes algo que no es válido (una letra, un número fuera de rango...), el juego te avisa y te vuelve a preguntar hasta que escribas una opción correcta.

En el ahorcado se escribe una letra cada vez. Si escribes más de una letra o un símbolo, también te la vuelve a pedir.

## Historia y finales

Te despiertas en el sótano de un faro abandonado. Subes a la planta baja, donde puedes elegir entre la cocina, la biblioteca o una puerta cerrada al fondo. Más adelante te cruzas con el fantasma del farero, que solo te deja pasar si le ganas a piedra, papel o tijera. Después llegas a la sala de la linterna, donde debes resolver un panel con una palabra secreta. Por último, en la azotea intentas llamar la atención de un barco o escapar por el muelle.

Finales del juego:

- **Final bueno:** [pendiente de completar]
- **Final neutral:** [pendiente de completar]
- **Final malo:** [pendiente de completar]
- **Final secreto:** [pendiente de completar]

## Inventario y vidas

**Vidas:** empiezas con 3. Se pierde una vida en estos casos:

- Coger la llave y la navaja a la vez (la tapa del baúl te golpea la mano).
- Intentar abrir la puerta del fondo sin la llave.
- Comerte la lata de la cocina.
- Perder contra el fantasma.
- Intentar encender la linterna sin cerillas.

**Inventario:** puede tener hasta 3 objetos.

| Objeto | Cómo se consigue | Para qué sirve |
|---|---|---|
| Llave | En el baúl del sótano | Abrir la puerta del fondo de la planta baja |
| Navaja | En el baúl del sótano | Forzar el cajón de la biblioteca (se pierde al usarla) |
| Cerillas | En el cajón de la biblioteca (necesitas la navaja) | Encender la linterna en la azotea |

Además, leer la página de la biblioteca da una pista que te regala un intento extra en el ahorcado.

## Minijuegos

**Piedra, papel o tijera** (escena 6, contra el fantasma del farero)
- Se juega al mejor de tres rondas contra el ordenador.
- Si ganas, sigues subiendo. Si pierdes, pierdes una vida.

**Ahorcado** (escena 7, panel de la linterna)
- La palabra secreta tiene que adivinarse letra a letra.
- Tienes 6 intentos (7 si has leído la pista de la biblioteca).
- Si fallas, el panel se bloquea y el juego acaba mal.

## Control de la entrada

- `leerOpcion(int max)`: pide un número por teclado y no lo da por bueno hasta que está entre 1 y `max`. Si escribes una letra o un número fuera de rango, muestra un aviso y vuelve a preguntar.
- `leerLetra()`: pide una única letra. Rechaza textos de más de un carácter y símbolos o números, y devuelve la letra en minúscula.

## Estructura del código

Todo está en la clase `Aventurafaro`, con estas variables estáticas: `sc` (Scanner), `azar` (Random), `vidas`, `tieneLlave`, `tieneNavaja`, `tieneCerillas` y `pistaLeida`.

| Función | Qué hace |
|---|---|
| `main` | Muestra el título y las reglas, pregunta si estás listo y lanza la escena 1 |
| `Escena1` | Sótano: subir las escaleras o registrar el baúl |
| `Escena2` | Baúl: coger la llave, la navaja o las dos (pierdes una vida) |
| `Escena3` | Planta baja: elegir entre cocina, biblioteca o la puerta del fondo |
| `Escena4` | Cocina: comer una lata (pierdes una vida) o seguir |
| `Escena5` | Biblioteca: leer la pista, forzar el cajón con la navaja o seguir |
| `Escena6` | Fantasma del farero: lanza el duelo de piedra, papel o tijera |
| `Escena7` | Sala de la linterna: intentar el panel (ahorcado) o bajar |
| `Escena8` | Azotea: encender la linterna (necesita cerillas) o bajar al muelle |
| `Escena9` | Muelle: bote atado con un candado (en desarrollo) |
| `leerOpcion` | Lee y valida un número entre 1 y `max` |
| `leerLetra` | Lee y valida una única letra |
| `jugarDuelo` | Piedra, papel o tijera al mejor de tres; devuelve si ganas |
| `PerderVidas` | Resta una vida y devuelve si sigues vivo |
| `jugarAhorcado` | Ahorcado con la palabra "linterna"; devuelve si lo completas |

## Pruebas

| Prueba | Resultado esperado |
|---|---|
| Escribir una letra en un menú numérico | Muestra un aviso y vuelve a preguntar |
| Escribir 0 o un número mayor que las opciones | Muestra un aviso y vuelve a preguntar |
| Coger las dos cosas del baúl | Pierdes una vida y tienes llave y navaja |
| Abrir la puerta del fondo sin llave | Pierdes una vida y vuelves a la planta baja |
| Forzar el cajón sin navaja | No aparece la opción 2 en el menú |
| Ahorcado: repetir una letra | Avisa de que ya estaba probada y no resta intento |
| Ahorcado: escribir dos letras o un número | Pide de nuevo una sola letra |
| Ahorcado con la pista leída | Empieza con 7 intentos en vez de 6 |
| Quedarte sin vidas | Va al final malo |

[Confirma que has probado todos estos casos.]

## Autores

- [Nombre y apellidos] — [curso] — [fecha]
- [Nombre y apellidos] — [curso] — [fecha]
