package maze.room;

import java.awt.*;
import java.awt.geom.AffineTransform;
import maze.Direction;
import maze.Door;
import maze.MapSite;
import maze.Maze;
import maze.Orientation;

public class Room implements MapSite {

    public static Color ROOM_COLOR = new Color(152, 251, 152);
    public static Color PLAYER_COLOR = Color.red;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Room room = (Room) super.clone();
        room.sides = new MapSite[4];
        for (int i = 0; i < 4; i++) {
            if (sides[i] != null) {
                room.sides[i] = (MapSite) sides[i].clone();
            }
        }
        return room;
    }

    public MapSite getSide(Direction dir) {
        if (dir != null) {
            return sides[dir.getOrdinal()];
        }
        return null;
    }

    public void setSide(Direction dir, MapSite site) {
        if (dir != null) {
            sides[dir.getOrdinal()] = site;
            if (site instanceof Door) {
                Door door = (Door) site;
                if (dir == Direction.NORTH
                        || dir == Direction.SOUTH) {
                    door.setOrientation(Orientation.HORIZONTAL);
                } else {
                    door.setOrientation(Orientation.VERTICAL);
                }
            }
        }
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public void enter(Maze maze) {
        maze.setCurrentRoom(this);
    }

    public boolean isInRoom() {
        return inroom;
    }

    public void setInRoom(boolean inroom) {
        this.inroom = inroom;
    }

    @Override
    public void draw(Graphics g, int x, int y, int w, int h) {
        draw(g, x, y, w, h, Direction.NORTH);
    }

    protected int roomNumber = 0;
    protected boolean inroom = false;
    protected MapSite[] sides = new MapSite[4];
    protected Point location = null;

    @Override
    public void draw(Graphics g, int x, int y, int w, int h, Direction d) {
        g.setColor(ROOM_COLOR);
        g.fillRect(x, y, w, h);
        if (inroom) {
            g.setColor(PLAYER_COLOR);
            //TODO: arrow in proportion to room dimensions.
            int width = 10;
            int height = 10;
            Point p1 = new Point(x + w / 2 - 5, y + h / 2);
            Point p2 = new Point(p1.x + width, p1.y);
            Point p3 = new Point(p1.x + (width / 2), p1.y - height);
            Point[] points = new Point[]{p1, p2, p3};
            int angle = 0;
            if (d.equals(Direction.EAST)) {
                angle = 90;
            } else if (d.equals(Direction.WEST)) {
                angle = 270;
            } else if (d.equals(Direction.SOUTH)) {
                angle = 180;
            }
            //Now rotater based on direction
            Point center = new Point(x + w / 2, y + h / 2);
            AffineTransform.getRotateInstance(Math.toRadians(angle),
                    center.x, center.y)
                    .transform(points, 0, points, 0, points.length);
            Polygon polygon = new Polygon();
            for (Point p : points) {
                polygon.addPoint(p.x, p.y);
            }
            g.fillPolygon(polygon);
        }
    }
}
