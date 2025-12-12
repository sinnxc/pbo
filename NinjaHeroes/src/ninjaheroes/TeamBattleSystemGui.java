package ninjaheroes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamBattleSystemGui {

    private final Random random = new Random();

    private final List<Hero> playerTeam;
    private final List<Hero> enemyTeam;

    private int playerIndex = 0;
    private int enemyIndex = 0;

    private BattleHeroState playerState;
    private BattleHeroState enemyState;

    private boolean gameOver = false;
    private String winner = null;

    public TeamBattleSystemGui(List<Hero> playerTeam, List<Hero> enemyTeam) {
        if (playerTeam.isEmpty() || enemyTeam.isEmpty()) {
            throw new IllegalArgumentException("Tim tidak boleh kosong");
        }
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;

        this.playerState = new BattleHeroState(playerTeam.get(playerIndex));
        this.enemyState = new BattleHeroState(enemyTeam.get(enemyIndex));
    }

    public Hero getCurrentPlayerHero() {
        return playerState.getHero();
    }

    public Hero getCurrentEnemyHero() {
        return enemyState.getHero();
    }

    public double getPlayerHpRatio() {
        Hero h = getCurrentPlayerHero();
        return (double) h.getCurrentHp() / h.getMaxHp();
    }

    public double getEnemyHpRatio() {
        Hero h = getCurrentEnemyHero();
        return (double) h.getCurrentHp() / h.getMaxHp();
    }

    public int getPlayerHeroSkillCd() {
        return playerState.getHeroSkillCooldown();
    }

    public int getPlayerClanSkillCd() {
        return playerState.getClanSkillCooldown();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner() {
        return winner;
    }

    private int getCooldownForSkill(Skill skill) {
        int baseCd;
        if (skill.getBaseCooldown() > 0) {
            baseCd = skill.getBaseCooldown();
        } else {
            double v = skill.getValue();
            switch (skill.getType()) {
                case ATTACK:
                    baseCd = (v >= 150.0) ? 3 : 2;
                    break;
                case HEAL:
                    baseCd = (v >= 22.0) ? 3 : 2;
                    break;
                case DEFENSE:
                    baseCd = (v >= 40.0) ? 3 : 2;
                    break;
                default:
                    baseCd = 2;
                    break;
            }
        }
        // Tambahkan +1 turn ke semua cooldown.
        return baseCd + 1;
    }

    private void performBasicAttack(BattleHeroState attacker, BattleHeroState defender, List<String> logs) {
        Hero a = attacker.getHero();
        Hero d = defender.getHero();

        if (random.nextDouble() < d.getDodgeRate()) {
            logs.add(a.getName() + " melakukan BASIC ATTACK, tapi " + d.getName() + " menghindar!");
            return;
        }

        double damage = a.getPower();
        boolean crit = random.nextDouble() < a.getCritRate();
        if (crit) damage *= a.getCritDamage();

        double totalDR = d.getDamageReduction() + defender.getTempDamageReduction();
        if (totalDR > 0.8) totalDR = 0.8;

        int finalDamage = (int) Math.round(damage * (1.0 - totalDR));
        d.setCurrentHp(d.getCurrentHp() - finalDamage);

        logs.add(a.getName() + " BASIC " + (crit ? "(CRIT) " : "")
                + "→ " + finalDamage + " damage ke " + d.getName()
                + " (HP: " + d.getCurrentHp() + ")");
        defender.resetTempDefense();
    }

    private void useSkill(BattleHeroState attacker, BattleHeroState defender,
                          Skill skill, boolean isClanSkill, List<String> logs) {

        Hero a = attacker.getHero();
        Hero d = defender.getHero();

        logs.add(a.getName() + " menggunakan SKILL [" + skill.getName() + "]");

        switch (skill.getType()) {
            case ATTACK:
                if (random.nextDouble() < d.getDodgeRate()) {
                    logs.add(" → Serangan skill MISS! " + d.getName() + " menghindar.");
                } else {
                    double damage = a.getPower() * (skill.getValue() / 100.0);
                    boolean crit = random.nextDouble() < a.getCritRate();
                    if (crit) damage *= a.getCritDamage();

                    double totalDR = d.getDamageReduction() + defender.getTempDamageReduction();
                    if (totalDR > 0.8) totalDR = 0.8;

                    int finalDamage = (int) Math.round(damage * (1.0 - totalDR));
                    d.setCurrentHp(d.getCurrentHp() - finalDamage);

                    logs.add(" → Skill HIT " + (crit ? "(CRIT) " : "")
                            + finalDamage + " damage ke " + d.getName()
                            + " (HP: " + d.getCurrentHp() + ")");
                }
                defender.resetTempDefense();
                break;

            case HEAL:
                int heal = (int) Math.round(a.getMaxHp() * (skill.getValue() / 100.0));
                a.setCurrentHp(a.getCurrentHp() + heal);
                logs.add(" → " + a.getName() + " HEAL " + heal
                        + " (HP: " + a.getCurrentHp() + ")");
                break;

            case DEFENSE:
                double extraDR = skill.getValue() / 100.0;
                attacker.setTempDamageReduction(extraDR);
                logs.add(" → " + a.getName() + " DEFENSE UP +" + (int) skill.getValue() + "%");
                break;
        }

        int cd = getCooldownForSkill(skill);
        if (isClanSkill) attacker.setClanSkillCooldown(cd);
        else attacker.setHeroSkillCooldown(cd);
    }

    private void enemyTurn(List<String> logs) {
        Hero enemy = enemyState.getHero();

        logs.add("=== GILIRAN MUSUH: " + enemy.getName() + " ===");

        double roll = random.nextDouble();
        if (roll < 0.20 && enemyState.getHeroSkillCooldown() == 0) {
            useSkill(enemyState, playerState, enemy.getHeroSkill(), false, logs);
        } else if (roll < 0.50 && enemyState.getClanSkillCooldown() == 0) {
            useSkill(enemyState, playerState, enemy.getClanSkill(), true, logs);
        } else {
            performBasicAttack(enemyState, playerState, logs);
        }
    }

    private boolean checkAndHandleDeath(List<String> logs) {
        Hero p = playerState.getHero();
        Hero e = enemyState.getHero();

        if (e.getCurrentHp() <= 0) {
            logs.add(e.getName() + " KALAH!");
            enemyIndex++;
            if (enemyIndex >= enemyTeam.size()) {
                gameOver = true;
                winner = "PLAYER";
                logs.add(">>> SEMUA HERO MUSUH KALAH • PLAYER MENANG!");
                return true;
            } else {
                Hero next = enemyTeam.get(enemyIndex);
                enemyState = new BattleHeroState(next);
                logs.add("Musuh mengganti hero ke: " + next.getName());
            }
        }

        if (p.getCurrentHp() <= 0) {
            logs.add(p.getName() + " KALAH!");
            playerIndex++;
            if (playerIndex >= playerTeam.size()) {
                gameOver = true;
                winner = "BOT";
                logs.add(">>> SEMUA HERO PLAYER KALAH • BOT MENANG!");
                return true;
            } else {
                Hero next = playerTeam.get(playerIndex);
                playerState = new BattleHeroState(next);
                logs.add("Player mengganti hero ke: " + next.getName());
            }
        }

        return false;
    }

    public List<String> performPlayerAction(PlayerAction action) {
        List<String> logs = new ArrayList<>();

        if (gameOver) {
            logs.add("Battle sudah selesai. Pemenang: " + winner);
            return logs;
        }

        Hero pHero = playerState.getHero();
        Hero eHero = enemyState.getHero();

        logs.add("=== TURN BARU ===");
        logs.add("Player: " + pHero.getName() + " (HP " + pHero.getCurrentHp() + ")");
        logs.add("Musuh : " + eHero.getName() + " (HP " + eHero.getCurrentHp() + ")");

        switch (action) {
            case HERO_SKILL:
                if (playerState.getHeroSkillCooldown() == 0) {
                    useSkill(playerState, enemyState, pHero.getHeroSkill(), false, logs);
                } else {
                    logs.add("Hero skill masih cooldown, BASIC ATTACK dilakukan.");
                    performBasicAttack(playerState, enemyState, logs);
                }
                break;

            case CLAN_SKILL:
                if (playerState.getClanSkillCooldown() == 0) {
                    useSkill(playerState, enemyState, pHero.getClanSkill(), true, logs);
                } else {
                    logs.add("Clan skill masih cooldown, BASIC ATTACK dilakukan.");
                    performBasicAttack(playerState, enemyState, logs);
                }
                break;

            case BASIC:
            default:
                performBasicAttack(playerState, enemyState, logs);
                break;
        }

        // cek musuh mati
        if (checkAndHandleDeath(logs)) {
            return logs;
        }

        enemyTurn(logs);

        // cek player mati
        if (checkAndHandleDeath(logs)) {
            return logs;
        }

        // akhir turn: kurangi cooldown
        playerState.decrementCooldowns();
        enemyState.decrementCooldowns();

        return logs;
    }
}
