package maze;

import maze.room.Wall;
import maze.room.Door;
import maze.room.Room;

/**
 *
 * @author Javier A. Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
public interface IMazeFactory {

    Door makeDoor(Room room1, Room room2);

    Maze makeMaze();

    Room makeRoom(int roomNumber);

    Wall makeWall();

    String getName();
}
