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
package maze.room;

import java.awt.Point;
import maze.test.TestHelper;
import org.junit.Test;

/**
 *
 * @author Javier Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
public class RoomTest {

    /**
     * Test of clone method, of class Room.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Room instance = new Room(1);
        instance.setLocation(new Point(1, 2));
        Room result = (Room) instance.clone();
        TestHelper.compareRooms(instance, result);
    }
}
