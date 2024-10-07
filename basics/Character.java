package clasesProgramacionAvanzada.examenUnidad1.basics;

/* Creación de la clase Player */
public abstract class Character {

    /* Atributos que debe tener un objeto de tipo Player */
    /* Fuerza, destreza, constitución, vida máxima, vida actual y id*/
    public int strength, dexterity, constitution, xp, maxHp, currentHp, id;

    /* Nombre y Clase */

    public String name;

    protected String className;

    /* Constructores para cualquier PC o NPC */
    public Character(String name, String className, int strength, int dexterity,
     int constitution, int maxHp, int currentHp, int id, int xp){
        this.name = name;
        this.className = className;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.id = id;
        this.xp = xp;
     }

     public Character(int id){
        this.id = id;
     }

    public abstract void move();

    public int getStrength(){
        return strength;
    }

    public int getDexterity(){
        return dexterity;
    }

    public int getConstitution(){
        return constitution;
    }

    public int getXp(){
        return xp;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public int getCurrentHp(){
        return currentHp;
    }

    public int getId(){
        return id;
    }

    
}
