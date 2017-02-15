package maze;

import maze.room.Room;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IMazeFactory.class)
public class MazeFactory implements IMazeFactory {

    @Override
    public Maze makeMaze() {
        return new Maze();
    }

    @Override
    public Wall makeWall() {
        return new Wall();
    }

    @Override
    public Room makeRoom(int roomNumber) {
        return new Room(roomNumber);
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return new Door(room1, room2);
    }

    @Override
    public String getName() {
        return "Default";
    }
}
