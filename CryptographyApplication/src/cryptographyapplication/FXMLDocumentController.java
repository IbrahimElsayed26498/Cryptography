/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographyapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import Cryptography.*;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Ibrahim
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField messageTextBok;
    @FXML
    private TextField keyTextBok;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private CheckBox shiftingCheckBox;
    @FXML
    private CheckBox vigenereCheckBox;
    @FXML
    private CheckBox playFairCheckBox;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handelEncryptyButtonAction(ActionEvent event) {
        String result = "";
        String message = messageTextBok.getText();
        String key = keyTextBok.getText();
        if (shiftingCheckBox.isSelected()) {
            result = "Shifting :\n";
            try {
                result += Shifting.encrypt(message,
                        Integer.parseInt(key));
                resultTextArea.setText("");
            } catch (Exception e) {
                resultTextArea.setText("key must be a number.");
                return;
            }
        }else if(vigenereCheckBox.isSelected()){
            result = "Vigenere :\n";
            result += Vigenere.encrypt(message, key);
        }else if(playFairCheckBox.isSelected()){
            result = "Play Fair :\n";
            result += PlayFair.encrypt(message, key);
        }
        
        resultTextArea.setText(result);
    }

    @FXML
    private void handelDecryptyButtonAction(ActionEvent event) {
        String result = "";
        String message = messageTextBok.getText();
        String key = keyTextBok.getText();
        
        
        if (shiftingCheckBox.isSelected()) {
            result = "Shifting :\n";
            try {
                result += Shifting.decrypt(message,
                        Integer.parseInt(key));
                resultTextArea.setText("");
            } catch (Exception e) {
                resultTextArea.setText("key must be a number.");
            }
        }else if(vigenereCheckBox.isSelected()){
            result = "Vigenere :\n";
            result += Vigenere.decrypt(message, key);
        }else if(playFairCheckBox.isSelected()){
            result = "Play Fair :\n";
            result += PlayFair.decryption(message, key);
        }
        
        resultTextArea.setText(result);
    }

    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        resultTextArea.setText("");
        messageTextBok.setText("");
        keyTextBok.setText("");
    }

    @FXML
    private void handleShiftingCheckBoxAction(ActionEvent event) {
        if(shiftingCheckBox.isSelected()){
            vigenereCheckBox.setSelected(false);
            playFairCheckBox.setSelected(false);
        }
    }

    @FXML
    private void handleVigenereCheckBoxAction(ActionEvent event) {
        if(vigenereCheckBox.isSelected()){
            shiftingCheckBox.setSelected(false);
            playFairCheckBox.setSelected(false);
        }
    }

    @FXML
    private void handlePlayFairCheckBoxAction(ActionEvent event) {
        if(playFairCheckBox.isSelected()){
            shiftingCheckBox.setSelected(false);
            vigenereCheckBox.setSelected(false);
        }
    }

}
