package mahyarise.common;

public class GameObjectID implements java.io.Serializable {

    private static int NEXT = 0;
    private static final int L1 = 5; // "[@id/".length

    /**
     * Creates a new ID. Acts like a Factory object creation pattern.
     *
     * @param type
     * @return GameObjectID
     */

    public static GameObjectID create(Class type) {
        return new GameObjectID(type, NEXT++);
    }

    /**
     * Sets next id number identifier. It's meant to be used after a save/load
     * cycle to prevent generation of repetitive IDs. By the way it's possible
     * to implement save/load algorithms in a way that this method is not
     * required.
     *
     * @param n
     */
    public void setNext(int n) {
        NEXT = n;
    }

    /**
     * Unsafe method to reconstruct a GameObjectID from its string value.
     * Important note: objects created using this method are not comparable to
     * objects created using constructor or <code>create</code> function using
     * == operator
     *
     * @param id
     * @return
     */
    public static GameObjectID reconstruct(String id) {
        if (!id.matches("\\[@id/[a-zA-Z0-9_]([a-zA-Z0-9_]|\\.)*[a-zA-Z0-9_]+,[0-9]+\\]")) {
            return null;
        }

        int commaLocation = id.indexOf(",");
        String className = id.substring(L1, commaLocation);
        System.out.println(className);
        int n = Integer.parseInt(id.substring(commaLocation + 1, id.length() - 1));
        try {
            return new GameObjectID(Class.forName(className), n);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private Class type;
    private int number;

    private GameObjectID(Class type, int number) {
        this.type = type;
        this.number = number;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        GameObjectID o = (GameObjectID) other;
        return (o.getNumber() == this.number && o.getType() == this.type);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + java.util.Objects.hashCode(this.type);
        hash = 29 * hash + this.number;
        return hash;
    }

    public int getNumber() {
        return number;
    }

    public Class getType() {
        return type;
    }

    @Override
    public String toString() {
        return new StringBuilder("[@id/").append(type.getCanonicalName()).append(',').append(number).append(']').toString();
    }
}
