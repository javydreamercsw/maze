package maze.snow;

import maze.room.Room;
import java.awt.*;
import maze.*;

class SnowWhiteRoom extends Room {

    public SnowWhiteRoom(int roomNumber) {
        super(roomNumber);
        ROOM_COLOR = new Color(255, 218, 185);
        PLAYER_COLOR = Color.white;
    }

    @Override
    public void enter(Maze maze) {
        maze.setCurrentRoom(this);
    }

    @Override
    public void draw(Graphics g, int x, int y, int w, int h) {
        g.setColor(ROOM_COLOR);
        g.fillRect(x, y, w, h);
        if (inroom) {
            g.setColor(PLAYER_COLOR);
            g.fillOval(x + w / 2 - 5, y + h / 2 - 5, 10, 10);
        }
    }
}
