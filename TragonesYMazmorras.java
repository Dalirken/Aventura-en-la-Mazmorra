package clasesProgramacionAvanzada.examenUnidad1;
import java.util.Scanner; // Utilidad de java para leer entradas de datos

import clasesProgramacionAvanzada.examenUnidad1.basics.Dungeon;
import clasesProgramacionAvanzada.examenUnidad1.basics.PlayerCharacter;

public class TragonesYMazmorras {
    public static Scanner scanner = new Scanner(System.in);

    static PlayerCharacter player;

    /* Booleano para saber si el juego está en marcha */
    public static boolean isRunning;

    /* Elementos de la historia */
    public static int place = 0, act;
    public static String[] places = {"Cueva", "???"};

    /* método para obtener imputs de usuario: recibe el input de la consola de comandos y 
     * un parámetro de cuantas elecciones entre 1 y n tiene el usuario.*/
    public static int readInt(String prompt, int userChoices){
        int input;

        do{
            System.out.println(prompt); 
            try{
                input = Integer.parseInt(scanner.next()); // trata de convertir el input en un entero
            }catch(Exception e){
                input = -1; // si no es un entero da la excepcion de -1 y manda el mensaje de error
                System.out.println("Por favor ingrese un número entero dentro de su rango de opciones");
            }
        }while(input < 1 || input > userChoices);
        /* Este bucle se repetirá mientras el input sea mayor a las opciones o menor a 1 */ 
        return input;
    }

    /* Método para simular limpiar la consola (100 lineas en blanco)*/
    public static void clearConsole(){
        for(int i = 0; i < 100; i++){
            System.out.println();
        }
    }

