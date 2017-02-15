package maze.command;

import maze.Direction;
import maze.Maze;

/**
 *
 * @author Javier A. Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
public class ActuateDoorCommand implements Command {

    private final Maze maze;
    private final Direction direction;

    public ActuateDoorCommand(Maze maze, Direction direction) {
        this.maze = maze;
        this.direction = direction;
    }

    @Override
    public void execute() {
        maze.useDoor(direction);
    }
}
