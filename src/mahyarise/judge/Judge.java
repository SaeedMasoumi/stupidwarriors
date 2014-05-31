package mahyarise.judge;

import Logic.*;
import common.exceptions.GameObjectNotFoundException;
import common.exceptions.NotEnoughMoneyException;
import common.exceptions.UnauthorizedAccessException;
import mahyarise.common.GameObjectID;
import mahyarise.common.exceptions.MahyariseExceptionBase;

import java.util.ArrayList;
import java.util.HashMap;

public class Judge extends JudgeAbstract {

    @Override
    public void setMapSize(int columns, int rows) {
        Game.getMap().setColumnsLength(columns);
        Game.getMap().setRowsLength(rows);
    }

    @Override
    public int getMapWidth() {
        return Game.getMap().getColLength();
    }

    @Override
    public int getMapHeight() {
        return Game.getMap().getRowsLength();
    }

    @Override
    public void loadMap(int[][] types) {
        Game.getMap().loadMap(types);
    }

    @Override
    public void setMapCellType(int col, int row, int type) {
        Game.getMap().setCellsType(type, col, row);
    }

    @Override
    public int getMapCellType(int col, int row) {
        return Game.getMap().getCellsType(col, row);
    }

    // TODO:: any statement need run before game start ...
    @Override
    public void setup() {

    }

    @Override
    public GameObjectID createAttacker(int teamID, int attackerType, int path, int lane) throws MahyariseExceptionBase {
        GameObjectID id = null;

        switch (attackerType) {
            case ATTACKER_INFANTRY:
                id = GameObjectID.create(Infantry.class);
                new Infantry(Game.getTeamByID(teamID).getMilitaryBases().get(path).getLane()[lane], id, Game.getTeamByID(teamID));
                break;

            case ATTACKER_TANK:
                id = GameObjectID.create(Tank.class);
                new Tank(Game.getTeamByID(teamID).getMilitaryBases().get(path).getLane()[lane], id, Game.getTeamByID(teamID));
                break;
        }
        return id;
    }

