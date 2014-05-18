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
//
//import engine.scene.controller.gameSelectController;
//import engine.scene.controller.menuController;
//import static engine.scene.mainScene.icon;
//import java.io.IOException;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
///**
// *
// * @author Saeed Masoumi
// */
//public class gameSelectScene {
//    private StackPane loader;
//    public void start(Stage stage) throws IOException {
//        //Create stage with fxml file
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/gameSelect.fxml"));
//        fxmlLoader.setController(new gameSelectController(stage));//set Controller for our fxml file
//        loader = fxmlLoader.load();
//        stage.getScene().setRoot(loader) ;
//        stage.setFullScreen(true);
//
//    }
//
//}
