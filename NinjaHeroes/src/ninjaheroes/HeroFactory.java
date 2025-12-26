package ninjaheroes;

import java.util.ArrayList;
import java.util.List;

public class HeroFactory {

    // helper untuk banner image
    private static String img(String name) {
        return "assets/banners/" + name + ".png";
    }

    public static List<Hero> createAllHeroes() {
        List<Hero> heroes = new ArrayList<>();

        Skill uzumakiClan  = new Skill("Uzumaki Regeneration", SkillType.HEAL,    18.0);
        Skill uchihaClan   = new Skill("Fireball Jutsu",       SkillType.ATTACK, 150.0);
        Skill hyugaClan    = new Skill("Byakugan Rotation",    SkillType.DEFENSE,35.0);
        Skill naraClan     = new Skill("Shadow Defense",       SkillType.DEFENSE,30.0);
        Skill akimichiClan = new Skill("Calorie Recovery",     SkillType.HEAL,   16.0);
        Skill yamanakaClan = new Skill("Mind Spike",           SkillType.ATTACK, 130.0);
        Skill inuzukaClan  = new Skill("Beast Claw",           SkillType.ATTACK, 135.0);
        Skill aburameClan  = new Skill("Bug Armor",            SkillType.DEFENSE,30.0);
        Skill medicalClan  = new Skill("Medical Chakra Flow",  SkillType.HEAL,   18.0);
        Skill hatakeClan   = new Skill("Sharingan Guard",      SkillType.DEFENSE,35.0);
        Skill taijutsuClan = new Skill("Strong Fist Combo",    SkillType.ATTACK, 135.0);
        Skill sunaClan     = new Skill("Sand Barrier",         SkillType.DEFENSE,30.0);
        Skill sanninClan   = new Skill("Sage Recovery",        SkillType.HEAL,   18.0);
        Skill otsutsukiClan = new Skill("Otsutsuki Divine Core", SkillType.ATTACK, 160.0);

        heroes.add(new Hero(
                "Naruto Uzumaki", img("naruto"),
                1300, 135,
                0.18, 0.12,
                0.05,
                1.70,
                new Skill("Rasengan", SkillType.ATTACK, 150.0),
                uzumakiClan
        ));

        heroes.add(new Hero(
                "Kushina Uzumaki", img("kushina"),
                1250, 120,
                0.14, 0.10,
                0.05,
                1.60,
                new Skill("Chakra Chains", SkillType.ATTACK, 140.0),
                uzumakiClan
        ));

        heroes.add(new Hero(
                "Boruto Uzumaki", img("boruto"),
                1050, 120,
                0.18, 0.16,
                0.03,
                1.75,
                new Skill("Lightning Burst", SkillType.ATTACK, 145.0),
                uzumakiClan
        ));

        heroes.add(new Hero(
                "Himawari Uzumaki", img("himawari"),
                900, 95,
                0.12, 0.14,
                0.02,
                1.55,
                new Skill("Gentle Heal", SkillType.HEAL, 23.0),
                uzumakiClan
        ));

        // Minato pindah ke Uzumaki
        heroes.add(new Hero(
                "Minato Namikaze", img("minato"),
                1200, 150,
                0.22, 0.20,
                0.04,
                2.00,
                new Skill("Flash Rasengan", SkillType.ATTACK, 160.0),
                uzumakiClan
        ));

        heroes.add(new Hero(
                "Sasuke Uchiha", img("sasuke"),
                1150, 145,
                0.20, 0.15,
                0.03,
                1.85,
                new Skill("Chidori", SkillType.ATTACK, 160.0),
                uchihaClan
        ));

        heroes.add(new Hero(
                "Itachi Uchiha", img("itachi"),
                1100, 140,
                0.22, 0.16,
                0.03,
                1.90,
                new Skill("Flame Slash", SkillType.ATTACK, 150.0),
                uchihaClan
        ));

        heroes.add(new Hero(
                "Madara Uchiha", img("madara"),
                1350, 160,
                0.18, 0.12,
                0.08,
                1.80,
                new Skill("Susanoo Slash", SkillType.ATTACK, 170.0),
                uchihaClan
        ));

        heroes.add(new Hero(
                "Obito Uchiha", img("obito"),
                1200, 135,
                0.17, 0.14,
                0.05,
                1.75,
                new Skill("Space Slash", SkillType.ATTACK, 145.0),
                uchihaClan
        ));

        heroes.add(new Hero(
                "Shisui Uchiha", img("shisui"),
                1100, 140,
                0.22, 0.18,
                0.02,
                2.00,
                new Skill("Swift Blade", SkillType.ATTACK, 150.0),
                uchihaClan
        ));

        heroes.add(new Hero(
                "Sarada Uchiha", img("sarada"),
                1000, 125,
                0.18, 0.16,
                0.02,
                1.70,
                new Skill("Blaze Punch", SkillType.ATTACK, 145.0),
                uchihaClan
        ));

        heroes.add(new Hero(
                "Neji Hyuga", img("neji"),
                1100, 135,
                0.18, 0.18,
                0.04,
                1.75,
                new Skill("Gentle Fist Strike", SkillType.ATTACK, 150.0),
                hyugaClan
        ));

        heroes.add(new Hero(
                "Hinata Hyuga", img("hinata"),
                1050, 110,
                0.14, 0.15,
                0.04,
                1.60,
                new Skill("Twin Lion Heal", SkillType.HEAL, 19.0),
                hyugaClan
        ));

        heroes.add(new Hero(
                "Hiashi Hyuga", img("hiashi"),
                1150, 125,
                0.15, 0.12,
                0.05,
                1.65,
                new Skill("Palm Thrust", SkillType.ATTACK, 145.0),
                hyugaClan
        ));

        heroes.add(new Hero(
                "Hanabi Hyuga", img("hanabi"),
                1000, 115,
                0.16, 0.16,
                0.03,
                1.65,
                new Skill("Gentle Burst", SkillType.ATTACK, 140.0),
                hyugaClan
        ));

        heroes.add(new Hero(
                "Shikamaru Nara", img("shikamaru"),
                1050, 115,
                0.14, 0.10,
                0.03,
                1.55,
                new Skill("Shadow Jab", SkillType.ATTACK, 130.0),
                naraClan
        ));

        heroes.add(new Hero(
                "Shikadai Nara", img("shikadai"),
                1000, 110,
                0.13, 0.11,
                0.03,
                1.50,
                new Skill("Binding Strike", SkillType.ATTACK, 135.0),
                naraClan
        ));

        heroes.add(new Hero(
                "Choji Akimichi", img("choji"),
                1300, 130,
                0.13, 0.05,
                0.08,
                1.50,
                new Skill("Expansion Punch", SkillType.ATTACK, 150.0),
                akimichiClan
        ));

        heroes.add(new Hero(
                "Choza Akimichi", img("choza"),
                1350, 135,
                0.12, 0.04,
                0.10,
                1.45,
                new Skill("Boulder Drop", SkillType.ATTACK, 155.0),
                akimichiClan
        ));

        heroes.add(new Hero(
                "Chocho Akimichi", img("chocho"),
                1150, 120,
                0.14, 0.07,
                0.06,
                1.50,
                new Skill("Butterfly Kick", SkillType.ATTACK, 140.0),
                akimichiClan
        ));

        heroes.add(new Hero(
                "Ino Yamanaka", img("ino"),
                1000, 110,
                0.13, 0.12,
                0.02,
                1.55,
                new Skill("Mind Rebalance", SkillType.HEAL, 20.0),
                yamanakaClan
        ));

        heroes.add(new Hero(
                "Inojin Yamanaka", img("inojin"),
                1000, 120,
                0.15, 0.14,
                0.02,
                1.60,
                new Skill("Ink Jab", SkillType.ATTACK, 140.0),
                yamanakaClan
        ));

        heroes.add(new Hero(
                "Fu Yamanaka", img("fu"),
                1050, 125,
                0.16, 0.12,
                0.03,
                1.60,
                new Skill("Psycho Push", SkillType.ATTACK, 145.0),
                yamanakaClan
        ));

        heroes.add(new Hero(
                "Kiba Inuzuka", img("kiba"),
                1100, 125,
                0.16, 0.16,
                0.03,
                1.70,
                new Skill("Fang Rush", SkillType.ATTACK, 145.0),
                inuzukaClan
        ));

        heroes.add(new Hero(
                "Hana Inuzuka", img("hana"),
                1150, 120,
                0.15, 0.13,
                0.03,
                1.65,
                new Skill("Beast Combo", SkillType.ATTACK, 150.0),
                inuzukaClan
        ));

        heroes.add(new Hero(
                "Shino Aburame", img("shino"),
                1100, 120,
                0.14, 0.10,
                0.04,
                1.55,
                new Skill("Bug Bite", SkillType.ATTACK, 140.0),
                aburameClan
        ));

        heroes.add(new Hero(
                "Torune Aburame", img("torune"),
                1150, 130,
                0.16, 0.10,
                0.05,
                1.60,
                new Skill("Toxic Punch", SkillType.ATTACK, 150.0),
                aburameClan
        ));

        heroes.add(new Hero(
                "Shibi Aburame", img("shibi"),
                1150, 120,
                0.13, 0.09,
                0.05,
                1.55,
                new Skill("Swarm Strike", SkillType.ATTACK, 145.0),
                aburameClan
        ));

        heroes.add(new Hero(
                "Sakura Haruno", img("sakura"),
                1150, 120,
                0.15, 0.13,
                0.04,
                1.60,
                new Skill("Medical Burst", SkillType.HEAL, 19.0),
                medicalClan
        ));

        heroes.add(new Hero(
                "Tsunade", img("tsunade"),
                1400, 140,
                0.15, 0.08,
                0.10,
                1.55,
                new Skill("Regeneration Palm", SkillType.HEAL, 16.0),
                medicalClan
        ));

        heroes.add(new Hero(
                "Shizune", img("shizune"),
                1050, 115,
                0.14, 0.12,
                0.03,
                1.55,
                new Skill("Toxin Needle", SkillType.ATTACK, 140.0),
                medicalClan
        ));

        heroes.add(new Hero(
                "Kakashi Hatake", img("kakashi"),
                1150, 140,
                0.20, 0.15,
                0.04,
                1.80,
                new Skill("Lightning Blade", SkillType.ATTACK, 155.0),
                hatakeClan
        ));

        heroes.add(new Hero(
                "Rock Lee", img("lee"),
                1100, 135,
                0.18, 0.20,
                0.03,
                1.85,
                new Skill("Leaf Hurricane", SkillType.ATTACK, 145.0),
                taijutsuClan
        ));

        heroes.add(new Hero(
                "Might Guy", img("guy"),
                1200, 150,
                0.20, 0.18,
                0.04,
                1.90,
                new Skill("Dynamic Punch", SkillType.ATTACK, 160.0),
                taijutsuClan
        ));

        heroes.add(new Hero(
                "Gaara", img("gaara"),
                1400, 135,
                0.14, 0.06,
                0.12,
                1.50,
                new Skill("Sand Crush", SkillType.ATTACK, 150.0),
                sunaClan
        ));

        heroes.add(new Hero(
                "Temari", img("temari"),
                1100, 125,
                0.16, 0.14,
                0.03,
                1.60,
                new Skill("Wind Slash", SkillType.ATTACK, 145.0),
                sunaClan
        ));

        heroes.add(new Hero(
                "Kankuro", img("kankuro"),
                1150, 125,
                0.15, 0.12,
                0.04,
                1.60,
                new Skill("Puppet Strike", SkillType.ATTACK, 150.0),
                sunaClan
        ));

        heroes.add(new Hero(
                "Chiyo", img("chiyo"),
                1100, 120,
                0.14, 0.10,
                0.04,
                1.55,
                new Skill("Puppet Palm", SkillType.ATTACK, 140.0),
                sunaClan
        ));

        heroes.add(new Hero(
                "Jiraiya", img("jiraiya"),
                1250, 140,
                0.17, 0.12,
                0.05,
                1.75,
                new Skill("Rasengan Impact", SkillType.ATTACK, 150.0),
                sanninClan
        ));

        heroes.add(new Hero(
                "Orochimaru", img("orochimaru"),
                1200, 140,
                0.17, 0.12,
                0.05,
                1.70,
                new Skill("Snake Blade", SkillType.ATTACK, 155.0),
                sanninClan
        ));

        heroes.add(new Hero(
                "Kaguya Otsutsuki", img("kaguya"),
                1600, 170,
                0.20, 0.10,
                0.12,
                2.00,
                new Skill("God Ash Bones", SkillType.ATTACK, 175.0),
                otsutsukiClan
        ));

        heroes.add(new Hero(
                "Momoshiki Otsutsuki", img("momoshiki"),
                1500, 165,
                0.22, 0.15,
                0.08,
                1.95,
                new Skill("Absorption Burst", SkillType.ATTACK, 165.0),
                otsutsukiClan
        ));

        heroes.add(new Hero(
                "Kinshiki Otsutsuki", img("kinshiki"),
                1550, 160,
                0.16, 0.08,
                0.14,
                1.80,
                new Skill("Chakra Blade", SkillType.ATTACK, 160.0),
                otsutsukiClan
        ));

        heroes.add(new Hero(
                "Isshiki Otsutsuki", img("isshiki"),
                1500, 170,
                0.25, 0.18,
                0.10,
                2.10,
                new Skill("Daikokuten Strike", SkillType.ATTACK, 180.0),
                otsutsukiClan
        ));

        return heroes;
    }
}
