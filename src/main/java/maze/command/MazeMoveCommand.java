
package maze.command;

import maze.Direction;
import maze.Maze;

public class MazeMoveCommand implements UndoableCommand { 

  public MazeMoveCommand(Maze maze, Direction direction) { 
    this.maze = maze;
    this.direction = direction; 
  }

  @Override
  public void execute() {
    maze.move(direction); 
  }

  @Override
  public void undo() {
    maze.move(direction.opposite()); 
  }

  protected Maze maze; 
  protected Direction direction; 

}
