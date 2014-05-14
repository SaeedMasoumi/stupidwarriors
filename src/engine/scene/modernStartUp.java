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

import static engine.scene.mainScene.JavaFx;
import static engine.scene.mainScene.icon;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Modern Startup create a Transparent Scene 
 * extends from mainScene
 * @author Saeed Masoumi
 */
public class modernStartUp extends mainScene {
    private Group rootContent;
    private static final int SCENE_WIDTH = 980;
    private static final int SCENE_HEIGHT = 600;   
    private ImageView slideShow = new ImageView();
/**
 * show start up Scene
 * @param stage given from mainScene
 */
  public void start(Stage stage){
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.sizeToScene();
    stage.setScene(new AppScene());
    stage.show();
         
        //create a 7 seconds delay and after that start Main Menu Scene  
         PauseTransition delay = new PauseTransition(Duration.seconds(6));
         delay.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // pass stage to mainMenuScene 
                 mainMenuScene mainmenu = new mainMenuScene();
                try {
                    mainmenu.start(stage);
                } catch (Exception ex) {
                    Logger.getLogger(startUpScene.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.hide();
            }
        });
        delay.play();
  }
  /**
   * transparent scene using in modern StartUp class
   */
     private class AppScene extends Scene {
         /*
         Appscene constructor
         */
        public AppScene() {
      super(rootContent = new Group(), SCENE_WIDTH, SCENE_HEIGHT, Color.TRANSPARENT);
            rootContent.setClip(new Ellipse(0, SCENE_HEIGHT / 2, SCENE_WIDTH / 3, SCENE_HEIGHT / 2)); //Scene shape and size
            
            Rectangle background = new Rectangle(-SCENE_WIDTH / 2, 0, SCENE_WIDTH, SCENE_HEIGHT);
            background.setFill(new LinearGradient(0, 0, 0, SCENE_HEIGHT, false, CycleMethod.NO_CYCLE, new Stop(0, Color.LIGHTBLUE), new Stop(0.3, Color.TURQUOISE),
            new Stop(1., new Color(1, 1, 1, 0)))); //background color
                // slide show 
                Timeline slideShowTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(slideShow.imageProperty(), new Image(JavaFx))),
                new KeyFrame(Duration.seconds(2.5), new KeyValue(slideShow.imageProperty(), new Image(GPLv3))),
                new KeyFrame(Duration.seconds(4), new KeyValue(slideShow.imageProperty(), new Image(OpenSource))),
                new KeyFrame(Duration.seconds(7), new KeyValue(slideShow.imageProperty(),null) ));
                slideShowTimer.play();
                slideShow.setRotate(180);
                slideShow.setTranslateX(-50);
                slideShow.setTranslateY(50);
                //background image
                ImageView bg = new ImageView(new Image(Background));
                bg.setRotate(180);
                bg.setTranslateX(-325);
                bg.setTranslateY(0);
                //add label 
                
            rootContent.getChildren().add(background);
            rootContent.getChildren().add(bg);
            rootContent.getChildren().add(slideShow);
            rootContent.getTransforms().addAll(new Translate(SCENE_WIDTH / 2, SCENE_HEIGHT), new Rotate(180));
        
        }
}
}
     