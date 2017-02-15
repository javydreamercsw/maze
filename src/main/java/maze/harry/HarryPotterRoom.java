package maze.harry;

import maze.room.Room;
import java.awt.*;
import maze.*;

class HarryPotterRoom extends Room {

    public HarryPotterRoom(int roomNumber) {
        super(roomNumber);
        ROOM_COLOR = new Color(85, 107, 47);
        PLAYER_COLOR = Color.black;
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
