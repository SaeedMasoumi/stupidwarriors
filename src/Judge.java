/**
*@author Saeed Masoumi
* @author Saeed Rajabzade
*/
import java.util.HashMap;
import mahyarise.common.GameObjectID;
import mahyarise.common.exceptions.MahyariseExceptionBase;
import mahyarise.judge.JudgeAbstract;
public class Judge extends JudgeAbstract {
    private Map map ;
    @Override
    public void setMapSize(int col, int row) {                
    		map = new Map(col,row);
    }
    @Override
    public int getMapWidth() {
    		return map.getColLength();
    }

    @Override
    public int getMapHeight() {
		return map.getRowLength();
    }

    @Override
    public void loadMap(int[][] types) {

		//TODO Need Refactoring ...
		for (int row = 0; row < types.length; row++) {
			for (int col = 0; col < types[0].length; col++) {
				map.setCellsType(types[row][col], col, row);
			}
		}    }

    @Override
    public void setMapCellType(int col, int row, int type) {
		map.setCellsType(type, col, row);
    }

    @Override
    public int getMapCellType(int col, int row) {
		return map.getCellsType(col, row);
	}

    @Override
    public void setup() {
    }

    @Override
    public GameObjectID createAttacker(int i, int i1, int i2, int i3) throws MahyariseExceptionBase {
        return null;
        }

    @Override
    public GameObjectID createTower(int i, int i1, int i2, int i3) throws MahyariseExceptionBase {
return null;    }

    @Override
    public void purchaseTeamPowerup(int i, int i1) throws MahyariseExceptionBase {
    }

    @Override
    public void purchaseTowerPowerup(int i, GameObjectID goid, int i1) throws MahyariseExceptionBase {
    }

    @Override
    public int getMoney(int i) {
return 0;
    }

    @Override
    public int[] getTeamPowerups(int i) {
return null;    }

    @Override
    public HashMap<String, String> getInfo(GameObjectID goid) throws MahyariseExceptionBase {
return null;    }

    @Override
    public GameObjectID[] getBuildingID(int i, int i1) {
    return null;
    }

    @Override
    public GameObjectID[] getInRange(GameObjectID goid) throws MahyariseExceptionBase {
        return null;
    }

    @Override
    public GameObjectID getTarget(GameObjectID goid) throws MahyariseExceptionBase {
    return null;
    }

    @Override
    public void next50milis() {
    }

    @Override
    public void startTimer() {
    }

    @Override
    public void pauseTimer() {
    }

    @Override
    public float getTime() {
return 0;

    }

    @Override
    public void setMoney(int i, int i1) {
    }

    @Override
    public void updateInfo(GameObjectID goid, String string, String string1) throws MahyariseExceptionBase {
    }

    @Override
    public void updateInfo(GameObjectID goid, HashMap<String, String> hm) throws MahyariseExceptionBase {
    }


}
