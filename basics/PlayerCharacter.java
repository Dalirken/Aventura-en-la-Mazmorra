package clasesProgramacionAvanzada.examenUnidad1.basics; 

import java.util.Random;

import clasesProgramacionAvanzada.examenUnidad1.Story;
import clasesProgramacionAvanzada.examenUnidad1.TragonesYMazmorras;

public class PlayerCharacter extends Character {

    /* Número de Skills */
    public int numAtkUpgrades, numDefUpgrades;

    /* Posiciones */

    public int xPosition = 67; //7
    public int yPosition = 6; //17

    /* Inventario */

    public Inventario inventario;

    /* Array para guardar las habilidades */
    public String[] atkUpgrades = {"Fuerza I", "Fuerza II", "Fuerza III", "Fuerza IV", "Fuerza V"};
    public String[] defUpgrades = {"Constitución I", "Constitución II", "Constitución III", "Constitución IV", "Constitución V",};

    /* Constructor específico del personaje */
    public PlayerCharacter(String name) {

        /* Iniciarlizar atributos del personaje */
        super("Player", "Human", 10, 10, 3,
         100, 100, 0,0);
        /* Super inicializa las clases de la misma manera que el constructor de chatacter,
         * de otra forma debería utilizar this. para inicializarlas todas de nuevo
         */

         this.inventario = new Inventario();

        /* Inicias con 0 puntos para subir tus stats */
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;

        /* Elegir un punto de habilidad entre ataque y defensa */
        chooseTrait();
    }

    public void chooseTrait() {
        TragonesYMazmorras.clearConsole();
        TragonesYMazmorras.printHeading("Elige un punto de habilidad: ");
        System.out.println("(1) " + atkUpgrades[numAtkUpgrades] + " (Mejora Fuerza)");
        System.out.println("(2) " + defUpgrades[numDefUpgrades] + " (Mejora Constitución)");
    
        // Elegir entre las dos opciones
        int input = TragonesYMazmorras.readInt("-> ", 2);
        TragonesYMazmorras.clearConsole();
    
        // Detectar elección y aplicarla
        if (input == 1) {
            TragonesYMazmorras.printHeading("¡Elegiste " + atkUpgrades[numAtkUpgrades] + "!");
            this.strength += 2; // Aumentar la fuerza en 2 puntos
            numAtkUpgrades++;
            System.out.println("Tu fuerza ha aumentado en 2 puntos. Fuerza actual: " + this.strength);
        } else {
            TragonesYMazmorras.printHeading("¡Elegiste " + defUpgrades[numDefUpgrades] + "!");
            this.constitution += 2; // Aumentar la constitución en 2 puntos
            numDefUpgrades++;
            System.out.println("Tu constitución ha aumentado en 2 puntos. Constitución actual: " + this.constitution);
        }
    
        TragonesYMazmorras.anythingToContinue();
    }
    
