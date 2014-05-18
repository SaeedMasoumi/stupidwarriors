///*
// * Copyright (C) 2014 Saeed Masoumi & Saeed Rajabzade
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//
//package engine.scene;
//import java.awt.Insets;
//import java.awt.Label;
//import java.io.File;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Timer;
//import java.util.TimerTask;
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.media.AudioClip;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.util.Duration;
////sound
//
//
///**
// * Main scene of the game
// * first start this then switch between fxml files and other things
// * @author Saeed Masoumi
// */
//public class mainScene extends Application{
//    ///main stage of Game
//    protected Stage stage;
//    //all image address (using in menus)
//    protected final static String icon = "file:src/resources/img/startup/icon.png" ;
//    protected final static String GPLv3 = "file:src/resources/img/startup/gpl.png" ;
//    protected final static String OpenSource= "file:src/resources/img/startup/opensource.png" ;
//    protected final static String JavaFx= "file:src/resources/img/startup/javaFX.png" ;
//    protected final static String Background= "file:src/resources/img/st.jpg" ;
//    protected final static String sw= "file:src/resources/img/iconMenu.jpg" ;
//    @Override
//    public void start(Stage primaryStage) throws IOException{
//        this.stage = primaryStage;
//        stage.setTitle("Stupid Warriors V1.0");
//        stage.getIcons().add(new Image(icon));
//       modernStartUp startup = new modernStartUp();
//       startup.start(stage);
////      startUpScene a = new startUpScene();
////      a.renderStartUpScene(stage);
////              new Timer().schedule(new TimerTask() {
//
////            @Override
////            public void run() {
////                System.out.println("FPS " + com.sun.javafx.perf.PerformanceTracker.getSceneTracker(stage.getScene()).getInstantFPS());
////            }
////        }, 0, 1000);
//    }
//    public void start(){
//    launch();
//    }
//
//}
