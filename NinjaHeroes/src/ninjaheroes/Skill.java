package ninjaheroes;

public class Skill {
    private String name;
    private SkillType type;
    private double value;       // persen (damage/heal/defense)
    private int baseCooldown;   // 0 = otomatis ditentukan, 2 atau 3 turn

    // Constructor lama (biar kompatibel)
    public Skill(String name, SkillType type, double value) {
        this(name, type, value, 0);
    }

    // Constructor baru
    public Skill(String name, SkillType type, double value, int baseCooldown) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.baseCooldown = baseCooldown;
    }

    public String getName() { return name; }
    public SkillType getType() { return type; }
    public double getValue() { return value; }
    public int getBaseCooldown() { return baseCooldown; }

    @Override
    public String toString() {
        String cdInfo = baseCooldown > 0 ? " (CD " + baseCooldown + ")" : "";
        return name + " [" + type + "] " + value + "%" + cdInfo;
    }
}
