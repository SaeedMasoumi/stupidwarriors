import mahyarise.common.GameObjectID;


public class GameObject {
	double health;
	int col, row;
	GameObjectID id; // each GameObject has it's own id
        // in method lazeme pas GameObject dige abstract nist
        public GameObjectID getID() {
            return id;
        }
        public void setHealth(double damage){
         this.health+=damage;
        }
}