    @Override
    public void move() {
        System.out.println("Elige una dirección para moverte:");
        System.out.println("(W) Arriba");
        System.out.println("(S) Abajo");
        System.out.println("(A) Izquierda");
        System.out.println("(D) Derecha");
        char direction = TragonesYMazmorras.scanner.next().toUpperCase().charAt(0); // Usa el Scanner de TragonesYMazmorras
        
        int newX = xPosition;
        int newY = yPosition;
        
        switch(direction) {
            case 'W': // Arriba
                newY--;
                break;
            case 'S': // Abajo
                newY++;
                break;
            case 'A': // Izquierda
                newX--;
                break;
            case 'D': // Derecha
                newX++;
                break;
            default:
                System.out.println("Dirección no válida.");
                return;
        }
    
        // Verificar los límites del mapa
        if (newX < 0 || newX >= Dungeon.dungeonWidth || newY < 0 || newY >= Dungeon.dungeonHeight) {
            System.out.println("No puedes moverte fuera del mapa.");
            return;
        }
    
        // Verificar el contenido de la nueva casilla
        char newTile = Dungeon.map[newY][newX];
    
        if (newTile == '╣' || newTile == '║' || newTile == '╗' || newTile == '╝' || newTile == '╩' || newTile == '╔' || newTile == '╦' || newTile == '╠' || newTile == '═' || newTile == '╬' || newTile == '╚') {
            System.out.println("Hay una pared, no puedes moverte allí.");
            return;
        } else if (newTile == '¥') { // Enemigo
            System.out.println("¡Un enemigo está aquí!");
            iniciarCombate(newX, newY); // Pasar las coordenadas del enemigo para eliminarlo tras el combate
        } else if (newTile == '©') { // Objeto
            System.out.println("¡Has encontrado un objeto!");
            recogerObjeto(newX, newY); // Pasar las coordenadas del objeto para eliminarlo
        } else if (newTile == '$') { // Tienda
            System.out.println("¡Has encontrado una tienda!");
            abrirTienda(); // Abrir la tienda al llegar a esta casilla
        } else if (newTile == 'B') { // Jefe
            System.out.println("¡Te enfrentas al jefe final!");
            iniciarCombateJefe(newX, newY); // Iniciar combate contra el jefe
        } else if (newTile == '§') { // Final del juego
            terminarJuego(); // Terminar el juego al llegar a este símbolo
        } else {
            // Si no hay obstáculos, mover al personaje
            Dungeon.map[yPosition][xPosition] = '░'; // Dejar el carácter del piso (asumido como espacio)
            xPosition = newX;
            yPosition = newY;
            Dungeon.map[yPosition][xPosition] = 'P'; // Actualizar la nueva posición del jugador en el mapa
        }
    }

    public void iniciarCombateJefe(int jefeX, int jefeY) {
        // Crear el jefe
        npc jefe = new npc("Jefe Final", "Legendario", 15, 5, 
        12, 25); 
    
        boolean enCombate = true;
    
        while (enCombate) {
            System.out.println("\n--- Batalla contra " + jefe.name + " ---");
            System.out.println("Tu HP: " + this.currentHp + "/" + this.maxHp);
            System.out.println(jefe.name + " HP: " + jefe.currentHp + "/" + jefe.maxHp);
            System.out.println("Elige una acción:");
            System.out.println("(1) Atacar");
            System.out.println("(2) Defender");
            int accion = TragonesYMazmorras.readInt("->", 2);
    
            if (accion == 1) {
                // Ataque del jugador
                int danioJugador = this.strength - (jefe.constitution / 2); 
                danioJugador = danioJugador > 0 ? danioJugador : 1; // Asegurar que siempre se inflige al menos 1 de daño
                jefe.currentHp -= danioJugador;
                System.out.println("Atacaste al " + jefe.name + " y le hiciste " + danioJugador + " de daño.");
            } else if (accion == 2) {
                System.out.println("Te preparas para defenderte.");
                this.constitution += 2; // Aumenta temporalmente la constitución
            }
    
            if (jefe.currentHp <= 0) {
                System.out.println("¡Has derrotado al " + jefe.name + "!");
                this.xp += 50;
                System.out.println("Ganas 50 puntos de experiencia. XP total: " + this.xp);
                enCombate = false;
                Dungeon.map[jefeY][jefeX] = '░'; // Eliminar al jefe del mapa
                TragonesYMazmorras.anythingToContinue();
                break;
            }
    
            // Turno del jefe
            System.out.println("El " + jefe.name + " te ataca.");
            int danioEnemigo = jefe.strength - this.constitution;
            danioEnemigo = danioEnemigo > 0 ? danioEnemigo : 0;
            this.currentHp -= danioEnemigo;
            System.out.println("El " + jefe.name + " te infligió " + danioEnemigo + " de daño.");
    
            if (accion == 2) {
                this.constitution -= 2;
            }
    
            if (this.currentHp <= 0) {
                System.out.println("Has sido derrotado por el " + jefe.name + ".");
                TragonesYMazmorras.isRunning = false;
                enCombate = false;
            }
        }
    }

