
// imports for classes used
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PuzzleUI_DELGADO extends Application {

    // declare this to identify the position of the blank tile
    int blnk_Row = 2;
    int blnk_Col = 2;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage ps) {

        // labels for each tile
        Label lbl1 = new Label("1");
        Label lbl2 = new Label("2");
        Label lbl3 = new Label("3");
        Label lbl4 = new Label("4");
        Label lbl5 = new Label("5");
        Label lbl6 = new Label("6");
        Label lbl7 = new Label("7");
        Label lbl8 = new Label("8");

        // to keep track the positions of the tiles also to identify the blank tile.
        Label[][] labels = { { lbl1, lbl2, lbl3 }, { lbl4, lbl5, lbl6 }, { lbl7, lbl8, null } };

        // gridpane to hold the labels.
        GridPane root = new GridPane();
        root.add(lbl1, 0, 0);
        root.add(lbl2, 1, 0);
        root.add(lbl3, 2, 0);
        root.add(lbl4, 0, 1);
        root.add(lbl5, 1, 1);
        root.add(lbl6, 2, 1);
        root.add(lbl7, 0, 2);
        root.add(lbl8, 1, 2);
        root.setAlignment(Pos.CENTER);

        // to get the image of css file
        root.getStyleClass().add("background-img");

        // nested loop
        for (Label[] row : labels) {
            for (Label label : row) {
                if (label != null) {

                    // minimum size, spacing and padding for each label
                    label.setMinSize(50, 50);
                    label.setPadding(new Insets(50));

                    // spacing of each label/tile
                    GridPane.setMargin(label, new Insets(3, 3, 3, 3));

                    // set style for every label
                    label.setStyle(
                            "-fx-background-color: linear-gradient(to right, midnightblue, teal); -fx-font-size:20; -fx-text-fill: aliceblue;"
                                    + "-fx-border-width: 2; -fx-border-color: aliceblue; -fx-font-family: monospace;"
                                    + "-fx-border-style: solid outside; -fx-border-insets: 2; "
                                    + "-fx-border-radius: 20 20 20 20; -fx-background-radius: 20 20 20 20; ");

                    // hover effects on label
                    label.setOnMouseEntered(e -> {
                        label.setStyle(
                                "-fx-background-color: linear-gradient(to right, midnightblue, teal); -fx-font-size:20; -fx-font-weight: bold; -fx-text-fill: white;"
                                        + "-fx-border-width: 2; -fx-border-color: gray; -fx-font-family: monospace;"
                                        + "-fx-border-style: solid outside; -fx-border-insets: 2; -fx-opacity: 0.9; "
                                        + "-fx-effect: innershadow(gaussian, rgba(255, 255, 255, 0.6), 40, 0, 0, 0);"
                                        + "-fx-border-radius: 20 20 20 20; -fx-background-radius: 20 20 20 20;");
                    });
                    label.setOnMouseExited(e -> {
                        label.setStyle(
                                "-fx-background-color: linear-gradient(to right, midnightblue, teal); -fx-font-size:20; -fx-text-fill: aliceblue;"
                                        + "-fx-border-width: 2; -fx-border-color: aliceblue; -fx-font-family: monospace;"
                                        + "-fx-border-style: solid outside; -fx-border-insets: 2; "
                                        + "-fx-border-radius: 20 20 20 20; -fx-background-radius: 20 20 20 20; ");

                    });

                }
            }
        }

        // scene for the grid pane
        Scene s = new Scene(root, 650, 650);
        s.getStylesheets().add(getClass().getResource("PuzzleUI_DELGADO.css").toExternalForm());
        ps.setTitle("PuzzleUI by Delgado, Clarence B. BSCPE-3B");
        ps.setScene(s);
        ps.show();

        // event handler to each label to handle moving the tiles.
        for (Label[] row : labels) {
            for (Label label : row) {
                if (label != null) {
                    label.setOnMouseClicked(e -> {
                        int rowIndex = GridPane.getRowIndex(label);
                        int colIndex = GridPane.getColumnIndex(label);
                        if ((rowIndex == blnk_Row && Math.abs(colIndex - blnk_Col) == 1)
                                || (colIndex == blnk_Col && Math.abs(rowIndex - blnk_Row) == 1)) {

                            // swap the label with the blank tile
                            labels[blnk_Row][blnk_Col] = label;
                            labels[rowIndex][colIndex] = null;
                            root.getChildren().remove(label);
                            root.add(label, blnk_Col, blnk_Row);
                            blnk_Row = rowIndex;
                            blnk_Col = colIndex;
                        }
                    });

                }
            }

        }

    }

}
