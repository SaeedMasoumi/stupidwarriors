
import java.util.ArrayList;


abstract public class Unit extends GameObject{
        protected ArrayList<GameObject> targetsEnemy = new ArrayList<GameObject>(); 
        protected Cell startingCell;
        
        public double reloadTime; //we have a reload time so first it's equal 0 
        //and each duration(it's a timer in javafx maybe it's 10ms) it +=10ms;
        public double finalReloadTime; //if our reloadtime is equals finalReload time it means we can now attack 

        protected double range;
        protected double cost;
        protected boolean isAlive; 

        

}
