package clasesProgramacionAvanzada.examenUnidad1.basics;

import java.util.ArrayList;

public class Items {
    class Item {
        String name;
        String rarity;
        int attributeBoost; // Puede ser fuerza, constitución o vida en el caso de pociones
        boolean isWeapon;
        boolean isArmor;
        boolean isPotion;
    
        public Item(String name, String rarity, int attributeBoost, boolean isWeapon, boolean isArmor, boolean isPotion) {
            this.name = name;
            this.rarity = rarity;
            this.attributeBoost = attributeBoost;
            this.isWeapon = isWeapon;
            this.isArmor = isArmor;
            this.isPotion = isPotion;
        }
    
        @Override
        public String toString() {
            return name + " (" + rarity + ") - Aumento: " + attributeBoost;
        }
    }
    

    // Listas para armas y armaduras
    public ArrayList<Item> weapons = new ArrayList<>();
    public ArrayList<Item> armors = new ArrayList<>();
    public ArrayList<Item> potions = new ArrayList<>();

    // Constructor que inicializa las listas con 5 armas y 5 armaduras
// En el constructor de Items
// Constructor que inicializa las listas con 5 armas, 5 armaduras, y pociones
public Items() {
    
    weapons.add(new Item("Great Sword", "Commmon", 3, true, false, false));
    weapons.add(new Item("Battle Axe", "Rare", 4, true, false, false));
    weapons.add(new Item("Poisoned Dagger", "Uncommon", 5, true, false, false));
    weapons.add(new Item("War Hammer", "Epic", 6, true, false, false));
    weapons.add(new Item("Cursed Sword", "Legendary", 8, true, false, false));
    
    armors.add(new Item("Leather Armor", "Common", 3, false, true, false));
    armors.add(new Item("Chain Armor", "Uncommon", 4, false, true, false));
    armors.add(new Item("Metal Armor", "Rare", 5, false, true, false));
    armors.add(new Item("Magic Armor", "Epic", 6, false, true, false));
    armors.add(new Item("Legendary Armor", "Legendary", 8, false, true, false));

    potions.add(new Item("Healing Potion", "Common", 20, false, false, true));
}

}
