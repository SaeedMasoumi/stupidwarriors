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
//package engine.scene;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.PathTransition;
//import javafx.animation.PauseTransition;
//import javafx.animation.Timeline;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.StackPane;
//import javafx.scene.shape.Path;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.util.Duration;
///**
// * Render the start Up menus
// * @author Saeed Masoumi
// */
//public class startUpScene extends mainScene{
//
//    private ImageView slideShow = new ImageView();
//    private StackPane loader ;
//
//     /**
//     * Render startup Scene about 7 seconds then call mainMenuScene
//     * @param stage  stage in mainScene
//     * @throws IOException
//     */
//    public void renderStartUpScene(Stage stage) throws IOException{
//
//         loader = FXMLLoader.load(getClass().getResource("layout/startUp.fxml"));
//         //show GPL ,JavaFx ,Opensource
//         Timeline slideShowTimer = new Timeline(
//                new KeyFrame(Duration.seconds(1), new KeyValue(slideShow.imageProperty(), new Image(JavaFx))),
//                new KeyFrame(Duration.seconds(2.5), new KeyValue(slideShow.imageProperty(), new Image(GPLv3))),
//                new KeyFrame(Duration.seconds(4), new KeyValue(slideShow.imageProperty(), new Image(OpenSource))),
//                new KeyFrame(Duration.seconds(7), new KeyValue(slideShow.imageProperty(),null) ));
//                slideShowTimer.play();
//        slideShow.setTranslateX(-200);
//        slideShow.setTranslateY(300);
//
//        //add slideshow to loader
//        loader.getChildren().add(slideShow);
//
//        //add loader to stage
//        stage.setScene(new Scene(loader));
//        stage.show();//show stage
//
//        //create a 7 seconds delay and after that start Main Menu Scene
//         PauseTransition delay = new PauseTransition(Duration.seconds(6));
//         delay.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // pass stage to mainMenuScene
//                 mainMenuScene mainmenu = new mainMenuScene();
//                try {
//                    Stage st = new Stage();
//                    mainmenu.start(st);
//                } catch (Exception ex) {
//                    Logger.getLogger(startUpScene.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                 stage.hide();
//
//            }
//        });
//        delay.play();
//    }
//
//}
//
//
////TODO add handler for background image for resizing when user resize the stage
////@Removed code
////        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
////        @Override
////        public void handle(Event event) {
////           refreshScene();
////           if(seconds==7){
////
////               mainMenuScene mainmenu = new mainMenuScene();
////               try {
////                   mainmenu.renderMainMenuScene(stage);
////               } catch (IOException ex) {
////                   Logger.getLogger(startUpScene.class.getName()).log(Level.SEVERE, null, ex);
////               }
////           }
////        }
////         }),
////        new KeyFrame(Duration.seconds(1.0)));
////        timeline.setCycleCount(7);
////
////        timeline.play();//start timer