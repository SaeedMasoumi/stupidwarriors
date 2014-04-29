
import graphics.ImageDisplayTest;
import graphics.RenderMenu;
import graphics.TimerTest;
import graphics.Xylophone;
import mahyarise.TAgraphics.GraphicsInterface;
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

/**
 *
 * @author Saeed Masoumi
 */
public class StupidWarriors {
    public static void JugeEngine(){
        Judge judge = new Judge();
        judge.setMapSize(200, 200);
        int[][] a = new int [200][200];
        for(int i=0;i<200;i++)
            for(int j = 0 ;j<200;j++)
                a[i][j] = 10;
        judge.loadMap(a);
        GraphicsInterface graphic;
        graphic = new GraphicsInterface();
        graphic.setJudge(judge);
        graphic.start();}
    public static void main(String[] args) {
        //JugeEngine();
        RenderMenu startUp = new RenderMenu();
        startUp.RenderStartUpMenu();
        ImageDisplayTest a = new ImageDisplayTest();
       // a.startAks();
        Xylophone b = new Xylophone();
        //b.start();
        TimerTest c = new TimerTest();
        //c.start();
        System.out.println("hi");
    }
}
