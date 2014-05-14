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

package engine.scene.controller;

/**
 *
 * @author Bl
 */
import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class renderController implements Initializable{

    @FXML
    private StackPane stackPane;
    
    public static Stage stage;
   @Override
    public void initialize(URL location, ResourceBundle resources) {
    
  
   
        //System.out.println("sss");
        //With JavaFX2
            
//       stage.widthProperty().addListener(new ChangeListener() { 
//            @Override
//            public void changed(ObservableValue ov, Object t, Object t1) {              
//               bgImageView.setFitWidth(stage.getWidth());
//            }
//        });
//        stage.heightProperty().addListener(new ChangeListener() { 
//            @Override
//            public void changed(ObservableValue ov, Object t, Object t1) {              
//                bgImageView.setFitHeight(stage.getHeight());
//            }
//        });  
    
    }

 
}