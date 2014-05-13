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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Saeed Masoumi
 */
public class gameSelectController implements Initializable{
    private Stage stage;
    @FXML ImageView background;
    @FXML StackPane stackPane;
    public gameSelectController(Stage stage) {
    this.stage=stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*************************************
         * resize Controller
         *************************************/
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
       /****************************************
        * 
        ***************************************/
       
       
       
    }
    
}
