Aventura en la mazmorra
¡Bienvenido a Aventura en la mazmorra, un juego de aventura en consola de comandos donde te enfrentas a monstruos y jefes para salir de ella!, gestiona tu inventario, experiencia enemigos para ganar.

Tabla de Contenidos
Descripción
Características
Instalación
Cómo Jugar
Contribuciones
Licencia

Descripción
En este juego el objetivo es no morir y gestionar tus items para vencer a los enemigos y encontrar la salida, este proyecto es el examen de la primera unidad, a decir verdad fue bastante dificil hacerlo

Características
Sistema de Combate por Turnos: Lucha contra enemigos aleatorios o jefes finales utilizando ataques o defendiendo para mitigar el daño.
Gestión de Inventario: Encuentra y equipa armas, armaduras, y pociones que mejoran tus atributos y te ayudan en la batalla.
Mazmorras Procedurales: Explora un mapa generado con salas, enemigos, y objetos, aunque tienen una posición fija, sus contenidos son distribuidos aleatoriamente.
Sistema de Experiencia: Gana puntos de experiencia al derrotar enemigos y mejora tus habilidades en ataque y defensa.
Interacciones con el Mapa: Enfréntate a enemigos ('¥'), encuentra objetos ('©'), visita tiendas ('$'), y lucha contra el jefe final ('B') para escapar ('§').

Instalación
Requisitos
Java 22 de preferencia, no ha sido provado en otras versiones.
Git (opcional, si vas a clonar el proyecto desde un repositorio).

Clonar el Proyecto
Si tienes Git instalado, clona el proyecto utilizando:

bash
Copy code
git clone https://github.com/tu-usuario/aventura-en-el-tec.git
Compilación
Para compilar el proyecto, navega a la carpeta principal del proyecto y usa el siguiente comando en tu terminal:

bash
Copy code
javac -d bin -sourcepath src src/clasesProgramacionAvanzada/examenUnidad1/Main.java
Ejecución
Una vez compilado, puedes ejecutar el juego usando:

bash
Copy code
java -cp bin clasesProgramacionAvanzada.examenUnidad1.Main
Cómo Jugar

Movimiento:

Una vez iniciado el juego deberas seleccionar cada acción que haces, si eliges continuar aventura, podras usar las teclas W, A, S, D para moverte en el mapa.
Evita las paredes y muévete estratégicamente para encontrar enemigos, objetos, y la tienda.

Combate:

Al encontrarte con un enemigo ('¥'), se iniciará un combate por turnos.
Tienes dos opciones en combate: Atacar (1) o Defender (2).
Al atacar, infligirás daño al enemigo basado en tu fuerza.
Si te defiendes, aumentarás temporalmente tu constitución y reducirás el daño recibido.
Inventario:

Encuentra y equipa armas, armaduras o usa pociones para curarte.
Accede a tu inventario en cualquier momento desde el menú del juego.
Tienda:

Visita la tienda ('$') para intercambiar puntos de experiencia por mejoras de habilidades.
Mejora tu Fuerza o Constitución con los puntos ganados en combate.
Objetos:

Armas y armaduras mejoran tus atributos de combate.
Las pociones te curan durante los combates o en momentos de descanso.
Ganar o Perder:

Derrota al jefe final ('B') y llega a la salida ('§') para ganar el juego.
Si tu HP llega a 0, habrás perdido el juego y podrás reiniciar o salir.
Contribuciones
Las contribuciones son bienvenidas. Si deseas contribuir a Aventura enla mazmorra, por favor sigue los siguientes pasos:

Haz un fork de este repositorio.
Crea una rama nueva para tus cambios: git checkout -b mi-nueva-funcionalidad.
Haz tus cambios y haz un commit: git commit -m 'Añadí una nueva funcionalidad'.
Envía los cambios a la rama principal: git push origin mi-nueva-funcionalidad.
Abre un pull request para revisar tus cambios.
