package Logic;

import mahyarise.common.GameObjectID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Saeed on 5/16/2014.
 */

public class Game implements Serializable{
    private Map map = new Map();

    private ArrayList<TimerTask> tasks = new ArrayList<TimerTask>();

    private Timer timer = new Timer();
    private int time = 0;

    private HashMap<GameObjectID, GameObject> objects = new HashMap<GameObjectID, GameObject>();

    private Team ce = new Team(GameState.TEAM_CE);
    private Team math = new Team(GameState.TEAM_MATH);


    public Map getMap() {
        return map;
    }
    public void setMap(Map map) {
        this.map = map;
    }

    public Team getTeamCE() {
        return ce;
    }
    public Team getTeamMath() {
        return math;
    }
    public Team getTeamByID (int id) {
        if (id == GameState.TEAM_CE)
            return ce;
        else return math;
    }

    public void addTimerTask(TimerTask task) {
        tasks.add(task);
    }

    public HashMap<GameObjectID, GameObject> getObjects() {
        return objects;
    }

    public Timer getTimer() {
        return timer;
    }

    public float getTime() {
        return time;
    }

    public void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                next50ms();
            }
        }
        , 0, 50);
    }

    public void stopTimer() {
        if (timer == null)
            return;
        timer.cancel();
        timer = null;
    }

    public synchronized void next50ms() {
        time += 50;
        for (TimerTask task: tasks) {
            task.run();
        }
    }
}
