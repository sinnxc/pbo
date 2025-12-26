package ninjaheroes;

public class Hero {
    private String name;
    private String bannerImage;
    private int maxHp;
    private int currentHp;
    private int power;
    private double critRate;
    private double dodgeRate;
    private double damageReduction;
    private double critDamage;
    private Skill heroSkill;
    private Skill clanSkill;

    public Hero(String name,
                String bannerImage,
                int maxHp,
                int power,
                double critRate,
                double dodgeRate,
                double damageReduction,
                double critDamage,
                Skill heroSkill,
                Skill clanSkill) {

        this.name = name;
        this.bannerImage = bannerImage;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.power = power;
        this.critRate = critRate;
        this.dodgeRate = dodgeRate;
        this.damageReduction = damageReduction;
        this.critDamage = critDamage;
        this.heroSkill = heroSkill;
        this.clanSkill = clanSkill;
    }

    public String getName() { return name; }
    public String getBannerImage() { return bannerImage; }
    public int getMaxHp() { return maxHp; }
    public int getCurrentHp() { return currentHp; }
    public int getPower() { return power; }
    public double getCritRate() { return critRate; }
    public double getDodgeRate() { return dodgeRate; }
    public double getDamageReduction() { return damageReduction; }
    public double getCritDamage() { return critDamage; }
    public Skill getHeroSkill() { return heroSkill; }
    public Skill getClanSkill() { return clanSkill; }

    public String getIdleSprite() {
        // Derive sprite file name from the banner name so assets stay in sync.
        String base = bannerImage;
        int slash = base.lastIndexOf('/');
        if (slash >= 0) base = base.substring(slash + 1);
        if (base.endsWith(".png")) base = base.substring(0, base.length() - 4);
        return "assets/sprites/" + base + "_idle.png";
    }

    public void setCurrentHp(int hp) {
        if (hp < 0) hp = 0;
        if (hp > maxHp) hp = maxHp;
        this.currentHp = hp;
    }
}
