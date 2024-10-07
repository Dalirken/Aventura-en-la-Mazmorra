package clasesProgramacionAvanzada.examenUnidad1.basics;
import java.util.ArrayList;

import clasesProgramacionAvanzada.examenUnidad1.TragonesYMazmorras;

public class Inventario {
    public ArrayList<Items.Item> objetos = new ArrayList<>();
    public Items.Item objetoEquipado = null;

    // Método para agregar un objeto al inventario
    public void agregarObjeto(Items.Item item) {
        objetos.add(item);
        System.out.println(item.name + " ha sido añadido a tu inventario.");
    }

    // Método para equipar un objeto
    public void equiparObjeto(Items.Item item) {
        objetoEquipado = item;
    }

    // Mostrar inventario
    public void mostrarInventario() {
        System.out.println("Inventario:");
        if (objetos.isEmpty()) {
            System.out.println("Tu inventario está vacío.");
        } else {
            for (int i = 0; i < objetos.size(); i++) {
                Items.Item item = objetos.get(i);
                System.out.println("(" + (i + 1) + ") " + item.name + " (" + item.rarity + ")");
            }
        }
        if (objetoEquipado != null) {
            System.out.println("Actualmente tienes equipado: " + objetoEquipado.name);
        }
    }

    
    public void agregarObjeto(Items.Item item, PlayerCharacter player) {
    if (item.isPotion) {
        System.out.println("¿Deseas usar la " + item.name + " ahora?");
        System.out.println("(1) Sí");
        System.out.println("(2) No");
        int decision = TragonesYMazmorras.readInt("->", 2);

        if (decision == 1) {
            // Aplicar curación
            int vidaRecuperada = item.attributeBoost;
            player.currentHp += vidaRecuperada;
            if (player.currentHp > player.maxHp) {
                player.currentHp = player.maxHp;
            }
            System.out.println("Usaste la " + item.name + " y recuperaste " + vidaRecuperada + " puntos de vida.");
        } else {
            objetos.add(item);
            System.out.println(item.name + " ha sido añadida a tu inventario.");
        }
    } else {
        objetos.add(item);
        System.out.println(item.name + " ha sido añadida a tu inventario.");
    }
}

public void usarPocion(PlayerCharacter jugador) {
    System.out.println("Elige una poción para usar:");

    // Mostrar las pociones disponibles en el inventario
    ArrayList<Items.Item> pociones = new ArrayList<>();
    for (Items.Item item : objetos) {
        if (item.isPotion) {
            pociones.add(item);
        }
    }

    if (pociones.isEmpty()) {
        System.out.println("No tienes pociones en el inventario.");
        TragonesYMazmorras.anythingToContinue();
        return;
    }

    // Listar las pociones disponibles
    for (int i = 0; i < pociones.size(); i++) {
        System.out.println("(" + (i + 1) + ") " + pociones.get(i).name + " (" + pociones.get(i).rarity + ")");
    }

    int eleccion = TragonesYMazmorras.readInt("->", pociones.size());

    // Usar la poción seleccionada
    Items.Item pocion = pociones.get(eleccion - 1);
    jugador.currentHp += pocion.attributeBoost;
    if (jugador.currentHp > jugador.maxHp) {
        jugador.currentHp = jugador.maxHp;  // Limitar la vida al máximo permitido
    }
    System.out.println("Has usado la " + pocion.name + " y recuperaste " + pocion.attributeBoost + " puntos de vida. Vida actual: " + jugador.currentHp);
    objetos.remove(pocion);  // Eliminar la poción usada del inventario

    TragonesYMazmorras.anythingToContinue();
}

}

