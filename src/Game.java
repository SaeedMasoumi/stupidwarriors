import mahyarise.common.GameObjectID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Saeed on 5/16/2014.
 */

public class Game {
    private static Map map = new Map();

    private static ArrayList<TimerTask> tasks = new ArrayList<TimerTask>();

    private static Timer timer = new Timer();
    private static int time = 0;

    private static HashMap<GameObjectID, GameObject> objects = new HashMap<GameObjectID, GameObject>();

    private static Team ce = new Team(GameState.TEAM_CE);
    private static Team math = new Team(GameState.TEAM_MATH);


    public static Map getMap() {
        return map;
    }
    public static void setMap(Map map) {
        Game.map = map;
    }

    public static Team getTeamCE() {
        return ce;
    }
    public static Team getTeamMath() {
        return math;
    }
    public static Team getTeamByID (int id) {
        if (id == GameState.TEAM_CE)
            return ce;
        else return math;
    }

    public static void addTimerTask(TimerTask task) {
        tasks.add(task);
    }

    public static HashMap<GameObjectID, GameObject> getObjects() {
        return objects;
    }

    public static Timer getTimer() {
        return timer;
    }

    public static float getTime() {
        return time;
    }

    public static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                next50ms();
            }
        }
        , 0, 50);
    }

    public static void stopTimer() {
        if (timer == null)
            return;
        timer.cancel();
        timer = null;
    }

    public static void next50ms() {
        time += 50;
        for (TimerTask task: tasks) {
            task.run();
        }
    }
}
