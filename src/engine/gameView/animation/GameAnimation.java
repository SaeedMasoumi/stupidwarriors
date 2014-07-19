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

package engine.gameView.animation;

import javafx.animation.FadeTransition;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Saeed Masoumi
 */
public class GameAnimation {
    
    public static void fadeText(String label,StackPane stack,double x,double y,Color c){
        String temp = "";
        if(label.length()>50)
            temp = label.substring(0, 50);
        else 
           temp = label; 
        Label lb = new Label(temp);
        lb.setTranslateX(x);
        lb.setTranslateY(y);
        lb.setTextFill(c);
        lb.setStyle("-fx-font-size: 20pt;");
        stack.getChildren().add(lb);
        FadeTransition ft = new FadeTransition(Duration.millis(2000),lb); 
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }
}
