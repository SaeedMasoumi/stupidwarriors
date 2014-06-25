package mahyarise.judge;

import Logic.*;
import common.exceptions.GameObjectNotFoundException;
import common.exceptions.NotEnoughMoneyException;
import common.exceptions.UnauthorizedAccessException;
import mahyarise.common.GameObjectID;
import mahyarise.common.exceptions.MahyariseExceptionBase;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    private static Game game = new Game();

    public static Game getGame() {
        return game;
    }


    public static void setMapSize(int columns, int rows) {
        game.getMap().setColumnsLength(columns);
        game.getMap().setRowsLength(rows);
    }


    public static int getMapWidth() {
        return game.getMap().getColLength();
    }

    public static int getMapHeight() {
        return game.getMap().getRowsLength();
    }

    public static void loadMap(int[][] types) {
        game.getMap().loadMap(types);
    }

    public static void setMapCellType(int col, int row, int type) {
        game.getMap().setCellsType(type, col, row);
    }

    public static int getMapCellType(int col, int row) {
        return game.getMap().getCellsType(col, row);
    }

    // TODO:: any statement need run before game start ...
    public static void setup() {

    }

    public static GameObjectID createAttacker(int teamID, int attackerType, int path, int lane) throws MahyariseExceptionBase {
        GameObjectID id = null;

        switch (attackerType) {
            case GameState.ATTACKER_INFANTRY:
                id = GameObjectID.create(Infantry.class);
                new Infantry(game.getTeamByID(teamID).getMilitaryBases().get(path).getLane()[lane], id, game.getTeamByID(teamID));
                break;

            case GameState.ATTACKER_TANK:
                id = GameObjectID.create(Tank.class);
                new Tank(game.getTeamByID(teamID).getMilitaryBases().get(path).getLane()[lane], id, game.getTeamByID(teamID));
                break;
        }
        return id;
    }

    // Complete
    public static GameObjectID createTower(int teamID, int towerType, int col, int row) throws MahyariseExceptionBase {
        //TODO need to check for path and lane and cell, each team has it's own tower
        try {
            GameObjectID id = null;
            switch (towerType) {
                case GameState.TOWER_TYPE_BLACK:
                    if (game.getTeamByID(teamID).getMoney() < BlackTower.getCost())
                        throw new NotEnoughMoneyException(game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(BlackTower.class);
                    new BlackTower(game.getMap().getCell(col, row), id, game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_GAME:
                    if (game.getTeamByID(teamID).getMoney() < GameTower.getCost())
                        throw new NotEnoughMoneyException(game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(GameTower.class);
                    new GameTower(game.getMap().getCell(col, row), id, game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_TANK:
                    if (game.getTeamByID(teamID).getMoney() < TankTower.getCost())
                        throw new NotEnoughMoneyException(game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(TankTower.class);
                    new TankTower(game.getMap().getCell(col, row), id, game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_GENERAL_MATH:
                    if (game.getTeamByID(teamID).getMoney() < GeneralMathTower.getCost())
                        throw new NotEnoughMoneyException(game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(GeneralMathTower.class);
                    new GeneralMathTower(game.getMap().getCell(col, row), id, game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_ELECTRICITY:
                    if (game.getTeamByID(teamID).getMoney() < ElectricityTower.getCost())
                        throw new NotEnoughMoneyException(game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(ElectricityTower.class);
                    new ElectricityTower(game.getMap().getCell(col, row), id, game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_POISON:
                    if (game.getTeamByID(teamID).getMoney() < PoisonTower.getCost())
                        throw new NotEnoughMoneyException(game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(PoisonTower.class);
                    new PoisonTower(game.getMap().getCell(col, row), id, game.getTeamByID(teamID));
                    break;
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void purchaseTeamPowerup(int teamID, int powerupType) throws MahyariseExceptionBase {
        //TODO each team has it's own powerup ...
        try {
            switch (powerupType) {
                case GameState.PU_CE_HEALTH:
                    game.getTeamByID(teamID).healthBounceUpgrade();
                    break;
                case GameState.PU_CE_PACE:
                    game.getTeamByID(teamID).speedUpgrade();
                    break;
                case GameState.PU_CE_ARMOR:
                    game.getTeamByID(teamID).shieldUpgrade();
                    break;
                case GameState.PU_MATH_ECO:
                    game.getTeamByID(teamID).moneyBounceUpgrade();
                    break;
                case GameState.PU_MATH_DEC_VAL:
                    game.getTeamByID(teamID).reduceUnitsPriceUpgrade();
                    break;
                case GameState.PU_MATH_PROFIT:
                    game.getTeamByID(teamID).enemyPriceUpgrade();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Complete
    public static void purchaseTowerPowerup(int teamID, GameObjectID id, int powerupType) throws MahyariseExceptionBase {
        try {
            if (!game.getObjects().containsKey(id))
                throw new GameObjectNotFoundException(id);

            if (!(game.getObjects().get(id) instanceof Tower))
                throw new UnauthorizedAccessException("is not a tower");

            Tower tower = (Tower) game.getObjects().get(id);

            switch (powerupType) {
                case GameState.PU_TOWER_POWER:
                    tower.powerUpgrade();
                    break;
                case GameState.PU_TOWER_PACE:
                    tower.reloadTimeUpgrade();
                    break;
                case GameState.PU_TOWER_RANGE:
                    tower.rangeUpgrade();
                    break;
                case GameState.PU_TOWER_AUTO_HEALING: // for phase2
                    tower.autoRepair();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getMoney(int teamID) {
        if (teamID == GameState.TEAM_CE)
            return game.getTeamCE().getMoney();
        else
            return game.getTeamMath().getMoney();
    }

    // Complete
    public static int[] getTeamPowerups(int teamID) {
        return game.getTeamByID(teamID).getTeamUpgradePurchaseList();
    }

    // Complete
    public static HashMap<String, Integer> getInfo(GameObjectID id) throws MahyariseExceptionBase {
        try {
            if (!game.getObjects().containsKey(id))
                throw new GameObjectNotFoundException(id);

            GameObject object = game.getObjects().get(id);

            if (object instanceof Attacker)
                return ((Attacker) object).getInfo();


            else if (object instanceof Tower)
                return ((Tower) object).getInfo();

            else {
                System.out.println("HERE");
                return ((Building) object).getInfo();
            }


        } catch (GameObjectNotFoundException e){
            e.printStackTrace();
            return null;
        }

    }

    // Complete
    public static GameObjectID[] getBuildingID(int teamID, int buildingType) {
        ArrayList<GameObjectID> idList = new ArrayList<GameObjectID>();

        idList.add(game.getTeamByID(teamID).getHeadQuarter().getID());

        for (MilitaryBase mb: game.getTeamByID(teamID).getMilitaryBases().values())
        {
            idList.add(mb.getID());
        }

        return idList.toArray(new GameObjectID[idList.size()]);
    }

    // Complete
    public static GameObjectID[] getInRange(GameObjectID id) throws MahyariseExceptionBase {
        ArrayList<GameObjectID> idList = new ArrayList<GameObjectID>();
        try {
            if (!(game.getObjects().get(id) instanceof Unit))
                throw new UnauthorizedAccessException("is not Unit");

            Cell enemyCells[] = ((Unit) game.getObjects().get(id)).findEnemies();
            for (Cell cell: enemyCells) {
                for (GameObject obj: cell.getObjects())
                {
                    idList.add(obj.getID());
                }
            }

            return idList.toArray(new GameObjectID[idList.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Complete
    public static GameObjectID getTarget(GameObjectID id) throws MahyariseExceptionBase {
        try {
            if (!(game.getObjects().get(id) instanceof Unit))
                throw new UnauthorizedAccessException("is not a Unit");

            Unit unit = (Unit) game.getObjects().get(id);

            return unit.findTargets(unit.findEnemies()).getObjects()[0].getID();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void next50milis() {
        game.next50ms();
    }

    public static void startTimer() {
        game.startTimer();
    }

    public static void pauseTimer() {
        game.stopTimer();
    }

    public static float getTime() {
        return game.getTime() / 1000;
    }

    // Complete
    public static void setMoney(int teamID, int amount) {
        if (teamID == GameState.TEAM_CE)
            game.getTeamCE().setMoney(amount);
        else
            game.getTeamMath().setMoney(amount);
    }

    // Complete
    public static void updateInfo(GameObjectID id, String infoKey, Integer infoValue) throws MahyariseExceptionBase {
        try {
            if (!game.getObjects().containsKey(id))
                throw new GameObjectNotFoundException(id);

            GameObject object = game.getObjects().get(id);

            if (object instanceof Attacker) {
                ((Attacker) object).getInfo().put(infoKey, infoValue);
            }

            else if (object instanceof Tower) {
                ((Tower) object).getInfo().put(infoKey, infoValue);
            }

            else ((Building) object).getInfo().put(infoKey, infoValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Complete
    public static void updateInfo(GameObjectID id, HashMap<String, Integer> newInfo) throws MahyariseExceptionBase {
        try {
            if (!game.getObjects().containsKey(id))
                throw new GameObjectNotFoundException(id);

            GameObject object = game.getObjects().get(id);

            if (object instanceof Attacker) {
                for (String key: newInfo.keySet()) {
                    if (((Attacker) object).getInfo().containsKey(key))
                        ((Attacker) object).getInfo().put(key, newInfo.get(key));
                }
            }
            else if (object instanceof Tower) {
                for (String key: newInfo.keySet()) {
                    if (((Tower) object).getInfo().containsKey(key))
                        ((Tower) object).getInfo().put(key, newInfo.get(key));
                }
            }
            else {
                for (String key: newInfo.keySet()) {
                    if (((Building) object).getInfo().containsKey(key))
                        ((Building) object).getInfo().put(key, newInfo.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the game in "saves" directory.
     * @author Saeed Rajabzadeh
     */
    public static void saveGame() throws IOException {
        //TODO the name of file must be get from user
        FileOutputStream fileOutputStream = new FileOutputStream("src/saves/save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(game);

        fileOutputStream.close();
        objectOutputStream.close();
    }

    /**
     * Loads the game from "saves" directory.
     * @author Saeed Rajabzadeh
     */
    public static void loadGame() throws IOException, ClassNotFoundException {
        //TODO the name of the file which must be loaded, should be get from user
        FileInputStream fileInputStream = new FileInputStream("src/saves/save.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        GameManager.game = (Game) objectInputStream.readObject();

        fileInputStream.close();
        objectInputStream.close();
    }

}