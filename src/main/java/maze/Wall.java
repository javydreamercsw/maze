package maze;

import java.awt.*;
import java.applet.AudioClip;

public class Wall implements MapSite {

    public static final Color WALL_COLOR = Color.orange;
    protected static AudioClip hurts = util.AudioUtility.getAudioClip("audio/that.hurts.au");

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void enter(Maze maze) {
        hurts.play();
    }

    @Override
    public void draw(Graphics g, int x, int y, int w, int h) {
        g.setColor(WALL_COLOR);
        g.fillRect(x, y, w, h);
    }

    @Override
    public void draw(Graphics g, int x, int y, int w, int h, Direction d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}