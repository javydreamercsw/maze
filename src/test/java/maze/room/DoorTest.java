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

import static org.junit.Assert.*;

/**
 *
 * @author Javier Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
public class DoorTest {

    /**
     * Test of clone method, of class Door.
     *
     * @throws java.lang.Exception
     */
    @org.junit.Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Room r1 = new Room(1);
        Room r2 = new Room(2);
        Door instance = new Door(r1, r2);
        instance.setOpen(true);
        Door result = (Door) instance.clone();
        assertEquals(r1, result.otherSideFrom(r2));
    }

    /**
     * Test of isOpen method, of class Door.
     */
    @org.junit.Test
    public void testIsOpen() {
        System.out.println("isOpen");
        Room r1 = new Room(1);
        Room r2 = new Room(2);
        Door instance = new Door(r1, r2);
        assertEquals(false, instance.isOpen());
        instance.setOpen(true);
        assertEquals(true, instance.isOpen());
    }

    /**
     * Test of setRooms method, of class Door.
     */
    @org.junit.Test
    public void testSetRooms() {
        System.out.println("setRooms");
        Room r1 = new Room(1);
        Room r2 = new Room(2);
        Room r3 = new Room(3);
        Room r4 = new Room(4);
        Door instance = new Door(r1, r2);
        assertEquals(r1, instance.otherSideFrom(r2));
        instance.setRooms(r3, r4);
        assertEquals(r3, instance.otherSideFrom(r4));
    }
}
