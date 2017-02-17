package maze.room;

import java.awt.*;
import java.util.Objects;
import maze.Direction;
import maze.MapSite;
import maze.Maze;
import maze.Orientation;
import maze.output.OutputConsumer;
import org.openide.util.Lookup;

public class Door implements MapSite {

    protected Room room1;
    protected Room room2;
    protected boolean open;
    protected Orientation orientation;

    public Door(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    public Door() {
        //Empty constructor;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setRooms(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Room otherSideFrom(Room room) {
        if (room != null) {
            if (room == room1) {
                return room2;
            } else if (room == room2) {
                return room1;
            }
        }
        return null;
    }

    @Override
    public void enter(Maze maze) {
        if (open) {
            Room otherRoom = otherSideFrom(maze.getCurrentRoom());
            if (otherRoom != null) {
                otherRoom.enter(maze);
            }
        } else {
            Lookup.getDefault().lookupAll(OutputConsumer.class).forEach((consumer) -> {
                consumer.output("Door is closed");
            });
        }
    }

    @Override
    public void draw(Graphics g, int x, int y, int w, int h) {
        g.setColor(Wall.WALL_COLOR);
        g.fillRect(x, y, w, h);
        if (orientation == Orientation.VERTICAL) {
            y += 2 * w;
            h -= 4 * w;
        } else {
            x += 2 * h;
            w -= 4 * h;
        }
        if (open) {
            g.setColor(Room.ROOM_COLOR);
            g.fillRect(x, y, w, h);
        } else {
            g.setColor(Color.red);
            g.fillRect(x, y, w, h);
            g.setColor(Color.black);
            g.drawRect(x, y, w, h);
        }
    }

    @Override
    public void draw(Graphics g, int x, int y, int w, int h, Direction d) {
        draw(g, x, y, w, h);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.room1);
        hash = 67 * hash + Objects.hashCode(this.room2);
        hash = 67 * hash + (this.open ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.orientation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Door other = (Door) obj;
        if (this.open != other.open) {
            return false;
        }
        if (!Objects.equals(this.room1, other.room1)) {
            return false;
        }
        if (!Objects.equals(this.room2, other.room2)) {
            return false;
        }
        if (!Objects.equals(this.orientation, other.orientation)) {
            return false;
        }
        return true;
    }

}
