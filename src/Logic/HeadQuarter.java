package Logic;

import mahyarise.common.GameObjectID;
import mahyarise.judge.GameManager;

import java.util.TimerTask;

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

/**
 *
 * @author Saeed
 */
public class HeadQuarter extends Building {

    public HeadQuarter(GameObjectID id, Team team) {
        super(id, team);
        isDestroyed = false;
        health = 10000;
        isAlive = 1;
        Game.getObjects().put(id, this);
    }

    public void setHQInCells() {
        for (int col = 0; col < 5; col++)
            for (int row = 0; row < 5; row++) {
                Game.getMap().getCell(col + leftUpCornerCell.getCol(), row + leftUpCornerCell.getRow()).addObject(this);
            }

        nextTurn();
    }

    private void nextTurn() {
        Game.addTimerTask(new TimerTask() {
            int counter = 0;

            @Override
            public void run() {
                counter += 50;
                if (HeadQuarter.this.isDie()) {
                    if (isDestroyed)
                        return;

                    isAlive = 0;
                    for (int col = 0; col < 5; col++)
                        for (int row = 0; row < 5; row++) {
                           Game.getMap().getCell(col + leftUpCornerCell.getCol(), row + leftUpCornerCell.getRow()).removeObject(HeadQuarter.this);
                        }
                }

//                if (counter >= 10000) {
//                    System.out.println(getInfo());
//                    counter = 0;
//                }
            }
        });
    }

    @Override
    public String toString() {
        return "I am HQ of Team " + team.getID() + " with health " + health;
    }
}
