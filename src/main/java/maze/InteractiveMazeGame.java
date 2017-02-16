package maze;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import maze.output.OutputConsumer;
import maze.room.Room;
import org.apache.commons.io.FilenameUtils;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Javier A. Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
@ServiceProvider(service = OutputConsumer.class)
public class InteractiveMazeGame extends javax.swing.JFrame
        implements OutputConsumer {

    private static final Logger LOG
            = Logger.getLogger(InteractiveMazeGame.class.getSimpleName());
    private Room[][] rooms;
    private IMazeFactory factory = new MazeFactory();
    private Maze maze;

    /**
     * Creates new form InteractiveMazeGame
     */
    public InteractiveMazeGame() {
        initComponents();
        layouts.removeAllItems();
        Lookup.getDefault().lookupAll(IMazeFactory.class).forEach((f) -> {
            layouts.addItem(f.getName());
        });
        mainSplitPane.setDividerLocation(0.75);
        mainSplitPane.setResizeWeight(0.5);
        roomSize.setValue(Maze.getRoomSize());
        wallThickness.setValue(Maze.getWallThickness());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPane = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        layouts = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        rows = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        columns = new javax.swing.JSpinner();
        generate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        roomSize = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        wallThickness = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainSplitPane.setDividerLocation(400);
        mainSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        layouts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Maze Layout");

        rows.setModel(new javax.swing.SpinnerNumberModel(3, 1, null, 1));

        jLabel2.setText("Rows");

        jLabel3.setText("Columns");

        columns.setModel(new javax.swing.SpinnerNumberModel(3, 1, null, 1));

        generate.setText("Generate");
        generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateActionPerformed(evt);
            }
        });

        jLabel4.setText("Room Size");

        roomSize.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        roomSize.setEnabled(false);

        jLabel5.setText("Wall Thickness");

        wallThickness.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        wallThickness.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(layouts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rows)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(generate))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(wallThickness, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(roomSize)
                            .addComponent(columns))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(layouts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(columns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wallThickness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(12, 12, 12)
                .addComponent(generate)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);
        jSplitPane1.setRightComponent(jScrollPane1);

        mainSplitPane.setLeftComponent(jSplitPane1);

        output.setEditable(false);
        output.setColumns(20);
        output.setRows(5);
        jScrollPane3.setViewportView(output);

        mainSplitPane.setRightComponent(jScrollPane3);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
        int row = (int) rows.getValue();
        int col = (int) columns.getValue();
        for (IMazeFactory f : Lookup.getDefault().lookupAll(IMazeFactory.class)) {
            if (f.getName().equals(layouts.getSelectedItem().toString())) {
                factory = f;
                break;
            }
        }
        maze = factory.makeMaze();
        Maze.setRoomSize((int) roomSize.getValue());
        Maze.setWallThickness((int) wallThickness.getValue());
        rooms = new Room[row][col];
        int count = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                rooms[r][c] = factory.makeRoom(++count);
                //Add walls on borders of the room
                if (r == 0) {
                    rooms[r][c].setSide(Direction.NORTH,
                            factory.makeWall());
                }
                rooms[r][c].setSide(Direction.WEST,
                        factory.makeWall());
                rooms[r][c].setSide(Direction.SOUTH,
                        factory.makeWall());
                if (c == rooms[0].length - 1) {
                    rooms[r][c].setSide(Direction.EAST,
                            factory.makeWall());
                }
                rooms[r][c].setLocation(new Point(c, r));
                //Link to nearby rooms
                LOG.log(Level.INFO, "Room: {0}, {1}", new Object[]{r, c});
                if (c > 0) {
                    //Has a room to the left. Add a wall, door or set open
                    Direction d = Direction.WEST;
                    setRandomSide(d, r, c);
                }
                if (r > 0) {
                    //Has a room to on top
                    Direction d = Direction.NORTH;
                    setRandomSide(d, r, c);
                }
                maze.addRoom(rooms[r][c]);
            }
        }
        LOG.log(Level.INFO, "Added {0} rooms", count);
        maze.setCurrentRoom(1);
        jScrollPane1.setViewportView(new Maze.MazePanel(maze));
    }//GEN-LAST:event_generateActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        if (maze != null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    File fileToSave = fileChooser.getSelectedFile();
                    if (!FilenameUtils.getExtension(fileToSave.getName())
                            .equalsIgnoreCase("png")) {
                        fileToSave = new File(fileToSave.toString() + ".png");  // append .png if "foo.jpg.png" is OK
                        fileToSave = new File(fileToSave.getParentFile(),
                                FilenameUtils.getBaseName(fileToSave.getName())
                                + ".png"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
                    }
                    BufferedImage bImg = new BufferedImage(
                            jScrollPane1.getWidth(),
                            jScrollPane1.getHeight(),
                            BufferedImage.TYPE_INT_RGB);
                    Graphics2D cg = bImg.createGraphics();
                    maze.draw(cg);
                    if (ImageIO.write(bImg, "png", fileToSave)) {
                        for (OutputConsumer c : Lookup.getDefault().lookupAll(OutputConsumer.class)) {
                            c.output("Saved maze to: " + fileToSave);
                        }
                    }
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    //Gets room at the other side of the MapSite in specified direction.
    private Room getOppositeRoom(Direction d, int r, int c) {
        Room r2 = null;
        if (d.equals(Direction.NORTH) && r > 0) {
            r2 = rooms[r - 1][c];
        } else if (d.equals(Direction.SOUTH) && r < rooms[0].length) {
            r2 = rooms[r + 1][c];
        } else if (d.equals(Direction.EAST) && c < rooms.length) {
            r2 = rooms[r][c + 1];
        } else if (c > 0) {
            r2 = rooms[r][c - 1];
        }
        return r2;
    }

    private void fixSide(Direction d, int r, int c) {
        LOG.log(Level.INFO, "Fixing Room: {0}, {1}; Direction {2}",
                new Object[]{r, c, d.toString()});
        MapSite side = rooms[r][c].getSide(d);
        Room oppositeRoom = getOppositeRoom(d, r, c);
        if (side instanceof Door) {
            if (oppositeRoom != null) {
                Door door = (Door) side;
                door.setRooms(rooms[r][c], oppositeRoom);
                oppositeRoom.setSide(d.opposite(), door);
            }
        } else if (side instanceof Wall) {
            if (oppositeRoom != null) {
                Wall wall = (Wall) side;
                oppositeRoom.setSide(d.opposite(), wall);
            }
        } else {
            oppositeRoom.setSide(d.opposite(), null);
        }
    }

    private void setRandomSide(Direction d, int row, int col) {
        if (new Random().nextBoolean()) {
            LOG.log(Level.INFO, "Adding door at: {0}", d.toString());
            Door door = factory.makeDoor(rooms[row][col], null);
            door.setOpen(new Random().nextBoolean());
            rooms[row][col].setSide(d, door);
        } else {
            LOG.log(Level.FINE, "Adding wall at: {0}", d.toString());
            rooms[row][col].setSide(d, factory.makeWall());
        }
        fixSide(d, row, col);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            LOG.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //This will add it to the lookup.
            Lookup.getDefault().lookupAll(OutputConsumer.class);
            Lookup.getDefault().lookup(InteractiveMazeGame.class).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JSpinner columns;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton generate;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JComboBox<String> layouts;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextArea output;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JSpinner roomSize;
    private javax.swing.JSpinner rows;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JSpinner wallThickness;
    // End of variables declaration//GEN-END:variables

    @Override
    public void output(String o) {
        output.append(o + "\n");
    }
}
