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


package engine.scene;
import java.awt.Insets;
import java.awt.Label;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Render the start Up menus 
 * @author Saeed Masoumi
 */
public class startUpScene extends mainScene{
    /**
     * Store Passing Time
     */
    private static int seconds = 0;
    private ImageView slideShow = new ImageView();
    private StackPane loader ;
     /**
     * first render startup about 6 seconds then call Menu Scene
     * @param stage  stage in mainScene
     * @throws IOException 
     */
    public void renderStartUpScene(Stage stage) throws IOException{
        
         loader = FXMLLoader.load(getClass().getResource("layout/mainScene.fxml"));
         //show GPL ,JavaFx ,Opensource 
         Timeline slideShowTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(slideShow.imageProperty(), new Image(JavaFx))),
                new KeyFrame(Duration.seconds(2.5), new KeyValue(slideShow.imageProperty(), new Image(GPLv3))),
                new KeyFrame(Duration.seconds(4), new KeyValue(slideShow.imageProperty(), new Image(OpenSource))),
                new KeyFrame(Duration.seconds(7), new KeyValue(slideShow.imageProperty(),null) ));
                slideShowTimer.play();
        slideShow.setTranslateX(-200);
        slideShow.setTranslateY(300);        
        //add slideshow to loader
        loader.getChildren().add(slideShow);
        
        //add loader to stage        
        stage.setScene(new Scene(loader));
        stage.show();//show stage
        
        //timer for calculate when should we exit from startUp
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
        @Override public void handle(Event event) {
           
           refreshScene();     
           if(seconds>=7){
               mainMenuScene mainmenu = new mainMenuScene();
               try {
                   mainmenu.renderMainMenuScene(stage);
               } catch (IOException ex) {
                   Logger.getLogger(startUpScene.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
         }),  
        new KeyFrame(Duration.seconds(1.0)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();//start timer
    }
        
     private void refreshScene() {
               
               
         seconds++;
     }

}
    

//TODO add handler for background image for resizing when user resize the stage