/*
 * Copyright (C) 2014 Saeed Masoumi & Saeed Rajabzade.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */


package graphics;
import java.awt.Insets;
import java.awt.Label;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Render the start Up menus And other things
 * @author Saeed Masoumi
 */
public class RenderMenu extends Application{
    private Stage stage ;
    final double sceneWidth =1024;
    final double sceneHeight =700;
    final double stageExtraWidht = 16 ;        
    final double stageExtraHeight = 154 ;
        
    @Override
    public void start(Stage Stage) {
        stage = Stage;
        showStartUp();
       
   }
    /**
     * implement launch method start() 
     * */
    public void RenderStartUpMenu(){
        launch();
    }

    private void showStartUp() {
 //create StackPane
        StackPane stackpane = new StackPane();
        //add Text
        Text loading= new Text("LOADING..........");
        //add Images
        Image backGround = new Image(getClass().getResourceAsStream("img/bg.jpg"));
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        Image stiOP = new Image(getClass().getResourceAsStream("img/stiOP.png"));
        Image stiFX = new Image(getClass().getResourceAsStream("img/stiFX.jpg"));
        ImageView bgStable = new ImageView(backGround);
        ImageView introChanger = new ImageView();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(introChanger.imageProperty(), stiOP)),
                new KeyFrame(Duration.seconds(2), new KeyValue(introChanger.imageProperty(), stiFX)),
                new KeyFrame(Duration.seconds(4), new KeyValue(introChanger.imageProperty(), stiOP)),
                new KeyFrame(Duration.seconds(6), new KeyValue(introChanger.imageProperty(), stiFX)),
                new KeyFrame(Duration.seconds(8), new KeyValue(introChanger.imageProperty(),null) ));
        
        timeline.play();
        //set BgBackground size
        bgStable.setFitWidth(sceneWidth);
        bgStable.setFitHeight(sceneHeight);
        introChanger.setTranslateY(300);
        introChanger.setFitWidth(100);
        introChanger.setFitHeight(100);
        //add them to stackpane
        stackpane.getChildren().add(bgStable);
        stackpane.getChildren().add(introChanger);
        stackpane.getChildren().add(loading);
        //create scene and add title and icon
        Scene scene =new Scene(stackpane,sceneWidth,sceneHeight);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setTitle("Stupid Warriors V1.0");
        //create a listener for handling when the size of stage changed
       stage.widthProperty().addListener(new ChangeListener() { 
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {              
               bgStable.setFitWidth(stage.getWidth());
            }
        });
        stage.heightProperty().addListener(new ChangeListener() { 
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {              
                bgStable.setFitHeight(stage.getHeight());
            }
        });

        stage.setScene(scene);                             
        stage.show();
    }
    
}
