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

import java.util.Date;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerTest extends Application {
    static int dx = 1;
    static int dy = 1;

    public  void start() {
        Application.launch();
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Animation");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300, Color.WHITE);

        primaryStage.setScene(scene);
        addBouncyBall(scene); 
        primaryStage.show();
    }
    private void addBouncyBall(final Scene scene) {
        final Circle ball = new Circle(100, 100, 20);

        final Group root = (Group) scene.getRoot();
        root.getChildren().add(ball);

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.millis(50),
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {

                        double xMin = ball.getBoundsInParent().getMinX();
                        double yMin = ball.getBoundsInParent().getMinY();
                        double xMax = ball.getBoundsInParent().getMaxX();
                        double yMax = ball.getBoundsInParent().getMaxY();

                        if (xMin < 0 || xMax > scene.getWidth()) {
                            dx = dx * -1;
                        }
                        if (yMin < 0 || yMax > scene.getHeight()) {
                            dy = dy * -1;
                        }

                        ball.setTranslateX(ball.getTranslateX() + dx);
                        ball.setTranslateY(ball.getTranslateY() + dy);

                    }
                });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }
}
/** Simple JavaFX Animation Sample. */
//public class TimerTest extends Application {
//  private       int         x        = 20;
//  private       String      status   = "";
//  private final Circle      dot      = new Circle(20, 20, 3);
//  private final TimeCounter counter  = new TimeCounter();
//
//  public void start()  { launch(); }
//  @Override public void start(final Stage stage) throws Exception {
//    final Timeline timeline = new Timeline(
//      new KeyFrame(Duration.ZERO, new EventHandler() {
//        @Override public void handle(Event event) {
//          refreshScene();
//        }
//      }),  
//      new KeyFrame(Duration.millis(25))
//    );
//    timeline.setCycleCount(Timeline.INDEFINITE);
//
//    stage.setScene(new Scene(new Group(dot), 50, 50));
//    stage.show();
//
//    counter.reset();
//    timeline.play();
//  }
//
//  private void refreshScene() {
//    gotoxy(x, 20);
//
//    status = "*****".equals(status) ? "*" : status + "*";
//    System.out.println(String.format("%7d", counter.elapsed()) + " ms " + x + " " + status);
//
//    if (x == 94) {
//      x = 20;
//    } else {
//      x++;
//    }
//  }      
//
//  private void gotoxy(int x, int y) {
//    dot.setCenterX(x); 
//    dot.setCenterY(y);
//  }
//
//  class TimeCounter {
//    private long start = new Date().getTime();
//    void reset()   { start = new Date().getTime(); }
//    long elapsed() { return new Date().getTime() - start; }
//  }
//}