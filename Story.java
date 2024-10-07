package clasesProgramacionAvanzada.examenUnidad1;

/* Clase donde se crearán los métodos para narrar la historia (que hueva) */
public class Story {
    
    /* Intro */
    public static void printIntro(){
        TragonesYMazmorras.clearConsole();
        TragonesYMazmorras.printSeparator(30);
        System.out.println("INTRO");
        TragonesYMazmorras.printSeparator(30);
        System.out.println("Acabas de despertar en un lugar bien raro si saber que onda");
        System.out.println("Si llegas a salida marcada por el simbolo § podrás volver a tu hogar");
        System.out.println("Ten cuidado, las casillas de ¥ son enemigos y la casilla de B es el jefe");
        System.out.println("Podrás encontrar items que te ayuden a vencerlos en las casillas ©");
        System.out.println("Por último, podrás utilizar la experiencia que te den los monstruos para comprar ");
        System.out.println("Mejoras de stats en la tienda, esta está marcada con el símbolo $");
        System.out.println("Suerte en tu aventura, gestiona bien tus pociones y stats para ganar");
    }

        public static void imprimirVictoria() {
            System.out.println("¡Felicitaciones, has escapado de la mazmorra!");
            System.out.println("Has vencido al jefe final y llegado a la salida.");
            System.out.println("El juego ha terminado. ¡Has ganado!");
        }
    
}
