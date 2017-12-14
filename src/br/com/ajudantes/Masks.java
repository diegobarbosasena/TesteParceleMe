package br.com.ajudantes;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Masks {

	public static void mascaraCelular(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==10&&textField.getText().substring(9,10).equals("-")){
                    textField.setText(textField.getText().substring(0,9));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==9&&textField.getText().substring(8,9).equals("-")){
                    textField.setText(textField.getText().substring(0,8));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==4){
                    textField.setText(textField.getText().substring(0,3));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==1){
                    textField.setText("");
                }

            }else{ //escrevendo

                if(textField.getText().length()==14) event.consume();

                if(textField.getText().length()==0){
                    textField.setText("("+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if(textField.getText().length()==3){
                    textField.setText(textField.getText()+")"+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if(textField.getText().length()==8){
                    textField.setText(textField.getText()+"-"+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if(textField.getText().length()==9&&textField.getText().substring(8,9)!="-"){
                    textField.setText(textField.getText()+"-"+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if(textField.getText().length()==13&&textField.getText().substring(8,9).equals("-")){
                    textField.setText(textField.getText().substring(0,8)+textField.getText().substring(9,10)+"-"+textField.getText().substring(10,13)+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
            }

        });

        textField.setOnKeyReleased((KeyEvent evt) -> {
            if(!textField.getText().matches("\\d()-*")){
                textField.setText(textField.getText().replaceAll("[^\\d()-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });
    }
}
