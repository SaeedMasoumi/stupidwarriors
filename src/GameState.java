/**
 * Created by Saeed on 5/21/2014.
 */
public class GameState {
    public static final int TOWER_TYPE_GAME = 0;
    public static final int TOWER_TYPE_BLACK = 1;
    public static final int TOWER_TYPE_TANK = 2;
    public static final int TOWER_TYPE_GENERAL_MATH = 3;
    public static final int TOWER_TYPE_ELECTRICITY = 4;
    public static final int TOWER_TYPE_POISON = 5;

    public static final int BUILDING_TYPE_HQ = 6;
    public static final int BUILDING_TYPE_MILITARY_BASE = 7;

    public static final int ATTACKER_INFANTRY = 8;
    public static final int ATTACKER_TANK = 9;

    public static final int CELL_TYPE_HQ = 10;
    public static final int CELL_TYPE_MILITARY_BASE = 11;
    public static final int CELL_TYPE_LANE = 12;
    public static final int CELL_TYPE_UNUSED = 13;

    public static final int TEAM_CE = 0;
    public static final int TEAM_MATH = 1;

    public static final int PU_TOWER_PACE = 0;
    public static final int PU_TOWER_POWER = 1;
    public static final int PU_TOWER_RANGE = 2;
    public static final int PU_TOWER_AUTO_HEALING = 3;
    public static final int PU_ATTACKER_POWER = 4;
    public static final int PU_ATTACKER_HEALTH = 5;
    public static final int PU_CE_HEALTH = 6;
    public static final int PU_CE_ARMOR = 7;
    public static final int PU_CE_PACE = 8;
    public static final int PU_MATH_ECO = 9;
    public static final int PU_MATH_PROFIT = 10;
    public static final int PU_MATH_DEC_VAL = 11;

    public static final String HEALTH="health";
    public static final String ROW="row";
    public static final String COLOUMN = "col";
    public static final String TANK_ATTACK = "TA";
    public static final String INFANTRY_ATTACK = "IA";
    public static final String IS_ALIVE = "alive";
    public static final String ATTACK = "attack";
    public static final String TEAM_ID = "id";
    public static final String VALUE = "value";
    public static final String PRICE = "price";
    public static final String RANGE = "range";
    public static final String ORIENTATION = "or";
    public static final String RELOAD_TIME = "time";

    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;

    public static final int oneSec = 1000;

}
