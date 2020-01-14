package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
public class Controller {
    @FXML
    private TextArea mainArea;

    @FXML
    private TextField msgField;

    public void sendMsgAction(ActionEvent actionEvent) {
            if(msgField.getText().trim().length() > 0)
            {
                mainArea.appendText(msgField.getText() + "\n");
                msgField.setText("");
                msgField.requestFocus();
            }
    }
}
