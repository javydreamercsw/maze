package maze.snow;

import maze.room.Room;
import maze.*;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IMazeFactory.class)
public class SnowWhiteMazeFactory extends MazeFactory {

    @Override
    public Wall makeWall() {
        return new SnowWhiteWall();
    }

    @Override
    public Room makeRoom(int roomNumber) {
        return new SnowWhiteRoom(roomNumber);
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return new SnowWhiteDoor(room1, room2);
    }

    @Override
    public String getName() {
        return "Snow";
    }
}
