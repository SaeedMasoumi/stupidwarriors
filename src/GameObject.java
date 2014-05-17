import mahyarise.common.GameObjectID;

public class GameObject {
	protected double health;
    protected static double MAX_HELATH; // baraye upgrade ha lazeme
	protected GameObjectID id; // each GameObject has it's own id
	protected Team team;
	protected Cell currentCell;
	protected int laneNumber; // shomareye masiri ke toosh hast .. ghalat emlaei
								// ham nadare :D

	protected double price; // arzeshe har unit arzesh be base ha ham taalogh
							// migire pas nabayad to unit bashe
	protected int xSize;// FOR PHASE 1 andaze toole objecta
	protected int ySize; // andaze arze objecta

	public GameObject(GameObjectID id, Team team) {
		this.id = id;
		this.team = team;
	}

	public GameObjectID getID() {
		return id;
	}

	public int getLaneNumber() {
		return laneNumber;
	}

        public double getPrice() {
            return price;
        }
        
	public void setHealth(double damage) {
		this.health += damage; // shayad bekhaim jonro ziad konim
	}

	public boolean isUnit() {
		return this.getClass().toString().matches(".*\\bTank.*\\b")
				|| this.getClass().toString().matches(".*\\bAttacker.*\\b")
				|| this.getClass().toString().matches(".*\\bSoldier.*\\b")
				|| this.getClass().toString().matches(".*\\bTower.*\\b");

	}

	public boolean isAttacker() {
		return this.getClass().toString().matches(".*\\bTank.*\\b")
				|| this.getClass().toString().matches(".*\\bAttacker.*\\b")
				|| this.getClass().toString().matches(".*\\bSoldier.*\\b");
	}

	public boolean isTank() {
		return this.getClass().toString().matches(".*\\bTank.*\\b");
	}

	public boolean isSoldier() {
		return this.getClass().toString().matches(".*\\bSoldier.*\\b");
	}

	public boolean isTower() {
		return this.getClass().toString().matches(".*\\bTower.*\\b");

	}

	public boolean isBuilding() {
		return this.getClass().toString().matches(".*\\bBuilding.*\\b")
				|| this.getClass().toString().matches(".*\\bmilitaryBase.*\\b")
				|| this.getClass().toString()
						.matches(".*\\bheadQuarterBase.*\\b");

	}

	// TODO: for rajab teamName ro doros kon
	public int getTeamID() {
		return this.team.getID();
	}
        
        public boolean isDie() {
            if (health <= 0)
                return true;
            
            else return false;
        }
}
