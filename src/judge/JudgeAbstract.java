package judge;
import java.util.HashMap;
import mahyarise.common.GameObjectID;
import mahyarise.common.exceptions.MahyariseExceptionBase;

public abstract class JudgeAbstract {
    // Tower types
    public static final int TOWER_TYPE_GAME = 0;
    public static final int TOWER_TYPE_BLACK = 1;
    public static final int TOWER_TYPE_TANK = 2;
    public static final int TOWER_TYPE_GENERAL_MATH = 3;
    public static final int TOWER_TYPE_ELECTRICITY = 4;
    public static final int TOWER_TYPE_POISON = 5;

    // Building types
    public static final int BUILDING_TYPE_HQ = 6;
    public static final int BUILDING_TYPE_MILITARY_BASE = 7;

    // Attacker types
    public static final int ATTACKER_INFANTRY = 8;
    public static final int ATTACKER_TANK = 9;

    // Map constants
    public static final int CELL_TYPE_HQ = 10;
    public static final int CELL_TYPE_MILITARY_BASE = 11;
    public static final int CELL_TYPE_LANE = 12;
    public static final int CELL_TYPE_UNUSED = 13;

    // Team IDs
    public static final int TEAM_CE = 0;
    public static final int TEAM_MATH = 1;

    // Powerup types
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

	//info fields
	public static final String HEALTH="health";
	public static final String ROW="row";
	public static final String COLOUMN = "col";
	public static final String TANK_ATTACK = "TA";
	public static final String INFANTRY_ATTACK = "IA";
	public static final String ATTACK = "attack";
	public static final String IS_ALIVE = "alive";
	public static final String TEAM_ID = "id";
	public static final String VALUE = "value";
	public static final String RANGE = "range";
	public static final String ORIENTATION = "or";
	public static final String RELOAD_TIME = "time";
	
	
    // Map functions
    public abstract void setMapSize(int columns, int rows);
    public abstract int getMapWidth();
    public abstract int getMapHeight();
    public abstract void loadMap(int[][] types);
    public abstract void setMapCellType(int col, int row, int type);
    public abstract int getMapCellType(int col, int row);
    public abstract void setup();

    // Creators
    public abstract GameObjectID createAttacker(int teamID, int attackerType, int path, int lane) throws MahyariseExceptionBase;
    public abstract GameObjectID createTower(int teamID, int towerType, int col, int row) throws MahyariseExceptionBase;

    // Powerups
    public abstract void purchaseTeamPowerup(int teamID, int powerupType) throws MahyariseExceptionBase;
    public abstract void purchaseTowerPowerup(int teamID, GameObjectID id, int powerupType) throws MahyariseExceptionBase;

    // Info
    public abstract int getMoney(int teamID);
    public abstract int[] getTeamPowerups(int teamID);
    public abstract HashMap<String, Integer> getInfo(GameObjectID id) throws MahyariseExceptionBase;
    public abstract GameObjectID[] getBuildingID(int teamID, int buildingType);
    public abstract GameObjectID[] getInRange(GameObjectID id) throws MahyariseExceptionBase;
    public abstract GameObjectID getTarget(GameObjectID id) throws MahyariseExceptionBase;

    // Controller
    public abstract void next50milis();
    public abstract void startTimer();
    public abstract void pauseTimer();
    public abstract float getTime();

    // Judge cheat functions
    public abstract void setMoney(int teamID, int amount);
    public abstract void updateInfo(GameObjectID id, String infoKey, Integer infoValue) throws MahyariseExceptionBase;
    public abstract void updateInfo(GameObjectID id, HashMap<String, Integer> newInfo) throws MahyariseExceptionBase;
}
