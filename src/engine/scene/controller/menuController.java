/*
 * Copyright (C) 2014 Saeed Masoumi & Saeed Rajabzade
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package engine.scene.controller;

import engine.audio.Sound;
import engine.scene.gameScene;
import engine.scene.gameSelectScene;
import engine.scene.mainMenuScene;
import engine.scene.startUpScene;
import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for menuScene.fxml
 * @author Saeed Masoumi
 */
public class menuController implements Initializable{
    static int seconds=0;
    @FXML ImageView background;
    @FXML StackPane stackPane;
    @FXML Rectangle recBox;
    @FXML Button playButton;
    @FXML Button loadButton;
    @FXML Button multiplayerButton;
    @FXML Button aboutusButton;
    @FXML Button optionButton;
    private final Stage stage;
    public menuController (Stage stage){
    this.stage = stage;
    }
    /**
     * Initialize method for mainMenu.fxml
     * we implement and control "main menu" in this class
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /******************************************************
        resize control
        ******************************************************/
       stackPane.heightProperty().addListener(new ChangeListener() { 
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {              
            background.setFitHeight(stackPane.getHeight());
            }
        });
       stackPane.widthProperty().addListener(new ChangeListener() { 
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {              
            background.setFitWidth(stackPane.getWidth());
            }
        });
        /*****************************************************
            background
        *****************************************************/
            background.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                System.out.println("clicked2")  ;
            }
        });     
            background.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
             //  Sound.bgSound.stop();
            }            
        });
     /*****************************************************
            welcoming message
     *****************************************************/             
     // remove message box(welcome message) with a 3 seconds timer loop
      PauseTransition delay = new PauseTransition(Duration.seconds(2));
         delay.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    stackPane.getChildren().remove(recBox);
                    stackPane.getChildren().remove(stackPane.lookup("#recText"));
            }
        });
        delay.play();  
      //Fade message box (welcome message)
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), recBox);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        ft.play();
        /*
            background sound 
        */
         Sound.bgMusic.setOnEndOfMedia(new Runnable() {
            public void run() { 
              Sound.bgMusic.seek(Duration.ZERO); //if at end of sound play again it
            }
        });
         /*****************************************************
            menu buttons
         ******************************************************/
         //play button
         
        playButton.setOnAction(new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 Sound.clicked1.play();
                 gameSelectScene  g = new gameSelectScene();
                 try {
                     g.start(stage);
                 } catch (Exception ex) {
                     Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
            });
        multiplayerButton.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent event) {
               
               Sound.clicked2.play();
           }
       });
        loadButton.setOnAction(new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 Sound.clicked1.play();
                 
             }
            });
        aboutusButton.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent event) {
               Sound.clicked1.play();
           }
       });
        optionButton.setOnAction(new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 Sound.clicked2.play();
                 
             }
            });
        
        
 
        }

    
}
    
    
   
