package maze;

import maze.room.Room;
import maze.output.OutputConsumer;
import java.awt.*;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
