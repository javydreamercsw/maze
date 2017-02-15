package maze;

/*
 *  An enumeration type
 */
public class Direction implements Comparable {

    public static final Direction NORTH = new Direction("North");
    public static final Direction EAST = new Direction("East");
    public static final Direction SOUTH = new Direction("South");
    public static final Direction WEST = new Direction("West");

    private static int nextOrdinal = 0;
    private final String name;
    private final int ordinal = nextOrdinal++;

    private static final Direction[] VALUES = {NORTH, EAST, SOUTH, WEST};

    @Override
    public String toString() {
        return name;
    }

    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Direction) {
            return ordinal - ((Direction) o).getOrdinal();
        }
        return 0;
    }

    public static Direction first() {
        return VALUES[0];
    }

    public Direction next() {
        if (ordinal < VALUES.length - 1) {
            return VALUES[ordinal + 1];
        } else {
            return null;
        }
    }

    public Direction opposite() {
        return VALUES[(ordinal + 2) % 4];
    }

    /*
  public static Direction first() {
    return NORTH;
  }

  public Direction next() {
    if (this == NORTH) {
      return EAST;
    } else if (this == EAST) {
      return SOUTH;
    } else if (this == SOUTH) {
      return WEST;
    } else if (this == WEST) {
      return null;
    }
    return null;
  }

  public Direction opposite() {
    if (this == NORTH) {
      return SOUTH;
    } else if (this == EAST) {
      return WEST;
    } else if (this == SOUTH) {
      return NORTH;
    } else if (this == WEST) {
      return EAST;
    }
    return null;
  }
     */
    private Direction(String name) {
        this.name = name;
    }
}