    public void terminarJuego() {
    TragonesYMazmorras.clearConsole();
    Story.imprimirVictoria(); // Mostrar el diálogo de victoria
    TragonesYMazmorras.isRunning = false; // Terminar el juego
}


    public void abrirTienda() {
        boolean enTienda = true;
    
        while (enTienda) {
            TragonesYMazmorras.clearConsole();
            TragonesYMazmorras.printHeading("Bienvenido a la tienda");
            System.out.println("Tienes " + this.xp + " puntos de experiencia.");
            System.out.println("Por cada 50 puntos de experiencia, puedes obtener un punto de habilidad.");
            System.out.println("(1) Comprar puntos de habilidad");
            System.out.println("(2) Salir de la tienda");
    
            int input = TragonesYMazmorras.readInt("->", 2);
    
            if (input == 1) {
                if (this.xp >= 50) {
                    this.xp -= 50; // Restar experiencia
                    System.out.println("¡Has comprado un punto de habilidad!");
                    chooseTrait(); // Llamar a chooseTrait() para mejorar el personaje
                } else {
                    System.out.println("No tienes suficiente experiencia.");
                    TragonesYMazmorras.anythingToContinue();
                }
            } else if (input == 2) {
                System.out.println("Saliendo de la tienda...");
                enTienda = false; // Salir de la tienda
            }
        }
    }
    

    public void iniciarCombate(int enemigoX, int enemigoY) {
        // Generar un enemigo aleatorio
        Random rand = new Random();
        int enemyId = rand.nextInt(5);  // 5 tipos de enemigos
        npc enemigo = new npc(enemyId);
    
        boolean enCombate = true;
    
        while (enCombate) {
            System.out.println("\n--- Batalla contra " + enemigo.name + " ---");
            System.out.println("Tu HP: " + this.currentHp + "/" + this.maxHp);
            System.out.println(enemigo.name + " HP: " + enemigo.currentHp + "/" + enemigo.maxHp);
            System.out.println("Elige una acción:");
            System.out.println("(1) Atacar");
            System.out.println("(2) Defender");
            int accion = TragonesYMazmorras.readInt("->", 2);
    
            if (accion == 1) {
                // Ataque del jugador
                int danioJugador = this.strength - enemigo.constitution;
                danioJugador = danioJugador > 0 ? danioJugador : 0;
                enemigo.currentHp -= danioJugador;
                System.out.println("Atacaste al " + enemigo.name + " y le hiciste " + danioJugador + " de daño.");
            } else if (accion == 2) {
                // Defensa del jugador (aquí podrías implementar reducción de daño)
                System.out.println("Te preparas para defenderte.");
                this.constitution += 2; // Aumenta temporalmente la constitución
            }
    
            // Comprobar si el enemigo ha sido derrotado
            if (enemigo.currentHp <= 0) {
                System.out.println("¡Has derrotado al " + enemigo.name + "!");
                this.xp += 25;
                System.out.println("Ganas 25 puntos de experiencia. XP total: " + this.xp);
                enCombate = false;
                Dungeon.map[enemigoY][enemigoX] = '░'; // Eliminar al enemigo del mapa
                TragonesYMazmorras.anythingToContinue();
                break; // Salir del ciclo de combate
            }
    
            // Turno del enemigo
            System.out.println("El " + enemigo.name + " te ataca.");
            int danioEnemigo = enemigo.strength - this.constitution;
            danioEnemigo = danioEnemigo > 0 ? danioEnemigo : 0;
            this.currentHp -= danioEnemigo;
            System.out.println("El " + enemigo.name + " te infligió " + danioEnemigo + " de daño.");
    
            // Si el jugador había defendido, restablece la constitución
            if (accion == 2) {
                this.constitution -= 2; // Restablecer la constitución
            }
    
            // Comprobar si el jugador ha sido derrotado
            if (this.currentHp <= 0) {
                System.out.println("Has sido derrotado por el " + enemigo.name + ".");
                TragonesYMazmorras.isRunning = false;  // Finalizar juego
                enCombate = false;
            }
        }
    }    

