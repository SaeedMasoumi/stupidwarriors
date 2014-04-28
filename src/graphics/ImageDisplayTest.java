/*
 * Copyright (C) 2014 Bl.
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
//imgae duration
//import javafx.animation.KeyFrame;


import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ImageDisplayTest extends Application {
   
        
        

/*durition image*/
//    @Override
//    public void start(Stage primaryStage) {
//        Image image1 = new Image(getClass().getResourceAsStream("icon.png"));
//        Image image2 = new Image(getClass().getResourceAsStream("ic.png"));
//        Image image3 = new Image(getClass().getResourceAsStream("img/bg.jpg"));
//        ImageView imageView = new ImageView();
//        imageView.setX(100);
//        ImageView imageViewTag = new ImageView();
//        imageViewTag.setX(250);
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(imageView.imageProperty(), image2)),
//                new KeyFrame(Duration.seconds(3), new KeyValue(imageView.imageProperty(), image1)),
//                new KeyFrame(Duration.seconds(5), new KeyValue(imageView.imageProperty(), image2))
//                );
//        
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.setAutoReverse(true);
//        timeline.play();
//
//        
//        StackPane root = new StackPane();
//        
//        //Group root = new Group();
//        root.getChildren().add(imageView);
//        primaryStage.setScene(new Scene(root, 800, 600));
//        primaryStage.show();
//        System.out.println(timeline.getCurrentTime());
//       
//    }

    public void startAks() {
        launch();
    }

    @Override
    public void start(Stage stage)  {
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp,1024,600,Color.AZURE);
        ToolBar toolbar = new ToolBar();
        bp.setTop(toolbar);

        Image image = new Image(getClass().getResourceAsStream("icon.png"));
        
        stage.getIcons().add(image);
        //set title
        stage.setTitle("Stupid Warriors v1.0");
        stage.setScene(scene);
        stage.show();
    }
}