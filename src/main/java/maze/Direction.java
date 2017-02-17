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

import static maze.Direction.values;

/**
 *
 * @author Javier Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    @Override
    public String toString() {
        char c[] = name().toLowerCase().toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        return new String(c);
    }

    public Direction opposite() {
        return values()[(ordinal() + 2) % values().length];
    }
}
