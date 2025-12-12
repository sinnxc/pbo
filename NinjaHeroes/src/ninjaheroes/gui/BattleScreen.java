package ninjaheroes.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ninjaheroes.*;

public class BattleScreen {

    private final Stage stage;
    private final TeamBattleSystemGui engine;

    private ImageView playerSprite;
    private ImageView enemySprite;

    private ImageView playerBanner;
    private ImageView enemyBanner;

    private ProgressBar playerHpBar;
    private ProgressBar enemyHpBar;

    private Label playerNameLabel;
    private Label enemyNameLabel;

    private Label skillNameLabel;

    private Button basicBtn;
    private Button heroSkillBtn;
    private Button clanSkillBtn;

    private Pane arenaPane;

    private boolean resultShown = false;

    public BattleScreen(Stage stage, TeamBattleSystemGui engine) {
        this.stage = stage;
        this.engine = engine;
    }

    public void show() {
        ImageView bgView = new ImageView(new Image("file:assets/backgrounds/konoha_arena.png"));
        bgView.setPreserveRatio(false);
        bgView.setSmooth(true);
        bgView.setFitWidth(stage.getWidth());
        bgView.setFitHeight(stage.getHeight());

        // Responsif background mengikuti resize
        stage.widthProperty().addListener((obs, oldV, newV) -> bgView.setFitWidth(newV.doubleValue()));
        stage.heightProperty().addListener((obs, oldV, newV) -> bgView.setFitHeight(newV.doubleValue()));

        BorderPane ui = new BorderPane();

        HBox top = new HBox(30);
        top.setAlignment(Pos.TOP_CENTER);
        top.setStyle("-fx-padding: 30 40 10 40;");

        // Player UI
        playerBanner = new ImageView();
        playerBanner.setFitWidth(110);
        playerBanner.setFitHeight(110);

        playerNameLabel = new Label();
        playerNameLabel.setFont(new Font("Arial", 20));
        playerNameLabel.setTextFill(Color.WHITE);

        playerHpBar = new ProgressBar(1.0);
        playerHpBar.setPrefWidth(360);
        playerHpBar.setStyle("-fx-accent: red;");

        VBox leftUI = new VBox(5, playerBanner, playerNameLabel, playerHpBar);
        leftUI.setAlignment(Pos.CENTER_LEFT);

        // Enemy UI
        enemyBanner = new ImageView();
        enemyBanner.setFitWidth(110);
        enemyBanner.setFitHeight(110);

        enemyNameLabel = new Label();
        enemyNameLabel.setFont(new Font("Arial", 20));
        enemyNameLabel.setTextFill(Color.WHITE);

        enemyHpBar = new ProgressBar(1.0);
        enemyHpBar.setPrefWidth(360);
        enemyHpBar.setStyle("-fx-accent: red;");

        VBox rightUI = new VBox(5, enemyBanner, enemyNameLabel, enemyHpBar);
        rightUI.setAlignment(Pos.CENTER_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        top.getChildren().addAll(leftUI, spacer, rightUI);
        ui.setTop(top);

        arenaPane = new Pane();
        arenaPane.setMinHeight(450);

        playerSprite = new ImageView();
        playerSprite.setPreserveRatio(true);
        playerSprite.setSmooth(false);

        enemySprite = new ImageView();
        enemySprite.setPreserveRatio(true);
        enemySprite.setSmooth(false);
        enemySprite.setScaleX(-1); // mirror musuh menghadap kiri

        skillNameLabel = new Label("");
        skillNameLabel.setFont(new Font("Arial", 26));
        skillNameLabel.setTextFill(Color.WHITE);

        arenaPane.getChildren().addAll(playerSprite, enemySprite, skillNameLabel);
        ui.setCenter(arenaPane);

        basicBtn = new Button("Basic");
        heroSkillBtn = new Button();
        clanSkillBtn = new Button();

        // Lebar dibuat lebih panjang, tinggi tetap besar
        double btnWidth  = 220;
        double btnHeight = 120;

        basicBtn.setPrefSize(btnWidth, btnHeight);
        heroSkillBtn.setPrefSize(btnWidth, btnHeight);
        clanSkillBtn.setPrefSize(btnWidth, btnHeight);

        // Biar teks bisa 2 baris kalau kepanjangan
        heroSkillBtn.setWrapText(true);
        clanSkillBtn.setWrapText(true);
        basicBtn.setWrapText(true);

        basicBtn.setStyle("-fx-font-size: 22px; -fx-background-color: white; -fx-border-radius: 50;");
        heroSkillBtn.setStyle("-fx-font-size: 22px; -fx-background-color: white; -fx-border-radius: 50;");
        clanSkillBtn.setStyle("-fx-font-size: 22px; -fx-background-color: white; -fx-border-radius: 50;");

        HBox bottom = new HBox(20, basicBtn, heroSkillBtn, clanSkillBtn);
        bottom.setAlignment(Pos.CENTER);
        bottom.setStyle("-fx-padding: 20 0 60 0;");

        ui.setBottom(bottom);

        StackPane root = new StackPane(bgView, ui);

        basicBtn.setOnAction(e -> performAction(PlayerAction.BASIC));
        heroSkillBtn.setOnAction(e -> performAction(PlayerAction.HERO_SKILL));
        clanSkillBtn.setOnAction(e -> performAction(PlayerAction.CLAN_SKILL));

        refreshUI();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();

        // Sprite responsive position
        arenaPane.widthProperty().addListener((o, ov, nv) -> layoutSprites());
        arenaPane.heightProperty().addListener((o, ov, nv) -> layoutSprites());
        layoutSprites();
    }

    private void performAction(PlayerAction action) {
        if (engine.isGameOver()) {
            showResultIfNeeded();
            return;
        }

        // Tampilkan nama skill di tengah
        Hero current = engine.getCurrentPlayerHero();
        if (action == PlayerAction.BASIC) {
            skillNameLabel.setText("Basic Attack");
        } else if (action == PlayerAction.HERO_SKILL) {
            skillNameLabel.setText(current.getHeroSkill().getName());
        } else if (action == PlayerAction.CLAN_SKILL) {
            skillNameLabel.setText(current.getClanSkill().getName());
        }

        engine.performPlayerAction(action);
        refreshUI();
    }

    private void refreshUI() {
        Hero p = engine.getCurrentPlayerHero();
        Hero e = engine.getCurrentEnemyHero();

        // Banner & nama
        playerBanner.setImage(new Image("file:" + p.getBannerImage()));
        enemyBanner.setImage(new Image("file:" + e.getBannerImage()));

        playerNameLabel.setText(p.getName());
        enemyNameLabel.setText(e.getName());

        // Sprite idle
        playerSprite.setImage(new Image("file:" + p.getIdleSprite()));
        enemySprite.setImage(new Image("file:" + e.getIdleSprite()));

        // HP bar (clamp 0..1)
        double pRatio = Math.max(0.0, Math.min(1.0,
                (double) p.getCurrentHp() / p.getMaxHp()));
        double eRatio = Math.max(0.0, Math.min(1.0,
                (double) e.getCurrentHp() / e.getMaxHp()));

        playerHpBar.setProgress(pRatio);
        enemyHpBar.setProgress(eRatio);

        basicBtn.setText("Basic");
        heroSkillBtn.setText(p.getHeroSkill().getName());
        clanSkillBtn.setText(p.getClanSkill().getName());

        int heroCD = engine.getPlayerHeroSkillCd();
        int clanCD = engine.getPlayerClanSkillCd();

        if (heroCD > 0) {
            heroSkillBtn.setDisable(true);
            heroSkillBtn.setText(p.getHeroSkill().getName() + " (" + heroCD + ")");
        } else {
            heroSkillBtn.setDisable(false);
        }

        if (clanCD > 0) {
            clanSkillBtn.setDisable(true);
            clanSkillBtn.setText(p.getClanSkill().getName() + " (" + clanCD + ")");
        } else {
            clanSkillBtn.setDisable(false);
        }

        // Jika battle sudah selesai
        if (engine.isGameOver()) {
            basicBtn.setDisable(true);
            heroSkillBtn.setDisable(true);
            clanSkillBtn.setDisable(true);

            String winner = engine.getWinner();
            skillNameLabel.setText("Pemenang: " + winner);
            showResultIfNeeded();
        } else {
            basicBtn.setDisable(false);
        }
    }

    private void showResultIfNeeded() {
        if (resultShown) return;
        resultShown = true;

        String winner = engine.getWinner();
        if (winner == null) winner = "Tidak diketahui";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Battle Selesai");
        alert.setHeaderText("Pemenang: " + winner);
        alert.setContentText("");
        alert.show();
    }

    private void layoutSprites() {
        double w = arenaPane.getWidth();
        double h = arenaPane.getHeight();
        if (w <= 0 || h <= 0) return;

        double charHeight = h * 0.75;   // 58% dari arena, proporsi paling realistis
        playerSprite.setFitHeight(charHeight);
        enemySprite.setFitHeight(charHeight);

        double floorY = h * 1.1;

        // Sprite berdiri di lantai (pakai tinggi sprite)
        double playerY = floorY - charHeight;
        double enemyY  = floorY - charHeight;

        double playerX = w * 0.15 - playerSprite.getFitWidth() / 2;
        double enemyX  = w * 0.77 - enemySprite.getFitWidth() / 2;

        // Apply
        playerSprite.setLayoutX(playerX);
        playerSprite.setLayoutY(playerY);

        enemySprite.setLayoutX(enemyX);
        enemySprite.setLayoutY(enemyY);

        // Skill text
        skillNameLabel.setLayoutX(w / 2.0 - 120);
        skillNameLabel.setLayoutY(h * 0.18);
    }

}
