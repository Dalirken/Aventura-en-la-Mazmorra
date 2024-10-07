package clasesProgramacionAvanzada.examenUnidad1.basics;

public class npc extends Character {

    public String[] foeName = {"Zombie", "Wolf", "Killclaw", "Ogre", "Dinotyrant"};
    public String[] foeClass = {"Common", "Common", "Rare", "Rare", "Super Rare"};
    public int[] foeStrength = {8, 7, 9, 10, 10};  
    public int[] foeDexterity = {5, 5, 5, 5, 5}; 
    public int[] foeConstitution = {2, 3, 4, 4, 6}; 
    public int[] foeMaxHp = {16, 16, 20, 22, 28};

    /* Constructor que crea un enemigo basado en su ID */
    public npc(int id) {
        super(id);

        // Validar que el ID sea válido
        if (id < 0 || id >= foeName.length) {
            throw new IllegalArgumentException("ID de enemigo no válido");
        }

        // Asignar los atributos del enemigo basado en el ID
        this.name = foeName[id];
        this.className = foeClass[id];
        this.strength = foeStrength[id];  // Asignar fuerza desde el array
        this.dexterity = foeDexterity[id];  // Asignar destreza desde el array
        this.constitution = foeConstitution[id];  // Asignar constitución desde el array
        this.maxHp = foeMaxHp[id];  // Asignar HP máximo desde el array
        this.currentHp = this.maxHp;  // La vida actual empieza igual que la vida máxima

        System.out.println("Te has encontrado con un enemigo " + this.className + " llamado " + this.name + " con " +
                this.maxHp + " puntos de vida, " + this.strength + " de fuerza, " + this.dexterity +
                " de destreza, y " + this.constitution + " de constitución.");
    }

    @Override
    public void move() {
        /* El enemigo aun no se mueve */        
    }

    public npc(String name, String className, int strength, int dexterity, int constitution, int maxHp) {
        super(0); // Pasar un ID predeterminado o 0
        this.name = name;
        this.className = className;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }
    
}
