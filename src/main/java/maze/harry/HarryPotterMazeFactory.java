package maze.harry;

import maze.room.Wall;
import maze.room.Door;
import maze.room.Room;
import maze.*;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IMazeFactory.class)
public class HarryPotterMazeFactory extends MazeFactory {

    @Override
    public Wall makeWall() {
        return new HarryPotterWall();
    }

    @Override
    public Room makeRoom(int roomNumber) {
        return new HarryPotterRoom(roomNumber);
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return new HarryPotterDoor(room1, room2);
    }

    @Override
    public String getName() {
        return "Harry";
    }
}
