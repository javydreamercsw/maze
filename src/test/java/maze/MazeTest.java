/*
 * Copyright 2017 Javier Ortiz Bultron <javier.ortiz.78@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package maze;

import java.util.ArrayList;
import java.util.List;
import maze.room.Room;
import maze.test.TestHelper;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Javier Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
public class MazeTest {

    /**
     * Test of clone method, of class Maze.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Maze instance = new Maze();
        IMazeFactory factory = new MazeFactory();
        List<Room> rooms = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            rooms.add(factory.makeRoom(i));
            instance.addRoom(rooms.get(i - 1));
        }
        Maze result = (Maze) instance.clone();
        assertEquals(instance.rooms.size(), result.rooms.size());
        for (int i = 0; i < instance.rooms.size(); i++) {
            TestHelper.compareRooms(rooms.get(i), result.findRoom(i + 1));
        }
    }
}
