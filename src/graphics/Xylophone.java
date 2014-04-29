/*
 * Copyright (c) 2010, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package graphics;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Xylophone extends Application {
    final double sceneWidth =1024;
    final double sceneHeight =700;
    final double stageExtraWidht = 16 ;        
    final double stageExtraHeight = 154 ;
        
    public  void start() {
        launch();
    }

    @Override
    public void start(Stage stage)  {
        //create StackPane
        StackPane stackpane = new StackPane();
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
