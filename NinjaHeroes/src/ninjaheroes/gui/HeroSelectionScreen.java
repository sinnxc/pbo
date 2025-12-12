package ninjaheroes.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ninjaheroes.Hero;
import ninjaheroes.HeroFactory;
import ninjaheroes.TeamBattleSystemGui;

import java.util.*;
import java.util.stream.Collectors;

public class HeroSelectionScreen {

    private final Scene scene;
    private final List<Hero> allHeroes = HeroFactory.createAllHeroes();
    private final List<Hero> selected = new ArrayList<>();   // max 3

    // mapping hero -> button untuk highlight ketika terpilih
    private final Map<Hero, Button> heroButtons = new HashMap<>();

    // slot verifikasi
    private final ImageView[] selectedImages = new ImageView[3];
    private final Label[] selectedLabels = new Label[3];

    public HeroSelectionScreen(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        VBox topBox = new VBox(15);
        topBox.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Pilih 3 Hero");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        HBox selectedBox = new HBox(20);
        selectedBox.setAlignment(Pos.CENTER);
        selectedBox.setPadding(new Insets(10, 0, 10, 0));

        for (int i = 0; i < 3; i++) {
            selectedImages[i] = new ImageView();
            selectedImages[i].setFitWidth(90);
            selectedImages[i].setFitHeight(90);

            selectedLabels[i] = new Label("Slot " + (i + 1));
            selectedLabels[i].setMaxWidth(100);
            selectedLabels[i].setWrapText(true);
            selectedLabels[i].setAlignment(Pos.CENTER);

            VBox slot = new VBox(5, selectedImages[i], selectedLabels[i]);
            slot.setAlignment(Pos.CENTER);
            slot.setPrefSize(120, 140);
            slot.setStyle("-fx-border-color: gray; -fx-border-width: 2; -fx-background-color: rgba(255,255,255,0.7);");

            final int idx = i;
            slot.setOnMouseClicked(e -> {
                // klik slot untuk mengosongkan hero di slot itu
                if (idx < selected.size()) {
                    Hero h = selected.get(idx);
                    selected.remove(h);
                    refreshSelectedPanel();
                    refreshHeroButtonStyles();
                }
            });

            selectedBox.getChildren().add(slot);
        }

        topBox.getChildren().addAll(title, selectedBox);
        root.setTop(topBox);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(20, 40, 20, 40));

        int columns = 11; // 44 hero -> 11 x 4
        int index = 0;
        for (Hero h : allHeroes) {
            ImageView img = new ImageView(new Image("file:" + h.getBannerImage()));
            img.setFitWidth(80);
            img.setFitHeight(80);

            Label name = new Label(h.getName());
            name.setWrapText(true);
            name.setMaxWidth(90);
            name.setAlignment(Pos.CENTER);

            VBox content = new VBox(5, img, name);
            content.setAlignment(Pos.CENTER);

            Button btn = new Button();
            btn.setGraphic(content);
            btn.setPrefSize(110, 130);
            btn.setStyle("-fx-background-color: white;");

            btn.setOnAction(e -> toggleHeroSelection(h));

            heroButtons.put(h, btn);

            int col = index % columns;
            int row = index / columns;
            grid.add(btn, col, row);
            index++;
        }

        // buat kolom & baris grid proporsional memenuhi layar
        for (int c = 0; c < columns; c++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / columns);
            grid.getColumnConstraints().add(cc);
        }
        int rows = (int) Math.ceil(allHeroes.size() / (double) columns);
        for (int r = 0; r < rows; r++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / rows);
            grid.getRowConstraints().add(rc);
        }

        root.setCenter(grid);


        Button startBtn = new Button("Start Battle");
        startBtn.setStyle("-fx-font-size: 22px; -fx-padding: 10 40 10 40;");
        startBtn.setDisable(true); // aktif kalau sudah 3 hero

        startBtn.setOnAction(e -> {
            if (selected.size() != 3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pilih Hero");
                alert.setHeaderText(null);
                alert.setContentText("Kamu harus memilih tepat 3 hero terlebih dahulu.");
                alert.show();
                return;
            }

            // acak 3 hero musuh yang tidak dipilih player
            List<Hero> pool = allHeroes.stream()
                    .filter(h -> !selected.contains(h))
                    .collect(Collectors.toList());
            Collections.shuffle(pool);
            List<Hero> enemyTeam = pool.stream().limit(3).collect(Collectors.toList());

            if (enemyTeam.size() < 3) {
                return; // guard
            }

            TeamBattleSystemGui engine = new TeamBattleSystemGui(new ArrayList<>(selected), enemyTeam);
            BattleScreen battle = new BattleScreen(stage, engine);
            battle.show();
        });

        BorderPane.setAlignment(startBtn, Pos.CENTER);
        BorderPane.setMargin(startBtn, new Insets(10, 0, 20, 0));
        root.setBottom(startBtn);

        // ukuran awal = ukuran layar utama
        double sw = Screen.getPrimary().getBounds().getWidth();
        double sh = Screen.getPrimary().getBounds().getHeight();
        scene = new Scene(root, sw, sh);

        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.setMaximized(true);

        // refresh awal
        refreshSelectedPanel();
        refreshHeroButtonStyles();

        // enable/disable Start berdasarkan selection
        scene.rootProperty().addListener((obs, oldRoot, newRoot) -> {
            // nothing, tapi biar tidak lupa kalau nanti root diganti
        });

        // binding sederhana: kalau selection berubah, panggil dari toggleHeroSelection
        this.startButtonRef = startBtn;  // lihat field di bawah
    }

    // trick kecil: supaya bisa enable/disable startBtn dari helper
    private Button startButtonRef;

    private void toggleHeroSelection(Hero h) {
        if (selected.contains(h)) {
            selected.remove(h);
        } else {
            if (selected.size() >= 3) {
                // kalau sudah 3, ganti hero terakhir dengan yang baru
                selected.set(2, h);
            } else {
                selected.add(h);
            }
        }
        refreshSelectedPanel();
        refreshHeroButtonStyles();
        if (startButtonRef != null) {
            startButtonRef.setDisable(selected.size() != 3);
        }
    }

    private void refreshSelectedPanel() {
        for (int i = 0; i < 3; i++) {
            if (i < selected.size()) {
                Hero h = selected.get(i);
                selectedImages[i].setImage(new Image("file:" + h.getBannerImage()));
                selectedLabels[i].setText(h.getName());
            } else {
                selectedImages[i].setImage(null);
                selectedLabels[i].setText("Slot " + (i + 1));
            }
        }
    }

    private void refreshHeroButtonStyles() {
        for (Hero h : allHeroes) {
            Button b = heroButtons.get(h);
            if (b == null) continue;
            if (selected.contains(h)) {
                b.setStyle("-fx-background-color: #ffe08a; -fx-border-color: #ff9000; -fx-border-width: 3;");
            } else {
                b.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-border-width: 1;");
            }
        }
    }

    public Scene getScene() {
        return scene;
    }
}
