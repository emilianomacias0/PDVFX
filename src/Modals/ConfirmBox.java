package Modals;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by fknrk on 6/29/2017.
 */
public class ConfirmBox {
    static  Boolean answer;
    public static Boolean display(String title, String message) {
        Stage window = new Stage();
        //No permite operar otras ventanas hasta que esta se cierre
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);

        window.setMinWidth(400);
        window.setMinHeight(200);

        Label label = new Label();
        label.setText(message);

        //Button
        Button buttonYes = new Button("Si");
        Button buttonNo = new Button("No");

        buttonYes.setOnAction(e -> {
            answer = true;
            window.close();
        });


        buttonNo.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox();
        HBox layout2 = new HBox();
        layout2.setAlignment(Pos.CENTER);
        layout2.setSpacing(25);
        layout2.getChildren().addAll(buttonYes,buttonNo);
        layout.getChildren().addAll(label,layout2);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

        window.setScene(scene);

        window.showAndWait();

        return answer;
    }
}