    /* Método para imprimir un separador de longitud n */
    public static void printSeparator(int n){
        for(int i = 0; i < n; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    /* Método para imprimir el título */
    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    /* Método para detener el juego */
    public static void anythingToContinue(){
        System.out.println("\nIntroduce cualquier cosa para continuar");
        scanner.next();
    }

    /* Método para iniciar el juego */
    public static void startGame(){
        boolean nameSet = false;
        String name;

        /* Imprimir el titulo inicial del juego */
        clearConsole();
        printSeparator(40);
        printSeparator(30);
        System.out.println(
                        "▄▄▄█████▓ ██▀███   ▄▄▄        ▄████  ▒█████   ███▄    █ ▓█████   ██████                \r\n" + //
                        "▓  ██▒ ▓▒▓██ ▒ ██▒▒████▄     ██▒ ▀█▒▒██▒  ██▒ ██ ▀█   █ ▓█   ▀ ▒██    ▒                \r\n" + //
                        "▒ ▓██░ ▒░▓██ ░▄█ ▒▒██  ▀█▄  ▒██░▄▄▄░▒██░  ██▒▓██  ▀█ ██▒▒███   ░ ▓██▄                  \r\n" + //
                        "░ ▓██▓ ░ ▒██▀▀█▄  ░██▄▄▄▄██ ░▓█  ██▓▒██   ██░▓██▒  ████▒▒▓█  ▄   ▒   ██▒               \r\n" + //
                        "  ▒██▒ ░ ░██▓ ▒██▒ ▓█   ▓██▒░▒▓███▀▒░ ████▓▒░▒██░   ▓██░░▒████▒▒██████▒▒               \r\n" + //
                        "  ▒ ░░   ░ ▒▓ ░▒▓░ ▒▒   ▓▒█░ ░▒   ▒ ░ ▒░▒░▒░ ░ ▒░   ▒ ▒ ░░ ▒░ ░▒ ▒▓▒ ▒ ░               \r\n" + //
                        "    ░      ░▒ ░ ▒░  ▒   ▒▒ ░  ░   ░   ░ ▒ ▒░ ░ ░░   ░ ▒░ ░ ░  ░░ ░▒  ░ ░               \r\n" + //
                        "  ░        ░░   ░   ░   ▒   ░ ░   ░ ░ ░ ░ ▒     ░   ░ ░    ░   ░  ░  ░                 \r\n" + //
                        "            ░           ░  ░      ░     ░ ░           ░    ░  ░      ░                 \r\n" + //
                        "                                                                                       \r\n" + //
                        "                                    ▓██   ██▓                                          \r\n" + //
                        "                                     ▒██  ██▒                                          \r\n" + //
                        "                                      ▒██ ██░                                          \r\n" + //
                        "                                      ░ ███▓░                                          \r\n" + //
                        "                                      ░ ██▒▓░                                          \r\n" + //
                        "                                       ██▒▒▒                                           \r\n" + //
                        "                                     ▓██ ░▒░                                           \r\n" + //
                        "                                     ▒ ▒ ░░                                            \r\n" + //
                        "                                     ░ ░                                               \r\n" + //
                        "                                     ░ ░                                               \r\n" + //
                        " ███▄ ▄███▓ ▄▄▄      ▒███████▒ ███▄ ▄███▓ ▒█████   ██▀███   ██▀███   ▄▄▄        ██████ \r\n" + //
                        "▓██▒▀█▀ ██▒▒████▄    ▒ ▒ ▒ ▄▀░▓██▒▀█▀ ██▒▒██▒  ██▒▓██ ▒ ██▒▓██ ▒ ██▒▒████▄    ▒██    ▒ \r\n" + //
                        "▓██    ▓██░▒██  ▀█▄  ░ ▒ ▄▀▒░ ▓██    ▓██░▒██░  ██▒▓██ ░▄█ ▒▓██ ░▄█ ▒▒██  ▀█▄  ░ ▓██▄   \r\n" + //
                        "▒██    ▒██ ░██▄▄▄▄██   ▄▀▒   ░▒██    ▒██ ▒██   ██░▒██▀▀█▄  ▒██▀▀█▄  ░██▄▄▄▄██   ▒   ██▒\r\n" + //
                        "▒██▒   ░██▒ ▓█   ▓██▒▒███████▒▒██▒   ░██▒░ ████▓▒░░██▓ ▒██▒░██▓ ▒██▒ ▓█   ▓██▒▒██████▒▒\r\n" + //
                        "░ ▒░   ░  ░ ▒▒   ▓▒█░░▒▒ ▓░▒░▒░ ▒░   ░  ░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░░ ▒▓ ░▒▓░ ▒▒   ▓▒█░▒ ▒▓▒ ▒ ░\r\n" + //
                        "░  ░      ░  ▒   ▒▒ ░░░▒ ▒ ░ ▒░  ░      ░  ░ ▒ ▒░   ░▒ ░ ▒░  ░▒ ░ ▒░  ▒   ▒▒ ░░ ░▒  ░ ░\r\n" + //
                        "░      ░     ░   ▒   ░ ░ ░ ░ ░░      ░   ░ ░ ░ ▒    ░░   ░   ░░   ░   ░   ▒   ░  ░  ░  \r\n" + //
                        "       ░         ░  ░  ░ ░           ░       ░ ░     ░        ░           ░  ░      ░  \r\n" + //
                        "                     ░                                                                 ");
        System.out.println("Por Guerrero Rivera Oscar Jesus");
        printSeparator(30);
        printSeparator(40);
        anythingToContinue();

        /* Imprimir la intro */
        Story.printIntro();
        anythingToContinue();

        /* Obtener el nombre del jugador */
        do{
            clearConsole();
            printHeading("¿Cual es tu nombre?");
            name = scanner.next();

            /* Confirmar nombre */
            clearConsole();
            printHeading("Así que tu nombre es " + name + ". \n ¿Es correcto?");
            System.out.println("(1) Si es correcto");
            System.out.println("(2) No, ese no es mi nombre, quiero cambiarlo ");
            int input = readInt("->", 2);
            if(input == 1){
                nameSet = true;
            }
        }while(!nameSet); //Esto se repite hasta que se ha asignado un nombre 

        /* Crear un jugador con el nombre seleccionado */
        player = new PlayerCharacter(name);

        /* Crear el loop en donde estará el juego y definiendo que si está en marcha */
        isRunning = true;

        /* Inicializar mazmorra */
        Dungeon dungeon = new Dungeon();
        dungeon.generateDungeonMap();
        dungeon.printDungeonMap();

        gameLoop();
    }

    /* Método para continuar el juego y moverse*/
    public static void continueJourney(){
        player.move();
    }

    public static void characterInfo(){
        clearConsole();
        printHeading("Información del personaje:");
        System.out.println(player.name + "\tHp: " + player.currentHp + "/" + player.maxHp);
        printSeparator(30);
        System.out.println("XP: " + player.xp);
        printSeparator(30);

        /* Imprimir los puntos de atributos mejorados */
        if(player.numAtkUpgrades > 0){
            System.out.println("Puntos de mejora en ataque: " + player.atkUpgrades[player.numAtkUpgrades - 1]);
            printSeparator(30);
        }
        if(player.numDefUpgrades > 0){
            System.out.println("Puntos de mejora en defensa: " + player.defUpgrades[player.numDefUpgrades - 1]);
            printSeparator(30);
        }
        anythingToContinue();
    }

    /* Método para imprimir el menú*/
    public static void printMenu(){
        clearConsole();
        printHeading(places[place]);
        printSeparator(30);
        Dungeon dungeon = new Dungeon();
        dungeon.printDungeonMap();
        System.out.println("Elige una acción:");
        System.out.println("-> (1) Continuar aventura");
        System.out.println("-> (2) Abrir menú del personaje");
        System.out.println("-> (3) Abrir inventario");
        System.out.println("-> (4) Cerrar el juego");
    }

    /* Una vez iniciado el juego, este loop se encarga de llamae a todas las acciones */
    public static void gameLoop(){
        while (isRunning) {
            printMenu();
            int input = readInt("->", 4);
            if(input == 1){
                continueJourney();
            }else if(input == 2){
                characterInfo();
            }else if(input == 3){
                player.mostrarInventario();
                anythingToContinue();
            }else{
                isRunning = false;
            }
        }
    }
}
