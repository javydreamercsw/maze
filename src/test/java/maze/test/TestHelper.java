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
package maze.test;

import maze.Direction;
import maze.room.Room;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Javier Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
public class TestHelper {

    public static void compareRooms(Room r1, Room r2) {
        assertEquals(r1.getRoomNumber(), r2.getRoomNumber());
        assertEquals(r1.getLocation(), r2.getLocation());
        for (Direction dir : Direction.values()) {
            if (r1.getSide(dir) != null) {
                assertTrue(r1.getSide(dir).equals(r2.getSide(dir)));
            } else {
                assertNull(r1.getSide(dir));
            }
        }
    }
}