    public void recogerObjeto(int objetoX, int objetoY) {
        Items items = new Items();  // Crear un nuevo conjunto de objetos
        Random rand = new Random();
        int tipo = rand.nextInt(3);  // Elegir entre 0 (arma), 1 (armadura), 2 (poción)
        Items.Item objetoEncontrado;
    
        if (tipo == 0 && !items.weapons.isEmpty()) {  // Asegurarse de que haya armas en la lista
            int id = rand.nextInt(items.weapons.size());
            objetoEncontrado = items.weapons.get(id);
            System.out.println("Has encontrado un arma: " + objetoEncontrado.name);
        } else if (tipo == 1 && !items.armors.isEmpty()) {  // Asegurarse de que haya armaduras
            int id = rand.nextInt(items.armors.size());
            objetoEncontrado = items.armors.get(id);
            System.out.println("Has encontrado una armadura: " + objetoEncontrado.name);
        } else if (tipo == 2 && !items.potions.isEmpty()) {  // Asegurarse de que haya pociones
            int id = rand.nextInt(items.potions.size());
            objetoEncontrado = items.potions.get(id);
            System.out.println("Has encontrado una poción: " + objetoEncontrado.name);
        } else {
            System.out.println("No hay más ítems disponibles.");
            return;
        }
    
        // Añadir el objeto al inventario
        inventario.agregarObjeto(objetoEncontrado, this);
        
        // Eliminar el objeto del mapa
        Dungeon.map[objetoY][objetoX] = '░'; // Reemplazar el icono del objeto por el piso
        TragonesYMazmorras.anythingToContinue();
    }
    

public void equiparObjeto(Items.Item item) {
    if (inventario.objetoEquipado != null) {
        System.out.println("Desequipaste " + inventario.objetoEquipado.name);
        // Revertir los atributos del objeto anterior
        if (inventario.objetoEquipado.isWeapon) {
            this.strength -= inventario.objetoEquipado.attributeBoost;
        } else if (inventario.objetoEquipado.isArmor) {
            this.constitution -= inventario.objetoEquipado.attributeBoost;
        }
    }

    inventario.equiparObjeto(item);

    // Aplicar los atributos del nuevo objeto
    if (item.isWeapon) {
        this.strength += item.attributeBoost;
    } else if (item.isArmor) {
        this.constitution += item.attributeBoost;
    }

    System.out.println("Equipaste " + item.name + ". Tus atributos han sido actualizados.");
}


public void mostrarInventario() {
    inventario.mostrarInventario();
    if (inventario.objetos.size() > 0) {
        System.out.println("Elige una opción:");
        System.out.println("(1) Equipar un objeto");
        System.out.println("(2) Usar una poción");
        System.out.println("(3) Salir");

        int decision = TragonesYMazmorras.readInt("->", 3);

        if (decision == 1) {
            System.out.println("Elige el número del objeto que deseas equipar:");
            for (int i = 0; i < inventario.objetos.size(); i++) {
                Items.Item item = inventario.objetos.get(i);
                if (!item.isPotion) {  // Solo listar armas y armaduras
                    System.out.println("(" + (i + 1) + ") " + item.name + " (" + item.rarity + ")");
                }
            }
            int eleccion = TragonesYMazmorras.readInt("->", inventario.objetos.size());
            Items.Item itemAEquipar = inventario.objetos.get(eleccion - 1);
            equiparObjeto(itemAEquipar);
        } else if (decision == 2) {
            inventario.usarPocion(this);  // Usar poción desde el inventario
        }
    }
}

}