    // Complete
    @Override
    public GameObjectID createTower(int teamID, int towerType, int col, int row) throws MahyariseExceptionBase {
        //TODO need to check for path and lane and cell, each team has it's own tower
        try {
            GameObjectID id = null;
            switch (towerType) {
                case GameState.TOWER_TYPE_BLACK:
                    if (Game.getTeamByID(teamID).getMoney() < BlackTower.getCost())
                        throw new NotEnoughMoneyException(Game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(BlackTower.class);
                    new BlackTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_GAME:
                    if (Game.getTeamByID(teamID).getMoney() < GameTower.getCost())
                        throw new NotEnoughMoneyException(Game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(GameTower.class);
                    new GameTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_TANK:
                    if (Game.getTeamByID(teamID).getMoney() < TankTower.getCost())
                        throw new NotEnoughMoneyException(Game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(TankTower.class);
                    new TankTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_GENERAL_MATH:
                    if (Game.getTeamByID(teamID).getMoney() < GeneralMathTower.getCost())
                        throw new NotEnoughMoneyException(Game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(GeneralMathTower.class);
                    new GeneralMathTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_ELECTRICITY:
                    if (Game.getTeamByID(teamID).getMoney() < ElectricityTower.getCost())
                        throw new NotEnoughMoneyException(Game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(ElectricityTower.class);
                    new ElectricityTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                    break;
                case GameState.TOWER_TYPE_POISON:
                    if (Game.getTeamByID(teamID).getMoney() < PoisonTower.getCost())
                        throw new NotEnoughMoneyException(Game.getTeamByID(teamID).getMoney());
                    id = GameObjectID.create(PoisonTower.class);
                    new PoisonTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                    break;
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void purchaseTeamPowerup(int teamID, int powerupType) throws MahyariseExceptionBase {
        //TODO each team has it's own powerup ...
        try {
            switch (powerupType) {
                case GameState.PU_CE_HEALTH:
                    Game.getTeamByID(teamID).healthBounceUpgrade();
                    break;
                case GameState.PU_CE_PACE:
                    Game.getTeamByID(teamID).speedUpgrade();
                    break;
                case GameState.PU_CE_ARMOR:
                    Game.getTeamByID(teamID).shieldUpgrade();
                    break;
                case GameState.PU_MATH_ECO:
                    Game.getTeamByID(teamID).moneyBounceUpgrade();
                    break;
                case GameState.PU_MATH_DEC_VAL:
                    Game.getTeamByID(teamID).reduceUnitsPriceUpgrade();
                    break;
                case GameState.PU_MATH_PROFIT:
                    Game.getTeamByID(teamID).enemyPriceUpgrade();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Complete
    @Override
    public void purchaseTowerPowerup(int teamID, GameObjectID id, int powerupType) throws MahyariseExceptionBase {
        try {
            if (!Game.getObjects().containsKey(id))
                throw new GameObjectNotFoundException(id);

            if (!(Game.getObjects().get(id) instanceof Tower))
                throw new UnauthorizedAccessException("is not a tower");

            Tower tower = (Tower) Game.getObjects().get(id);

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

    @Override
    public int getMoney(int teamID) {
        if (teamID == GameState.TEAM_CE)
            return Game.getTeamCE().getMoney();
        else
            return Game.getTeamMath().getMoney();
    }

    // Complete
    @Override
    public int[] getTeamPowerups(int teamID) {
        return Game.getTeamByID(teamID).getTeamUpgradePurchaseList();
    }

    // Complete
    @Override
    public HashMap<String, Integer> getInfo(GameObjectID id) throws MahyariseExceptionBase {
        try {
            if (!Game.getObjects().containsKey(id))
                throw new GameObjectNotFoundException(id);

            GameObject object = Game.getObjects().get(id);

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
    @Override
    public GameObjectID[] getBuildingID(int teamID, int buildingType) {
        ArrayList<GameObjectID> idList = new ArrayList<GameObjectID>();

        idList.add(Game.getTeamByID(teamID).getHeadQuarter().getID());

        for (MilitaryBase mb: Game.getTeamByID(teamID).getMilitaryBases().values())
        {
            idList.add(mb.getID());
        }

        return idList.toArray(new GameObjectID[idList.size()]);
    }

    // Complete
    @Override
    public GameObjectID[] getInRange(GameObjectID id) throws MahyariseExceptionBase {
        ArrayList<GameObjectID> idList = new ArrayList<GameObjectID>();
        try {
            if (!(Game.getObjects().get(id) instanceof Unit))
                throw new UnauthorizedAccessException("is not Unit");

            Cell enemyCells[] = ((Unit) Game.getObjects().get(id)).findEnemies();
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
    @Override
    public GameObjectID getTarget(GameObjectID id) throws MahyariseExceptionBase {
        try {
            if (!(Game.getObjects().get(id) instanceof Unit))
                throw new UnauthorizedAccessException("is not a Unit");

            Unit unit = (Unit) Game.getObjects().get(id);

            return unit.findTargets(unit.findEnemies()).getObjects()[0].getID();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void next50milis() {
        Game.next50ms();
    }

    @Override
    public void startTimer() {
        Game.startTimer();
    }

    @Override
    public void pauseTimer() {
        Game.stopTimer();
    }

    @Override
    public float getTime() {
        return Game.getTime() / 1000;
    }

    // Complete
    @Override
    public void setMoney(int teamID, int amount) {
        if (teamID == GameState.TEAM_CE)
            Game.getTeamCE().setMoney(amount);
        else
            Game.getTeamMath().setMoney(amount);
    }

    // Complete
    @Override
    public void updateInfo(GameObjectID id, String infoKey, Integer infoValue) throws MahyariseExceptionBase {
        try {
            if (!Game.getObjects().containsKey(id))
                throw new GameObjectNotFoundException(id);

            GameObject object = Game.getObjects().get(id);

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
    @Override
    public void updateInfo(GameObjectID id, HashMap<String, Integer> newInfo) throws MahyariseExceptionBase {
        try {
            if (!Game.getObjects().containsKey(id))
                throw new GameObjectNotFoundException(id);

            GameObject object = Game.getObjects().get(id);

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
}