package maze;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import maze.command.ActuateDoorCommand;
import maze.command.ChangeDirectionCommand;
import maze.command.Command;
import maze.command.MazeMoveCommand;
import maze.command.UndoableCommand;
import maze.room.Door;
import maze.room.Room;

public class Maze implements Cloneable {

    private static Direction lastDirection = Direction.NORTH;
    protected List rooms = new ArrayList();
    protected Dimension dim;
    protected Point offset;
    protected Room curRoom = null;
    protected Stack moves = new Stack();

    protected Component view;

    private static int ROOM_SIZE = 40;
    private static int WALL_THICKNESS = 6;
    private static final int MARGIN = 20;

    private static final Logger LOG
            = Logger.getLogger(Maze.class.getSimpleName());

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void addRoom(Room room) {
        if (room != null) {
            rooms.add(room);
        }
    }

    public Room findRoom(int roomNumber) {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = (Room) rooms.get(i);
            if (roomNumber == room.getRoomNumber()) {
                return room;
            }
        }
        return null;
    }

    public void setCurrentRoom(int roomNumber) {
        Room room = findRoom(roomNumber);
        setCurrentRoom(room);
    }

    public void setCurrentRoom(Room room) {
        if (room != curRoom) {
            if (curRoom != null) {
                curRoom.setInRoom(false);
            }
            if (room != null) {
                room.setInRoom(true);
                curRoom = room;
            }
            if (view != null) {
                view.repaint();
            }
        }
    }

    public Room getCurrentRoom() {
        return curRoom;
    }

    public void move(Direction direction) {
        if (curRoom != null) {
            MapSite side = curRoom.getSide(direction);
            if (side != null) {
                side.enter(this);
            }
        }
    }

    public void useDoor(Direction direction) {
        if (curRoom != null) {
            MapSite side = curRoom.getSide(direction);
            if (side != null) {
                if (side instanceof Door) {
                    Door door = (Door) side;
                    door.setOpen(!door.isOpen());
                    if (view != null) {
                        view.repaint();
                    }
                }
            }
        }
    }

    public void draw(Graphics g) {
        if (dim == null) {
            calculateDimension();
        }
        int dx = MARGIN + -offset.x * getRoomSize();
        int dy = MARGIN + -offset.y * getRoomSize();

        LOG.log(Level.FINE, "Maze.Draw(): offset={0}, {1}",
                new Object[]{offset.x, offset.y});

        // draw rooms first
        for (int i = 0; i < rooms.size(); i++) {
            Room room = (Room) rooms.get(i);
            if (room != null) {
                Point location = room.getLocation();
                if (location != null) {

                    LOG.log(Level.FINE,
                            "Maze.Draw(): Room {0} location: {1}, {2}",
                            new Object[]{room.getRoomNumber(), location.x,
                                location.y});

                    room.draw(g,
                            dx + location.x * getRoomSize(),
                            dy + location.y * getRoomSize(), getRoomSize(),
                            getRoomSize(), getLastDirection());
                }
            }
        }
        // draw walls and doors
        for (int i = 0; i < rooms.size(); i++) {
            Room room = (Room) rooms.get(i);
            if (room != null) {
                Point location = room.getLocation();
                if (location != null) {
                    for (Direction dir : Direction.values()) {
                        MapSite side = room.getSide(dir);
                        if (side != null) {
                            switch (dir) {
                                case NORTH:
                                    side.draw(g,
                                            dx + location.x * getRoomSize()
                                            - getWallThickness() / 2,
                                            dy + location.y * getRoomSize()
                                            - getWallThickness() / 2,
                                            getRoomSize() + getWallThickness(),
                                            getWallThickness());
                                    break;
                                case EAST:
                                    side.draw(g,
                                            dx + location.x * getRoomSize()
                                            + getRoomSize()
                                            - getWallThickness() / 2,
                                            dy + location.y * getRoomSize()
                                            - getWallThickness() / 2,
                                            getWallThickness(),
                                            getRoomSize() + getWallThickness());
                                    break;
                                case SOUTH:
                                    side.draw(g,
                                            dx + location.x * getRoomSize()
                                            - getWallThickness() / 2,
                                            dy + location.y * getRoomSize()
                                            + getRoomSize()
                                            - getWallThickness() / 2,
                                            getRoomSize() + getWallThickness(),
                                            getWallThickness());
                                    break;
                                default:
                                    side.draw(g,
                                            dx + location.x * getRoomSize()
                                            - getWallThickness() / 2,
                                            dy + location.y * getRoomSize()
                                            - getWallThickness() / 2,
                                            getWallThickness(),
                                            getRoomSize() + getWallThickness());
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public Dimension getDimension() {
        if (dim == null) {
            calculateDimension();
        }
        return dim;
    }

    protected void calculateDimension() {
        if (rooms.size() > 0) {
            int minX = 0, maxX = 0, minY = 0, maxY = 0;
            Room room = (Room) rooms.get(0);
            room.setLocation(new Point(0, 0));
            boolean changed = true;
            while (changed
                    && !isAllRoomsSet()) {
                changed = false;
                for (int i = 0; i < rooms.size(); i++) {
                    room = (Room) rooms.get(i);
                    Point location = room.getLocation();
                    if (location != null) {
                        for (Direction dir : Direction.values()) {
                            MapSite side = room.getSide(dir);
                            if (side instanceof Door) {
                                Door door = (Door) side;
                                Room otherSide = door.otherSideFrom(room);
                                if (otherSide != null
                                        && otherSide.getLocation() == null) {
                                    switch (dir) {
                                        case NORTH:
                                            otherSide.setLocation(
                                                    new Point(location.x,
                                                            location.y - 1));
                                            minY = Math.min(minY, location.y - 1);
                                            break;
                                        case EAST:
                                            otherSide.setLocation(
                                                    new Point(location.x + 1,
                                                            location.y));
                                            maxX = Math.max(maxX,
                                                    location.x + 1);
                                            break;
                                        case SOUTH:
                                            otherSide.setLocation(
                                                    new Point(location.x,
                                                            location.y + 1));
                                            maxY = Math.max(maxY,
                                                    location.y + 1);
                                            break;
                                        default:
                                            otherSide.setLocation(
                                                    new Point(location.x - 1,
                                                            location.y));
                                            minX = Math.min(minX,
                                                    location.x - 1);
                                            break;
                                    }
                                    changed = true;
                                }
                            }
                        }
                    }
                }
            }
            offset = new Point(minX, minY);
            dim = new Dimension(maxX - minX + 1, maxY - minY + 1);
        } else {
            offset = new Point(0, 0);
            dim = new Dimension(0, 0);
        }
    }

    protected boolean isAllRoomsSet() {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = (Room) rooms.get(i);
            if (room.getLocation() == null) {
                return false;
            }
        }
        return true;
    }

    protected void setView(Component view) {
        this.view = view;
    }

    protected void doCommand(Command command) {
        if (command != null) {
            moves.push(command);
            command.execute();
        }
    }

    protected void undoCommand() {
        if (!moves.empty()) {
            Object top = moves.peek(); // looking at the top element without popping it
            if (top instanceof UndoableCommand) {
                moves.pop();
                UndoableCommand undoableCommand = (UndoableCommand) top;
                undoableCommand.undo();
            }
        }
    }

    protected void showFrame(String frameTitle) {
        JFrame frame;
        frame = new JFrame(frameTitle);
        frame.setContentPane(new Maze.MazePanel(this));
        frame.pack();
        Dimension frameDim = frame.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width / 2 - frameDim.width / 2,
                screenSize.height / 2 - frameDim.height / 2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void changeDirection(Direction direction) {
        lastDirection = direction;
        if (view != null) {
            view.repaint();
        }
    }

    public final static class MazePanel extends JPanel {

        private final Maze maze;
        private Dimension dim;

        public MazePanel(Maze maze) {
            this.maze = maze;
            if (maze != null) {
                maze.setView(MazePanel.this);
                Dimension d = maze.getDimension();
                if (d != null) {
                    dim = new Dimension(d.width * getRoomSize() + 2 * MARGIN,
                            d.height * getRoomSize() + 2 * MARGIN);
                }
                addKeyListener(new MazeKeyListener(maze));
            }
        }

        @Override
        public void paint(Graphics g) {
            Dimension d = getSize();
            g.setColor(Color.white);
            g.fillRect(0, 0, d.width, d.height);
            if (maze != null) {
                maze.draw(g);
            }
            requestFocus();
        }

        @Override
        public boolean isFocusable() { // 1.4
            return true;
        }

        @Override
        public Dimension getPreferredSize() {
            return dim;
        }

        @Override
        public Dimension getMinimumSize() {
            return dim;
        }
    }

    static class MazeKeyListener extends KeyAdapter {

        private Maze maze;

        MazeKeyListener(Maze maze) {
            this.maze = maze;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Key pressed");
            Command command;
            int code = e.getKeyCode();
            Direction newDirection = null;
            switch (code) {
                case KeyEvent.VK_UP:
                    System.out.println("Up key");
                    newDirection = Direction.NORTH;
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("Down key");
                    newDirection = Direction.SOUTH;
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("Left key");
                    newDirection = Direction.WEST;
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("Right key");
                    newDirection = Direction.EAST;
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("Space");
                    maze.doCommand(new ActuateDoorCommand(maze, getLastDirection()));
                    break;
                default:
                    System.out.println("Key press ignored");
            }
            if (newDirection != null && !lastDirection.equals(newDirection)) {
                //Just change direction
                command = new ChangeDirectionCommand(maze, newDirection);
            } else {
                //Move
                command = new MazeMoveCommand(maze, getLastDirection());
            }
            maze.doCommand(command);
        }
    }

    /**
     * @return the lastDirection
     */
    public static Direction getLastDirection() {
        return lastDirection;
    }

    /**
     * @return the ROOM_SIZE
     */
    public static int getRoomSize() {
        return ROOM_SIZE;
    }

    /**
     * @param aROOM_SIZE the ROOM_SIZE to set
     */
    public static void setRoomSize(int aROOM_SIZE) {
        ROOM_SIZE = aROOM_SIZE;
    }

    /**
     * @return the WALL_THICKNESS
     */
    public static int getWallThickness() {
        return WALL_THICKNESS;
    }

    /**
     * @param aWALL_THICKNESS the WALL_THICKNESS to set
     */
    public static void setWallThickness(int aWALL_THICKNESS) {
        WALL_THICKNESS = aWALL_THICKNESS;
    }
}
