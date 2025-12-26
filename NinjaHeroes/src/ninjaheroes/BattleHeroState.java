package ninjaheroes;

public class BattleHeroState {
    private Hero hero;
    private int heroSkillCooldown;
    private int clanSkillCooldown;
    private double tempDamageReduction;

    public BattleHeroState(Hero hero) {
        this.hero = hero;
        this.heroSkillCooldown = 0;
        this.clanSkillCooldown = 0;
        this.tempDamageReduction = 0.0;
    }

    public Hero getHero() { return hero; }

    public int getHeroSkillCooldown() { return heroSkillCooldown; }
    public void setHeroSkillCooldown(int cd) { this.heroSkillCooldown = cd; }

    public int getClanSkillCooldown() { return clanSkillCooldown; }
    public void setClanSkillCooldown(int cd) { this.clanSkillCooldown = cd; }

    public double getTempDamageReduction() { return tempDamageReduction; }
    public void setTempDamageReduction(double v) { this.tempDamageReduction = v; }

    public void decrementCooldowns() {
        if (heroSkillCooldown > 0) heroSkillCooldown--;
        if (clanSkillCooldown > 0) clanSkillCooldown--;
    }

    public void resetTempDefense() {
        this.tempDamageReduction = 0.0;
    }
}
